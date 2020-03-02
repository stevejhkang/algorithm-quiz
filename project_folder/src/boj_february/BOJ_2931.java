package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 27. 오후 2:55:03
 * @category @problem_description 해커가 블록하나를 없애서 모스크바와 자그레브로 가스가 가는 것을 막는다. 해커가
 *           없앤 블록의 위치와 종류를 알아내라
 * @solving_description 모스크바에서 출발해서 점을 만날때까지 이동시키고 자그레브에서 점을 만날때까지 이동시켜서 만난 좌표가
 *                      거기고 방향을 유지해서
 */
public class BOJ_2931 {
	private static int R;
	private static int C;
	private static char[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean[][] visit;
	private static char ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		visit = new boolean[R][C];
		map = new char[R][C];
		int startY = 0;
		int startX = 0;
		int endY = 0;
		int endX = 0;
		for (int i = 0; i < R; i++) {
			String s = bufferedReader.readLine();
			for (int j = 0; j < C; j++) {
				char c = s.charAt(j);
				if (c == 'M') {
					startY = i;
					startX = j;
					visit[startY][startX] = true;
				} else if (c == 'Z') {
					endY = i;
					endX = j;
					visit[endY][endX] = true;
				}
				map[i][j] = c;
			}
		}

		int r = startY;
		int c = startX;
		Stack<dot> stack = new Stack<>();
//		int dir = 0;
		int first_dir = 0;
		int end_dir = 0;

		for (int i = 0; i < 4; i++) {
			int ny = startY + dy[i];
			int nx = startX + dx[i];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C)
				continue;
			if (map[ny][nx] != '.') {
				// 아닌경우 스택에 넣는다.
				stack.push(new dot(ny, nx, i));
				first_dir = i;
//				next= map[ny][nx];
				break;
			}
		}

		while (!stack.isEmpty()) {
			dot now = stack.pop();
			int y = now.y;
			int x = now.x;
			int dir = now.dir;
			visit[y][x] = true;
			dot next_dot = move(y, x, map[y][x], dir);

			if (next_dot == null)
				continue;
//			System.out.println(next_dot.y+","+next_dot.x);
			stack.push(next_dot);
			if (map[next_dot.y][next_dot.x] == '.') { // 해커가 뺀 지점이면 저장
				r = next_dot.y ;
				c = next_dot.x ;
				first_dir = next_dot.dir;
			}
		}

		int end_r = endY;
		int end_c = endX;
		for (int i = 0; i < 4; i++) {
			int ny = endY + dy[i];
			int nx = endX + dx[i];
			if (ny < 0 || ny >= R || nx < 0 || nx >= C)
				continue;
			if (map[ny][nx] != '.') {
				// 아닌경우 스택에 넣는다.
				stack.push(new dot(ny, nx, i));
				end_dir = i;
//				next= map[ny][nx];
				break;
			}
		}
		while (!stack.isEmpty()) {
			dot now = stack.pop();
			int y = now.y;
			int x = now.x;
			int dir = now.dir;
			visit[y][x] = true;
			dot next_dot = move(y, x, map[y][x], dir);
			if (next_dot == null)
				continue;
//			System.out.println(next_dot.y+","+next_dot.x);
			stack.push(next_dot);
			if (map[next_dot.y][next_dot.x] == '.') { // 해커가 뺀 지점이면 저장
				end_r = next_dot.y;
				end_c = next_dot.x;
				end_dir = next_dot.dir;
			}
		}
//		System.out.println(r + ", " + c);
//		System.out.println(end_r + ", " + end_c);

//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.ou t.print(visit[i][j] + " ");
//			}
//			System.out.println();
//		}
		ans = '.';
		// 상하좌우
//		System.out.println("first_dir" + first_dir);
//		System.out.println("end_dir" + end_dir);
		char[] car = { '+', '|', '-', '1', '2', '3', '4' };
		if ((r == startY && c == startX)) {
			// 출발을 못했을 때 end_r end_c만 그 전지점으로 도착
			for (int i = 0; i < car.length; i++) {
				if (move2(car[i], end_r, end_c, startY, startX, end_dir)) {
					if (car[i] == '+') {
						// 상하좌우 체크하고 visit안한데가 있으면 넘어가
						// 상
						if (end_r - 1 >= 0 && map[end_r - 1][end_c] != '.' && !visit[end_r - 1][end_c]) {
							continue;
						} else if (end_c - 1 >= 0 && map[end_r][end_c - 1] != '.' && !visit[end_r][end_c - 1]) {
							continue;
						} else if (end_r + 1 < R && map[end_r + 1][end_c] != '.' && !visit[end_r + 1][end_c]) {
							continue;
						} else if (end_c + 1 < C && map[end_r][end_c + 1] != '.' && !visit[end_r][end_c + 1])
							continue;
					}
					else {
						ans=car[i];
						break;
					}
				} 
			}
			System.out.println((end_r+1) + " " + (end_c+1) + " " + ans);
		}

