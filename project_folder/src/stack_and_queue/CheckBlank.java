package stack_and_queue;

import java.util.Scanner;
import java.util.Stack;

import org.junit.jupiter.api.Nested;

public class CheckBlank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		String string = scanner.next();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < string.length(); i++) {
			if(string.charAt(i)=='(') {
				stack.push(string.charAt(i));
			}
			else if(string.charAt(i)==')') {
				if(stack.empty()) {
					System.out.println("not correct2");
					return;
				}
				if(stack.peek()!='(') {
					System.out.println("not correct2");
					return;
				}
				else if(stack.peek()=='(') {
					stack.pop();
				}
			}
			
		}
		if(!stack.isEmpty()) {
			System.out.println("not correct2");
			return;
		}
		else {
			System.out.println("correct");
			return;
		}
	}

}
