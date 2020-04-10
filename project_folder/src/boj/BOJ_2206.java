package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 2. 오전 11:57:50
 * @category 
* @problem_description N X M 11에서 NM으로 이동하려는데 최단 경로로 이동하려 한다. 최단 경로는 맵에서
* 가장 적은 개수의 칸을 지나는 경로를 말하는데, 시작하는 칸과 끝나는 칸도 포함해서 센다.
* 
* 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀더 경로가 짧아진다면
* 벽을 한개까지 부수고 이동해도 된다.
* 
* 맵이 주어졌을 때 최단 경로를 구해내는 프로그램 이동 불가일때는 -1 출력
* @solving_description 
*/
public class BOJ_2206 {
	private static int n;
	private static int m;
	private static int[][] map;
	private static ArrayList<dot> al;
	private static int min;
	private static int[][][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int k;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n= Integer.parseInt(stringTokenizer.nextToken());
		m= Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n+1][m+1];
		visit = new int[k+1][n+1][m+1];
		for(int i=1;i<=n;i++) {
			String s = bufferedReader.readLine();
			for(int j=1;j<=m;j++) {
				int a =  Integer.parseInt(s.substring(j-1,j));
				map[i][j] =a;
				for(int q=0;q<=k;q++) {
					visit[q][i][j] = Integer.MAX_VALUE;
				}
			}
		}
		min = Integer.MAX_VALUE;
		
		bfs();
		
		System.out.println((min==Integer.MAX_VALUE?-1:min));
	}//main
	static void bfs() {
		Queue<dot> queue = new LinkedList<dot>();
		queue.offer(new dot(1, 1,0,k));
		while(!queue.isEmpty()) {
			dot now = queue.poll();
			int y = now.y; int x = now.x; int move = now.move; int cnt =now.cnt;
			if(move>min) {
				return;
			}
			if(y==n&&x==m) {
				min= Math.min(min, move+1);
				return;
			}
			if(visit[cnt][y][x]<=move) continue;
			visit[cnt][y][x] = move;
			
			for(int i=0;i<4;i++) {
				int ny = y+dy[i]; int nx = x+dx[i];
				//범위
				if(ny<=0||ny>n||nx<=0||nx>m) continue;
			
				if(map[ny][nx]==1) {
					if(cnt>0&&visit[cnt-1][ny][nx]>move+1) {
						queue.offer(new dot(ny, nx, move+1, cnt-1));
					}
				}
				else {
					if(visit[cnt][ny][nx]>move+1) {
						queue.offer(new dot(ny, nx,move+1,cnt));
					}
				}
//				if(map[ny][nx]==0&&visit[cnt][ny][nx]>move+1) { //방문하지 않았고 일반 길이면
//					queue.offer(new dot(ny, nx,move+1,cnt));
//				}
//			    else if(map[ny][nx]==1&&cnt==1&&visit[cnt-1][ny][nx]>move+1) { //방문하지 않았고  벽이고 아직 뚫을 수 있으면
//					queue.offer(new dot(ny, nx, move+1, cnt-1));
//				}
			}
			
		}
		
	}//bfs
	static class dot{
		int y,x,move,cnt;
		public dot(int y, int x, int move, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.move = move;
			this.cnt = cnt;
		}
	}//dot
}
