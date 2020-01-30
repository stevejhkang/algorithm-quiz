package bruteforce;

import java.util.Arrays;

public class NextPermutationTest {
	static int[] src= {1,2,4,3};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arrays.sort(src);
		do {
			System.out.println(Arrays.toString(src));
		}while(nextPermutation());
	}
	private static boolean nextPermutation() {
		int i;
		//step1: 맨뒤에서 처음으로 a[i]<a[i+1]이 되는 i를 찾는다.
		for(i=src.length-1-1; i>=0;i--) { 
			if(src[i]<src[i+1]) {
				break;
			}
		}
		//만약 그런 i를 찾지 못하면 i는 -1이 되므로 false return
		if(i<0)
			return false; 
		
		//step2: 맨뒤에서 처음오르 a[i]보다 큰 a[j]를 찾는다.
		int j;
		for(j=src.length-1;j>i;j--) {
			if(src[i]<src[j]) {
				break;
			}
		}
		
		//step3: a[i]와 a[j]를 바꾼다.
		int temp= src[i];
		src[i]=src[j];
		src[j]=temp;
		
		//step4: i+1부터 마지막까지 숫자를 바꿔준다.
		int start=i+1; int end=src.length-1;
		while(start<end) {
			int a = src[start];
			src[start]=src[end];
			src[end]=a;
			start++; end--;
		}
		return true;
	}

}
