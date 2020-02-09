package boj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ_1181 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		String strings[] = new String[n];
		for(int i=0;i<n;i++) {
			strings[i]=scanner.next();
		}
		Arrays.sort(strings,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if(o1.length()>o2.length()) {
					return 1;
				}
				else if(o1.length()<o2.length()) {
					return -1;
				}
				else {
					return o1.compareTo(o2);
				}
				
			}
		});
		
		for (int i = 0; i < n; i++) {
			if(i==0) {
				System.out.println(strings[i]);
				continue;
			}
			if(strings[i].equals(strings[i-1])) continue;
			System.out.println(strings[i]);
		}
	}
}
