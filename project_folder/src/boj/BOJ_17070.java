package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17070 {
	static int n,ans=0;
	static int[][] map;
	//0: 가로, 1: 세로, 2: 대각선
	static int[][] dx= {{1,0,1},{0,0,1},{1,0,1}};
	static int[][] dy= {{0,0,1},{0,1,1},{0,1,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n]; //벽확인
		//벽입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int a=Integer.parseInt(st.nextToken());
				map[i][j]=a;
			}
		}
		//첫 방향은 가로
		int dir=0;
		Stack<dot> stack = new Stack<>();
		stack.push(new dot(0,1,dir));
		int cnt=0;
		
		while(!stack.isEmpty()) {
			dot temp = stack.pop();
			//방문했었으면
//			if(visit[temp.dir][temp.y][temp.x]) {
//				continue;
//			}
//			System.out.println(temp.y+":"+temp.x);
			//마지막에 도달 했을 때
			int y = temp.y; int x= temp.x;
			dir=temp.dir;
			
			if(y==n-1&&x==n-1) {
				cnt++;
				continue;
			}
			
			for(int i=0;i<3;i++) {
				if(dy[dir][i]==0 &&dx[dir][i]==0) continue;
				
				int ny=y+dy[dir][i]; int nx=x+dx[dir][i];
				if(0>ny||ny>=n||nx<0||nx>=n) continue;
				
				if((i==0||i==1)&&map[ny][nx]==0) {
					stack.push(new dot(ny, nx, i));
				}
				else {
					if(map[ny][nx]==0&&map[ny-1][nx]==0&&map[ny][nx-1]==0) 
						stack.push(new dot(ny, nx, i));
				}
			}
		}
		System.out.println(cnt);
	}
	static class dot{
		int y;
		int x;
		int dir;
		public dot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "[y=" + y + ", x=" + x + ", dir=" + dir + "]";
		}
		
		
	}

}

