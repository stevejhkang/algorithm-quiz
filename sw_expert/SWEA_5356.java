package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_5356 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		int tc=scanner.nextInt();
		for(int t=1;t<=tc;t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			//초기화
			char[][] charr = new char[5][15];
			for (int i = 0; i < charr.length; i++) {
				for (int j = 0; j < charr[0].length; j++) {
					charr[i][j]='@'; //*로 초기화
				}
			}
			
			//문자열 입력
			for (int i = 0; i < charr.length; i++) {
				String s = scanner.next();
				for(int j  =0;j<s.length();j++) {
					charr[i][j]=s.charAt(j);
				}
			}
			
			//sb에 추가
			for (int i = 0; i < charr[0].length; i++) {
				for(int j  =0;j<charr.length;j++) {
					if(charr[j][i]!='@') {
						sb.append(charr[j][i]);
					}
				}
			}
			sb.append("\n");
			System.out.print(sb);
		}
	}
}
