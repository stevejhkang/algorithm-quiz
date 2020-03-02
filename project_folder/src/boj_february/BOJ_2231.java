package boj_february;

import java.util.Scanner;

public class BOJ_2231 {
	public static void main(String[] args) {
		//분해합이란 자기의 수랑 각 자리수의 합을 의미
		//m의 분해합이 n인경우 m을 n의 생성자
		
		//출력: n의 가장 작은 생성자를 구하는 프로그램
		//256(245+2+4+5)의 생성자는 245임
		//없으면 0 출력
		Scanner scanner =new Scanner(System.in);
		int n = scanner.nextInt();
		boolean flag = false;
		for(int i=0;i<n;i++) {
			int a=i;
			int sum =a;
			while(a>0) {
				int remain = a%10;
				a=a/10;
//				System.out.print(remain+" ");
				sum+=remain;
			}
//			System.out.println(sum);
			if(sum==n) {
				System.out.println(i);
				flag = true;
				break;
			}
//			System.out.println();
		}
		if(!flag) {
			System.out.println(0);
		}
	}
}
