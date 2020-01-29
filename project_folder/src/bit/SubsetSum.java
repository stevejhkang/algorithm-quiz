package bit;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr= {-7,-3,-2,5,8};
		for (int i = 1; i < (1<<arr.length); i++) {
			System.out.println(Integer.toBinaryString(i));
			List<Integer> list = new ArrayList<Integer>();
			int a=0;
			for(int j = 0;j<arr.length;j++) {
				//10101-> 0,2,4번째 인덱스에 넣어야됨. 그러기 위해서 
				//j에서 1 10 100 1000 10000 이런 식으로 i에 해당 비트가 있는 지
				//확인하고 해당 인덱스의 원소를 넣어준다.
				if((i&1<<j)>0) { 
					list.add(arr[j]);
				}
			}
			if(a==0) {
				System.out.println(-1);
//				return;
			}
			else
				System.out.println(a);
		}
		
	}

}
