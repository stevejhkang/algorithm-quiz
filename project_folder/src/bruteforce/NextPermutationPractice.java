package bruteforce;

import java.util.Arrays;

import com.sun.swing.internal.plaf.basic.resources.basic;

import sun.print.resources.serviceui;

public class NextPermutationPractice {
	static int[] arr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		arr= new int[4];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=i+1;
		}
		do {
			System.out.println(Arrays.toString(arr));
		} while (next_permutation());
		
	}
	static boolean next_permutation() {
		//step1: 맨뒤에서 처음으로 a[i]<a[i+1]이 되는 i를 찾는다.
		int i;
		for(i=arr.length-1-1;i>=0;i--) {
			if(arr[i]<arr[i+1]) {
				break;
			}
		}
		//만약 그런 i를 찾지 못하면 i는 -1이 되므로 false return
		if(i<0)
			return false;
		
		//step2: 맨뒤에서 처음오르 a[i]보다 큰 a[j]를 찾는다.
		int j;
		for(j=arr.length-1;j>i;j--) {
			if(arr[i]<arr[j]) {
				break;
			}
		}
		//step3: a[i]와 a[j]를 바꾼다.
		int temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
		
		//step4: i+1부터 마지막까지 숫자를 서로 바꿔준다.
		int start = i+1; int end = arr.length-1;
		while(start<end) {
			temp=arr[start];
			arr[start]=arr[end];
			arr[end]=temp;
			start++;
			end--;
		}
//		for(int a=i+1, b=arr.length; a<b;a++,b-- ) {
//			temp=arr[a];
//			arr[a]=arr[b];
//			arr[b]=temp;
//		}
		return true;
	}
}
