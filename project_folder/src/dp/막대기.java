package dp;

import java.util.Scanner;

public class 막대기 {
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int n = sc.nextInt();
		dp = new int[n+1];
		
		dp[0] =1;
		dp[1]= 1;
		dp[2] = 2;
	
		for(int i=3;i<=n;i++) {
			dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
		}
		
		System.out.println(dp[n]);
	}
}
