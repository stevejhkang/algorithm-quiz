package programmers;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 29. 오후 10:26:11
 * @category 
* @problem_description n편 중, h번 이상 인용된 논문이 h편이상이고 나머지 논문이 h번이하 인용되었다면
* h의 최댓값이 이 과학자의 H-index이다.
* @solving_description 
* 배열 전체의 값이 i보다 클 수도 있다. 그러면 citations.length 값이된다.
* 이 경우도 생각해줘야 한다.
*/

public class 정렬_H_Index {
	public static void main(String[] args) {
		int[] citations = {22,42};
		Solution s= new Solution();
		System.out.println(s.solution(citations));
	}
	static class Solution {
	    public int solution(int[] citations) {
	        
	        
	        //한 과학자가 발표한 논문 n편 중, h번이상 인용된 논문이 h편이상이고
	        //나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H인덱스
	    	int max = 0;
	    	for(int i=0;i<=citations.length;i++) {
	    		int h = i;
	    		int high=0; int low = 0;
	    		for(int j=0;j<citations.length;j++) {
	    			if(citations[j]>=i) {
	    				high++;
	    			}
	    			if(citations[j]<i) {
	    				low++;
	    			}
	    		}
	    		if(high>=h&&low<=h) {
	    			max= h;
	    		}
	    	
	    	}
	    	return max;
	    }
	}
}
