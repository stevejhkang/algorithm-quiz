
/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 7. 오후 11:00:06
 * @category 완전탐색
* @level 3
* @problem_description
* 일정 단위로 자르면서 그 뒤에 부분도 같은게 있는 지를 확인해야한다. 
* 최대 자를 수 있는 단위는 문자열 길이의 절반 부터 시작한다.
* @solving_description 
*/


public class 블라인드20_문자열압축 {
	public static void main(String[] args) {
		String s = 	"aabbaccc";
//		String s = 	"ababcdcdababcdcd";
//		String s =	"abcabcdede";
//		String s = "abcabcabcabcdededededede";
//		String s = 	"xababcdcdababcdcd";
	}
	static class Solution {
	    public int solution(String s) {
	        int answer = 0;
	        
	        int len = s.length()/2;
	        for(;len>=1;len--) { //len이 가능할 때마다 
	        	for(int index=0;index+len<=s.length();index+=len) {
	        		String unit = s.substring(index,index+len);
	        	}
	        }
	        
	        return answer;
	    }
	}
}
