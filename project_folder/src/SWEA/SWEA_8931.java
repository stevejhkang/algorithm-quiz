package SWEA;

import java.util.Scanner;
import java.util.Stack;

public class SWEA_8931 {
	static int[] input;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		int t= scanner.nextInt();
		
		for(int tc=1;tc<=t;tc++) {
			int k = scanner.nextInt();
			
			Stack<Integer> stack =new Stack<>();
			int sum=0;
			for(int i=0;i<k;i++) {
				int a= scanner.nextInt();
				if(a==0) {
					int sub=stack.pop();
					sum-=sub;
				}
				else {
					sum+=a;
					stack.push(a);
				}
			}
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("#"+tc+" "+sum);
			System.out.println(sBuilder);
		}
	}

}
