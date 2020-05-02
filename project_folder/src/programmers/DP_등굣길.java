package programmers;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 30. 오전 1:58:10
 * @category DP
* @level 2
* @problem_description 물에 잠기지 않은 지역을 통해 학교를 가려고 합니다.
* 집에서 학교까지 갈 수 있는 최단경로의 개수를 출력하라.
* @solving_description 
* 
*/

public class DP_등굣길 {
	public static void main(String[] args) {
		int m = 4; int n =3;
		int[][] puddles = {{2,2}};
		Solution s = new Solution();
		System.out.println(s.solution(m, n, puddles));
	}
	static class Solution {
	    private int[][] dp;

		public int solution(int m, int n, int[][] puddles) {
	        int answer = 0;
	        int len = puddles.length;
	        dp = new int[n+1][m+1];
	        dp[1][1] =1;
	        for(int i=0;i<len;i++) {
	        	int y = puddles[i][1];
	        	int x = puddles[i][0];
	        	
	        	dp[y][x] = -1;
	        }
	        for(int i=1;i<=n;i++) {
	        	for(int j=1;j<=m;j++) {
	        		if(dp[i][j]==0) { //-1이면 물웅덩이이므로
	        			if(i-1>=1&&dp[i-1][j]!=-1) {
	        				dp[i][j]=(dp[i][j]+dp[i-1][j])%1000000007;
	        			}
	        			if(j-1>=1&&dp[i][j-1]!=-1) {
	        				dp[i][j]=(dp[i][j]+dp[i][j-1])%1000000007;
	        			}
	        		}
	        	}
	        }
	        answer=(int) ((dp[n][m])%1000000007);
	        return answer;
	    }
	}
}

