package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	private static int m;
	private static int n;
	private static int[][] map;
	private static int[][] visit;
	private static ArrayList<tomato> riped_toma;
	private static int raw_tomato;
	static int dy[] = {1,0,-1,0}; //하 우 상 좌
	static int dx[] = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		m= Integer.parseInt(stringTokenizer.nextToken());
		n= Integer.parseInt(stringTokenizer.nextToken());
		map= new int[n][m];
		visit = new int[n][m];
		
		raw_tomato= 0;
		riped_toma= new ArrayList<tomato>();
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<m;j++) {
				int a =Integer.parseInt(stringTokenizer.nextToken());
				map[i][j]= a;
//				visit[i][j]=0;
				if(a==1) {
					riped_toma.add(new tomato(i, j));
					visit[i][j]=0;
				}
				if(a==0) {
					visit[i][j]= Integer.MAX_VALUE;
					raw_tomato++;
				}
			}
		}//for i
		Queue<tomato> queue =new LinkedList<tomato>();
		for(int i=0;i<riped_toma.size();i++) {
			queue.offer(riped_toma.get(i));
		}
		while(!queue.isEmpty()) {
			tomato now = queue.poll();
			int y = now.y; int x = now.x;
			
			for(int i=0;i<4;i++) {
				int ny = y+dy[i]; int nx = x+dx[i];
				//범위 벗어나면 이미 방문했으면 
				if(0>nx||nx>=m||0>ny||ny>=n) continue;
				//0이 아니면서 
				if(visit[ny][nx]==-1) continue;
				if(visit[ny][nx]==Integer.MAX_VALUE||visit[ny][nx]>visit[y][x]+1) {
					visit[ny][nx]=visit[y][x]+1;
					queue.offer(new tomato(ny, nx));
				}
			}
		}//while
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				System.out.print(visit[i][j]+" ");
//			}
//			System.out.println("");
//		}
		int max =Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(visit[i][j]!=0&&max<visit[i][j])
					max=visit[i][j];
			}
		}
		if(max==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else if(raw_tomato==0) {
			System.out.println(0);
		}
		else {
			System.out.println(max);
		}
		
		
	}//main
	static class tomato{
		int y; int x;

		public tomato(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
