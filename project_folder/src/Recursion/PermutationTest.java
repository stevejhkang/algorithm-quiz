package Recursion;

import java.util.Arrays;

public class PermutationTest {
	static char[] src = { '1', '2', '3', '4' };

	public static void main(String[] args) {
//		makePermutationIter();
		char[] temp = new char[3];
		boolean[] visited =new boolean[src.length];
//		makePermutation(0, temp, visited);
//		makePermutationSwap(3, 0);
		makePermutation();
	}

	/**
	 * 반목문을 이용한 순열 생성
	 */
	static void makePermutationIter() {
		for (int i = 0; i < src.length; i++) {
			for (int j = 0; j < src.length; j++) {
				if (i == j) {
					continue;
				}
				for(int k=0;k<src.length;k++) {
					if(i==k||j==k) {
						continue;
					}
					System.out.printf("반복문 순열: %c, %c, %c%n",src[i],src[j],src[k]);
				}
			}
		}
	}
	/*
	 * 
	 */
	static void makePermutation(int r, char[] temp, boolean[] visited) {
		if(r==3) {
			for(int i =0;i<temp.length;i++) {
				System.out.print(temp[i]+" ");
			}
			System.out.println("");
			return;
		}
		for(int i=0;i<src.length;i++) {
			if(!visited[i]) {
				visited[i]= true;
				temp[r] = src[i];
				makePermutation(r+1, temp, visited);
				visited[i]= false; 
			}
		}
	}
	
	/*
	 * swap을 이용한 순열처리
	 * @param r
	 * @param depth
	 */
	static void makePermutationSwap(int r, int depth) {
		if(depth ==r) {
			System.out.println(Arrays.toString(Arrays.copyOfRange(src, 0, r)));
		}else { //depth에 따라서 바꾸는 횟수가 달라진다.
			for(int i=depth; i<src.length;i++) {
				swap(depth,i);
				makePermutationSwap(r, depth+1);
				swap(depth,i);
			}
			
		}
	}
	static void swap(int a, int b) {
		char temp = src[a];
		src[a]= src[b];
		src[b]= temp; 
		
	}
	static boolean nextPermutation() {
		int i;
		//뒤에서부터 i+1의 값이 i보다 큰 값이 처음 나올때 까지 이동한다.
		for(i=src.length-2; i>=0;i--) {
			if(src[i]<src[i+1]) {
				break;
			}
		}
		//없으면 -1까지 이동하므로 return false 시켜준다.
		if(i<0) {
			return false;
		}
		
		//뒤에서부터 맨 뒤j에서 i까지 중에  i보다 큰 처음 값을 찾는다.
		int j;
		for(j= src.length-1;j>i;j--) {
			if(src[i]<src[j]) {
				break;
			}
		}
		//그 둘을 바꾸고
		swap(i,j);
		
		//i+1에서부터 마지막까지 서로 바꾼다.
		for(int a=i+1, b=src.length-1;a<b;a++,b--) {
			swap(a, b);
		}
		return true;
	}
	static void makePermutation() {
		Arrays.sort(src);
		do {
			System.out.println(Arrays.toString(src));
		}while(nextPermutation());
	}
}
