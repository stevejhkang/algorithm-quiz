package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2194 {
	private static int n;
	private static int m;
	private static int a;
	private static int b;
	private static int[][] map;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };
	private static int startY;
	private static int startX;
	private static int endY;
	private static int endX;
	private static int[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 지도 크기
		m = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken()); // 물체의 크기
		b = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken()); // 장애물 위치 개수
//		System.out.println(n + "," + m + "," + a + "," + b + "," + k);
		map = new int[n + 1][m + 1];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
//			System.out.println(y + "," + x);
			map[y][x] = -1; // 장애물은 -1표시

		}
		// 제일 왼쪽 위 한점만 주어진다.
		st = new StringTokenizer(br.readLine());
		startY = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		endY = Integer.parseInt(st.nextToken());
		endX = Integer.parseInt(st.nextToken());

		visit = new int[n + 1][m + 1];
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] { startY, startX,0 }); // y,x
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int y = temp[0];
			int x = temp[1];
			int d = temp[2];

			if(y==endY&&x==endX) {
				System.out.println(d);
				return;
			}
			// 몸체 전부가 사방 그리고 같은 방향으로 이동가능해야한다.
			// 범위 안벗어나고 장애물 없고 visit은 맨왼쪽 위만 하자
			
			for (int z = 0; z < 4; z++) {//사방탐색
				int body_y = y+dy[z]; 
				int body_x = x+dx[z];
				if (body_y <= 0 || body_y > n || body_x <= 0 || body_x > m) {
					continue;
				}
				if(visit[body_y][body_x]==1) continue;
				boolean flag = true;
				//물체의 몸 하나하나가 범위를 벗어나거나 장애물에 부딫히면 안된다.
				cantmove: for (int i = 0; i < a; i++) {
 					for (int j = 0; j < b; j++) {
						body_y = y + i + dy[z];
						body_x = x + j+ dx[z];
						// 하나라도 범위벗어나거나 장애물 있으면 아예 그방향으로 못감
						if (body_y <= 0 || body_y > n || body_x <= 0 || body_x > m) {
							flag = false;
							break cantmove;
						}
						if (map[body_y][body_x] == -1) {
							flag = false;
							break cantmove;
						}

					}
				} //cantmove
				//해당 방향으로 갈 수 있으면 넣어준다.
				if(flag) {
					body_y = y+dy[z]; 
					body_x = x+dx[z];
					visit[body_y][body_x]=1;
					queue.offer(new int[] {body_y,body_x,d+1});
				}
			} //for z
			
		} //while
		System.out.println(-1);
	}//main
}
