package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404 {
	private static int n;
	private static int m;
	private static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(bufferedReader.readLine());
		m = Integer.parseInt(bufferedReader.readLine());
		
		dp = new int[n+1][n+1];
		
		int inf = 987654321;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i==j) continue;
				
				dp[i][j]= inf;
				
			}
		}
		StringTokenizer stringTokenizer;
		for(int i=0;i<m;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			
			System.out.println(from+","+to+","+weight);
			if(dp[from][to]>weight)
				dp[from][to] = weight;
		}
		
	
//		
//		for(int i=0;i<=n;i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		
		
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					
					if(dp[i][j]>dp[i][k]+dp[k][j]) {
						dp[i][j] = dp[i][k]+dp[k][j];
					}
				}
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(dp[i][j]==inf) {
					System.out.print(0+" ");
				}
				else {
					System.out.print(dp[i][j]+" ");
				}
				
			}
			System.out.println();
		}
	}//main
	
	
}
