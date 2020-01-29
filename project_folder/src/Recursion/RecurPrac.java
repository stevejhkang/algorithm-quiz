package Recursion;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.imageio.event.IIOReadWarningListener;

import org.junit.jupiter.api.Test;

public class RecurPrac {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(palindromeCheck("Helleo"));
	}
	static public int GetMultiFunc(int n, int m) {
		if(n==m) {
			return m;
		}
		
		return n*GetMultiFunc(n+1, m);
			//2 3 4일때 4반환
		
	}
	@Test
	public void getMultiTest() {
		assertEquals(GetMultiFunc(2, 4), 24);
	}
	
	static int getPower(int n, int m) {
		if(m==1) {
			return n;
		}
		return n*getPower(n, m-1);
	}

	@Test
	public void getPowerTest() {
		assertEquals(getPower(2, 4), 16);
	}
	
	//num의 각 자리수의 합 sum을 출력하는 함수
	static void getDigitSum(int num, int sum) {
		if(num/10==0) {
			sum+=num%10;
//			System.out.println(num%10);
			System.out.println(sum);
			return;
		}
		sum+=num%10;
//		System.out.println(num%10);
		getDigitSum(num/10, sum);
	}
	
	@Test
	public void getDigitSumTest() {
		getDigitSum(12321, 0);
	}
	
	//문자열의 길이를 반환하는 함수
	static int getStringlength(String str) {
		if(str.equals("")) {
			return 0;
		}
		return 1+getStringlength(str.substring(1));
	}
	@Test
	public void getStringLenTest() {
		assertEquals(getStringlength("Hello"), 5);
		System.out.println(getStringlength("Hello"));
	}
	static void getString(String str) {
		if(str.equals("")) {
			System.out.println("");
			return;
		}
		System.out.print(str.charAt(0));
		getString(str.substring(1));
	}
	@Test
	public void getStringTest() {
		getString("Hello");
	}
	static void printStringReverse(String str) {
		if(str.equals("")) {
			return ;
		}
//		System.out.print(str.charAt(str.length()-1));
//		getStringReverse(str.substring(0,str.length()-1));
		printStringReverse(str.substring(1)); 
		System.out.print(str.charAt(0));//뒤에 넣어서 하나 남은 것부터 출력된다.
	}
	@Test
	public void getStringReverseTest() {
		printStringReverse("Hello");
	}
	static void printBinaryNumber(int dec,String s) {
		if(dec/2==0) {
			String string = Integer.toString(dec%2)+s;
			System.out.println(string);
			return;
		}
		String string= Integer.toString(dec%2)+s;
		printBinaryNumber(dec/2, string);
	}
	@Test
	public void printBinaryNumberTest() {
		printBinaryNumber(10,"");
	}
	
	static int getarraySum(int[] arr, int idx,int len) {
		if(idx==len-1) {
			return arr[idx];
		}
		return arr[idx]+getarraySum(arr,idx+1, len);
	}
	@Test
	public static void arraySumTest() {
		int[] arr={1,2,3,4,5};
		System.out.println(getarraySum(arr,0,arr.length));
		
	}
	static int getarrayMax(int[] arr, int idx) {
		if(idx==arr.length-1) {
			return arr[idx];
		}
		return Math.max(arr[idx], getarrayMax(arr, idx+1));
	}
	@Test
	public static void getarrayMaxTest() {
		int[] arr={1,2,3,7,5};
		System.out.println(getarrayMax(arr,0));
	}
	static int binarySearch(int[] arr, int start, int end,  int target) {
		int mid=(start+end)/2;
		if(start>end) {
			return -1;
		}
		if(arr[mid]==target) {
			return mid;
		}
		else if(arr[mid]<target) {
			return binarySearch(arr,mid+1,end,target);
		}
		else {
			return binarySearch(arr,start,mid-1,target);
		}
	}
	@Test
	public static void binarySearchTest() {
		int[] arr={1,2,3,5,8};
		int len= arr.length;
		System.out.println(binarySearch(arr,0,len,3));
	}
	
	static boolean palindromeCheck(String str) {
//		System.out.println(str.substring(1,str.length()-1));
		if(str.equals("")||str.length()==1) {
			return true;
		}
		else if(str.charAt(0)==str.charAt(str.length()-1)) {
			return palindromeCheck(str.substring(1,str.length()-1));
		}
		else {
			return false;
		}
	}
	
}
