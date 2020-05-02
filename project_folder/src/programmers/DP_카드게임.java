package programmers;
  
public class DP_카드게임 {
	public static void main(String[] args) {
		int[] left = {3,2,5};
		int[] right = {2,4,1};
		
		Solution s = new Solution();
		System.out.println(s.solution(left, right));
	}
	static class Solution {
	    private int n;
		private int[][] dp;

		public int solution(int[] left, int[] right) {
	        int answer = 0;
	        
	        n= left.length;
	        dp = new int[n+1][n+1];
	        
	        
	        for(int i=n-1;i>=0;i--) { //i가 오른쪽 j가 왼쪽
	        	for(int j=n-1;j>=0;j--) {
	        		
	        		//현재에 집중을 하자
	        		//왼쪽 버리는거랑 둘다 버리는 거는 자유
	        		dp[i][j] = Math.max(dp[i][j+1],dp[i+1][j+1]);
	        		
	        		if(left[j]>right[i]) { //올라가는거
	        			dp[i][j]= Math.max(dp[i][j],dp[i+1][j]+right[i]);
	        		}
	        		
	        	}
	        }
	        
	        
	        answer = dp[0][0];
	        return answer;
	    }
		
	}
}