		else if ((end_r == endY && end_c == endX)) {
			// 끝에서 출발 못했을때 r,c만 그 전 지점으로 도착한다.
			for (int i = 0; i < car.length; i++) {
				if (move2(car[i], r, c, endY, endX, first_dir)) {
					if (car[i] == '+') {
						if (r - 1 >= 0 && map[r - 1][c] != '.' && !visit[r - 1][c]) {
							continue;
						} else if (c - 1 >= 0 && map[r][c - 1] != '.' && !visit[r][c - 1]) {
							continue;
						} else if (r + 1 < R && map[r + 1][c] != '.' && !visit[r + 1][c]) {
							continue;
						} else if (c + 1 < C && map[r][c + 1] != '.' && !visit[r][c + 1])
							continue;
					}
					else {
						ans=car[i];
						break;
					}
				} 
			}
			System.out.println((r+1) + " " + (c+1) + " " + ans);
		}

		else {
			// 해당 지점에서
			if ((first_dir == 2 && end_dir == 3) || (first_dir == 3 && end_dir == 2)) { // -
				// 위 아래가 있고 방문처리가 안되어 있으면+
				if (r - 1 >= 0 && r + 1 < R && map[r - 1][c] != '.' && map[r + 1][c] != '.' && !visit[r - 1][c]
						&& !visit[r + 1][c])
					ans = '+';
				else {
					ans = '-';
				}
			} else if ((first_dir == 0 && end_dir == 1) || (first_dir == 1 && end_dir == 0)) { // |
				// 좌우가 있고 방문처리가 안되어 있면 +
				if (c - 1 >= 0 && c + 1 < C && map[r][c - 1] != '.' && map[r][c + 1] != '.' && !visit[r][c - 1]
						&& !visit[r][c + 1])
					ans = '+';
				else {
					ans = '|';
				}

			} else if ((first_dir == 0 && end_dir == 2) || (first_dir == 2 && end_dir == 0)) { // 1
				// 위와 왼쪽이 있고 방문처리가 안되어 있으면 +
				if (r - 1 >= 0 && c - 1 < C && map[r - 1][c] != '.' && map[r][c - 1] != '.' && !visit[r - 1][c]
						&& !visit[r][c - 1])
					ans = '+';
				else {
					ans = '1';
				}
			} else if ((first_dir == 1 && end_dir == 2) || (first_dir == 2 && end_dir == 1)) { // 2
				// 왼쪽과 아래가 있고 방문처리가 안되어 있으면 +
				if (c - 1 >= 0 && r + 1 < R && map[r + 1][c] != '.' && map[r][c - 1] != '.' && !visit[r + 1][c]
						&& !visit[r][c - 1])
					ans = '+';
				else {
					ans = '2';
				}
			} else if ((first_dir == 3 && end_dir == 1) || (first_dir == 1 && end_dir == 3)) {// 3
				// 오른쪽과 아래가 있고 방문 처리가 안되어 있으면 +
				if (r + 1 < R && c + 1 < C && map[r + 1][c] != '.' && map[r][c + 1] != '.' && !visit[r + 1][c]
						&& !visit[r][c + 1])
					ans = '+';
				else {
					ans = '3';
				}
				// 나머지는 그냥 3
			} else {// 4
					// 위 오른쪽이 있고 방문처리가 안되어있으면 +
				if (r - 1 >= 0 && c + 1 < C && map[r - 1][c] != '.' && map[r][c + 1] != '.' && !visit[r - 1][c]
						&& !visit[r][c + 1])
					ans = '+';
				else {
					ans = '4';
				}
			}
			System.out.println((r+1) + " " + (c+1) + " " + ans);
		}

//		
		
//		for(int i=0;i<R;i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}// main

