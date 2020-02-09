package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_10816 {
	static int input[];
	static int target=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bReader.readLine());
		input=new int[n];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		for (int i = 0; i < n; i++) {
			int a=Integer.parseInt(stringTokenizer.nextToken());
			input[i]=a;
			if(map.containsKey(a)) {
				map.replace(a, map.get(a)+1);
			}
			else {
				map.put(a, 1);
			}
		}
		Arrays.sort(input);
		int m = Integer.parseInt(bReader.readLine());
		int ans[] = new int[m];
		stringTokenizer = new StringTokenizer(bReader.readLine());
		for(int i=0;i<m;i++) {
			target=Integer.parseInt(stringTokenizer.nextToken());
			
			if(binarySearch(0, input.length-1)) {
				ans[i]=map.get(target);
			}
			else {
				ans[i]=0;
			}
			
		}
		for(int i=0;i<m;i++) {
			System.out.print(ans[i]+" ");
		}
		System.out.println("");
	}
	static boolean binarySearch(int start, int end) {
		int mid= (start+end)/2;
		if(target==input[mid]) {
			return true;
		}
		while(start<end) {
			if(target<input[mid]) {
				return binarySearch(start, mid-1);
			}
			if(target>input[mid]){
				return binarySearch(mid+1, end);
			}
		}
		return false;
	}
}
