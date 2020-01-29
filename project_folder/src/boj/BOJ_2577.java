package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ_2577 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		int sum=1;
		for(int i=0;i<3;i++) {
			sum*=scanner.nextInt();
		}
		String string_sum=Integer.toString(sum);
		int[] arr = new int[10];
		for (int i = 0; i < string_sum.length(); i++) {
			int a = (string_sum.charAt(i)-'0');
			arr[a]++;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
