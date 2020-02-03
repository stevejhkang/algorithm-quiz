package dfs_and_bfs;

import java.util.Stack;

public class Postfix_calc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "6528-*2/+";
		
	}
	public static int getOrder(String s) {//스택 안에서의 우선순위
		if(s.equals("*")||s.equals("/")) {
			return 4;
		}
		else if(s.equals("+")||s.equals("-")) {
			return 3;
		}
		else if(s.equals("(")) {
			return 2;
		}
		else if(s.equals(")")) {
			return 1;
		}
		else { //피연산자
			return 0;
		}
	}

}
