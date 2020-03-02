package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1920 {
	private static int[] array;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());
		array= new int[n];
		StringTokenizer st= new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			array[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(array);
		int m =Integer.parseInt(br.readLine());
//		System.out.println(Integer.MAX_VALUE);
		st= new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			int search = Integer.parseInt(st.nextToken());
			if(binarySearch(search, 0, array.length-1)) {
				System.out.println(1);
			}
			else {
				System.out.println(0);
			}
		}
		
	}
	static boolean binarySearch(int search, int start, int end) {
		int mid = (start+end)/2;
		if(search==array[mid]) {
			return true;
		}
		while(start<end) {
			if(search<array[mid]) {
				return binarySearch(search, start, mid-1);
			}
			else if(search>array[mid]){
				return binarySearch(search, mid+1, end);
			}
		}
		return false;
		
	}
}