	static dot move(int y, int x, char c, int dir) {
		if (dir == 0) { // 상인 경우
			if (c == '|' || c == '+') { // 위로
				return new dot(y - 1, x, dir);
			} else if (c == '1') { // 상우
				return new dot(y, x + 1, 3);
			} else if (c == '4') { // 상좌
				return new dot(y, x - 1, 2);
			}
		} else if (dir == 1) {
			if (c == '|' || c == '+') { // 아래로
				return new dot(y + 1, x, dir);
			} else if (c == '3') { // 하좌
				return new dot(y, x - 1, 2);
			} else if (c == '2') { // 하우
				return new dot(y, x + 1, 3);
			}
		} else if (dir == 2) {
			if (c == '-' || c == '+') { // 좌
				return new dot(y, x - 1, dir);
			} else if (c == '1') { // 좌하
				return new dot(y + 1, x, 1);
			} else if (c == '2') { // 좌상
				return new dot(y - 1, x, 0);
			}
		} else {
			if (c == '-' || c == '+') { // 우
				return new dot(y, x + 1, dir);
			} else if (c == '3') {// 우상
				return new dot(y - 1, x, 0);
			} else if (c == '4') { // 우하
				return new dot(y + 1, x, 1);
			}
		}
		return null;
	}

	static boolean move2(char c, int y, int x, int targetY, int targetX, int dir) {
		if (dir == 0) { // 상인 경우
			if (c == '|' || c == '+') { // 위로
				if (y - 1 == targetY && x == targetX) {
					return true;
				}
			} else if (c == '1') { // 상우
				if (y == targetY && x + 1 == targetX) {
					return true;
				}
			} else if (c == '4') { // 상좌
				if (y == targetY && x - 1 == targetX) {
					return true;
				}
			}
		} else if (dir == 1) {
			if (c == '|' || c == '+') { // 아래로
				if (y + 1 == targetY && x == targetX) {
					return true;
				}
			} else if (c == '3') { // 하좌
				if (y == targetY && x - 1 == targetX) {
					return true;
				}
			} else if (c == '2') { // 하우
				if (y == targetY && x + 1 == targetX)
					return true;
			}
		} else if (dir == 2) {
			if (c == '-' || c == '+') { // 좌
				if (y == targetY && x - 1 == targetX) {
					return true;
				}
			} else if (c == '1') { // 좌하
				if (y + 1 == targetY && x == targetX) {
					return true;
				}
			} else if (c == '2') { // 좌상
				if (y - 1 == targetY && x == targetX) {
					return true;
				}
			}
		} else {
			if (c == '-' || c == '+') { // 우
				if (y == targetY && x + 1 == targetX) {
					return true;
				}
			} else if (c == '3') {// 우상
				if (y - 1 == targetY && x == targetX) {
					return true;
				}
			} else if (c == '4') { // 우하
				if (y + 1 == targetY && x == targetX) {
					return true;
				}
			}
		}
		return false;
	}

	static class dot {
		int y, x, dir;

		public dot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

	}
}
