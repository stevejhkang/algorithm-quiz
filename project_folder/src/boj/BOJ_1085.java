package boj;

import java.util.Scanner;

public class BOJ_1085 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt(); int y = scanner.nextInt();
		int w = scanner.nextInt(); int h =scanner.nextInt();
		int min=Integer.MAX_VALUE;
		int num1=Math.abs(x-w);
		if(min>num1) {
			min=num1;
		}
		int num2=Math.abs(x-0);
		if(min>num2) {
			min=num2;
		}
		int num3= Math.abs(y-0);
		if(min>num3) {
			min=num3;
		}
		int num4 = Math.abs(y-h);
		if(min>num4)
			min=num4;
		System.out.println(min);
	}
}
