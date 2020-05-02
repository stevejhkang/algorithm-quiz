package programmers;
import java.util.Collections;

public class 이분탐색_입국심사 {
	public static void main(String[] args) {
		Solution s = new Solution();
		
		int n = 6;
		int[] times = {7,10};
		System.out.println(s.solution(n, times));
	}
	static class Solution {
	    public long solution(int n, int[] times) {
	        long answer = Long.MAX_VALUE;
	        
	        long left=0;
	        long right= 0;
	        
	        //오른쪽 값 찾는다.
	        for(int i=0;i<times.length;i++) {
	        	if(right<times[i]) {
	        		right = times[i];
	        	}
	        }
	        right  = n*right; //최대로 걸리는 시간은 제일 늦게 처리하는 사람 * n 그것을 최댓값으로 잡는다.
	        
	        while(left<=right) {
//	        	System.out.println("left: "+left+"right: "+right);
	        	long people=0;
	        	long mid = (left+right)/2;
	        	//이걸 가지고 이 시간동안 각각의 심사관이 몇명을 심사할 수 있는 지 확인한다.
	        	
	        	for(int i=0;i<times.length;i++) {
	        		people += mid/times[i];
	        	}
	        	if(people>=n) { //충분히 커버하면 시간이 큰것이므로 더 줄인다.
	        		right = mid-1;
	        		answer= (answer>mid?mid:answer);
	        	}
	        	else {
	        		left = mid+1;
	        	}
//	        	System.out.println("left: "+left+"right: "+right);
	        }
//	        answer=left;
	        
	        return answer;
	    }
	}
}
