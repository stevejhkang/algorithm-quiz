package bruteforce;

import java.util.Arrays;

public class NextPermutationPractice {
	static int[] a;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		a= new int[4];
		for (int i = 0; i < a.length; i++) {
			a[i]=i+1;
		}
		do {
			System.out.println(Arrays.toString(a));
		} while (next_permutation());
		
	}
	static boolean next_permutation() {
		//step1: 맨뒤에서 처음으로 a[i]<a[i+1]이 되는 i를 찾는다.
		int i;
		for(i=a.length-1-1;i>=0;i--) {
			if(a[i]<a[i+1]) {
				break;
			}
		}
		//만약 그런 i를 찾지 못하면 i는 -1이 되므로 false return
		if(i<0)
			return false;
		
		//step2: 맨뒤에서 처음오르 a[i]보다 큰 a[j]를 찾는다.
		int j;
		for(j=a.length-1;j>i;j--) {
			if(a[i]<a[j]) {
				break;
			}
		}
		//step3: a[i]와 a[j]를 바꾼다.
		int temp = a[i];
		a[i]=a[j];
		a[j]=temp;
		
		//step4: i+1부터 마지막까지 숫자를 서로 바꿔준다.
		int start = i+1; int end = a.length-1;
		while(start<end) {
			temp=a[start];
			a[start]=a[end];
			a[end]=temp;
			start++;
			end--;
		}
		return true;
	}
}
