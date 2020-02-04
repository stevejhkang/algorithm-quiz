package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11403 {
	static int[][] map;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bReader.readLine());
		int n = Integer.parseInt(stringTokenizer.nextToken());
		map=new int[n][n];
		for (int i = 0; i < n; i++) {
			stringTokenizer= new StringTokenizer(bReader.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		
	}

}
