package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 3. 오전 1:08:35
 * @category BFS + 시뮬레이션
 * @level 5
 * @problem_description K시간 후 살아 있는 줄기세포(비활성 상태+ 활성상태)의 총 개수를 구하는 프로그램 1. 세로N,
 *                      가로M <=50 2. k<=300 300이므로 3. 공간이 무한해서 가장자리에 닿을 일은 없다. 4.
 *                      줄기 세포의 생명력은 life <=10이다.
 * @solving_description 1. 생명력이 1인 세포가 번식을 하는데 필요한 시간은 2시간이므로 k시간에서 최대로 뻗어나갈 수
 *                      있는 길이는 k/2가 된다.
 * 
 *                      ** 내가 어려워 했던 것이 생명력 순으로 넣어주기 위해서 어떻게 처리해야될까 문제 ** 활성화
 *                      대기시간을 +1해줘서 생명력과 똑같을 때 퍼뜨려야 하나? 아니면 다음 턴에서 퍼뜨려야 하나.
 * 
 *                      1. 현재 상태를 가지고 있는 2차원 배열과 다음 시간의 상태를 저장할 2차원 배열 2개를
 *                      사용해야한다, 만약 한 개의 2차원 배열을 사용한다면 같은 시간에 생성된 세포의 상태 변화가 될 수
 *                      있기 때문이다. k시간에 존재하고 있던 세포들만 상태가 변경된다.
 * 
 *                      배열의 원소 업데이트 1. 죽은 세포일 경우, 현재 상태를 그대로 다음 턴에 넘겨줍니다. 2. 비활성
 *                      상태 세포일 경우, 다음 턴에 HP증가시켜주고 만약 비활성 시간과 생명력 시간이 같아진다면 다음 턴을
 *                      활성 상태로 만들어 준다. (결국 다음턴으로 넘겨주네)
 * 
 *                      3-1 활성 상태가 된 후 첫번째 시간인 세포일때는 두가지 경우가 있을 수 있따. 1) 번식하는
 *                      위치에 현재 2차원 배열에는 비어있지만 다음 턴의 2차원 배열에는 비어있지 않을 경우 2) 다른
 *                      세포가 먼저 번식을 한 경우입니다. 생명력이 큰 세포를 다음턴에 저장해준다. 3-2 줄기 세포가 활성
 *                      상태이지만 첫 시간이 아닌 경우에는 hp를 감소시켜주고 hp가 0이 되었을 때는 죽은 상태로
 *                      변경해줍니다.
 * 
 *                      상태 별 동작이 완료되면, 다음 상태로 저장해둔 2차원 배열을 현재 상태로 바꿉니다. K시간 동안 이
 *                      동작을 반복해줍니다.
 * 
 *                      세포 자료구조 status(0: 빈공간, 1: 비활성, 2: 활성, 3: 죽은 상태) LP: life
 *                      point HP: 죽은 상태 0, 비활성 상태일 경우 증가, 활성 상태일 경우 감소
 * 
 */

public class SWEA_5653_4 {
	static int n, m, k;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static final int MAXL=352;
	private static cell[][][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder();
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			k = Integer.parseInt(stringTokenizer.nextToken());
			
			map = new cell[2][MAXL][MAXL];

			for (int i = 0; i < n + k + 2; i++) {
				for (int j = 0; j < m + k + 2; j++) {
					map[0][i][j] = new cell();
					map[1][i][j] = new cell();
					map[0][i][j].status=0;
					map[1][i][j].status=0;
				}
			}
			
			//초기값 입력
			for (int i = 0 + k / 2 + 1; i < n + k / 2 + 1; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0 + k / 2 + 1; j < m + k / 2 + 1; j++) {
					int life = Integer.parseInt(stringTokenizer.nextToken());
					if (life != 0) {
						map[0][i][j].status = 1; //비활성화
						map[0][i][j].lp = life; //생명력 설정
						map[0][i][j].hp = 0; //대기시간 설정
					}
				}
			}

			// 최대 범위 N,M
			int N = n + k + 2;
			int M = m + k + 2;

			
			int curMap = 0;
			//k시간 만큼 돌린다.
			for (int time = 0; time < k; time++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						
						if (map[curMap][i][j].status == 3) { // 죽은 세포일 경우 그냥 죽은상태만 넘기고 끝낸다.
							map[1 - curMap][i][j].status = map[curMap][i][j].status;
							continue;
						} //status 3
						else if (map[curMap][i][j].status == 1) { //비활성 상태인 경우
							// 비활성 시간 증가
							map[1 - curMap][i][j].hp = map[curMap][i][j].hp + 1;
							map[1 - curMap][i][j].lp = map[curMap][i][j].lp;
							
							// 비활성 시간이 생명력과 같아졌을 경우 
							if (map[1 - curMap][i][j].hp == map[1 - curMap][i][j].lp) {
								// 바로 활성화 된다고 했으므로
								map[1 - curMap][i][j].status = 2;
							} 
							else {
								map[1 - curMap][i][j].status = 1;
							}
						} // status 1
						else if (map[curMap][i][j].status == 2) {
							// 활성화 상태이고 딱 처음 활성화 단계이면 퍼뜨린다.
							if (map[curMap][i][j].hp == map[curMap][i][j].lp) {
								
								for (int dir = 0; dir < 4; dir++) {
									int ni = i + dy[dir];
									int nj = j + dx[dir];

									// 현재 비어있는 경우
									if (map[curMap][ni][nj].status == 0) {
										// 다음도 비어 있는 경우 그냥 넣는다.
										if (map[1 - curMap][ni][nj].status == 0) {
											map[1 - curMap][ni][nj].status = 1;
											map[1 - curMap][ni][nj].lp = map[curMap][ni][nj].lp;
											map[1 - curMap][ni][nj].hp = 0;
										}
										// 비어있지 않고 넣으려는게 더 크면 갱신한다.
										else if (map[1 - curMap][ni][nj].status == 1
												&& map[1 - curMap][ni][nj].lp < map[curMap][ni][nj].lp) {
											map[1 - curMap][ni][nj].lp = map[curMap][ni][nj].lp;
										}

									}
								}
							}
							
							//살아있는 시간 1감소
							map[1 - curMap][i][j].hp = map[curMap][i][j].hp - 1;
							// 활성 상태 시간이 lp시간 만큼 지났을 경우
							if (map[1 - curMap][i][j].hp == 0) {
								map[1 - curMap][i][j].status = 3;
							} else {
								map[1 - curMap][i][j].status = 2;
							}
						} // status 2

					} // for m
				} // for n
				curMap = 1 - curMap;
			} // for time
			int answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[curMap][i][j].status == 1 || map[curMap][i][j].status == 2) {
						answer++;
					}
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

	static class cell {
		int status, lp, hp;

	
	}// cell
}
