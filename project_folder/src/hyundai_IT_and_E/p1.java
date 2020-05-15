package hyundai_IT_and_E;

import java.util.LinkedList;
import java.util.Queue;

public class p1 {
	public static void main(String[] args) {
		int[][] maps = {{0, 0, 1, 0, 0}, {0, 1, 1, 0, 1}, {0, 0, 1, 0, 1}, {1, 1, 1, 0, 1}};
//		int[][] maps = {{1, 0, 1, 1}, {0, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 0, 0}};
		System.out.println(new Solution().solution(maps));
	}
	static class Solution{
	    private boolean[][] visit;
	    static int[] dy = { -1, 1, 0, 0 };
		static int[] dx = { 0, 0, -1, 1 };
		public int solution(int[][] maps){
	        int answer = 0;
	        
	        int n = maps.length;
	        int m = maps[0].length;
	        
	        visit = new boolean[n][m];
	        for(int i=0;i<n;i++) {
	        	for(int j =0;j<m;j++) {
	        		if(!visit[i][j]&&maps[i][j]==1) {
	        			Queue<dot> queue = new LinkedList<>();
	        			queue.add(new dot(i, j));
	        			
	        			while(!queue.isEmpty()) {
	        				dot now = queue.poll();
	        				int y = now.y;
	        				int x = now.x;
	        				
	        				if(visit[y][x]) continue; //이미 방문했으면 패스
	        				visit[y][x] = true;
	        				
	        				int cnt = 4;//몇 방향에 1과 만나는지 확인한다. 
	        				for(int dir=0;dir<4;dir++) { //
	        					int ny = y+dy[dir];
	        					int nx = x+dx[dir];
	        					
	        					//범위 체크
	        					if(ny<0||ny>=n||nx<0||nx>=m) continue;
	        					
	        					//방문하지 않았고 1이면
	        					if(maps[ny][nx]==1) {
	        						
	        						cnt--;
	        						if(!visit[ny][nx])
	        							queue.add(new dot(ny, nx));
	        					}
	        				}
	        				//해당 땅의 둘레를 더해준다
	        				
//	        				System.out.println("y,x:"+y+","+x+": "+cnt);
	        				answer+=cnt;
	        			}
	        		}
	        	}
	        }
	        
	        return answer;
	    }
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

