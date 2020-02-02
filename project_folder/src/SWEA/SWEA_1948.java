package SWEA;

import java.util.Scanner;

public class SWEA_1948 {
	static int[] days= {0,31,28,31,30,31,30,31,31,30,31,30,31};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		int t = scanner.nextInt();
		for(int tc=1;tc<=t;tc++) {
			int m1 = scanner.nextInt(); int d1=scanner.nextInt();
			int m2 = scanner.nextInt(); int d2= scanner.nextInt();
			int sum=0;
			while(m1<=m2) {
				if(m1==m2) {
					sum+=d2-d1+1;
					m1++;
					continue;
					
				}
				sum+=days[m1]-d1;
				d1=0;
				m1++;
			}
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("#"+tc+" "+sum);
			System.out.println(stringBuilder);
		}
	}

}
