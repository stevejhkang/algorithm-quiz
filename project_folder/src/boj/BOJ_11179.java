package boj;

import java.util.Scanner;

public class BOJ_11179 {
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		long n = scanner.nextLong();
		String string = Long.toBinaryString(n);
		
		long result=0;
		for(int i=string.length()-1; i>=0;i--) {
			
			if(string.charAt(i)=='1') {
				result+=1<<i;
			}
		}
		System.out.println(result);
	    
	}
}
