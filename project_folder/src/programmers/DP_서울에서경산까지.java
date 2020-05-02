package programmers;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 30. 오전 2:15:31
 * @category DP
 * @level 4
 * @problem_description 서울에서 출발해 다른 도시를 정해진 순서대로 딱 한번 방문한 후 경산으로 도착할 예정 K시간 이내로
 *                      여행할때 모을 수 있는 최대 모금액을 알아보려한다.
 * @solving_description dp[직전구간][이동시간] for문으로 0부터 K값까지 순회하면서 dp[직전구간][이동시간]!=0
 *                      일때, 그 값에 다음 구간 이동 시간을 더한다. 만약 다음 루트 이동시간이 K보다 크다면 이동하지
 *                      않는다. 그리고 기존에 저장된 값과 갱신될 값을 비교해서 돈이 최대가 되도록 한다.
 * 
 */

public class DP_서울에서경산까지 {
	public static void main(String[] args) {
		int K = 1650;
//		int[][] travel = { { 500, 200, 200, 100 }, { 800, 370, 300, 120 }, { 700, 250, 300, 90 } };
		int[][] travel = { { 1000, 2000, 300, 700 }, { 1100, 1900, 400, 900 }, { 900, 1800, 400, 700 },
				{ 1200, 2300, 500, 1200 } };
		// [0]도보시간[1]도보모금액[2]자전거이동시간[3]자전거모금액

		Solution s = new Solution();
		System.out.println(s.solution(K, travel));
	}
	static class Solution {
		private int[][] dp;

		public int solution(int K, int[][] travel) {
			int answer = 0;

			int n = travel.length;

			dp = new int[n + 1][K + 1]; //

			int max = Integer.MIN_VALUE;

			for (int i = 1; i <= n; i++) {
				// i번째에서 자전거를 탈지 도보를 할지 정하게 된다. 따로 저장하는게 아니라
				// 쭉 전단계를 쭉탐색하면서 현재 시간+전단계(값이 0이 아님)까지 걸린 시간 합이 K가 넘지 않도록 한다.
				// 그 안에는 액수를 저장하여 갱신한다. 그 안에서 최댓값을 찾아야 한다.
				for (int time = 0; time < K; time++) {
					if (i == 1) { // 1일땐 그냥 넣어주고 끝낸다.
						if (travel[0][0] <= K) {
							dp[1][travel[0][0]] = travel[0][1];

						}
						if (travel[0][2] <= K) {
							dp[1][travel[0][2]] = travel[0][3];
						}
						break;
					}
					if (dp[i - 1][time] != 0) { // 전단계를 무조건 거쳐야 하므로 0아닌 것만!
						// 도보
						int walking_time = travel[i - 1][0];
						int walking_money = travel[i - 1][1];
						if (time + walking_time <= K) { // 시간을 넘지 않으면
							// 이미 있는 것과 비교한다.
							dp[i][time + walking_time] = Math.max(dp[i][time + walking_time],dp[i - 1][time] + walking_money);
							max = Math.max(max, dp[n][time]);
						}
						// 자전거
						int cycle_time = travel[i - 1][2];
						int cycle_money = travel[i - 1][3];
						if (time + cycle_time <= K) { // 시간을 넘지 않으면 현재 저장된 값과 (이전단계 값과 현재를 합친 값을 비
							dp[i][time + cycle_time] = Math.max(dp[i][time + cycle_time], dp[i - 1][time] + cycle_money);
							// 이미 있는 것과 비교한다.
							max = Math.max(max, dp[n][time]);
						}
					}
				}
			}
			for (int time = 0; time <= K; time++) {
				if (dp[n][time] != 0) {
//					System.out.println(dp[n][time]);
					max = Math.max(max, dp[n][time]);
				}
			}
			answer = max;

			return answer;
		}

		static class move {
			int time, money;

			public move(int time, int money) {
				super();
				this.time = time;
				this.money = money;
			}

		}
	}
}

