package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1919 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		String string1 = stringTokenizer.nextToken();
		stringTokenizer = new StringTokenizer(bReader.readLine());
		String string2 = stringTokenizer.nextToken();
		
		int[] alpa = new int [26];
		for(int i=0;i<string1.length();i++) {
			int a = string1.charAt(i)-'a';
			alpa[a]++;
		}
		for(int i=0;i<string2.length();i++) {
			int a = string2.charAt(i)-'a';
			alpa[a]--;
		}
		int sum=0;
		for (int i : alpa) {
			if(i<0)
				sum-=i;
			else
				sum+=i;
		}
		System.out.println(sum);
	}

}
