package programmers;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 29. 오후 9:42:24
 * @category 
* @problem_description 
* @solving_description 그냥 두 수를 문자열로 합쳤을 때 어떤 값이 더크냐를 확인하면 된다.
* 34 3 30
* 
* 1. 343 334
* 2. 330 303
* 3. 334 303
*/

public class 정렬_가장큰수 {
	public static void main(String[] args) {
		Solution s = new Solution();
//		int[] arr = {6,10,2};
		int[] arr = {3, 30, 34, 5, 9};
		System.out.println(s.solution(arr));
		
	}
	static class Solution {
	    public String solution(int[] numbers) {
	        String answer = "";
	        
	        Integer[] array = new Integer[numbers.length];
	        for(int i=0;i<numbers.length;i++) {
	        	array[i] = numbers[i];
	        }
	        Arrays.sort(array, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					String tmp1 = o1.toString();
					String tmp2 = o2.toString();
					return (tmp1+tmp2).compareTo(tmp2+tmp1) *-1;
				}
			});
	        StringBuilder stringBuilder = new StringBuilder();
	        for(int i=0;i<array.length;i++) {
	        	stringBuilder.append(array[i]);
	        }
	        answer = stringBuilder.toString();
	        if(answer.substring(0, 1).equals("0")) {
	        	answer="0";
	        }
	        return answer;
	    }
	}
}
//class Solution {
//    public String solution(int[] numbers) {
//        String answer = "";
//        
//        Integer[] array = new Integer[numbers.length];
//        for(int i=0;i<numbers.length;i++) {
//        	array[i] = numbers[i];
//        }
//        Arrays.sort(array, new Comparator<Integer>() {
//
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				// TODO Auto-generated method stub
//				String tmp1 = o1.toString();
//				String tmp2 = o2.toString();
//				return (tmp1+tmp2).compareTo(tmp2+tmp1) *-1;
//			}
//		});
//        StringBuilder stringBuilder = new StringBuilder();
//        for(int i=0;i<array.length;i++) {
//        	stringBuilder.append(array[i]);
//        }
//        answer = stringBuilder.toString();
//        if(answer.substring(0, 1).equals("0")) {
//        	answer="0";
//        }
//        return answer;
//    }
//}