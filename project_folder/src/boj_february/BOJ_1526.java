package boj_february;

import java.util.Scanner;

/**
 * @author junhukang
 * @time 2020. 1. 30. 오후 10:22:32
 * @category 재귀
* @problem_description n보다 작거나 같은 4와 7로 이루어진 수 중 최대 출력 
* @solving_description basecase-n의 길이보다 길면 종료, 판단-계속 숫자를 키워가면서
* n보다 작거나 같은 최댓값을 완전탐색으로 찾아준다.
*/
public class BOJ_1526 {
	static int[] number=new int[2];
	static int n;
	static int lenN;
	static int max=4;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String sn = scanner.next();
		lenN = sn.length();
		n = Integer.parseInt(sn);
		number[0]=7;number[1]=4;
		recursion(0, "");
		System.out.println(max);
	}
	static void recursion(int len,String s) {
		if(lenN<s.length()) {//길이 넘으면 리턴
			return;
		}
		if(!s.equals("")&&n>=Integer.parseInt(s)&&max<Integer.parseInt(s)) {
			//빈 배열이 아니고, n보다는 작거나 같고, 기존의 max값보다 크면 갱신
			max=Integer.parseInt(s);
		}
		for(int i=0;i<2;i++) { //계속 4나 7을 붙여준다.
			recursion(len+1,s+number[i]);
		}
	}
}
