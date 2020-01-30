package boj;

import java.util.Scanner;

/**
 * @author junhukang
 * @time 2020. 1. 30. 오후 10:21:35
 * @category 재귀
* @problem_description
* @solving_description 
*/
public class BOJ_10870 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		int n = scanner.nextInt();
		System.out.println(fibonacci(n));
	}
	static int fibonacci(int n) {
		if(n==0) {
			return 0;
		}
		else if(n==1) {
			return 1;
		}
		return fibonacci(n-1)+fibonacci(n-2);
	}
}
