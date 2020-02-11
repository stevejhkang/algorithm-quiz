package boj;

import java.util.Scanner;

public class BOJ_16637 {
	private static char[] char_list;
	private static int max;
	private static int n;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		char_list= new char[n];
		String string = scanner.next();
		char_list = string.toCharArray();
		max= Integer.MIN_VALUE;
		
		dfs(0, n-1);
		
		System.out.println(max);
	}
	static int dfs(int start, int end) {
		if(start==end) {
//			System.out.println(Character.getNumericValue(char_list[start]));
			return Character.getNumericValue(char_list[start]);
		}
		int a= 0; int b=0;
		Character oper=';';
		int result=0;
		for(int i=start;i<=end-2;i+=2) {
			a =dfs(start, i);
			oper = char_list[i+1];
			b= dfs(i+2,end);
			
//			System.out.println(a+Character.toString(oper)+b);
			if(oper=='+') {
				result= a+b;
			}
			else if(oper=='-') {
				result= a-b;
			}
			else {
				result= a*b;
			}
			if(max<result) {
				max=result;
			}
		}
		
//		System.out.println(result);
		return result;
	}
}
