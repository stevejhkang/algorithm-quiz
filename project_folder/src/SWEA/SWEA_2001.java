package SWEA;

import java.util.Scanner;

public class SWEA_2001 {
	static int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int T= scanner.nextInt();
		for(int tc=1;tc<=T;tc++) {
			n = scanner.nextInt(); int m = scanner.nextInt();
			int[][] input = new int[n][n];
			for (int i = 0; i < input.length; i++) {
				for (int j = 0; j < input.length; j++) {
					input[i][j]=scanner.nextInt();
				}
			}
			int max=0;
			for (int i = 0; i < input.length; i++) {
				for (int j = 0; j < input.length; j++) {
					int temp_sum=0;
					for (int j2 = i; j2 <i+m; j2++) {
						for (int k = j; k < j+m; k++) {
							if(canMove(j2, k)) {
								temp_sum+=input[j2][k];
							}
						}
					}
					if(max<temp_sum) {
						max=temp_sum;
					}
				}
			}
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("#"+tc+" ").append(max);
			System.out.println(sBuilder);
		}
	}
	static boolean canMove(int y,int x) {
		return (x>=0 && x<n &&y>=0 &&y<n);
	}
}
