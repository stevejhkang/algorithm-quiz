package boj_february;

import java.util.Scanner;

public class BOJ_2438 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		for(int i=a;i>=1;i--) {
			for(int j=i;j>=1;j--) {
				System.out.print("*");
			}
			System.out.println("");
		}
	}
}
