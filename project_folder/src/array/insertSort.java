package array;

import java.util.Arrays;
import java.util.Random;

public class insertSort {
	static void insertionSort(int[] data) {
		for(int i=1;i<data.length;i++) {
			int idx=0;
			for(int j=idx;j>=0;j--) {
				if(data[i]<data[j]) {
					//데이터를 미룬다.
					int k=idx;
					int temp = data[idx+1];
					while(k>=j) {
						data[k+1]=data[k];
						k--;
					}
					data[j]=temp;
					break;
				}
			}
			idx++;
		}
	}
	public static void main(String[] args) {
		int len =1000;
		int[] data = new int[len];
		
		Random random = new Random();
		for(int i=0;i<data.length;i++) {
			data[i]= random.nextInt(len)+1;
		}
		int[] data2= {10,69,30,2,16,8,31,22};
		System.out.println("정렬 전: "+Arrays.toString(data2));
		insertionSort(data2);
		System.out.println("정렬 후: "+Arrays.toString(data2));
//		System.out.println(Arrays.toString(data));
//		for(int i=0;i<len-1;i++) {
//			if(data[i]>data[i+1]) {
//				System.out.println("Error");
//			}
//		}
	}
}
