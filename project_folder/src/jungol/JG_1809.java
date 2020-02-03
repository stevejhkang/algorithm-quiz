package jungol;

import java.util.Scanner;
import java.util.Stack;

public class JG_1809 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			int a= scanner.nextInt();
			if(i==0) {
				System.out.print(0+" ");
			}
			if(stack.isEmpty()) {
				stack.push(a);
			}
			else {
				int top = stack.peek();
				int cnt=0;
				while(top<a) {
					top=stack.pop();
					cnt++;
				}
				System.out.println(c);
			}
		}
	}
}
