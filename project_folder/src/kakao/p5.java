package kakao;
import java.util.Arrays;

public class p5 {
	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k=3;
		Solution s = new Solution();
		System.out.println(s.solution(stones, k));
	}
	static class Solution {
	    public int solution(int[] stones, int k) {
	        int answer = 0;
	        int[] dp = new int[stones.length];
	        int min = Integer.MAX_VALUE;
	        for(int i=0;i<stones.length;i++) {
	        	min = Math.min(min, stones[i]);
	        	
	        }
	        for(int i=0;i<stones.length;i++) {
	        	stones[i]-=min;
	        }
	        answer+=min;
	        out:while(true) {
	        	for(int i=0;i<stones.length;i++) {
	        		//한바퀴 돌면서 최솟값을 찾아서 
	        		if(stones[i]<=0) { //0이면 앞으로 k-1개를 체크해본다.
	        			boolean can= false;
	        			int kstack=1;
	        			if(dp[i]!=0) {
	        				kstack=dp[i];
	        			}
	        			while(kstack<k) {
	        				//범위체크
	        				if(i+kstack>=stones.length) {
	        					can= true;
	        					break;
	        				}
	        				//0이 아니면  
	        				if(stones[i+kstack]>0) {
	        					dp[i]=kstack;
	        					can=true;
	        					break;
	        				}
	        				kstack++;
	        			}
	        			//불가능 하면 out
	        			if(!can) {
	        				break out;
	        			}
	        		}
	        		//가능하면 계속 줄여준다.
		        	stones[i]-=1;
		        }
	        	//끝나면 한명을 추가한다.
	        	answer++;
//	        	System.out.println(answer);
//	        	System.out.println(Arrays.toString(stones));
	        }
	        return answer;
	    }
	}//Solution
}
