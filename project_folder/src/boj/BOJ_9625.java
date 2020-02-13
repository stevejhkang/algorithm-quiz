package boj;

import java.util.Scanner;

public class BOJ_9625 {
	private static int k;
	static int ans_a,ans_b;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		recursion(1, 1, 0);
		System.out.println(ans_a+" "+ans_b);
	}
	static void recursion(int n, int a, int b) {
		if(n==k) {
			ans_a=b;
			ans_b=a+b;
			return;
		}
		int na= b;
		int nb= a+b;
		recursion(n+1,na,nb);
	}
}
