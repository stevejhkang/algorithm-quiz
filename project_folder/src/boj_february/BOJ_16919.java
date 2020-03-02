package boj_february;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16919 {
	static final int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		if (N > 1) {
			N = (N - 2) % 4 + 2;
		}

		int second = 0;
		Queue<dot> q = new LinkedList<>();
		do {
			second++;
			if (second % 2 == 0) {
				map = fillWithBombs(map);
			} else {
				explode(map, q);
				q = findBombs(map);
			}
		} while (second != N);

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.printf("%c", map[i][j]);
			}
			System.out.println();
		}
	}//main
	static class dot {
		int x, y;

		public dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static char[][] fillWithBombs(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = 'O';
			}
		}
		return map;
	}

	static char[][] explode(char[][] map, Queue<dot> q) {
		int R = map.length, C = map[0].length;
		while (!q.isEmpty()) {
			int cx = q.peek().x;
			int cy = q.peek().y;
			q.poll();

			map[cx][cy] = '.';
			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}

				map[nx][ny] = '.';
			}
		}
		return map;
	}
	static Queue<dot> findBombs(char[][] map) {
		Queue<dot> q = new LinkedList<>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'O') {
					q.add(new dot(i, j));
				}
			}
		}
		return q;
	}
}
