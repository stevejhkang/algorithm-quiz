package boj_february;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_2870 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		ArrayList<String> arrayList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String string = scanner.next();
			StringBuilder numBuilder = new StringBuilder();
			
			for(int j =0;j<string.length();j++) {
				char c = string.charAt(j);
				if(c>=48 && c<=57) {//숫자이면 추가해준다.
					numBuilder.append(c);	
					if(j==string.length()-1) {
//						arrayList.add(Integer.parseInt(numBuilder.toString()));
						String temp = numBuilder.toString();
						int idx=0;
						while(true) {
							if(temp.length()==1&& temp.charAt(idx)=='0') {
								arrayList.add("0");
								break;
							}
							else if(temp.charAt(idx)=='0') {
								temp= temp.substring(idx+1);
//								System.out.println(temp);
							}
							else {
								arrayList.add(temp);
								break;
							}
						}
						
					}
				}
				else if(c>=97 &&c<=122) { //영어
					if(numBuilder.length()!=0) { //숫자를 우선순위큐 넣어주고 초기화 한다.
//						Integer tempInteger = Integer.parseInt(numBuilder.toString());
//						arrayList.add(tempInteger);
						String temp = numBuilder.toString();
						int idx=0;
						while(true) {
							if(temp.length()==1&& temp.charAt(idx)=='0') {
								arrayList.add("0");
								break;
							}
							else if(temp.charAt(idx)=='0') {
								temp= temp.substring(idx+1);
//								System.out.println(temp);
							}
							else {
								arrayList.add(temp);
								break;
							}
						}
						numBuilder=new StringBuilder();
					}
				}
			}
			
		}
		Collections.sort(arrayList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				Integer len1= o1.length();
				Integer len2= o2.length();
				if(o1.length()==o2.length()) {
					return o1.compareTo(o2);
				}
				else {
					return len1.compareTo(len2);
				}
			}
		});
		for(int i=0;i<arrayList.size();i++) {
			System.out.println(arrayList.get(i));
		}
	}
}
