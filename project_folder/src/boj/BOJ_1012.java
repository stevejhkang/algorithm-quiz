package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 26. 오전 9:46:50
 * @category 
* @problem_description 흰지렁이 구입, 이 지렁이는 인접한 배추 이동가능 인접배추도 보호받는다.
* 서로 인접해 있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지
* 필요한 최소한의 지렁이 마리수 계산
* @solving_description 그냥 BFS/DFS로 돌려서 이중포문 돌려서 BFS/DFS로 방문체크해주면서 덩어리 개수
* 세는 문제
*/
public class BOJ_1012 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int m;
	private static int n;
	private static int baechu;
	private static boolean[][] map;
	private static boolean[][] visit;
	private static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			m = Integer.parseInt(stringTokenizer.nextToken());//가로
			n = Integer.parseInt(stringTokenizer.nextToken());//세로
			baechu = Integer.parseInt(stringTokenizer.nextToken());
			map = new boolean[n][m];
			for(int q =0;q<baechu;q++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int x = Integer.parseInt(stringTokenizer.nextToken());
				int y = Integer.parseInt(stringTokenizer.nextToken());
				map[y][x] =true;
			}
			visit =new boolean[n][m];
			//BFS돌면서 덩어리가 몇개인지 세준다.
			cnt=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]&&!visit[i][j]) {
						cnt++;
						bfs(i,j);
					}
				}
			}
			System.out.println(cnt);
		}//for tc
		
	}//main
	static void bfs(int i,int j) {
		//큐에 넣는다.
		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(i, j));
		while(!queue.isEmpty()) {
			dot now = queue.poll();
			int y = now.y; int x = now.x;
			
			if(visit[y][x]) continue;
			visit[y][x] = true;
			
			for(int dir=0;dir<4;dir++) {
				int ny = y+dy[dir]; int nx =x+dx[dir];
				//범위
				if(ny<0||ny>=n||nx<0||nx>=m) continue;
				
				if(!visit[ny][nx]&&map[ny][nx]) {
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
