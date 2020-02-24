package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2309 {
	private static int[] input;
	private static ArrayList<Integer> real_hobit;

	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		input = new int[9];
		for(int i=0;i<9;i++) {
			input[i] = scanner.nextInt();
		}
		Arrays.sort(input);
//		System.out.println(Arrays.toString(input));
		real_hobit = new ArrayList<Integer>();
		com(0,0,0);
	}
	static void com(int k,int index,int sum) {
		if(k==7) {
			if(sum==100) {
				for(int i=0;i<real_hobit.size();i++) {
					System.out.println(real_hobit.get(i));
				}
				System.exit(0);
			}
			
			return ;
		}
		for(int i=index;i<input.length;i++) {
			real_hobit.add(input[i]);
			com(k+1, i+1, sum+input[i]);
			real_hobit.remove(k);
		}
		
	}
}
