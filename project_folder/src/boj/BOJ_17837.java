package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17837 {
	private static int n,k;
	private static int[][] map;
	static List[][] pieces;
	static int dy[]= {0,0,0,-1,1}; static int dx[] = {0,1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken()); 
		k = Integer.parseInt(stringTokenizer.nextToken()); 
		
		map = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			for(int j=1;j<=n;j++) {
				map[i][j]= Integer.parseInt(stringTokenizer.nextToken()); 
			}
		}
		pieces = new List[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				pieces[i][j]= new ArrayList<piece>(); 
			}
		}
		
		ArrayList<piece> order = new ArrayList<piece>();
		for(int i=1;i<=k;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int y = Integer.parseInt(stringTokenizer.nextToken());
			int x = Integer.parseInt(stringTokenizer.nextToken());
			int dir = Integer.parseInt(stringTokenizer.nextToken());
			piece new_piece = new piece(y,x,k, dir);
			pieces[y][x].add(new_piece);
			order.add(new_piece);
			//어떻게 할 것인가 일단 큐에 넣어보자.
			
		}
//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<=n;j++) {
//				System.out.print(pieces[i][j].toString()+" ");
//			}
//			System.out.println();
//		}
		for(int i=0;i<order.size();i++) {
			piece now = order.get(i);
			int y = now.y; int x = now.x; int dir = now.dir; int num = now.num;
			//방향에 따라 이동시킨다. 
			int ny= y+dy[dir]; int nx = x+dx[dir];
			if(ny<=0) { //<=
				now.dir = 2;
			}
			else if(ny>n) {
				
			}
			else if(nx<=0) {
				
			}
			else {
				
				
			}
			//이동할 공간의 색을 확인한다. 0흰 1빨강 2파랑
			if(map[ny][nx]==0) {
				
			}
			else if(map[ny][nx]==1) { //빨강
				
			}
			else {//파랑
				
			}
		}
		
		
	}//main
	static class piece implements Comparable<piece>{
		int y; int x;
		int dir;//우, 좌, 상, 하
		int num;
		
		public piece(int y, int x, int dir, int num) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.num = num;
		}
		@Override
		public String toString() {
			return "[dir=" + dir + ", num=" + num + "]";
		}
		@Override
		public int compareTo(piece o) {
			
			if(this.num<o.num) {
				return 1;
			}
			else if(this.num>o.num) {
				return -1;
			}
			else
				return 0;
		}
		
		
	}
}
