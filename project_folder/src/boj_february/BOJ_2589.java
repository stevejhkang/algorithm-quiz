package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {
	private static int n;
	private static int m;
	private static int[][] map;
	private static int max;
	private static boolean[][] visit;
	static int[] dy = {1,0,-1,0}; static int[] dx = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n][m];
		
		for(int i=0;i<n;i++) {
			String s = bufferedReader.readLine();
			for(int j =0;j<m;j++) {
				char a = s.charAt(j);
				if(a=='W')
					map[i][j] = 0;
				else {
					map[i][j] =1;
				}
			}
		}
		
//		for(int i=0;i<n;i++) {
//			
//			for(int j =0;j<m;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//			
//		}
		max = Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==1) {//육지이면
					visit = new boolean[n][m];
					Queue<dot> queue = new LinkedList<dot>();
					queue.offer(new dot(i, j, 0));
					while(!queue.isEmpty()) {
						dot now = queue.poll();
						int y= now.y; int x = now.x; int cnt = now.cnt;
						if(visit[y][x]) continue;
						visit[y][x]=true;
						
						if(max<cnt) {
							max=cnt;
						}
						
						for(int k=0;k<4;k++) {
							int ny =y+dy[k]; 
							int nx =x+dx[k];
							if(ny<0||ny>=n||nx<0||nx>=m) continue;
							if(!visit[ny][nx]&&map[ny][nx]==1) {
								queue.offer(new dot(ny, nx, cnt+1));
							}
						}
					}
				}
			}
		}//for i
		System.out.println(max);
		
	}//main
	static class dot{
		int y,x,cnt;

		public dot(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		
	}
}
