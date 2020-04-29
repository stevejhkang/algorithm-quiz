package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 29. 오전 9:52:27
 * @category @problem_description 생명력 X인 줄기세포, 처음은 비활성 X시간이 지나는 순간 활성상태됨. 활성 상태가
 *           되면 X시간 동안 살아 있을 수 있으며 X시간 지나면 죽는다. 죽은 상태로 셀 차지 활성화 된 줄기 세포 첫 1시간동안
 *           상하좌우 번식을 한다. 번식된 줄기세포는 비활성 상태이다. 이미 있는 경우 번식하지 않는다. 두개가 동시에 번식하려는
 *           경우 생명력수치가 높은 줄기 세포가 차지한다.
 * 
 * @solving_description 0은 아직 퍼지지 않은거, -1은 이미 활성화 된 후 죽은 경우 전체 그리드에서 1~10인 수를
 *                      구하면 된다.
 */
public class SWEA_5653 {
	private static int n;
	private static int m;
	private static int k;
	private static int[][] map;
	private static int[][] time;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
//		System.out.println(new Integer(16).toHexString(16));
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			k = Integer.parseInt(stringTokenizer.nextToken());

			map = new int[2 * k + n][2 * k + m]; //생명력 저장
			time = new int[2 * k + n][2 * k + m]; //들어온 시간 저장

			Queue<cell> queue = new LinkedList<cell>();

			for (int i = 0; i < n; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < m; j++) {
					int x = Integer.parseInt(stringTokenizer.nextToken());
					map[k + i][k + j] = x; //(k,k)가 시작점.
					time[k+i][k+j]=0;
					queue.add(new cell(k + i, k + j, x, x, 0, false, 0)); // y,x,생명력,활성화까지 남은 시간, 죽을 때까지 남은 시간, 활성화 여부, 시간
				}
			}
			while (!queue.isEmpty()) {
				cell now = queue.poll();
				int i = now.i;
				int j = now.j;
				int x = now.x;
				int inact_time = now.inact_time - 1; // 시작부터 -1 해준다.
				int act_time = now.act_time;
				boolean status = now.status;
				int time = now.time;

				if (map[i][j] != 0 && map[i][j] != x)
					continue; // 0이 아닌데 map에 저장된 생명력과 같지 않으면 작은 값이므로 스킵

				if (inact_time > 0) { // inact_time에서 -1 했을 때 아직 남았을 때 그냥 넣어준다.
					queue.add(new cell(i, j, x, inact_time, act_time, status, time + 1));
				} else if (inact_time <= 0 && !status) { // 비활성 대기시간이 0이고 활성화 된적이 없으면
					status = true; // 활성화 시키고 퍼뜨린다.
					for (int k = 0; k < 4; k++) { //
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (ny < 0 || ny >= 2 * k + n || nx < 0 || nx >= 2 * k + m)
							continue; // 범위 벗어나면 받아들이지 않는다.

						// 근데 이게 언제 넣엇냐가 중요한데;;; 같은시간에 넣었으면은 크기를 비교하고
						// 다른 시간에 넣었으면 이미 넣은 게 빠르므로 그냥 넘겨주면 되는데...
						if (map[ny][nx] == 0) { // 만약 방문한적이 없으면 넣어준다.
							queue.add(new cell(ny, nx, x, x, 0, false, time + 1));
							time[ny][nx] = time; //넣은 시간을 계산한다.
						} else { //이미 들어가 있으면 시간을 비교한다.
							if (time[ny][nx] < time) { //이전 시간에 들어갔으면 입력하지 않는다.
								continue;
							} else if(time[ny][nx]==time){ // 시간이 같거나 작으면 그냥 넣어준다. 그리고 map값 갱신해서 나중에 확인할 때 같을때만 퍼뜨리게 한다.
								if (map[ny][nx] < x) { //이미 들어간 것보다 크면 값 갱신하고 넣어준다.
									map[ny][nx] = x;
									queue.add(new cell(ny, nx, x, x, 0, false, time + 1));
								}
							}else {//시간이 적게 들어갔으면
								map[ny][nx]= x;
								time[ny][nx]=time;
								queue.add(new cell(ny, nx, x, x, 0, false, time+1));
							}
						}
					}
					// act_time을 작동시킨다.
					queue.add(new cell(i, j, x, 0, 1, true, time + 1));
				} else if (inact_time <= 0 && status) { // 비활성상태
					if (act_time == x) { // acttime이 지났으면 -1 처리해준다.
						map[i][j] = -1;
					} else if (act_time != x) {
						// 다시 넣어준다.
						queue.add(new cell(i, j, x, 0, act_time - 1, true, time + 1));
					}
				}

				if (time == k) {
					break;
				}
			} // while
			int cnt = 0;
			for (int i = 0; i < 2 * k + n; i++) {
				for (int j = 0; j < 2 * k + m; j++) {
					if (map[i][j] != 0 && map[i][j] != -1) {
						cnt++;
					}
				}
			}
			System.out.println(cnt);

		} // for tc
	}// main

	static class cell {
		int i, j, x, inact_time, act_time, time;
		boolean status;

		public cell(int i, int j, int x, int inact_time, int act_time, boolean status, int time) {
			super();
			this.i = i;
			this.j = j;
			this.x = x;
			this.inact_time = inact_time;
			this.act_time = act_time;
			this.status = status;
			this.time = time;
		}

	}
}
