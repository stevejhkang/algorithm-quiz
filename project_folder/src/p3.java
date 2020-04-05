
/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 5. 오전 10:40:57
 * @category 
* @problem_description 손상된 부분 0, 정상 1, 손상되지 않는 가장 긴구간을 구한다. 
* n은 최대로 보수할 수 있는 횟수 n<30만, road=30만
* @solving_description 
*/

public class p3 {
	public static void main(String[] args) {
		Solution s = new Solution();
		
		String road= "111011110011111011111100011111";
		int n = 3;
//		String road= "001100";
//		int n = 5;
		
		System.out.println(s.solution(road,n));
	}
	static class Solution {
	    public int solution(String road, int n) {
	        int answer = -1;
	        return answer;
	    }
	}
}
