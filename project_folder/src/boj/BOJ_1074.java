package boj;

import java.util.Scanner;

//���
public class BOJ_1074 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int r = scanner.nextInt(); int c = scanner.nextInt();
		System.out.println(recursion(n, c, r, 0));
	}
	static int recursion(int n, int x, int y,int sum) {
		if(n==0) {
			sum+=x+2*y;
			return sum;
		}
		int sq = (int) Math.pow(2,n-1);
		sum+=sq*sq*(2*(y/sq)+1*(x/sq));
		x=x%sq;
		y=y%sq;
		return recursion(n-1,x,y,sum);
	}
}
