package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501 {
	private static int n;
	private static int[][] input;
	private static int max;
	private static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = new int[2][n+1];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			input[0][i] = Integer.parseInt(st.nextToken());
			input[1][i] = Integer.parseInt(st.nextToken());
		}
		max=Integer.MIN_VALUE;
		sum=0;
		dfs(1,0,0);
		System.out.println(max);
	}
	static void dfs(int day,int sum, int added) {
		if(day == n+1) {
			if(max<sum)
				max=sum;
			return;
		}
		else if(day>n+1) {
			if(max<sum-added)
				max=sum-added;
			return;
		}
		for(int i=day;i<=n;i++) {
			dfs(i+input[0][i],sum+input[1][i],input[1][i]);
		}
	}
}
