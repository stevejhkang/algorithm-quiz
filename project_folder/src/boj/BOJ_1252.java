package boj;

import java.util.Scanner;

public class BOJ_1252 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int a = Integer.parseInt(scanner.next(),2);
		int b = Integer.parseInt(scanner.next(),2);
		int result = a+b;
		System.out.println(Integer.toBinaryString(result));
	}

}
