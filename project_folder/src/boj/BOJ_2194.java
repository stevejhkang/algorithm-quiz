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
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new int[n + 1][m + 1];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				map[y][x] = -1; // 장애물은 -1표시
			}
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
		queue.offer(new int[] { startY, startX }); // y,x
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int y = temp[0];
			int x = temp[1];
			int MoveAll = a * b;

			// 몸체 전부가 사방으로 이동가능해야한다.
			// 범위 안벗어나고 장애물 없고 visit은 맨왼쪽 위만 하자
			for (int z = 0; z < 4; z++) {
				int body_y=0; int body_x=0; boolean flag=true;
				cantmove:for (int i = 0; i < a; i++) {
					for (int j = 0; j < b; j++) {
						// 몸전체 사방
						body_y = y + a+dy[z];
						body_x = x + b+dx[z];
						//하나라도 범위벗어나거나 장애물 있으면 아예 그방향으로 못감
						if(body_y<=0||body_y>n||body_x<=0||body_x>n) {
							flag=false;
							break cantmove;
						}
						if(map[body_y][body_x]==-1) {
							flag=false;
							break cantmove;
						}

					}
				}
			}
		}

	}
}
