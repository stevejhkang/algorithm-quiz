package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7568 {
	private static int[][] input;
	private static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());
		
		input = new int [n][2];
		for(int i=0;i<n;i++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			input[i][0] = Integer.parseInt(stringTokenizer.nextToken());
			input[i][1] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		result = new int [n];
		for(int i=0;i<n;i++) {
			for(int j =0;j<n;j++) {
				if(i==j) continue;
				if(input[i][0]<input[j][0]&&input[i][1]<input[j][1]) {
					result[i]++;
				}
			}
			System.out.print((result[i]+1)+" ");
		}
		
	}//main
}
