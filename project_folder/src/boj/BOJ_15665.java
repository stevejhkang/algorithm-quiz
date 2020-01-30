package boj;

import java.util.Arrays;
import java.util.Scanner;

// 같은 수 여러번 골라도 되지만 수열이 중복되면 안된다.
// 해당자리에 이미 해당 숫자를 사용한 적이 있는지 없는지 체크
public class BOJ_15665 {
	static int[] input; static int n;
	static int[] jari;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		 n = scanner.nextInt();  int m = scanner.nextInt();
		input=new int[n];
		jari= new int[m];
		for (int i = 0; i < n; i++) {
			int a=scanner.nextInt();
			input[i]=a;
		}
		Arrays.sort(input);
//		System.out.println(Arrays.toString(input));
		recursion(0, m, "");
	}
	//11 17 19 19(x) 71 77 
	static void recursion(int r, int m,String s) {
		if(r==m) {
			System.out.println(s);
			return;
		}
		for (int i = 0; i < n; i++) {
			if(jari[r]!=input[i]) {
				jari[r]=input[i];
				recursion(r+1, m, s+input[i]+" ");
			}
		}
		jari[r]=0;
	}

}
