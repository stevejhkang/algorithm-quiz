package kakao;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 8. 오전 11:00:17
 * @category BFS
* @level 1
* @problem_description
*  몇개의 영역이 있는지
* @solving_description
*  
*/

public class 코드17예선_컬러링북 {
	public static void main(String[] args) {
		int m = 6; 
		int n = 4;
//		int[][] picture = {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		
		System.out.println(Arrays.toString(new Solution().solution(m, n, picture)));
	}
	static class Solution {
		
		static int[] dy = { -1, 1, 0, 0 };
		static int[] dx = { 0, 0, -1, 1 };
		private boolean[][] visit;
		
	    public int[] solution(int m, int n, int[][] picture) {
	        int numberOfArea = 0;
	        int maxSizeOfOneArea = 0;

	        visit = new boolean[m][n];
//	        Set<Integer> set = new HashSet<Integer>();
	        for(int i=0;i<m;i++) {
	        	for(int j=0;j<n;j++) {
	        		if(!visit[i][j]&&picture[i][j]!=0) { //방문한적이 없으면 방문하고 BFS 돌린다.
	        			numberOfArea++;
	        			Queue<dot> queue = new LinkedList<>();
	        			queue.add(new dot(i,j));
	        			int area= 0;
	        			while(!queue.isEmpty()) {
	        				dot now = queue.poll();
	        				int y = now.y;
	        				int x =now.x;
	        				
	        				if(visit[y][x]) continue;
	        				visit[y][x]= true;
	        				area++;
	        				
	        				for(int dir=0;dir<4;dir++) {
	        					int ny = y+dy[dir];
	        					int nx = x+dx[dir];
	        					
	        					if(ny<0||ny>=m||nx<0||nx>=n) {
	        						continue;
	        					}
	        					if(!visit[ny][nx]&&picture[i][j]==picture[ny][nx]) {
	        						queue.add(new dot(ny, nx));
	        					}
	        				}
	        			}//while
	        			if(picture[i][j]!=0) {
	        				maxSizeOfOneArea= Math.max(maxSizeOfOneArea, area);
	        			}
	        		}
	        	}
	        }
	        
	        int[] answer = new int[2];
	        answer[0] = numberOfArea;
	        answer[1] = maxSizeOfOneArea;
	        return answer;
	    }//main
	    
	    static class dot{
	    	int y,x;

			public dot(int y, int x) {
				super();
				this.y = y;
				this.x = x;
			}
	    	
	    }
	}
}
