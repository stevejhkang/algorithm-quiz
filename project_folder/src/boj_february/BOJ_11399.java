package boj_february;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11399 {
	private static int[] input;

	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		int n = scanner.nextInt();
		input=new int[n];
		for(int i=0;i<n;i++) {
			input[i]= scanner.nextInt(); 
		}
		Arrays.sort(input);
		int sum=0;
//		System.out.println(Arrays.toString(input));
		for(int i=0;i<input.length;i++) {
			sum+=input[i];
			int temp=0;
			for(int j=0;j<i;j++) {
				temp+=input[j];
			}
			sum+=temp;
			
		}
		System.out.println(sum);
	}
}
