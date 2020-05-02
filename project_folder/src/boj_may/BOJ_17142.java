package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.org.apache.xpath.internal.operations.Minus;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 2. 오후 2:07:14
 * @category DFS
* @level 2
* @problem_description 
* @solving_description
* m개를 활성화 시켜야 한다. 여기서 조합을 이용하면 될듯
* 퍼뜨리는게 BFS이다. 그리고 모든 빈칸에 퍼뜨렸을 때 시간을 구하는 것이므로
* 빈칸의 개수를 파악하는게 중요할 듯. 
* 그리고 초가 중요하니까 초에 대한 정보도 따로 배열에 저장하던지 아니면 따로 객체에 저장하는게 필요
*/


public class BOJ_17142 {
	private static int n;
	private static int m;
	private static int[][] map;
	private static int min_time;
	private static ArrayList<dot> inactive;
	private static dot[] active;
	private static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int blank;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n][n];
		
		blank = 0;
		inactive = new ArrayList<dot>(); //처음 비활성화 바이러스를 담는 리스트
		active = new dot[m]; //활성화로 선택된 바이러스를 담는 리스트
		min_time = Integer.MAX_VALUE; //최소 걸린시간이므로 맥스값으로 잡아준다.
		
		
		
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<n;j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = a;
				if(a==0) { //빈칸의 개수를 세준다.
					blank++;
				}
				else if(a==2) { //비활성화 바이러스의 정보를 입력해준다.
					inactive.add(new dot(i, j));
				}
			}
		}//for i
		
		if(blank==0) {
			System.out.println(0);
			return;
		}
		
		visit = new boolean[n][n];
		//이제 inactive리스트에서 m개를 선택한다.
		dfs(0,0);
		
		min_time = min_time==Integer.MAX_VALUE? -1: min_time;
		System.out.println(min_time);
	}//main
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static void dfs(int r,int index) {
		if(r==m) {
			//spread
			spread();
			return;
		}
		for(int i=index;i<inactive.size();i++) {
			active[r] = inactive.get(i);
			dfs(r+1,i+1);
		}
		
	}//dfs
	
	static void spread() {
		int blank_copy = blank; //빈칸의 개수를 복사변수에 저장
		visit = new boolean[n][n]; //방문 초기화 해주고
		Queue<active_virus> queue = new LinkedList<>();
		for(int i=0;i<m;i++) {
			queue.add(new active_virus(active[i].y, active[i].x, 0));
		}
		while(!queue.isEmpty()) {
			active_virus now = queue.poll();
			int y = now.y; int x = now.x; int t = now.t;
			
			if(visit[y][x]) continue;
			visit[y][x] = true;
			//여기서 blank--를 처리해주는 이유는
			//아래에서 해버리면은 다음 차례에 방문을 하려고 큐에 넣어서 있는 상태인데
			//해당 점은 아직 방문처리가 안되어 있기 때문에 또 방문해서 경우의 수를 줄여버리는 일이 생긴다.
			if(map[y][x]==0) {
				blank_copy--;
				if(blank_copy==0) {
					min_time=Math.min(min_time,t);
				}
			}
			for(int k=0;k<4;k++) {
				int ny = y+dy[k]; int nx = x+dx[k];
				//범위
				if(ny<0||ny>=n||nx<0||nx>=n) continue;
				
				if(!visit[ny][nx]&&map[ny][nx]!=1) { //방문한적 없고 벽이 아니면
					
					//0이나 2이나 퍼지는 건 똑같다.
					queue.add(new active_virus(ny, nx, t+1));
				}
			}
		}
	}
	
	static class active_virus{
		int y, x,t;

		public active_virus(int y, int x, int t) {
			super();
			this.y = y;
			this.x = x;
			this.t = t;
		}
		
		
	}
}
