package dfs_and_bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class backtracking1 {
	static int maze[][]= 
		{
				{0,0,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,1},
				{1,1,1,0,1,1,1,1},
				{1,1,1,0,1,1,1,1},
				{1,0,0,0,0,0,0,1},
				{1,0,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,0}
		};
	static int[]dx= { 0,1,-1,0}; static int	dy[]= {1,0,0,-1};
	static boolean[][] visit= new boolean [8][8];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<position> path = new ArrayList<position>();
	
		Stack<position> stack = new Stack<>();
		stack.push(new position(0, 0));
//		path.add(new position(0, 0));
		
		outer: while(!stack.isEmpty()) {
			
			int y= stack.peek().y; int x= stack.pop().x;
			if(visit[y][x])
				continue;
			
			visit[y][x]=true;
			
			path.add(new position(y, x));
			
			for(int i=0;i<4;i++) {
				int ny=y+dy[i]; int nx=x+dx[i];
				
				if(ny>=0&&ny<8&&nx>=0&&nx<8&&maze[ny][nx]!=1&&!visit[ny][nx]) {
					if(ny==7&&nx==7) {
						path.add(new position(ny, nx));
						System.out.println("can go!");
						System.out.println(path);
						break outer;
					}
					stack.push(new position(ny, nx));
				}
				
			}
		}
	}
	
}
class position {
	int y; int x;
	public position(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}
	@Override
	public String toString() {
		return "[y=" + y + ", x=" + x + "]";
	}
	

}
