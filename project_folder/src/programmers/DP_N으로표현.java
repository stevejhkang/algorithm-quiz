package programmers;
import java.util.ArrayList;
import java.util.List;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 30. 오전 1:28:49
 * @category DFS
* @level 5
* @problem_description 
* @solving_description dfs를 얼만큼 잘 사용하는지 
*/

public class DP_N으로표현 {
	public static void main(String[] args) {
		Solution s = new Solution();
//		int N = 5; int number = 12;
		int N=2; int number =11;
		System.out.println(s.solution(N, number));
	}
	static class Solution {
		static int answer =9;
		static int static_N=0;
		static int static_number=0;
	    public int solution(int N, int number) {
	        static_N=N;
	        static_number= number;
	        
	        dfs(0,0);
	        
	        return answer==9? -1:answer;
	    }
	    static void dfs(int cnt, int num) { 
	    	//8보다 많이 사용하면 리턴 8까지는 가능하다.
	    	if(cnt>=9) {
	    		return;
	    	}
	    	//숫자 값을 찾았으면 작은 값으로 갱신
	    	if(num==static_number) {
//	    		System.out.println(cnt);
	    		answer= Math.min(answer, cnt);
	    	}
	    	//숫자 1자리 부터 8자리까지 반복
	    	int n =0;
	    	for(int i=0;i<8;i++) { //0연산n부터 nnnnnnn연산n을 하게 만들어 주는 dfs
	    		n = (n*10)+ static_N; 
	    		//1자리부터 8자리까지 만들어주고 기존것을 *10 해주고 N을 더해준다.
	    		
	    		//cnt에 사용한만큼 추가해주고 연산처리를 해준다.
	    		dfs(cnt+i+1,num+n);
	    		dfs(cnt+i+1,num-n);
	    		if(num!=0) {
	    			dfs(cnt+i+1,num*n);
	    			if(n!=0) {
	    				dfs(cnt+i+1,num/n);
	    			}
	    		}
	    		
	    	}
	    }
	}

}
