package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1233 {
	static char[] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1;tc<=10;tc++) {
			int n = Integer.parseInt(br.readLine());
			map = new char[n];
			for (int i = 0; i < n; i++) {
				StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(stringTokenizer.nextToken());
				char c = stringTokenizer.nextToken().charAt(0);
				map[i]=c;
				while(stringTokenizer.hasMoreElements()) {
					stringTokenizer.nextToken();
				}
			}
			System.out.print("#" + tc + " ");
			for(int i=0;i<n;i++) {
				if(n/2-1<i) {
					if(map[i] == '+' || map[i] == '-' || map[i] == '*' || map[i] == '/') {
						System.out.print(0); break;
					}
					else {
						System.out.print(1); break;
					}
				}
				else if(n/2-1>=i) {
					if(map[i] == '+' || map[i] == '-' || map[i] == '*' || map[i] == '/') {}
					else {
						System.out.print("0"); break;
					}
				}
			}
			System.out.println("");
		}
	}
}
