package study;

import java.util.Scanner;

public class BOJ_3474 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner  scanner = new Scanner(System.in);
		int tcase = scanner.nextInt();
		for(int t=0;t<tcase;t++) {
			int jari=0;
			int n = Integer.parseInt(scanner.next());

			while(n>0) {
				jari+=n/5;
				n/=5;
			}
			System.out.println(jari);
		}
	}
}
