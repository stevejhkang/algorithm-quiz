package jungol;

import java.util.Scanner;
import java.util.Stack;

public class JG_1809 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> indexstack = new Stack<>();
		
		for(int i=1;i<=n;i++) {
			int a= scanner.nextInt();
			if(i==1) {
				stack.push(a);
				indexstack.push(1);
				sb.append("0 ");
				continue;
			}
			while(!stack.isEmpty()) {
				if(a<stack.peek()) {
					sb.append(indexstack.peek()+" ");
					break;
				}
				stack.pop();
				indexstack.pop();
			}
			if(stack.isEmpty()) {
				sb.append("0 ");
			}
			stack.push(a);
			indexstack.push(i);
		}
		System.out.println(sb);
	}
}
