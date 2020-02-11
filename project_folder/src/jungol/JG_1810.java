package jungol;

import java.util.Scanner;

public class JG_1810 {
	static int[] input;
	static int[] ans;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		input= new int[9];
		ans = new int[7];
		for(int i=0;i<9;i++) {
			input[i]= scanner.nextInt(); 
		}
//		System.out.println("");
		com(0, 0, 0);
	}
	static void com(int k,int sum,int idx) {
		if(k==7) {
			if(sum==100) {
				for(int i=0;i<7;i++) {
					System.out.println(ans[i]);
				}
			}
			return;
		}
		for(int i=idx;i<9;i++) {
			sum+=input[i];
			ans[k]=input[i];
			com(k+1, sum, i+1);
			sum-=input[i];
		}
	}
}
