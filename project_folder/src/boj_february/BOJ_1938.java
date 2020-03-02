package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 27. 오전 11:50:20
 * @category @problem_description 1은 아직 잘려지지 않은 나무 0은 아무것도 없음 BBB는 통나무 길이는 항상 3
 *           EEE로 옮기는 작업 U 통나무 위로 한칸 D 통나무를 아래로 한 칸 L,R, T 중심점을 중심으로 90도 회전 회전을
 *           하기 위해서는 통나무를 둘러싼 3*3 정사각형 구역의 나무가 없어야 한다. 최소 동작 횟수를 출력한다. 불가능하면 0
 *           출력 최소니까 BFS?
 * @solving_description 일단 길이가 3밖에 안되서
 * 
 */
public class BOJ_1938 {
	private static int n;
	private static int[][] map;
	private static dot[] tong;
	private static dot[] arrive;
	private static int[][][] visit;
	static final int U = 0;
	static final int D = 1;
	static final int L = 2;
	static final int R = 3;
	static final int T = 4;
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { -1, 0, 1 };
	static Queue<center> queue;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());

		map = new int[n][n]; // 나무를 표시하기 위한 배열
		tong = new dot[3]; // 시작 지점을 나타내기 위한 배열
		arrive = new dot[3]; // 도착지점을 나타내기 위한 배열
		int index = 0;
		int end_idx = 0;
		for (int i = 0; i < n; i++) {
			String a = bufferedReader.readLine();
			for (int j = 0; j < n; j++) {
				char c = a.charAt(j);
				if (c == 'B') {
					tong[index] = new dot(i, j);
					index++;
				} else if (c == '1') {
					map[i][j] = 1;
				} else if (c == 'E') {
					arrive[end_idx] = new dot(i, j);
					end_idx++;
				}
			}
		}
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}

		// 5가지 방법으로 이동을 시작하되 이동횟수와 방향을 따로한다.
		visit = new int[2][n][n]; // 0이 가로 1이 세로 //맥스값으로 초기화
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					visit[k][i][j] = Integer.MAX_VALUE;
				}
			}
		}
		// 초기 방향만 잡고
		int init_dir = (tong[0].y - tong[1].y == 0 ? 0 : 1); // y값이 같으면 가로 다르면 세로
		int end_dir = (arrive[0].y - arrive[1].y == 0 ? 0 : 1);

		int startY = tong[1].y;
		int startX = tong[1].x;
		int endY = arrive[1].y;
		int endX = arrive[1].x;

