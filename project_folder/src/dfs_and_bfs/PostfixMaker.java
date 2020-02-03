package dfs_and_bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class PostfixMaker {
	static String string="2+(3*4)/5";
	public static void main(String[] args) {
		
		Stack<String> stack = new Stack<>();
		System.out.println(getPostfixNotation());
		List<String> list = getPostfixNotation();
		System.out.println(calc(list));
	}
	public static List<String> getPostfixNotation(){
		//출력된 결과를 가질 리스트
		List<String> postfixNotation = new ArrayList<String>();
		//연산자의 정보를 관리할  stack
		Stack<String> stack = new Stack<>();
		//문자열 파싱 후 처리
		for(int i=0;i<string.length();i++) {
			String s = string.substring(i,i+1);
			int num= getOrder(s);
			if(num==0) { //숫자
				postfixNotation.add(s);
			}
			else if(num==2) {  //여는 괄호
				stack.push(s);
			}
			else if(num==1) { //닫는 괄호일때는 여는 괄호가 나올 때까지 팝
				while(true) {
					String top = stack.pop();
					if(top.equals("(")) { 
						break;
					}
					else {
						postfixNotation.add(top);
					}
				}
			}else { //3번이랑 4번이랑 원리가 같다
				//현재 s이 스택의 탑에 있는 우선순위보다 무조건 높아야 한다.
				while(true) {
					if(stack.isEmpty())
						break;
					String top = stack.peek();
					if(getOrder(top)>=num) {
						//같거나 크면 빼서 정답에 넣어준다.
						postfixNotation.add(stack.pop());
					}
					else {
						break;
					}
				}
				//이제 자기가 제일 우선순위가 높으므로
				stack.push(s);
			}
			//아직 남아있는 것들을 다 꺼내야된다.
			
		}
		while(!stack.isEmpty()) {
			postfixNotation.add(stack.pop());
		}
		return postfixNotation;
	}
	public static int calc(List<String> postfix) {
		Stack<String> stack = new Stack<>();
		for(int i=0;i<postfix.size();i++) {
			String s = postfix.get(i);
			int order = getOrder(s);
			if(order==0) {
				stack.push(s);
			}
			else {
				int b= Integer.parseInt(stack.pop());
				int a =Integer.parseInt( stack.pop());
				int result=0;
				if(s.equals("*")) {
					result=b*a;
				}
				else if(s.equals("/")) {
					result=a/b;
				}
				else if(s.equals("+")) {
					result=a+b;
				}
				else if(s.equals("-")) {
					result=a-b;
				}
				stack.push(Integer.toString(result));
			}
		}
		return Integer.parseInt(stack.pop());
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
