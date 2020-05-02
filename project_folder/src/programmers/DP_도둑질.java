package programmers;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 30. 오전 11:58:33
 * @category 
* @level 3
* @problem_description 두집을 연속으로 털면 안된다.
* @solving_description 
* 두집을 연속으로 털면 안되므로 2차원 배열을 만들어서 전단계에 훔쳤는지 안 훔쳤는지를 저장할 필요가 있었고
* 처음 집이 털렸을 때 마지막 집을 털면 안되므로 이를 나눠서 두번 계산을 했다.
*/

public class DP_도둑질 {
	public static void main(String[] args) {
		int[] money = {1,2,3,1};
		
		Solution s = new Solution();
		System.out.println(s.solution(money));
	}
	static class Solution {
	    private int[][] dp;

		public int solution(int[] money) {
	        int answer = 0;
	        int n = money.length;
	        
	        
	        dp = new int[2][n+1];
	        
	        
	        //애초에 시작을 처음 집을 포함을 안 시키고 계산하고 둘다 확인하고
	        //애초에 포함을 안시키면 dp[0][2]부터 시작하면 된다.
	        for(int i=2;i<=n;i++) { //0이 털고 1이 안털고
	        	dp[0][i]= dp[1][i-1]+money[i-1]; 
	        	dp[1][i] = Math.max(dp[0][i-1], dp[1][i-1]);
	        }
	        answer = Math.max(dp[0][n],dp[1][n]);
	        dp = new int[2][n+1];
	        //처음 집을 포함시키고 마지막을 포함 안시킨것을 확인하면 될듯?
	        
	        //첫번재 집을 털었을 때
	        for(int i=1;i<=n;i++) { //0이 털고 1이 안털고
	        	dp[0][i]= dp[1][i-1]+money[i-1]; 
	        	dp[1][i] = Math.max(dp[0][i-1], dp[1][i-1]);
	        }
	        answer = Math.max(answer,dp[1][n]);
	       
	        
	        return answer;
	    }
	}
}
