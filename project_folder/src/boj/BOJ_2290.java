package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2290 {
	private static int s;

	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		s = scanner.nextInt();
		String string = scanner.next();
		ArrayList<Integer> al = new ArrayList<>();
		for(int k=0;k<string.length();k++) {
			int a = Integer.parseInt(string.substring(k,k+1));
			al.add(a);
		}
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0;i<=2*s+2;i++) {
			for(int j=0;j<al.size();j++) {
				int num = al.get(j);
				if(i==0) {
					if(num==1||num==4) { //0000
						stringBuilder.append(" ");
						for(int t=0;t<s;t++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append(" "+" ");
					}
					else { //0--0
						stringBuilder.append(" ");
						for(int t=0;t<s;t++) {
							stringBuilder.append("-");
						}
						stringBuilder.append(" "+" ");
					}
				}
				else if(i>=1&&i<s+1) {
					if(num==5||num==6) { //1000
						stringBuilder.append("|");
						for(int t=0;t<s;t++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append(" "+" ");
					}
					else if(num==1||num==2||num==3||num==7){ //0001
						stringBuilder.append(" ");
						for(int t=0;t<s;t++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append("|"+" ");
					}
					else {
						stringBuilder.append("|");
						for(int t=0;t<s;t++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append("|"+" ");
					}
				}
				else if(i==s+1) {
					if(num==1||num==7||num==0) { //0000
						stringBuilder.append(" ");
						for(int t=0;t<s;t++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append(" "+" ");
					}
					else { //0--0
						stringBuilder.append(" ");
						for(int t=0;t<s;t++) {
							stringBuilder.append("-");
						}
						stringBuilder.append(" "+" ");
					}
				}
				else if(i>s+1&&i<2*s+2) {
					if(num==2) {//1000
						stringBuilder.append("|");
						for(int t=0;t<s;t++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append(" "+" ");
					}
					else if(num==6||num==8||num==0) { //1001
						stringBuilder.append("|");
						for(int t=0;t<s;t++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append("|"+" ");
					}
					else { //0001
						stringBuilder.append(" ");
						for(int t=0;t<s;t++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append("|"+" ");
					}
				}
				else if(i==2*s+2) {
					if(num==1||num==4||num==7) {//0000
						stringBuilder.append(" ");
						for(int t=0;t<s;t++) {
							stringBuilder.append(" ");
						}
						stringBuilder.append(" "+" ");
					}
					else  { //0--0
						stringBuilder.append(" ");
						for(int t=0;t<s;t++) {
							stringBuilder.append("-");
						}
						stringBuilder.append(" "+" ");
					}
				}
				
			}//al.size
			stringBuilder.append("\n");
		} //for
		System.out.println(stringBuilder);
	}//main
}
	
