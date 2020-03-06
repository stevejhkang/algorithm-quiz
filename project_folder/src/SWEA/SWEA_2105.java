package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 6. 오전 10:11:10
 * @category 시뮬레이션, DFS
 * @problem_description 대각선 방향으로 움직이고 사각형 모양을 그리고 출발한 카페로 돌아와야 한다. 카페 투어 중에 같은
 *                      숫자(종류)의 디저트를 팔고 있는 카페가 있으면 안 된다. 하나의 카페에서 디저트를 먹는 것도
 *                      안된다. 같이 왔던 길을 다시 돌아가는것도 안된다. 디저트를 가장 많이 먹을 수 있는 경로를 찾아
 *                      그때의 디저트 수를 정답으로 출력하는 프로그램 가장 많이 먹을 수 있는 디저트 종류 디저트를 먹을 수
 *                      없는 경우 -1 출력 디저트 종류는 1~100까지 정수
 * @solving_description 한 변의 길이가 N일 경우 최대로 큰 사각형의 길이는 N/2이다. 그러므로 처음 가운데에서 다 방문할
 *                      수 있는지 체크하고 나머지는 1~n/2-1까지를 대보고 범위를 초과하는지를 체크하면 될듯? 갈때가지
 *                      계속 DFS하면 될듯!
 */

public class SWEA_2105 {
	private static int N;
	private static int[][] map;
	private static boolean[][] visit;
	private static boolean[] nVisit;
	private static int result;
	private static int startY;
	private static int startX;
	static int[][] dir = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bufferedReader.readLine());
			map = new int[N][N];
			StringTokenizer stringTokenizer;
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			visit = new boolean[N][N];
			nVisit = new boolean[101];
			result = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					startY = i;
					startX = j;
					visit[i][j] = true;
					nVisit[map[i][j]] = true;
					// 방문처리해주고 DFS
					dfs(i, j, 1, 0);
					visit[i][j] = false;
					nVisit[map[i][j]] = false;
				}
			}
			if (result < 4)
				result = -1;
			System.out.println("#" + tc + " " + result);

		} // for tc
	}// main

	// 파라미터로 무엇을? 들어갈때마다 이전에 갔던 것을 저장해야한다.
	// 현재가고 있는 방향, 이전에 방문했던거? 이거 dfs라서 중복 안될 것 같은데
	static void dfs(int y, int x, int cnt, int d) {
		// base case: 범위를 벗어났을 때 같은 카페에 들렀을때, 다른 방향으로 바꿔주고
		// 둘다 갈 수 있으면 기존방향 진행과 다음 방향 진행 2가지로 DFS
		if (d == 4)
			return;
		
		int ny = y + dir[d][0];
		int nx = x + dir[d][1];
		
		if (nx < 0 || ny < 0 || nx >= N || ny >= N)
			return;
		if (visit[ny][nx] || nVisit[map[ny][nx]]) {
			// 이미 갔던 지점이 시작 점일 경우 개수 따지기
			if (nx == startX && ny == startY)
				result = Math.max(result, cnt);
			return;
		}
		// 숫자 사용 & 방문 표시
		nVisit[map[ny][nx]] = true;
		visit[ny][nx] = true;
		// 한 방향으로 쭉
		dfs(ny, nx, cnt + 1, d);
		// 그 때 그 때 다음 방향으로 틀기
		dfs(ny, nx, cnt + 1, d + 1);
		visit[ny][nx] = false;
		nVisit[map[ny][nx]] = false;
	}

}
