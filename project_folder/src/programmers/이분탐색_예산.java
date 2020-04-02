package programmers;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 27. 오후 11:43:58
 * @category 
* @problem_description 예산 심사 분배
* 총액은 미리 정해져 있어서 모든 예산 요청을 배정하기 어렵다. 그래서 정해진 총액 이하에서 가능한 최대의 총 예산을 다음과 같은 방법으로 배정합니다.
* 1. 모든 요청이 배정될 수 있는 경우에는 요청한 금액 그래도 배정합니다.
* 2. 모든 요청이 배정될 수 없는 경우에는 특정 정수 상한액을 계산하여 그 이상인 예산 요청에는 모두 상한액을 배정한다.
* 상한액 이하의 예산 요청에는 요청한 금액 그대로 배정
* 위의 조건을 모두 만족하는 상한액을 return 하도록 
* @solving_description 
*/
import java.util.*;

public class 이분탐색_예산 {
	public static void main(String[] args) {

		Solution s = new Solution();
		int[] budgets = { 120, 110, 140, 150 };
		int M = 485;
		System.out.println(s.solution(budgets, M));
	}
}
class Solution {
	static int answer = Integer.MIN_VALUE;
	static int bud;
	static boolean[] visit;
	private int min;

	public int solution(int[] budgets, int M) {

		long sum = 0;
		int max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		bud = M;

		for (int i = 0; i < budgets.length; i++) {
			sum += budgets[i];
			max = Math.max(max, budgets[i]);
			min = Math.min(min, budgets[i]);
		}

		visit = new boolean[max];
		if (M >= sum) { // 예산 분배 가능하면 max값 출력
			return max;
		}

		else {
			int mid = (0 + max) / 2;
			binarysearch(budgets, 0, mid, max);
			if (answer == Integer.MIN_VALUE) {
				return 0;
			} else {
				return answer;
			}

		}
	}

	public void binarysearch(int[] budgets, int start, int upper_limit, int end) {
		long sum = 0;
		for (int i = 0; i < budgets.length; i++) {
			if (upper_limit >= budgets[i]) {
				sum += budgets[i];
			} else {
				sum += upper_limit;
			}
		}
		if (sum > bud) { // 예산을 넘으면 줄인다.
			if (!visit[upper_limit]) {
				visit[upper_limit] = true;
				int mid = (start + upper_limit) / 2;
				binarysearch(budgets, start, mid, upper_limit);
			}
		} else { // 예산을 안벗어나면 최신화시키고 더 높게 측정해본다.
			if (!visit[upper_limit]) {
				visit[upper_limit] = true;
				answer = Math.max(answer, upper_limit);
				int mid = (upper_limit + end) / 2;
				binarysearch(budgets, upper_limit, mid, end);
			}

		}
	}
}