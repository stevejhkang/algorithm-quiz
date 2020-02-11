package array;

import java.util.ArrayList;
import java.util.Arrays;

public class Sort {
	static ArrayList<Integer> input2 = new ArrayList<>();
	static int[] input3 = {69,10,30,2,16,8,31,22};
	public static void main(String[] args) {
		int[] input = {69,10,30,2,16,8,31,22};
		mergeSort(input, 0, input.length-1);
		System.out.println(Arrays.toString(input));
	}
	static void mergeSort(int[] arr, int si, int ei) {
		//종료 조건: 크기가 1이면 더 이상 나눌 필요가 없다.
		//Base case
		if(si==ei) {
			return;
		}
		//Recursive Case: 반씩 나눠가며 재귀 호출
		int mi =(si+ei)/2;
		mergeSort(arr, si, mi);
		mergeSort(arr, mi+1, ei);
		//분할이 완료되면 -> 맨 아래 가지에서 만난 두 재귀의 결과에서 병합
		merge(arr,si,mi,ei);
	}
	static void merge(int[] arr, int si, int mi, int ei) {
		//병합 정렬은 별도의 메모리 공간을 필요로 한다.
		int[] temp = new int[arr.length];
		int li = si; //왼쪽 배열의 시작점
		int ri = mi+1;//오른쪽 배열의 시작점
		int ti = si; //결과를 저장할 임시 배열의 시작점
		
		while(li<=mi&&ri<=ei) { //왼쪽의 것이 아직 남아 있고 오른쪽의 것도 남아 있으면
			if(arr[li]<=arr[ri]) {
				temp[ti++]=arr[li++];
			}
			else{
				temp[ti++]=arr[ri++];
			}
		}
		//한쪽 배열에서 다 채워지면
		while(li<=mi) {
			temp[ti++]=arr[li++];
		}
		while(ri<=ei) {
			temp[ti++]=arr[ri++];
		}
		for(int i=si;i<=ei;i++) {
			arr[i]=temp[i];
		}
	}
}
