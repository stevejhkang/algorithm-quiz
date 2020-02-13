package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class BOJ_14500 {
	private static int n;
	private static int m;
	private static int[][] map;
	static int dy[]= {0,1,0,-1}; //우 하 좌 상
	static int dx[] = {1,0,-1,0};
	private static int max;
//	static ArrayList<dot> arrayList = new ArrayList<>();
	private static int[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		map= new int[n][m];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(stringTokenizer.nextToken());
			}
		}//for i
		max =Integer.MIN_VALUE;
		visit= new int[n][m];
		for (int i = 0; i < n; i++) {
			for(int j=0;j<m;j++) {
//				arrayList.add(new dot(i, j));
				visit[i][j]=1; 
				dfs(i, j, 1, map[i][j]);
				extra(i, j);
//				arrayList.remove(0);
				visit[i][j]=0; 
			}
		}
		System.out.println(max);
		
	}//main
	static boolean canMove(int ny, int nx) {
		return(ny>=0&&ny<n&&nx>=0&&nx<m);
	}//canMove
	static void dfs(int y, int x, int cnt,int sum) {
		if(cnt==4) {
			if(max<sum) {
//				System.out.println(y+","+x);
				max=sum;
//				System.out.println(max);
//				System.out.println(sum);
//				for(int i=0;i<arrayList.size();i++) {
//					System.out.print(arrayList.get(i)+" ");
//				}
//				System.out.println("");
			}
			return;
		}
		for(int i=0;i<4;i++) {
			int ny = y+dy[i]; int nx = x+dx[i];
			if(canMove(ny, nx)&&visit[ny][nx]==0) {
				visit[ny][nx]=1;
//				arrayList.add(new dot(ny, nx));
				dfs(ny, nx, cnt+1, sum+map[ny][nx]);
//				arrayList.remove(cnt-1);
				visit[ny][nx]=0; 
			}
		}
	}//dfs
	static void extra(int i, int j) {
		int max_temp =Integer.MIN_VALUE;
		int temp =0;
		//ㅓ
		if(canMove(i+1, j-1)&&canMove(i+1, j)&&canMove(i+2, j)) {
			temp=map[i+1][j-1]+map[i+1][j]+map[i+2][j]+map[i][j];
		}
		max_temp=Math.max(max_temp, temp);
		temp=0;
		//ㅗ
		if(canMove(i+1, j-1)&&canMove(i+1, j)&&canMove(i+1, j+1)) {
			temp=map[i+1][j-1]+map[i+1][j]+map[i+1][j+1]+map[i][j];
		}
		max_temp=Math.max(max_temp, temp);
		temp=0;
		//ㅏ
		if(canMove(i+1, j)&&canMove(i+1, j+1)&&canMove(i+2, j)) {
			temp=map[i+1][j]+map[i+1][j+1]+map[i+2][j]+map[i][j];
		}
		max_temp=Math.max(max_temp, temp);
		temp=0;
		//ㅜ
		if(canMove(i, j+1)&&canMove(i, j+2)&&canMove(i+1, j+1)) {
			temp=map[i][j+1]+map[i][j+2]+map[i+1][j+1]+map[i][j];
		}
		max=Math.max(max_temp, max);
	}
	static class dot {
		int y; int x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "dot [y=" + y + ", x=" + x + "]";
		}
		
	}
}
