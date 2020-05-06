package boj_may;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 6. 오후 4:28:28
 * @category BFS
* @level 3
* @problem_description 번호매겨서 섬간의 거리의 최솟값을 구하고 소트해서 출력하게 만들면 될듯. 
* map을 한번 사용해보자.  
* @solving_description 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146 {
	private static int n;
	private static int[][] map;
	private static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean[][] visit2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(bufferedReader.readLine());

		map  = new int[n][n];
		StringTokenizer stringTokenizer = null;
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		visit = new boolean[n][n];
		int number=1;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]==1&&!visit[i][j]) {
					map[i][j] = number;
					Queue<dot> queue = new LinkedList<>();
					queue.add(new dot(i,j));
					
					while(!queue.isEmpty()) {
						dot now = queue.poll();
						
						int y = now.y;
						int x = now.x;
						
						if(visit[y][x]) continue;
						visit[y][x] = true;
						
						for(int dir=0;dir<4;dir++) {
							int ny = y+dy[dir];
							int nx =x+dx[dir];
							
							//범위 체크
							if(ny<0||ny>=n||nx<0||nx>=n) continue;
							
							if(!visit[ny][nx]&&map[ny][nx]==1) {
								map[ny][nx]=number;
								queue.add(new dot(ny, nx));
							}
						}
					}
					number++;
				}//map
			}
		}//for i
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		//모든 점에 대해서  BFS를 해본다. BFS할때 좌표랑 길이를 저장한다. 근데 중간에 계산된 값보다 적은 값이면
		//가지치기 백트레킹해준다.
		int min = Integer.MAX_VALUE;
		visit = new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]!=0&&!visit[i][j]) {
					visit2 = new boolean[n][n];
					Queue<bridge> bqueue = new LinkedList<>();
					bqueue.add(new bridge(i, j, 0));
					while(!bqueue.isEmpty()) {
						bridge now = bqueue.poll();
						int y = now.y;
						int x = now.x;
						int len = now.len;
						
						if(len>min) {
							continue;
						}
						
						if(visit2[y][x]) continue;
						visit2[y][x] =true;
						
						for(int dir= 0;dir<4;dir++) {
							int ny = y+dy[dir];
							int nx = x+dx[dir];
							
							//범위
							if(ny<0||ny>=n||nx<0|nx>=n) continue;
							//1. 0이면 더들어가고 
							if(!visit2[ny][nx]&&map[ny][nx]==0) {
								bqueue.add(new bridge(ny, nx, len+1));
							}
							//2. 같은 거면 버려주고
							else if(map[ny][nx]==map[i][j]) {
								continue;
							}
							//3. 0이 아니면서 다른거면 길이를 갱신해준다.
							else if(map[ny][nx]!=0&&map[i][j]!=map[ny][nx]) {
//								System.out.println("y,x:" +y+","+x);
//								System.out.println("ny,nx"+ny+","+nx);
//								System.out.println(len);
								min= Math.min(min,len);
								continue;
							}
						}
					}
				}
			}
		}//for i
		System.out.println(min);
	}//main
	static class dot {
		int y,x;
		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	static class bridge{
		int y, x, len;

		public bridge(int y, int x, int len) {
			super();
			this.y = y;
			this.x = x;
			this.len = len;
		}
		
	}
}
