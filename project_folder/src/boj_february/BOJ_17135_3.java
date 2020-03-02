package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135_3 {
	private static int n;
	private static int m;
	private static int d;
	private static int[][] map;
	private static int enemy;
	// 좌 상 우
	static int dy[] = { 0, -1, 0 };
	static int dx[] = { -1, 0, 1 };
	private static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		d = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n + 1][m];
		enemy = 0;
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < m; j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				if (a == 1)
					enemy++;
				map[i][j] = a;
			}
		}
//		System.out.println("");
		max = Integer.MIN_VALUE;
		com(0, 0);
		System.out.println(max);

	}

	// 0~m-1까지 3명 선택하기
	static void com(int k, int idx) {
		if (k == 3) { // k==3이면 게임을 시작한다.
			attack();
		}
		for (int i = idx; i < m; i++) {
			map[n][i] = 2;
			com(k + 1, i + 1);
			map[n][i] = 0;
		}
	}// dfs

	static void attack() {
		// map을 복사한다. 적 수도 복사
//		System.out.println("");
		int kill = 0;
		//게임에 사용할 map복사
		int map_copy[][] = new int[n + 1][m];
		for (int i = 0; i <= n; i++) {
			map_copy[i] = map[i].clone();
		}
		//한번마다 사용하는 map_copy2
		
		
		int time = n;
		while (time >= 1) {
			int map_copy2[][] = new int[n + 1][m];
			for(int i =0;i<=n;i++) {
				map_copy2[i]= map_copy[i].clone(); 
			}
			for (int i = 0; i < m; i++) {
				// 궁수이면 큐에 넣고 잡기를 시작한다.
				if (map_copy[time][i] == 2) {
					Queue<dot> queue = new LinkedList<dot>();
					queue.offer(new dot(time - 1, i, d - 1));
					kill_one: while (!queue.isEmpty()) {
						dot now = queue.poll();
						int y = now.y;
						int x = now.x;
						int dist = now.d;
						// 적을 발견하면 죽인다.
						if (map_copy[y][x] == 1) {
							if (map_copy2[y][x] == 1) {// 처음 죽이는 적이면
								kill++;
								map_copy2[y][x] = 0;
							}
							break kill_one;
						}
						// 적이 없고 공격범위가 남아 있으면 다음 왼쪽방향부터 멀리 공격한다.
						if (dist > 0) {
							for (int k = 0; k < 3; k++) {
								int ny = y + dy[k];
								int nx = x + dx[k];
								// 범위 체크
								if (ny < 0 || ny >= time || nx < 0 || nx >= m)
									continue;
								queue.offer(new dot(ny, nx, dist - 1));
							}
						}
					} // while

				}
			} // 궁수를 선택하는 for문 궁수들이 다 죽이면 적을 앞으로 땡기고 앞으로 땡기기보다는 궁수를 앞으로 보낸다.
			
			if (time > 0) {
				for (int i = 0; i < m; i++) {
					map_copy2[time - 1][i] = map_copy2[time][i];
				}
			}
			time--;
//			System.out.println("time: "+time);
//			for (int i = 0; i <= n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(map_copy2[i][j] + " ");
//				}
//				System.out.println("");
//			}
//			System.out.println("");
			for (int i = 0; i <= time; i++) {
				map_copy[i] = map_copy2[i].clone();
			}
//			for (int i = 0; i <= n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(map_copy2[i][j] + " ");
//				}
//				System.out.println("");
//			}
//			System.out.println("");
		} // while
		if (max < kill) {
			max = kill;
		}
		
	}

	static class dot {
		int y;
		int x;
		int d;

		public dot(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}

//for(int i=0;i<=n;i++) {
//for(int j=0;j<m;j++) {
//	System.out.print(map_copy[i][j]+" ");
//}
//System.out.println("");
//}
