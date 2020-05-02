package programmers;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 29. 오후 11:09:38
 * @category 
* @problem_description 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰
* 경우를 찾아보려고 합니다. 
* @solving_description 내려오는 규칙을 잘 찾으면 된다. 그리고 나서 마지막에 맥스값을 찾아주면됨.
*/

public class DP_정수삼각형 {
	public static void main(String[] args) {
		int[][] triangle= {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
		Solution s = new Solution();
		System.out.println(s.solution(triangle));
	}
	static class Solution {
	    private int[][] dp;

		public int solution(int[][] triangle) {
	        int answer = 0;
	        
	        int n = triangle.length;
	        dp = new int[n][n];
	        
	        
	        for(int h=0;h<n;h++) {
	        	for(int i=0;i<=h;i++) {
	        		if(h==0) {
	                	dp[h][i]=triangle[h][i];
	                	continue;
	                }
	        		else if(h==1) {
	        			dp[h][i]=dp[h-1][0]+triangle[h][i];
	        			continue;
	        		}
	        		if(i==0) {
	        			dp[h][i] = dp[h-1][i]+triangle[h][i];
	        			continue;
	        		}
	        		else if(i==h) {
	        			dp[h][i]=dp[h-1][i-1]+triangle[h][i];
	        			continue;
	        		}
	        		else {
	        			dp[h][i] = Math.max(dp[h-1][i-1],dp[h-1][i])+triangle[h][i];
	        			continue;
	        		}
	        	}
	        }
	        int max = Integer.MIN_VALUE;
	        for(int i=0;i<n;i++) {
	        	max=Math.max(dp[n-1][i], max);
	        }
	        answer = max;
	        return answer;
	    }
	}
}
