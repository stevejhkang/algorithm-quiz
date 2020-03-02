package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 25. 오후 3:01:14
 * @category 
* @problem_description 빙산이 동서남북 네 방향으로 붙어있는 0의 개수만큼 일년마다 줄어든다.
* 그러나 0보다 줄지는 않는다.
* 출력: 빙산이 주어질 때, 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램
* 전부 다 녹을때까지 두 덩어리 이상으로 분리되지 않으면 0 출력
* @solving_description 
*/
public class BOJ_2573 {
	private static int n;
	private static int m;
	private static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean[][] visit;
	private static int[][] map_copy;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n][m];
		int startY=0; int startX=0;
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] =Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		int time =0;
		while(true) {
			time++;
			boolean is_ice=false;
//			for(int i=0;i<n;i++) {
//				for(int j=0;j<m;j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			out:for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					//하나도 남아있지 않으면 0출력
					if(map[i][j]!=0) {
						is_ice=true;
						startY=i; startX=j;
						break out;
					}
					
				}
			}
			if(!is_ice) {
				System.out.println(0);
				return;
			}
			Queue<dot> queue = new LinkedList<dot>();
			queue.offer(new dot(startY,	 startX));
			//처음 0이 아닌 것을 찾고 사방을 탐색해서 0이 아닌 수가 있으면 큐에 넣고
			//0인 개수를 세서 해당 빙산을 줄여준다. 만약 4방향에 모두 빙산이 없으면 두조각이므로 
			//출력 현재 년도 출력한다.
			visit = new boolean[n][m];
			visit[startY][startX]=true;
			map_copy = new int[n][m];// 한턴에 갱신되는 것을 임시 저장
			boolean near_ice =false;
			while(!queue.isEmpty()) {
				
				dot now = queue.poll();
				int y= now.y; int x =now.x;
				
				near_ice=false;
				int sea_cnt=0;
				
				for(int i=0;i<4;i++) {
					int ny = y+dy[i]; int nx = x+dx[i];
					if(ny<0||ny>=n||nx<0||nx>=m) continue;
					if(map[ny][nx]==0) sea_cnt++;
					if(map[ny][nx]!=0&&!visit[ny][nx]) {//0아니고 방문 안 했었으면
						visit[ny][nx]=true;
						near_ice=true;
						queue.offer(new dot(ny, nx)); //다음 방문할 섬
					}
				}
				
				//0의 개수만큼 줄여준다.
				map_copy[y][x]= map[y][x] - sea_cnt;
				if(map_copy[y][x]<0) {
					map_copy[y][x]=0;
				}
				
				
//				System.out.println();
//				for(int i=0;i<n;i++) {
//					for(int j=0;j<m;j++) {
//						System.out.print(map_copy[i][j]+" ");
//					}
//					System.out.println();
//				}
				//map에 갱신
				
			}//while
//			System.out.println("-------------------------");
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					//0이아니고 방문하지않은 곳이 있으면
					//두개섬
					if(map[i][j]!=0&&!visit[i][j]) {
						System.out.println(time-1);
						return;
					}
				}
			}
			
			for(int i=0;i<n;i++) {
				map[i] =map_copy[i].clone();
			}
		}
		
		
	}//main
	static class dot {
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
