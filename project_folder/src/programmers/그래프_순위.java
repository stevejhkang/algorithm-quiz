package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 19. 오후 3:03:20
 * @category 
* @problem_description 주어진 경기 결과를 가지고 선수들의 순위를 매기려 한다. 정확하게 순위를 매길 수 없는데
* n과 결과배열이 매개변수로 주어질 때 정확히 순위를 매길 수 있는 선수의 수를 리턴하도록 하라. 
* @solving_description 
* 흠... 어디서 많이 풀어봤던 문제인데..... 순서가 정해져 있는 작업을 차례로 수행해야할 때 그 순서를 결정해주기 위해
* 사용하는 위상정렬인 줄 알았지만... 모든 한 정점에서 모든 다른 정점으로 이어져 있는 지를 확인해야 하므로 플로이드 와샬
* 알고리즘이었다.
*/

public class 그래프_순위 {
	public static void main(String[] args) {
		int n = 5; int[][] results = {{4,3},{4,2},{3,2},{1,2},{2,5}};
		Solution s = new Solution();
		System.out.println(s.solution(n, results));
	}
	static class Solution {
	    private int[] degree;
		private boolean[] visit;
		private int[][] map;

		public int solution(int n, int[][] results) {
	       int answer = 0;
	        
	        //초기화해주고
	       map = new int[n+1][n+1];
	       for(int i=1;i<=n;i++) {
	    	   for(int j=1;j<=n;j++) {
	    		   if(i==j) continue;
	    		   map[i][j] = 987654321;
	    	   }
	       }
	       
	       for(int i=0;i<results.length;i++) {
	    	   int from = results[i][0];
	    	   int to = results[i][1];
	    	   map[from][to]=1;
	       }
	        
	       for(int k=1;k<=n;k++) {
	    	   for(int i=1;i<=n;i++) {
	    		   for(int j=1;j<=n;j++) {
	    			   if(map[i][j]>map[i][k]+map[k][j]) {
	    				   map[i][j]= map[i][k]+map[k][j];
	    			   }
	    		   }
	    	   }
	       }
	       
	       for(int i=1;i<=n;i++) {
	    	   int cnt=n;
	    	   for(int j=1;j<=n;j++) {
	    		   if(map[i][j]!=987654321||map[j][i]!=987654321) {
	    			   cnt--;
	    		   }
	    	   }
	    	   if(cnt==0) {
	    		   answer++;
	    	   }
	       }
	        return answer;
	    }
	}
}
