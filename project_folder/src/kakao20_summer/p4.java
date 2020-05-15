package kakao20_summer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 9. 오후 2:10:12
 * @category 
* @level 3
* @problem_description 
* 00~n-1n-1까지 가는데 걸리는 턴수와 꺾은 경로
* 그리고 
* @solving_description 
*/

public class p4 {
	public static void main(String[] args) {
//		int[][] board = 	{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//		int[][] board = 	{{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, 
//		                	 {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1}, 
//		                	 {0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}};
//		int[][] board = 	{{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}};
		int[][] board = 		{{0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0}, {0, 0, 1, 0, 0, 0},
		                		 {1, 0, 0, 1, 0, 1}, {0, 1, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0}};
		
		System.out.println(new Solution().solution(board));
	}
	static class Solution {
		static int[] dy = { -1, 1, 0, 0 }; //상 하 좌 우
		static int[] dx = { 0, 0, -1, 1 };
		private int[][] visit;
		
	    public int solution(int[][] board) {
	        int answer = 0;


	        int n = board.length;
	        visit = new int [n][n]; //해당 점까지 가는데 걸리는 가격의 최솟값을 저장
	        for(int i=0;i<n;i++) { 
	        	Arrays.fill(visit[i], Integer.MAX_VALUE); //맥스값으로 초기화 시켜준다.
	        }
	        
	        int min = Integer.MAX_VALUE;
	        Queue<dot> queue = new LinkedList<dot>();
	        if(board[1][0]!=1) {
	        	
	        }
	        if(board[0][1]!=1) {
	        	
	        }
	        queue.add(new dot(0, 0, 1, 0)); //처음 점을 큐에추가 //아래와 오른쪽으로
	        queue.add(new dot(0,0,3,0));
	        
	        while(!queue.isEmpty()) {
	        	dot now = queue.poll();
	        	int y = now.y;
	        	int x = now.x;
	        	int direction = now.direction;
	        	int price = now.price;
	        	
	        	//MAX값이 아니고
	        	if(visit[y][x]!=Integer.MAX_VALUE&&visit[y][x]<price) { //이미 저장되어 있는게 싸면 그냥 넘긴다.
	        		continue;
	        	}
	        	System.out.println("y,x: "+y+","+x);
	        	System.out.println(price);
	        	visit[y][x] = price; 
	        	
	        	if(y==n-1&&x==n-1) {
	        		min= Math.min(min, price);
	        	}
	        	
	        	for(int dir=0;dir<4;dir++) {
	        		int ny = y+dy[dir];
	        		int nx = x+dx[dir];
	        		
	        		//범위 체크
	        		if(ny<0||ny>=n||nx<0||nx>=n) {
	        			continue;
	        		}
	        		int new_price=0;
	        		if(dir==direction) { //방향이 같거나 처음이면 직선도로
	        			new_price= price+100;
	        		}
	        		else if(dir!=direction){
	        			new_price= price+600;
	        		}
	        		//돌이 아니고 현재 방문하려고 하는 곳에 저장된 값보다 적으면 들어간다.
	        		if(board[ny][nx]!=1&&visit[ny][nx]>new_price) {
	        			queue.add(new dot(ny, nx, dir, new_price));
	        		}
	        		
	        	}
	        }
	        
	        return min;
	    }
	    static void dfs(dot now) {
	    	
	    }
	    static class dot {
	    	int y, x, direction, price;

			public dot(int y, int x, int direction, int price) {
				super();
				this.y = y;
				this.x = x;
				this.direction = direction;
				this.price = price;
			}

			
		
	    	
	    }
	}
}
