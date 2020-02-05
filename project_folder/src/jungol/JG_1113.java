package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class JG_1113 {
	static int n, m, lastY, lastX; // 세로 가로
	static int[][] map;
	static int[][] visit;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		stringTokenizer = new StringTokenizer(br.readLine());
		lastY = Integer.parseInt(stringTokenizer.nextToken());
		lastX = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n][m];
		visit = new int[n][m];

		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		// 시작은 오른쪽과 아래로 시작
		Stack<dot> stack = new Stack<>();
		stack.push(new dot(0, 0, 0));
		int cnt = 1;
		visit[0][0] = cnt;

		while (!stack.isEmpty()) {
			dot temp = stack.pop();

			int y = temp.y;
			int x = temp.x;
			int dir = temp.dir;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
				if (visit[ny][nx] <= visit[y][x] && visit[nx][ny] != 0) continue;
				if (map[ny][nx] == 0) continue;
				if (dir == i || (y == 0 && x == 0))
					visit[ny][nx] = visit[y][x];
				else
					visit[ny][nx] = visit[y][x] + 1;
				stack.push(new dot(ny, nx, i));
			}
		}
		System.out.println(visit[lastY][lastX]-1);
	}

	static class dot {
		int y;
		int x;
		int dir;

		public dot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "dot [y=" + y + ", x=" + x + "]";
		}
	}
}


