package boj_february;

import java.util.Scanner;

//규칙이 있다

public class BOJ_17466 {
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		long n = scanner.nextInt(); long p = scanner.nextInt();
		long remain=1;
		for(long i=1;i<=n;i++) {
			remain=(remain*i)%p;
		}
		System.out.println(remain);
	}
}
