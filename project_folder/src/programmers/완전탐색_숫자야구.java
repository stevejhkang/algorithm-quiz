package programmers;
/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 30. 오후 9:27:27
 * @category 
* @level 3
* @problem_description 
* 1. 숫자는 맞지만 위치가 틀렸을 때는 볼
* 2. 숫자와 위치가 모두 맞을 때는 스트라이크
* 3. 숫자와 위치가 모두 틀렸을 때는 아웃
* 
* 경우의 수이니까,,, 9개9개9개 경우의 수 중에서 
* 
* @solving_description 
* 
* 그냥 처음부터 123 ~ 987까지 전부 해보고 맞는 경우의 수를 찾으면 된다.
* 
*/


public class 완전탐색_숫자야구 {
	public static void main(String[] args) {
//		System.out.println("1"+2);
		Solution s = new Solution();
		
		int[][] baseball = {{123,1,1},{356,1,0},{327,2,0},{489,0,1}};
		
		System.out.println(s.solution(baseball));
	}
	static class Solution {
		static int[][] static_baseball;
		static int ans;
		private static boolean[] visit;
	    public int solution(int[][] baseball) {
	        int answer = 0;
	        ans =0;
	        static_baseball = new int[baseball.length][3];
	        for(int i=0;i<baseball.length;i++) {
	        	static_baseball[i] = baseball[i].clone();
	        }
	        visit = new boolean[10];
	        dfs(0,"");
	        answer= ans;
	        return answer;
	    }
	    static void dfs(int r, String s) {
	    	if(r==3) {
	    		//비교를 시작한다.
//	    		System.out.println(s);
	    		
	    		boolean match=true;
	    		for(int i=0;i<static_baseball.length;i++) {
	    			String num = new Integer(static_baseball[i][0]).toString();
	    			int ball=0; int strike=0;
	    			for(int j=0;j<3;j++) { //s
	    				for(int k=0;k<3;k++) { //num
	    					if(j==k&&s.charAt(j)==num.charAt(k)) { //인덱스가 같고 값도 같을 때
	    						strike++;
	    					}
	    					else if(j!=k&&s.charAt(j)==num.charAt(k)) { //인덱스가 다르고 값은 같으면
	    						ball++;
	    					}
	    				}
	    			}
//	    			System.out.println("strike: "+strike+", ball: "+ball);
	    			//다 끝나고 값이 맞는지 확인한다.
	    			if(strike!=static_baseball[i][1]||ball!=static_baseball[i][2]) {
	    				//다른 값이 있으면 그 값이 아니므로 패스
	    				match=false;
	    				break;
	    			}
	    		}//for i
	    		if(match) {
	    			ans++;
	    		}
	    		return;
	    	}//if r==3
	    	//여기서 값을 생성한다.
	    	for(int i=1;i<=9;i++) {
	    		if(!visit[i]) {
	    			visit[i] = true;
	    			dfs(r+1,s+i);
	    			visit[i] = false;
	    		}
	    	}
	    }
	}
}
