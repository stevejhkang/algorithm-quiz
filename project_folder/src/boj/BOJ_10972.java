package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10972 {
	static int[] src;
	private static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		src = new int[n];
		for(int i=0;i<n;i++) {
			src[i]= Integer.parseInt(stringTokenizer.nextToken()); 
		}
		
		if(!next_permutation()) {
			System.out.println(-1);
		}
		else {
			for(int i=0;i<src.length;i++) {
				System.out.print(src[i]+" ");
			}
		}
		
	}
	static boolean next_permutation() {
		int i;
		//i+1값이 더큰 경우까지 이동
		for( i=src.length-2;i>=0;i-- ) {
			if(src[i]<src[i+1]) {
				break;
			}
		}
		if(i==-1) {
			return false;
		}
		int j;
		//i값보다 큰 처음 j값찾기
		for(j=src.length-1; j>i;j--) {
			if(src[j]>src[i]) {
				break;
			}
		}
		//i,j 바꿔주고
		int temp = src[i];
		src[i]= src[j];
		src[j] =temp;
		i++;
		j=src.length-1;
		while(i<=j) {
			int temp2 =src[i];
			src[i]=src[j];
			src[j]=temp2;
			i++;
			j--;
		}
		return true;
		
	}
}
