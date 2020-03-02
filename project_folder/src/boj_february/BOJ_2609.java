package boj_february;

import java.util.Scanner;

public class BOJ_2609 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt(); int b = scanner.nextInt();
		int g= recursion(a,b);
		System.out.println(g);
		System.out.println(g*(a/g)*(b/g));
	}
	static int recursion(int a, int b) {
		if(a%b==0) {
			return b;
		}
		return recursion(b, a%b);
	}
}
