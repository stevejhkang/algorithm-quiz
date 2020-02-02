package SWEA;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA_1966 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for(int tc=1;tc<=t;tc++) {
			int n = scanner.nextInt();
			int [] input =new int[n];
			for (int i = 0; i < n; i++) {
				input[i]=scanner.nextInt();
			}
			Arrays.sort(input);
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("#"+tc+" ");
			for (int i = 0; i < n; i++) {
				stringBuilder.append(input[i]+ " ");
			}
			System.out.println(stringBuilder);
		}
		
	}
}
