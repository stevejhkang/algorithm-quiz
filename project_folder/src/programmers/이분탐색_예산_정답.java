package programmers;

public class 이분탐색_예산_정답 {
	public static void main(String[] args) {

		Solution s = new Solution();
		int[] budgets = { 120, 110, 140, 150 };
		int M = 485;
		System.out.println(s.solution(budgets, M));
	}
	static class Solution {
		static int answer = Integer.MIN_VALUE;
		static int bud;
		static boolean[] visit;
		private int min;

		public int solution(int[] budgets, int M) {
			
			int answer=0;
			int left =0;
			int right =0;
			
			for(int budget : budgets) {
				if(budget>right) {
					right=budget;
					//answer의 최댓값을 budget의 요소의 최댓값
				}
			}
			
			int middle = 0;
			while(left<=right) {
				long sum=0;
				middle  = (left+right)/2;
				
				for(int budget: budgets) {
					if(budget>=middle) {
						sum+=middle;
					}
					else {
						sum+=budget;
					}
				}
				
				if(sum>M) {
					right=middle-1;
				}
				else {
					answer=middle;
					left=middle+1;
				}
			}
			return answer;
		}

		
	}
}
