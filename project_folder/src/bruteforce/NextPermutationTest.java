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
		//step1: 뒤에서부터 arr[i]<arr[i+1]가 성립하는 처음 i를 구하기 
		for(i=src.length-1-1; i>=0;i--) { 
			if(src[i]<src[i+1]) {
				break;
			}
		}
		//i를 찾지 못하면 -1이면 마지막이므로 false
		if(i<0)
			return false; 
		
		//step2: 뒤에서부터 arr[i]<arr[j]가 성립하는 처음 j를 구하기
		int j;
		for(j=src.length-1;j>i;j--) {
			if(src[i]<src[j]) {
				break;
			}
		}
		
		//step3: 스왑해준다.
		int temp= src[i];
		src[i]=src[j];
		src[j]=temp;
		
		//step4: i+1부터 끝까지 reverse 시켜준다.
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
