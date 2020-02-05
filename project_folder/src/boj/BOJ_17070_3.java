package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author junhukang
 * @time 2020. 2. 6. 오전 2:27:37
 * @category 조건부 3방향 DFS 완전탐색
 * @problem_description
 * @solving_description 어떻게 3방향으로 움직일지가 관건
 */

public class BOJ_17070_3 {
	static int n, ans = 0;
	static int[][] map;
	static int[][] dy = { { 0, 0, 1 }, { 0, 1, 1 }, { 0, 1, 1 } }; // 이렇게 0,0을 넣어서 여러 방향으로 이동하는 것을 쉽게 해결
	static int[][] dx = { { 1, 0, 1 }, { 0, 0, 1 }, { 1, 0, 1 } };
	//0가로 1세로 2대각선
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n ][n ]; // 벽확인
		// 벽입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][j] = a;
			}
		}
		dfs(0,1,0);
		System.out.println(ans);
	}

	static void dfs(int y, int x, int num) {
		for (int i = 0; i < 3; i++) {
			//
			if (dy[num][i] == 0 && dx[num][i] == 0) {
				continue;
			}
			if (y == n - 1 && x == n - 1) {// 다음 값이 목표하는 값이면 +1해주고 끝낸다.
				ans++;
				return;
			}
			int ny = y + dy[num][i];
			int nx = x + dx[num][i];
			
			// 범위 체크 후 
			if (0 <= ny && ny < n && 0 <= nx && nx < n) {
				if ((i == 0 || i == 1) && map[ny][nx] == 0)
					dfs(ny, nx, i);
				else if (i == 2) {
					// 다음, 다음 왼쪽, 다음 위쪽이 0이어야 한다.
					if (map[ny][nx] == 0 && map[ny - 1][nx] == 0 && map[ny][nx - 1] == 0)
						dfs(ny, nx, i);
				}
			}
		}
	}
}
