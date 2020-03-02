package boj_february;

import java.io.BufferedReader;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 11. 오후 4:29:01
 * @category DFS(완전탐색)
 * @problem_description 
 * 연산자 우선순위는 동일, 계산은 왼쪽에서부터 순서대로 계산, 괄호 추가하면 괄호 안에 있는 식은 먼저 계산해야한다.
 * 단 괄호 안에는 연산자 하나만 들어있어야 한다. 수식이 주어졌을 때, 괄호를 적절히 추가해 만들 수 있는 식의 결과의 
 * 최댓값 출력
 * @solving_description
 * 3+(8*7)//-9*2을 할지 (3+8)//*7-9*2 이렇게 두가지 결과의 중간값을 dfs의 파라미터로 넘겨주어서
 * 마지막까지 숫자가 연산될 때까지 값을 구해서 최댓값과 비교해서 갱신.
 * 앞에서부터 괄호 묶는 방식을 정해주고 뒤에 괄호를 어떻게 할지는 뒤에 호출되는 재귀에서 결정이 된다.     
 */

public class BOJ_16637_2 {
	static int max =Integer.MIN_VALUE;
	private static int n;
	static int ans=Integer.MIN_VALUE;
	static char[] input;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		String string = scanner.next();
		input = new char[n];
		input= string.toCharArray();
		//길이가 1일땐 그냥 출력
		if(string.length()==1) {
			System.out.println(Integer.parseInt(string));
			return;
		}
		//길이가 3일땐 바로 연산해서 출력
		else if(string.length()==3) {
			int a = Integer.parseInt(string.substring(0,1));
			char oper = string.charAt(1);
			int b = Integer.parseInt(string.substring(2,3));
			System.out.println(calc(a, oper, b));
			return;
		}
		//그 다음부턴 dfs 
		dfs(2, Integer.parseInt(string.substring(0,1)));
		System.out.println(ans);
	}
	//index:다음 첫번째 숫자의 인덱스, num:현재까지 구한 답을 넘겨준다.
	static void dfs(int index, int num) {
		//base case 인덱스가 마지막으로 갔을때 최댓값 갱신
		if(index>=n) {
			ans=(ans<num?num:ans);
			return;
		}
		
		//다음 것과 같이 안 묶어서 연산할때
		//첫번째 숫자와 num을 연산
		//(3+8)->*7-9*2
		int a=0;
		char oper = input[index-1];
		int b = Character.getNumericValue(input[index]);
		a= calc(num, oper, b);
		dfs(index+2, a); //다음숫자는 +2
		
		//뒤에 두개를 묶을 때
		//뒤에 두개 연산의 결과를 num과 연산
		//3+(8*7)->-9*2을 할지 
		if(index+2<n) {
			a =0;
			int n1 = Character.getNumericValue(input[index]);
			char oper2 = input[index+1];
			int n2 = Character.getNumericValue(input[index+2]);
			int temp= calc(n1, oper2, n2);
			a= calc(num, oper, temp);
			dfs(index+4, a); //다다음 숫자는 +4
		}
		
		
		
	}
	//두 숫자 연산
	static int calc(int n, char oper, int b) {
		if(oper=='+') {
			return n+b;
		}
		else if(oper=='-') {
			return n-b;
		}
		else {
			return n*b;
		}
	}
}
