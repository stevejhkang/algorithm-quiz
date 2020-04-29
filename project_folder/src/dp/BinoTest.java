package dp;

public class BinoTest {
	static int binoDP(int n , int r) {
		int[][] dp = new int[n+1][n+1];
		dp[0][0]=1;
		
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=Math.min(i,r);j++) { 
				//r이상으로 가면 안되고, 그리고 i이상으로 만들어도 nCi를 만들기위해 n-1Ci+n-1Ci-1이어서 필요없음
				if(j==0||j==i) {
					dp[i][j] = 1;
				}else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
		}
		return dp[n][r];
	}
	public static void main(String[] args) {
		System.out.println("DP: "+binoDP(10, 3) );
	}
}
