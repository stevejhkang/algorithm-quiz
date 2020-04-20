package boj;

import java.util.Scanner;

public class BOJ_11727 {
	private static long[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		dp = new long[n+1];
		
		
		for(int i=1;i<=n;i++) {
			if(i==1||i==2) {
				if(i==1)
					dp[1]= 1;
				else
					dp[2]=3;
				continue;
			}
			dp[i] = (dp[i-1]%10007+2*dp[i-2]%10007)%10007;
		}
		System.out.println(dp[n]);
	}
}
