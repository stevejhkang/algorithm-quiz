package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class SWEA_5603 {
	static int[] input;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		int T = scanner.nextInt();
		for(int tc=1;tc<=T;tc++) {
			int n = scanner.nextInt();
			input = new int[n];
			int sum=0;
			for(int i=0;i<n;i++) {
				int a= scanner.nextInt();
				input[i]= a;
				sum+=a;
			}
			int ave= sum/n;
			int ans=0;
			for (int i = 0; i < n; i++) {
				if(ave>input[i]) {
					ans+=ave-input[i];
				}
			}
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("#"+tc+" "+ans);
			System.out.println(sBuilder);
			
		}
	}

}
