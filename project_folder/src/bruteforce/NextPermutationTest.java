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
		//step1: �ڿ������� arr[i]<arr[i+1]�� �����ϴ� ó�� i�� ���ϱ� 
		for(i=src.length-1-1; i>=0;i--) { 
			if(src[i]<src[i+1]) {
				break;
			}
		}
		//i�� ã�� ���ϸ� -1�̸� �������̹Ƿ� false
		if(i<0)
			return false; 
		
		//step2: �ڿ������� arr[i]<arr[j]�� �����ϴ� ó�� j�� ���ϱ�
		int j;
		for(j=src.length-1;j>i;j--) {
			if(src[i]<src[j]) {
				break;
			}
		}
		
		//step3: �������ش�.
		int temp= src[i];
		src[i]=src[j];
		src[j]=temp;
		
		//step4: i+1���� ������ reverse �����ش�.
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
