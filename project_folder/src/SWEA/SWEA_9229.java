package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_9229 {
	static int[] arr;
	static int max;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int tc = scanner.nextInt();
		for(int t=1;t<=tc;t++) {
			int n = scanner.nextInt(); int m = scanner.nextInt(); //종류, 최대
			System.out.print("#"+t);
			arr = new int[n];
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer stringTokenizer= new StringTokenizer(bf.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i]=Integer.parseInt(stringTokenizer.nextToken());
			}
			
		}
	}
	static void combination(int n, int r) {
		if(n==r) {
			
		}
	}

}
