package kakao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 8. 오전 11:27:28
 * @category 문자열 편집, 자료구조 활용, 정규표현식
* @level 2
* @problem_description 
* 1. 튜플은 중복된 원소가 있을 수 있다.
* 2. 원소에 정해진 순서가 있으며, 순서가 다르면 서로다른 튜플
* 3. 튜플의 원소 개수는 유한합니다.
* 
* 특정 튜플을 표현하는 집합이 담긴 문자열 s가 매개변수로 주어질때, s가 표현하는 튜플을 배열에 담아 리턴
*  
* s의 길이는 5이상 100만 이하
* s는 숫자와 {,},,로만 이루어져있다.
* s의 원소는 10만이하
* @solving_description 
* 문자열 편집: 앞뒤를 자르고
* },{를 토크나이저로 하려고 했으나 안되서 replace를 통해서 하나의 문자로 통합했다.
* 항상 이렇게 공통으로 처리할 것을 하나로 만들어줘야하는 경우가 많은 것 같다.
* 
* 그리고 중복을 확인하기 위해서 set을 사용해서 이미 추가가 되었을 경우 뒤를 탐색하게 하는 방식으로 처리를 한다.
* 
*/

public class 겨울인턴19_튜플 {
	public static void main(String[] args) {
//		String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
		String s="{{1,2,3},{2,1},{1,2,4,3},{2}}";
		System.out.println(Arrays.toString(new Solution().solution(s)));
	}
	static class Solution {
	    private String[][] s_arr;

		public int[] solution(String s) {
	        int[] answer = {};
	        
	        //문자열을 ,로 자른다
	        s = s.substring(2,s.length()-2).replace("},{","-"); //
	        String[] arr  = s.split("-");
//	        StringTokenizer stringTokenizer = new StringTokenizer(s,"\\},\\{");
	        s_arr= new String[arr.length][];
	        int index =0;
	        for(String one_str: arr) {
	        	s_arr[index] = one_str.split(",");
//	        	Arrays.sort(s_arr[index]);
	        	index++;
	        }
	        Arrays.sort(s_arr, new Comparator<String[]>() {

				@Override
				public int compare(String[] o1, String[] o2) {
					// TODO Auto-generated method stub
					
					
					return Integer.compare(o1.length, o2.length);
				}
			});
	        
	        answer = new int[arr.length];
	        int arr_len = arr.length;
	        
	        HashSet<Integer> set = new HashSet<>();
	        for(int i=0;i<arr_len;i++) {
	        	for(int j=0;j<s_arr[i].length;j++) {
	        		int num = Integer.parseInt(s_arr[i][j]);
//	        		System.out.println(num);
	        		if(!set.contains(num)) {
	        			set.add(num);
	        			answer[i]= num;
	        			break;
	        		}
	        	}
//	        	answer[i] = Integer.parseInt(s_arr[i][i]);
	        }
	        
	        return answer;
	    }
	}
}
