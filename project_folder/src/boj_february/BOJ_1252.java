package boj_february;

import java.util.Scanner;

//문자열 처리, Character.getNumericValue로 문자 타입의 숫자를 정수로 반환
public class BOJ_1252 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		String a = scanner.next();
		String b = scanner.next();
		int lenA= a.length();
		int lenB = b.length();
		int sub = lenA-lenB;
		
		StringBuffer sa = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		StringBuffer temp = new StringBuffer();
		
		if(sub<0) {
			sub*=-1;
			for(int i=0;i<sub;i++) {
				temp.append('0');
			}
			sa.append(temp);
			sa.append(a);
			sb.append(b);
			
		} 
		else if(sub>0) {
			for(int i=0;i<sub;i++) {
				temp.append('0');
			}
			sb.append(temp);
			sa.append(a);
			sb.append(b);
		}
		else {
			sa.append(a);
			sb.append(b);
		}
//		System.out.println(sa);
//		System.out.println(sb);
		
		char[] arr = new char[sa.length()+1];
		for(int i=0;i<arr.length;i++) {
			arr[i]='0';
		}
		
		for(int i=sa.length()-1;i>=0;i--) {
			int inta= Character.getNumericValue(sa.charAt(i));
			int intb= Character.getNumericValue(sb.charAt(i));
			int intarr= Character.getNumericValue(arr[i+1]);
			int sum=inta+intb+intarr;
			if(sum==3) {
				arr[i]='1'; arr[i+1]='1';
			}
			else if(sum==2) {
				arr[i]='1'; arr[i+1]='0';
			}
			else if(sum==1) {
				arr[i+1]='1';
			}
			else {
				arr[i+1]='0';
			}
		}
		int flag =0;
		StringBuilder sBuilder = new StringBuilder();
		for(int i=0;i<arr.length;i++) {
			if(flag==0&&arr[i]=='1') {
				flag=1;
			}
			if(flag==1) {
				sBuilder.append(arr[i]);
			}
				
		}
		if(sBuilder.length()==0) {
			System.out.println(0);
		}
		else {
			System.out.println(sBuilder);
		}
		
		
	}
	
}
