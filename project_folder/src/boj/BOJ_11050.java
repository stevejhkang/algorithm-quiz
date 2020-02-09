package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11050 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k  = scanner.nextInt();
		int map[][] = new int[n+2][n+2];
		map[1][1]=1;
		map[2][1]=1;
		map[2][2]=1;
		for(int i=3;i<=n;i++) {
			for(int j=1;j<=i;j++) {
				if(j==1||j==i) {
					map[i][j]=1;
				}
				map[i][j]=map[i-1][j-1]+map[i-1][j];
			}
		}
//		for(int i=0;i<=n;i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		System.out.println(map[n][k+1]+map[n][k]);
	}
}
