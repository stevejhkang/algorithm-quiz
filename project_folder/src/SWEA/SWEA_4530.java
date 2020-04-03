package SWEA;

import java.util.Stack;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 3. 오후 4:52:46
 * @category 
* @problem_description -999,999,999,999 ~ 999,999,999,999
* @solving_description 
*/

public class SWEA_4530 {
	public static void main(String[] args) {
		long a = Long.parseLong("789789789789");
		long b = Long.parseLong("-123123123123");
		
		long result =0;
		Stack<Integer> stack = new Stack<Integer>();
		while(a!=0) {
			int remain = (int) (a%=9);
			System.out.println(remain);
			stack.push(remain);
			a/=9;
		}
		StringBuilder stringBuilder = new StringBuilder();
		while(!stack.isEmpty()) {
			Integer now = stack.pop();
			stringBuilder.append(now.toString());
		}
		long la = Long.parseLong(stringBuilder.toString());
		System.out.println(la);
		while(b!=0) {
			int remain = (int) (b%=9);
			stack.push(remain);
			b/=9;
		}
		stringBuilder = new StringBuilder();
		while(!stack.isEmpty()) {
			Integer now = stack.pop();
			stringBuilder.append(now.toString());
		}
		long lb = Long.parseLong(stringBuilder.toString());
		System.out.println(lb);
	}
}
