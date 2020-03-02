package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 28. 오후 4:35:26
 * @category 
* @problem_description 적록색양은 빨간색과 초록색의 차이를 느끼지 못함(같다)
* @solving_description 두번 돌려야 되나? 한번에 돌리는 방법은 없을까?
* BFS를 두번 돌리면 되나?
*/
public class BOJ_10026 {
	private static int n;
	private static char[][] input;
	private static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int setNormal;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(bufferedReader.readLine());
		
		input = new char[n][n];
		
		for(int i=0;i<n;i++) {
			String s = bufferedReader.readLine();
			for(int j=0;j<n;j++) {
				input[i][j] = s.charAt(j);
			}
		}
		
		int normal = 0; 
		setNormal = 1;
		visit = new boolean[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visit[i][j] ) {
					normal++;
					bfs(i,j);
				}
			}
		}
		
		int RGsame=0;
		setNormal=0;
		visit =new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visit[i][j] ) {
					RGsame++;
					bfs(i,j);
				}
			}
		}
		System.out.println(normal+" "+RGsame);
		
		
	}//main
	static void bfs(int py,int px) {
		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(py, px));
		while(!queue.isEmpty()) {
			dot now = queue.poll();
			int y = now.y; int x = now.x;
			
			if(visit[y][x])continue;
			visit[y][x] =true;
			
			for(int i=0;i<4;i++) {
				int ny = y+dy[i]; int nx = x+dx[i];
				//범위
				if(ny<0||ny>=n||nx<0||nx>=n) continue;
				//방문하지 않았고 이전것과 같으면 계속 붙는다.
				if(setNormal==1) {
					if(!visit[ny][nx]&&input[y][x]==input[ny][nx])
						queue.offer(new dot(ny,	 nx));
				}
				else if(setNormal==0&&!visit[ny][nx]) {
					if(input[ny][nx]==input[y][x]) {
						queue.offer(new dot(ny,	 nx));
					}
					else if((input[ny][nx]=='R'&&input[y][x]=='G')||(input[ny][nx]=='G'&&input[y][x]=='R')) {
						queue.offer(new dot(ny,	 nx));
					}
				}
			}
			
		}
	}
	static class dot{
		int y, x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
		
	}
}
