package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 25. 오후 4:40:31
 * @category DFS/BFS
* @problem_description 더이상 인구 이동이 없을때까지 인구이동이 지속된다.
* 국경선을 공유하는 두 나라의 인구차이가 L이상R이하라면 두나라간 국경선을 연다
* 이동을 시작한다. 국경선 열려있어 이동가능하면 오늘 하루 동안 연합이라고 한다.
* 연합을 이루고 있는 각 칸 인구수는 연합의 인구수/ 연합을 이루고 있는 칸의 개수가 된다.
* 소숫점 버린다
*  연합을 해체하고 국경선을 닫는다.
*  출력: 인구수가 주어졌을 때 인구 이동이 몇번 발생하는지 구하자
* @solving_description 처음에 DFS로 구하려고 했는데 그러려면 재귀로 하는게 아니라 큐/스택을 사용했어야 했다.
* 왜냐하면 재귀같은 경우에는 쭉 들어갔다가 다시 리턴하는 조건이 필요한데 여기서는 전부 
* 연합가능한 나라를 찾기 위해 끝까지 돌아야하기 때문에 재귀로 하기는 힘들다.
* 
* 아무튼 인구차이가 l과 r사이인 모든 나라의 합과 개수를 구하고 그 좌표들을 모두 리스트에
* 담아준 후 나중에 해당 값으로 수정을 해준다.
*/

public class hwalgo0423_서울_14_강준후 {




	private static int n;
	private static int l;
	private static int r;
	private static int[][] map;
	private static int[][] copy_map;
	private static boolean[][] visit;
	private static ArrayList<dot> al;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		n = Integer.parseInt(stringTokenizer.nextToken());
		l = Integer.parseInt(stringTokenizer.nextToken());
		r = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		// dfs로 차이가 l이상 r이하 차이이면 계속 들어가서 군집화시킨다.
		int time = 0;
		boolean check = false;
		while (true) {
			time++;
			copy_map = new int[n][n];
			visit = new boolean[n][n];
			int visit_time = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visit[i][j]) {
						visit_time++;
						bfs(i, j);
					}
				}
			} // for i
			if (visit_time == n * n) {
				// 하나하나 방문했으면 연합이 한번도 이뤄진적이 없는 것이므로
				// 인구이동이 일어나지 않은 것이므로
				System.out.println(time - 1);
				return;
			}
//				System.out.println();
//				for(int i=0;i<n;i++) {
//					for(int j=0;j<n;j++) {
//						System.out.print(copy_map[i][j]+" ");
//					}
//					System.out.println();
//				}
			// 수정된 것을 map에 갱신시켜준다.
			for (int i = 0; i < n; i++) {
				map[i] = copy_map[i].clone();
			}
		} // while

	}// main

	static class dot {
		int y, x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}// dot

	static void bfs(int i, int j) {
		ArrayList<dot> union = new ArrayList<>();
		Queue<dot> queue = new LinkedList<dot>();
		queue.offer(new dot(i, j));
		union.add(new dot(i, j));
		// 다 연결 될때까지 sum이랑 숫자 카운트하고 리스트에 넣는다.
		int sum = 0;
		int num = 0;
		while (!queue.isEmpty()) {
			dot temp = queue.poll();
			int y = temp.y;
			int x = temp.x;
			if (visit[y][x])
				continue;
			visit[y][x] = true;
			sum += map[y][x];
			num++;
			for (int k = 0; k < 4; k++) {
				int ny = y + dy[k];
				int nx = x + dx[k];
				if (ny < 0 || ny >= n || nx < 0 || nx >= n)
					continue;
				if (visit[ny][nx])
					continue;
				int cha = Math.abs(map[ny][nx] - map[y][x]);
				if (cha >= l && cha <= r) {
					union.add(new dot(ny, nx));
					queue.offer(new dot(ny, nx));
				}
			}
		} // while
			// 큐 밖에서 리스트에 담긴 것들 전부 갱신시켜준다.
		for (int i1 = 0; i1 < union.size(); i1++) {
			dot temp = union.get(i1);
			copy_map[temp.y][temp.x] = sum / num;
		}
	}// bfs
}
