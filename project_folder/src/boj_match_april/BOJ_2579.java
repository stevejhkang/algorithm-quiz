package boj_match_april;

import java.util.Scanner;

public class BOJ_2579 {
	private static int[] point;
	private static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		point = new int[n+1];
		
		for(int i=1;i<=n;i++) {
			point[i] = sc.nextInt();
		}
		dp = new int[n+1];
		
		for(int i=0;i<=n;i++) {
			if(i==0) {
				dp[i]=0;
			}
			else if(i==1) {
				dp[1] = point[1];
			}
			else if(i==2) {
				dp[2] = dp[1] + point[2];
			}
			else {
				dp[i] = Math.max(dp[i-2],dp[i-3]+point[i-1])+point[i];
			}
		}
		System.out.println(dp[n]);
	}
}
