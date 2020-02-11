package array;

import java.util.Arrays;
import java.util.Comparator;

public class Practice_Array2Dim {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a;
		int [] arr1;
		int [] [] arr2 = new int [2][4];
		
		System.out.println(arr2.length);
		arr2[1][3]=200;
		arr2[0][3]=100;
		System.out.println("hello");
//		System.out.println(Arrays.toString());
		for (int i = 0; i < arr2.length; i++) {
			System.out.println(Arrays.toString(arr2[i]));
		}
		
		// 총 합으로 내림차순
		Arrays.sort(arr2,new Comparator<int[]>(){
			@Override
			public int compare(int[] arr1, int[] arr2) {
				int sum1=0; int sum2=0;
				for (int i = 0; i < arr1.length; i++) {
					sum1+=arr1[i];
				}
				for (int i = 0; i < arr2.length; i++) {
					sum2+=arr2[i];
				}
				return sum1<=sum2 ? 1:-1;
			}
		});
		Arrays.sort(arr2, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				Integer sum1=0;
				for(int num: o1) {
					sum1+=num;
				}
				Integer sum2=0;
				for(int num: o2) {
					sum2+=num;
				}
//				return sum1.compareTo(sum2); //오름차순
				return sum1.compareTo(sum2)*-1;//내림차순
			}
			
		});
		
		for (int i = 0; i < arr2.length; i++) {
			System.out.println(Arrays.toString(arr2[i]));
		}
	}
}

