package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_17087 {
	private static int n;
	private static int s;
	private static int[] visit;
	private static int[] sis;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//		Scanner scanner = new Scanner(System.in);
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		s = Integer.parseInt(stringTokenizer.nextToken());
		
		sis = new int[n];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for(int i=0;i<n;i++) {
			sis[i] = Integer.parseInt(stringTokenizer.nextToken());
			sis[i] = Math.abs(sis[i]-s);
		}
		
	}
	
}
