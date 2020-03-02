package boj_february;

import java.util.Scanner;

public class BOJ_9946 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t=1;
		while(true) {
			StringBuilder sb = new StringBuilder();
			sb.append("Case "+t+": ");
			t++;
			String s = sc.next();
			String s2 = sc.next();
			if(s.equals("END")&&s2.equals("END"))
				break;
			int[] alphabet= new int[26];
			if(s.length()!=s2.length()) {
				sb.append("different");
				System.out.println(sb);
				continue;
			}
			int[] hash= new int[26];
			for(int i=0;i<s.length();i++) {
//				System.out.println(s.charAt(i)-'a');
				hash[s.charAt(i)-'a']++;
			}
			for(int i=0;i<s2.length();i++){
				hash[s2.charAt(i)-'a']--;
			}
			boolean flag=true;
			for(int i=0;i<26;i++) {
				if(hash[i]!=0) {
					sb.append("different");
					System.out.println(sb);
					flag=false;
					break;
				}
			}
			if(flag) {
				sb.append("same");
				System.out.println(sb);
			}
		}
	}
}
