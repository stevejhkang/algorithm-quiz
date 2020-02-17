package Recursion;

import java.util.Arrays;

public class PowerSetTest {
	static int[] src = {3,6,7,1,5,4};
	private static int[] result;
	private static boolean[] visit;
	public static void main(String[] args) {
//		for(int i=0;i<=src.length;i++) {
//			if(i==0) {
//				System.out.println("");
//				continue;
//			}
//			result = new int[i];
//			visit = new boolean[src.length];
//			subset(0,i,0);
//			
//			
//		}
		subset();
	}
	//반복문
	static void subset(int k,int r, int index) {
		if(k==r) {
			System.out.println(Arrays.toString(result));
			return;
		}
		for(int i=index;i<src.length;i++) {
			if(!visit[i]) {
				visit[i]= true;
				result[k]= i; 
				subset(k+1, r,i+1);
				visit[i]= false; 
			}
		}
	}
	//비트마스크
	static void subset() {
		for(int i=0;i<(1<<src.length);i++) {
			for(int j=0;j<src.length;j++) {
				if((i&(1<<j))>0) {
					System.out.print(src[j]+" ");
				}
			}
			System.out.println("");
		}
	}
}
