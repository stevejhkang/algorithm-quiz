package boj_february;

import java.util.Scanner;

/**
 * @author junhukang
 * @time 2020. 1. 30. 오후 11:27:33
 * @category 
* @problem_description 
* @solving_description 
*/
public class BOJ_2798 {
	static int n,m;
	static int[] input ;
	static int max;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt(); m = scanner.nextInt();
		input=new int[n];
		for (int i = 0; i < n; i++) {
			input[i]=scanner.nextInt();
		}
		max=0;
		recursion(0, 0, 0);
		System.out.println(max);
	}
	static void recursion(int r,int sum,int idx) {
		if(r==3) {
			if(sum<=m&&max<sum) {
				max=sum;
			}
			return;
		}
		for (int i = idx; i < n; i++) {
			sum+=input[i];
			recursion(r+1, sum, i+1);
			sum-=input[i];
		}
	}
}
