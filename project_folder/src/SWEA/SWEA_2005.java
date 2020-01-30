package SWEA;

import java.util.Scanner;

public class SWEA_2005 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(System.in);
		int T = scanner.nextInt();
		for(int tc=1;tc<=T;tc++) {
			int n =scanner.nextInt();
			int[][] output=new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<=i;j++) {
					if(j==0) {
						output[i][j]=1;
					}
					else if(i==j) {
						output[i][j]=1;
					}
					else {
						output[i][j]=output[i-1][j-1]+output[i-1][j];
					}
				}
			}
			System.out.print("#"+tc+"\n");
			for(int i=0;i<n;i++) {
				for(int j=0;j<=i;j++) {
					System.out.print(output[i][j]+" ");
				}
				System.out.println("");
			}
		}
	}

}
