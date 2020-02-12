package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 12. 오후 7:21:03
 * @category BFS
* @problem_description
* @solving_description 빈공간이면 큐에 넣지말고, 이미 익은 토마토도 큐에 넣으면 안됨.
* 최소 이동거리(날짜)를 구하기 위해서 visit에 담아둔다.  
*/
public class BOJ_7569 {
	private static int[][][] map;
	private static int m;
	private static int n;
	private static int height;
	private static int cnt;
	private static int[][][] visit;
	static int[][] move= {{-1,0,0},{1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		//m,n,h
		m = Integer.parseInt(stringTokenizer.nextToken());
		n = Integer.parseInt(stringTokenizer.nextToken());
		height = Integer.parseInt(stringTokenizer.nextToken());
		cnt=0;
		map=new int[height][n][m];
		visit= new int[height][n][m];
		Queue<tomato> queue = new LinkedList<tomato>();
		int max=Integer.MIN_VALUE; //큐에 
		
		for(int h=0;h<height;h++) {
			for(int i=0;i<n;i++) {
				StringTokenizer st = new StringTokenizer(bReader.readLine());
				for(int j=0;j<m;j++) {
					int a = Integer.parseInt(st.nextToken());
					if(a==0) { //안 익은 토마토 개수 세기
						cnt++;
					}
					if(a==1) {
						queue.offer(new tomato(i,j,h));
					}
					map[h][i][j]=a;
				}
			}
		}//for h
		
		if(cnt!=0&&queue.isEmpty()) { //0개인데 1이 하나도 없으면 -1
			System.out.println(-1);
			return;
		}
		else if(cnt==0) { //0개면은 그냥 0출력하고 끝
			System.out.println(0);
			return;
		}
		//익은 토마토를 꺼내서 옆으로 전파시킨다.
		while(!queue.isEmpty()) {
			tomato now = queue.poll();
			int y = now.y; int x = now.x; int h = now.h;
			
			//걸리는 시간을 visit에 저장
			if(max<visit[h][y][x])
				max=visit[h][y][x];
			
			for(int i=0;i<6;i++) {
				int ny = y+move[i][0]; int nx = x+move[i][1]; int nh=h+move[i][2];
				//범위 벗어날때, -1 빈공간일때, 이미 방문했을때
				if(nh<0||nh>=height||ny<0||ny>=n||nx<0||nx>=m) continue;
				if(map[nh][ny][nx]==-1 ||map[nh][ny][nx]==1) continue; //빈칸이 아니고
				if(visit[nh][ny][nx]!=0) continue;
				visit[nh][ny][nx]=visit[h][y][x]+1;
				cnt--;
				
				queue.offer(new tomato(ny, nx, nh));
			}
		}//while
		
		if(cnt!=0) {
			System.out.println(-1);
		}
		else {
			System.out.println(max);
		}
		
	}//main
	static class tomato{
		int y; int x; int h;

		public tomato(int y, int x, int h) {
			super();
			this.y = y;
			this.x = x;
			this.h = h;
		}
		
	}
}
