package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1780 {
	private static int n;
	private static int[][] map;
	private static int[] count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]= Integer.parseInt(stringTokenizer.nextToken()); 
			}
		}
		
		int first=map[0][0];
		count = new int[3];
		boolean flag= true;
//		for(int q=0;q<3;q++) {
//			for(int w=0;w<3;w++) {
//				divide(divide*q,divide*w,divide);
//			}
//		}
		out:for(int i=0;i<n;i++) {
			for(int j=1;j<n;j++) {
				if(first!=map[i][j]) {
					//분할정복 실시
					flag= false;
					int div = n/3;
					for(int q=0;q<3;q++) {
						for(int w=0;w<3;w++) {
							divide(div*q,div*w,div);
						}
					}
					break out;
				}
			}
		}
		if(flag==true) {
			check(0, 0);
		}
		
//		System.out.println(Arrays.toString(count));
		for(int i=0;i<3;i++) {
			System.out.println(count[i]);
		}
	}//main
	static void divide(int y, int x, int div) {
		if(div==1) {
			check(y, x);
			return;
		}
		int first=map[y][x];
		boolean flag= true;
		out: for(int i=y;i<y+div;i++) {
			for(int j=x;j<x+div;j++) {
				//쭉 검색하는데 다른게 발생하면 divide!
				if(first!=map[i][j]) {
					//분할정복 실시
					flag= false;
					int new_div = div/3;
					for(int q=0;q<3;q++) {
						for(int w=0;w<3;w++) {
							//y가 6 x=0
							divide(y+new_div*q,x+new_div*w,new_div);
						}
					}
					break out;
				}
			}
		}
		if(flag) {
			check(y, x);
		}
	}//divide
	static void check(int y,int x) {
		if(map[y][x]==-1) {
			count[0]++;
		}
		else if(map[y][x]==0 ) {
			count[1]++;
		}
		else {
			count[2]++;
		}
	}
}
