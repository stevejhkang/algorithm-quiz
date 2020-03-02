package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2630 {
	private static int n;
	private static boolean[][] map;
	private static int[] cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		map = new boolean[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j =0;j<n;j++) {
				int a= Integer.parseInt(stringTokenizer.nextToken());
				if(a==1) {
					map[i][j]= true; 
				}
			}
		}
		cnt = new int[2];
		divide(0,0,n);
		System.out.println(cnt[0]+"\n"+cnt[1]);
		
	}
	static void divide(int y, int x, int div) {
		if(div==1) {
			if(map[y][x]) {
				cnt[1]++; //파랑색
			}
			else {
				cnt[0]++;
			}
			return;
		}
//		System.out.println(y+","+x);
		boolean first = map[y][x];
		boolean check =true;
		out: for(int i=y;i<y+div;i++) {
			for(int j= x;j<x+div;j++) {
				if(first!=map[i][j]) {
					check = false;
					int new_div = div/2;
					for(int q=0;q<2;q++) {
						for(int w=0;w<2;w++) {
							divide(y+new_div*q, x+new_div*w, new_div);
						}
					}
					break out;
				}
			}
		}//out
		if(check) {
			if(first) {
				cnt[1]++; //파랑색
			}
			else {
				cnt[0]++;
			}
		}
	}
}
