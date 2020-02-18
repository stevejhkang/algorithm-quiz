package SWEA;

import java.util.Scanner;

public class SWEA_7965 {
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		int t = scanner.nextInt();
		for(int tc=1;tc<=t;tc++) {
			long n = scanner.nextInt();
			long sum= 0;
			//9223372036854775807
			//1000000007*1000000
			for(long i=1;i<=n;i++) {
				sum+=multi(i, i)%1000000007;
				sum%=1000000007;
			}
			System.out.println("#"+tc+" "+sum);
		}//tc
		
	}//main
	static long multi(long x, long n) {
		if(n==0) {
			return 1;
		}
		if(n%2==0) {
			long result =multi(x, n/2)%1000000007;
			return (result*result)%1000000007;
		}
		else {
			long result =multi(x, (n-1)/2)%1000000007;
			return (((result*result)%1000000007)*x)%1000000007;
		}
	}
}
