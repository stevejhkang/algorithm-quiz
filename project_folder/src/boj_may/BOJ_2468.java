package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 14. 오후 5:51:40
 * @category 
* @problem_description 어떤 지역의 높이 정보가 주어졌을때, 장마철에 물에 잠기지 않는
* 안전한 영역의 최대 개수를 계산하라
* @solving_description 
*/
public class BOJ_2468 {
	private static int n;
	private static int[][] map;
	private static int max_cnt;
	private static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int water_level;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		
		map = new int[n][n];
		int start =Integer.MAX_VALUE; int end = Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<n;j++) {
				int a= Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = a;
				if(start>a)//제일 작은 값을 구한다.
					start = a;
				if(end<a) //제일 큰 값을 구한다.
					end = a;
			}
		}
		
		//start에서부터 end까지 하나씩 증가하면서 섬의 개수를 체크하기 시작한다.
		//이중포문을 돌면서 현재 물수위보다 큰게 있으면 BFS돌리면서 visit하고
		//전부 방문했는지 체크하자
		water_level = start-1;
		max_cnt = Integer.MIN_VALUE;
		
		while(water_level<=end) {
			int safe_zone=0;
			visit = new boolean[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j]>water_level&&!visit[i][j]) {
						safe_zone++;
						bfs(i,j);
					}
				}
			}//for i
			if(max_cnt<safe_zone) {
				max_cnt = safe_zone;
			}
			water_level++;
		}//while
		System.out.println(max_cnt);
	}//main
	static void bfs(int i,int j) {
		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(i, j));
		//ij와 연결되고 물레벨 보다 높은 섬들을 다 방문처리 해준다.
		while(!queue.isEmpty()) {
			dot now = queue.poll();
			int y = now.y; int x= now.x;
			
			if(visit[y][x]) continue;
			visit[y][x] =true;
			
			for(int k=0;k<4;k++) {
				int ny = y+dy[k]; int nx = x+dx[k];
				//범위
				if(ny<0||ny>=n||nx<0||nx>=n) continue;
				if(water_level<map[ny][nx]&&!visit[ny][nx]) {
					//현재 물레벨보다 높고 방문한 적 없으면 큐에 넣어준다.
					queue.offer(new dot(ny, nx));
				}
			}
		}
	}//bfs
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}