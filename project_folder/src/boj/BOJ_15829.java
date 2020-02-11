package boj;

import java.util.Scanner;

public class BOJ_15829 {
//	static int[] ans;
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		int n = scanner.nextInt();
		String s = scanner.next();
//		ans = new int[n];
		double sum=0;
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			int a = c-'a'+1;
//			ans[i]=a;
			sum+=(Math.pow(31, i))*a%1234567891;
//			System.out.println(a);
		}
		System.out.println((int)sum);
		
	}
}
