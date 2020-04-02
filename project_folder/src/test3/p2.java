package test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 27. 오후 4:20:29
 * @category 
* @problem_description 해외여행 시작날짜 끝 날짜 정해져 급여 제각각
* 여러개 하려고 한다. 끝나는날 다른 알바 시작가능 
* 시작날짜 끝 날짜 급여가 담긴 배열을 매개변수로 주어질 때
* 최대로 벌수 있는 돈 
* @solving_description 
*/

public class p2 {
	public static void main(String[] args) {
		int[] arr = {3,0,6,1,5};
		int[] arr2 = {10,8,5,4,3};
		int[] arr3= {25,8,5,3,3,};
		System.out.println();
	
		
		HashMap<Integer,ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
		
		
		Solution s = new Solution();
		System.out.println(s.solution(arr3));
	}
	static class Solution {

		public int solution(int[] citations) {
	        int answer = 0;
	        int size = citations.length;
	        
	        Arrays.sort(citations);
	        
	       for(int i=0;i<size;i++) {
	    	   int h = citations[i];
	    	   int cnt = size-i;
	    	   if(h<=cnt) {
	    		   answer=h;
	    	   }
	       }
	        
	        
	        return answer;
	    }
	}
}
