package Recursion;

import org.junit.jupiter.api.Test;

public class RecurTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] arr = new int[1];
//		recur3(0,3,arr);
	}
	static public void recur3(int k, int n, int[] arr) {
		if(k==n) {
			return;
		}
		arr[0]=k;
		System.out.println(k+": "+arr[0]);
		recur3(k+1, n, arr);
		arr[0]=k;
		System.out.println(k+": "+arr[0]);
	}
	static public int recur4( int n) {
		if(n<=0) {
			return 0;
		}
		else {
			return n+recur4(n-1);
		}
	}
	
	@Test
	public void recur3Test() {
		int[] arr = new int[1];
		recur3(0, 3, arr);
	}
}