//		System.out.println(init_dir); 
		cnt = Integer.MAX_VALUE;
		// 중심이랑 방향만 큐에 넣어주자.
		queue = new LinkedList<center>();
		queue.offer(new center(startY, startX, init_dir));
		visit[init_dir][startY][startX] = 0;
		
		while (!queue.isEmpty()) {
			center now = queue.poll();
			int y = now.y;
			int x = now.x;
			int dir = now.dir;
			if(cnt<visit[dir][y][x]) { //현재 저장된 값보다 visit값이 크면 더이상 가지 않는다.
				continue;
			}
			//끝나는 지점의 센터와 현재 센터가 일치하고 방향도 같으면 갱신
			if (y == endY && x == endX && dir == end_dir) {
				if (cnt > visit[dir][y][x]) {
					cnt = visit[dir][y][x];
				}
			}
			for (int i = 0; i < 5; i++) { //다섯가지 방법으로 이동시킨다.
				Move(y, x, dir, i);
			}

		}//while
		
		if (cnt == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(cnt);
		}
	}// main
		// 이동할 수 있다면? 이동하게한다.
		// 이동할 수 있는지 판단하는 함수

	static boolean Move(int y, int x, int dir, int code) {
		if (dir == 0) {// 가로일때 LR편함
			if (code == U) { //위로일때 세 점을 모두 비교해줘야함.
				for (int i = 0; i < 3; i++) {
					int nx = x + dx[i]; 
					int ny = y - 1;
					// 범위를 벗어나거나 나무가 있으면 false
					if (ny < 0 || map[ny][nx] == 1)
						return false;
				}
				// 바로 큐에 넣어버리자
				//현재 저장된 값보다 작을 경우 갱신시켜준다.
				if (visit[dir][y - 1][x] > visit[dir][y][x] + 1) {
					visit[dir][y - 1][x] = visit[dir][y][x] + 1;
					queue.offer(new center(y - 1, x, dir));
					return true;
				}
			} else if (code == D) { // 아래로 세 점 모두 비교
				for (int i = 0; i < 3; i++) {
					int nx = x + dx[i];
					int ny = y + 1;
					if (ny >= n || map[ny][nx] == 1)
						return false;
				}
				if (visit[dir][y + 1][x] > visit[dir][y][x] + 1) {
					visit[dir][y + 1][x] = visit[dir][y][x] + 1;
					queue.offer(new center(y + 1, x, dir));
					return true;
				}
			} else if (code == L) { // 왼쪽일때는 왼쪽부분(x-1)만 체크해주면 된다
				if ((x - 1) - 1 < 0 || map[y][x - 1 - 1] == 1) {
					return false;
				} else {
					if (visit[dir][y][x - 1] > visit[dir][y][x] + 1) {
						visit[dir][y][x - 1] = visit[dir][y][x] + 1;
						queue.offer(new center(y, x - 1, dir));
					}
				}
			} else if (code == R) { //오른쪽일때는 오른쪽부분(x+1)만 체크해주면 된다.
				if (x + 1 + 1 >= n || map[y][x + 1 + 1] == 1) {
					return false;
				} else {
					if (visit[dir][y][x + 1] > visit[dir][y][x] + 1) {
						visit[dir][y][x + 1] = visit[dir][y][x] + 1;
						queue.offer(new center(y, x + 1, dir));
					}
				}
			} else { // 6방향 확인해야
				if (y - 1 >= 0 && y + 1 < n) { //x방향은 고려 안해도  됨. 
					if (map[y - 1][x - 1] == 1 || map[y - 1][x] == 1 || map[y - 1][x + 1] == 1)
						return false;
					if (map[y + 1][x - 1] == 1 || map[y + 1][x] == 1 || map[y + 1][x + 1] == 1) {
						return false;
					} else {
						int new_dir = 1;
						if (visit[new_dir][y][x] > visit[dir][y][x] + 1) {
							visit[new_dir][y][x] = visit[dir][y][x] + 1;
							queue.offer(new center(y, x, new_dir));
							return true;
						}
					}
				}
			}
		} else if (dir == 1) {// 세로 일때 UD편함
			if (code == U) { //
				if ((y - 1) - 1 < 0 || map[y - 1 - 1][x] == 1) {
					return false;
				} else {
					if (visit[dir][y - 1][x] > visit[dir][y][x] + 1) {
						visit[dir][y - 1][x] = visit[dir][y][x] + 1;
						queue.offer(new center(y - 1, x, dir));
					}
				}
			} else if (code == D) {
				if (y + 1 + 1 >= n || map[y + 1 + 1][x] == 1) {
					return false;
				} else {
					if (visit[dir][y + 1][x] > visit[dir][y][x] + 1) {
						visit[dir][y + 1][x] = visit[dir][y][x] + 1;
						queue.offer(new center(y + 1, x, dir));
					}
				}
			} else if (code == L) {
				for (int i = 0; i < 3; i++) {
					int nx = x - 1;
					int ny = y + dy[i];
					// 범위를 벗어나거나 나무가 있으면 false
					if (nx < 0 || map[ny][nx] == 1)
						return false;
				}
				// 바로 큐에 넣어버리자
				if (visit[dir][y][x - 1] > visit[dir][y][x] + 1) {
					visit[dir][y][x - 1] = visit[dir][y][x] + 1;
					queue.offer(new center(y, x - 1, dir));
					return true;
				}
			} else if (code == R) {
				for (int i = 0; i < 3; i++) {
					int nx = x + 1;
					int ny = y + dy[i];
					if (nx >= n || map[ny][nx] == 1)
						return false;
				}
				if (visit[dir][y][x + 1] > visit[dir][y][x] + 1) {
					visit[dir][y][x + 1] = visit[dir][y][x] + 1;
					queue.offer(new center(y, x + 1, dir));
					return true;
				}
			} else {
				if (x - 1 >= 0 && x + 1 < n) {
					if (map[y - 1][x - 1] == 1 || map[y][x - 1] == 1 || map[y + 1][x - 1] == 1)
						return false;
					if (map[y - 1][x + 1] == 1 || map[y][x + 1] == 1 || map[y + 1][x + 1] == 1) {
						return false;
					} else {
						int new_dir = 0;
						if (visit[new_dir][y][x] > visit[dir][y][x] + 1) {
							visit[new_dir][y][x] = visit[dir][y][x] + 1;
							queue.offer(new center(y, x, new_dir));
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	static class dot {
		int y, x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "dot [y=" + y + ", x=" + x + "]";
		}
	}

	static class center {
		int y, x, dir;

		public center(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

	}
}
