package sort;

import java.util.Arrays;

public class QuickSort {
	static int[] nums = {3,2,4,6,9,1,8,7,5};
	public static void main(String[] args) {
		quicksort(nums, 0, nums.length-1);
		System.out.println(Arrays.toString(nums));
	}
	/**
	 * 배열  a의 begin부터 end사이를 정렬하는 메서드
	 * @param a
	 * @param begin
	 * @param end
	 */
	static void quicksort(int[] a, int begin, int end) {
		//정렬 대상의 크기가 1보다 커야 한다.
		if(begin<end) {
			int s = partition(a, begin, end);
			//분할 -> 정복
			quicksort(a, begin, s-1);
			quicksort(a, s+1, end);
		}
	}//quicksort
	static int partition(int[] a, int begin, int end) {
		//기준 위치 잡아주기
		int pivot = begin; //피벗의 인덱스
		int left = begin; int right = end;
		
		while(left<right) {
			//피벗 값보다 작으면 인덱스 증가, 그렇지 않으면 중지 --> 자리바꿈 대상
			while(a[left]<a[pivot]) {
				if(left>=end) {
					break;
				}
				left++;
			}
			//반대방향도 진행
			while(a[right]>=a[pivot]) {
				if(right<=begin) {
					break;
				}
				right--;
			}
			//쭉 이동시키다가 바꿔야할 요소들이 나왔다면 자리 바꿈
			if(left<right) {
				swap(a,left,right);
			}
		}//while
		//피봇값을 각 그룹의 경계에 집어넣기
		swap(a, pivot, right);
		
		return right;
	}//partition
	static void swap(int[] a,int i, int j) {
		int temp =a[i];
		a[i]= a[j];
		a[j]= temp; 
	}
}
