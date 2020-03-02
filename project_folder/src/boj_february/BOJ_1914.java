package boj_february;

import java.util.Scanner;

public class BOJ_1914 {
	private static int N;
	private static int cnt;
	static StringBuilder stringBuilder =new StringBuilder();
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		cnt=0;
		recursion(N, 1, 2, 3);
		
		System.out.println(cnt);
		if(N<=20) {
			System.out.println(stringBuilder);
		}
	}
	static void recursion(int n,int from, int by, int end) {
		if(n==0) {
			return;
		}
		recursion(n-1, from, end, by);
		if(N<=20)
			stringBuilder.append(from+" "+end+"\n");
		cnt++;
		recursion(n-1, by, from, end);
		
	}
}
