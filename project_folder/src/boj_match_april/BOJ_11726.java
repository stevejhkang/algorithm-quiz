package boj_match_april;

import java.util.Scanner;

public class BOJ_11726 {
	private static long[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		dp = new long[n+1];
		
		
		for(int i=1;i<=n;i++) {
			if(i==1||i==2) {
				dp[i]=i; continue;
			}
			dp[i] = (dp[i-1]%10007+dp[i-2]%10007)%10007;
		}
		System.out.println(dp[n]);
	}
}
