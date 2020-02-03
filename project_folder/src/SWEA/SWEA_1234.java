package SWEA;

import java.util.Scanner;
import java.util.Stack;

public class SWEA_1234 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		for(int tc=1;tc<=10;tc++) {
			int n = scanner.nextInt();
			String num = scanner.next();
			Stack<String> stk = new Stack<>();
			for(int i=0;i<num.length();i++) {
				String temp =num.substring(i,i+1);
				if(stk.isEmpty()) {
					stk.push(temp);
					continue;
				}
				if(temp.equals(stk.peek())){
					stk.pop();
				}
				else {
					stk.push(temp);
				}
			}
			StringBuilder stringBuilder = new StringBuilder();
			System.out.print("#"+tc+" ");
			while(!stk.isEmpty()) {
				stringBuilder.insert(0,stk.peek());
				stk.pop();
			}
			System.out.println(stringBuilder);

		}
	}

}
