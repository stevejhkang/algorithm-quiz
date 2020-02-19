	package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17779_2 {

	private static int n;
	private static int[][] map;
	private static int[] city;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub			
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bReader.readLine());
		map= new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bReader.readLine());
			for(int j=1;j<=n;j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j]=a;
			}
		}//for i
		city=new int[6];
		//1번부터 1,1에서 BFS한다.
	}//main

}
