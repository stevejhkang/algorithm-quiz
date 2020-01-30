package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_1220 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		
		for( int tc= 1;tc<=10;tc++) {
			int ans=0;
			StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
			int n= Integer.parseInt(stringTokenizer.nextToken());
			int input[][] = new int[n][n];
			for(int i=0;i<n;i++) {
				stringTokenizer = new StringTokenizer(bReader.readLine());
				for(int j=0;j<n;j++) {
					input[i][j]=Integer.parseInt(stringTokenizer.nextToken());
				}
			}	
			for (int i = 0; i < input.length; i++) {
				for (int j = 0; j < input.length; j++) {
					if(input[i][j]==1) {//아래로
						int move=i;
						do {
							move++;
							if(move>=n)
								break;
							if(input[move][j]==1) {
								break;
							}
							else if(input[move][j]==2) {
								ans++;
								break;
							}
						}while(move<n);
					
					}
				}
			}
			StringBuilder sBuilder =new StringBuilder();
			sBuilder.append("#"+tc+" "+ans);
			System.out.println(sBuilder);
		}
	}

}
