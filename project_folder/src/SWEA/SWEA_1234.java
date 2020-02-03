package SWEA;

import java.util.Scanner;
import java.util.Stack;

public class SWEA_1234 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		for (int tc= 1; tc <= 1; tc++) {
			int n = scanner.nextInt();
			Stack<Character> stack = new Stack<>();
			String string = scanner.next();
			for(int i=0;i<string.length()-1;i++) {
				if(stack.isEmpty()) {
					stack.push(string.charAt(i));
					continue;
				}
				else {
					
				}
			}
			System.out.println(string);
		}
	}

}
