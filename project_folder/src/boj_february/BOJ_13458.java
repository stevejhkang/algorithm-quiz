package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458 {
	private static int n;
	private static int[] input;
	private static int b;
	private static int c;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bReader.readLine());
		input= new int[n];
		StringTokenizer stringTokenizer =new StringTokenizer(bReader.readLine());
		for (int i = 0; i < n; i++) {
			input[i]=Integer.parseInt(stringTokenizer.nextToken());
		}
		stringTokenizer = new StringTokenizer(bReader.readLine());
		b = Integer.parseInt(stringTokenizer.nextToken());
		c = Integer.parseInt(stringTokenizer.nextToken());
		
		long cnt=0;
		for (int i = 0; i < n; i++) {
			input[i]-=b;
			cnt++;
			if(input[i]>0) {
				if(input[i]%c==0) {
					cnt+=input[i]/c;
				}
				else {
					cnt+=input[i]/c;
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
