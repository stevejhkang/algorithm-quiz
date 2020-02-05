package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//min값보다 크면 더이상 탐색을 하지 않는다. 
public class JG_1113_2 {
	static int[][] map ; static int n,m; //세로 가로
	static int lastY, lastX;
	static boolean[][] visit;
	static int min;
	static int[] dy= {-1,0,1,0}; static int[] dx= {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		map=new int[n][m]; visit= new boolean[n][m];
		
		stringTokenizer = new StringTokenizer(br.readLine());
		lastY= Integer.parseInt(stringTokenizer.nextToken());
		lastX= Integer.parseInt(stringTokenizer.nextToken());
		
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]= Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		min=Integer.MAX_VALUE;
		recursion(new dot(0, 0, 1, 0));
//		recursion(new dot(0, 0, 2, 0));
		System.out.println(min);
	
	}
	static boolean canMove(int y, int x) {
		return(y>=0&&y<n&&x>=0&&x<m&&map[y][x]==1);
	}
	static void recursion(dot temp) {
		int y = temp.y; int x = temp.x; int dir= temp.dir; int corner= temp.corner;
		if(min<corner) { /////////////////////////////////////////////////////////////////백트레킹//////////////////////////////////////////////////////////////////////////
			return;
		}
		if(visit[y][x])
			return;
		visit[y][x]=true;
		if(y==lastY&&x==lastX) {
			if(min>corner) {
				min=corner;
			}
			
			visit[y][x]= false;
			return;
		}
		if(y==0&&x==0) {
			for(int i=0;i<4;i++) {
				int ny=y+dy[i]; int nx= x+dx[i];
				if(canMove(ny, nx)&&!visit[ny][nx]) {
					if(dir!=i) {
						recursion(new dot(ny, nx, i, corner+1));
					}
					else {
						recursion(new dot(ny, nx, dir, corner));
					}
				}
			}
			dir=2;
			for(int i=0;i<4;i++) {
				int ny=y+dy[i]; int nx= x+dx[i];
				if(canMove(ny, nx)&&!visit[ny][nx]) {
					if(dir!=i) {
						recursion(new dot(ny, nx, i, corner+1));
					}
					else {
						recursion(new dot(ny, nx, dir, corner));
					}
				}
			}
		}
		else {
			for(int i=0;i<4;i++) {
				int ny=y+dy[i]; int nx= x+dx[i];
				if(canMove(ny, nx)&&!visit[ny][nx]) {
					if(dir!=i) {
						recursion(new dot(ny, nx, i, corner+1));
					}
					else {
						recursion(new dot(ny, nx, dir, corner));
					}
				}
			}
		}
		visit[y][x]=false;
	}
	static class dot{
		int y;
		int x;
		int corner;
		int dir;
		
		public dot(int y, int x,int dir,int corner) {
			super();
			this.y = y;
			this.x = x;
			this.corner = corner;
			this.dir=dir;
		}

		@Override
		public String toString() {
			return "dot [y=" + y + ", x=" + x + ", corner=" + corner + "]";
		}
	}
}
