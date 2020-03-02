package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 28. 오후 4:55:43
 * @category 
* @problem_description 미로는 n*m크기 빈방또는 벽으로 이뤄 짐. 벽을 부수지 않으면 이동할 수 없다.
* 이동은 상하좌우 벽은 평소에 이동할 수 없지만 벽을 부술 수 있다. 
* 1,1에서 n,m으로 이동하려면 벽을 최소 몇개 부수어야 하는지 구하라
* @solving_description 
*/

public class BOJ_1261 {
	private static int m;
	private static int n;
	private static int[][] map;
	private static int[][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		m = Integer.parseInt(stringTokenizer.nextToken());
		n = Integer.parseInt(stringTokenizer.nextToken());
		
		map =new int[n+1][m+1];
		for(int i=1;i<=n;i++) {
			String s= bufferedReader.readLine();
			for(int j=1;j<=m;j++) {
				map[i][j] = Integer.parseInt(s.substring(j-1,j));
			}
		}
		
//		for(int i=1;i<=n;i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		//갈 수 있으면 그냥 가고 이미 방문했을때 현재까지 부순 수보다 작으면 갱신하는 방식으로?
		//진행하면 될듯?
		//현재까지 부순 벽돌의 개수를 파악
		visit = new int[n+1][m+1];
		//visit배열 맥스로 초기화
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		min = Integer.MAX_VALUE;
		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(1, 1));
		visit[1][1] = 0;
		while(!queue.isEmpty()) {
			dot now = queue.poll();
			int y = now.y; int x= now.x;
			
			if(y==n&&x==m) {
				min = Math.min(min, visit[y][x]);
			}
			for(int i=0;i<4;i++) {
				int ny = y+dy[i]; int nx = x+dx[i];
				//범위
				if(ny<=0||ny>n||nx<=0||nx>m) continue; 
				//벽일때
				if(map[ny][nx]==1&&visit[ny][nx]>visit[y][x]+1) {
					visit[ny][nx] = visit[y][x]+1;
					queue.offer(new dot(ny, nx));
				}
				//벽이 아닐때
				else if(map[ny][nx]==0&&visit[ny][nx]>visit[y][x]) {
					visit[ny][nx] = visit[y][x];
					queue.offer(new dot(ny, nx));
				}
			}
		}//while
		System.out.println(min);
		
	}//main
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
