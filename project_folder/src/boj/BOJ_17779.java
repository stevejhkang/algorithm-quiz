package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779 {
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bReader.readLine());
		map= new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bReader.readLine());
			for(int j=1;j<=n;j++) {
				map[i][j]=Integer.parseInt(stringTokenizer.nextToken());
			}
		}
	}
}