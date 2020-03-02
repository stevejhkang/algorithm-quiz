package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * @author junhukang
 * @time 2020. 2. 10. 오전 12:52:54
 * @category BFS
 * @problem_description
 * @solving_description BFS를 이용한 완전탐색 class에 움직인 거리를 저장하여 최솟값을 갱신하고 지금까지 한 점프
 *                      횟수를 저장하여 0이 되기 전까지 움직일 수 있게한다.
*/
public class BOJ_1600 {
	static int k, w, h;
	static int input[][];

	static int dx[] = { 0, 1, 0, -1, -2, -2, -1, -1, 1, 1, 2, 2 };
	static int dy[] = { 1, 0, -1, 0, 1, -1, 2, -2, 2, -2, 1, -1 };
	static int visit[][][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		w = Integer.parseInt(stringTokenizer.nextToken());
		h = Integer.parseInt(stringTokenizer.nextToken());

		input = new int[w][h];
		visit = new int[w][h][2]; //0이 jump_remain 1이 dist

		for (int i = 0; i < w; i++) {
			stringTokenizer = new StringTokenizer(br.readLine());
			for (int j = 0; j < h; j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				input[i][j] = a;
			}
		}

		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(0, 0, k, 0));

		while (!queue.isEmpty()) {
			dot temp = queue.poll();
			int y = temp.y;
			int x = temp.x;
			int dist = temp.dist;
			int jump_remain = temp.jump_remain;
			
			if (y == h-1 && x == w-1) {
				System.out.println(dist);
				return;
			}

			//0이 jump_remain 1이 dist
			if (visit[y][x][0]!=0&&visit[y][x][1]!=0 && visit[y][x][0]>jump_remain&&visit[y][x][1]<dist)
				continue;
			
			visit[y][x][0]=jump_remain; visit[y][x][1]=dist;

			// 0~4까지는 사방탐색
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || ny >= h || nx < 0 || nx >= w)
					continue;
				if (visit[ny][nx][0]!=0&&visit[ny][nx][1]!=0&&visit[ny][nx][0]>jump_remain&&visit[ny][nx][1]<dist+1)
					continue;
				if (input[ny][nx] == 0) { //갈 수 있으면
					queue.offer(new dot(ny, nx, jump_remain, dist + 1));
				}
			}
			if (jump_remain > 0) { //점프할 수 있다.
				for (int i = 4; i < 12; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if (ny < 0 || ny >= h || nx < 0 || nx >= w)
						continue;
					if (visit[ny][nx][0]!=0&&visit[ny][nx][1]!=0&&visit[ny][nx][0]>(jump_remain-1)&&visit[ny][nx][1]<dist+1)
						continue;
					if (input[ny][nx] == 0) {
						queue.offer(new dot(ny, nx, jump_remain - 1, dist + 1));
					}
				}

			}
		}
		System.out.println(-1);
	}// main

	//점프 남은 횟수, 거리를 담는 점 클래스
	static class dot {
		int y;
		int x;
		int jump_remain;
		int dist;

		public dot(int y, int x, int jump_remain, int dist) {
			super();
			this.y = y;
			this.x = x;
			this.jump_remain = jump_remain;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "dot [y=" + y + ", x=" + x + ", jump_remain=" + jump_remain + ", dist=" + dist + "]";
		}

	}
}
