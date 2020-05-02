package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686 {
	private static int n;
	private static int m;
	private static int[][] map;
	private static ArrayList<dot> list;
	private static int min_dist;
	private static dot[] remain;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n][n];
		list = new ArrayList<dot>();
		
		
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<n;j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = a;
				if(a==2) { //치킨 집이면
					list.add(new dot(i,j));
				}
			}
		}
		min_dist = Integer.MAX_VALUE;
		remain = new dot[m];
		//dfs시켜서 m개 선택해준다.
		dfs(0,0);
		System.out.println(min_dist);
		
	}//main
	static void dfs(int r, int index) {
		if(r==m) {
			//거리를 계산한다.
			calDistance();
			return;
		}
		for(int i=index;i<list.size();i++) {
			remain[r] = list.get(i);
			dfs(r+1,i+1);
		}
		
	}//dfs
	
	static void calDistance() {
		int dist = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]==1) {
					int min_onedist = Integer.MAX_VALUE;
					//m개의 치킨집과의 최소 거리를 구한다.
					for(int k=0;k<m;k++) {
						int chic_y = remain[k].y;
						int chic_x = remain[k].x;
						
						int temp_dist= Math.abs(chic_y-i)+Math.abs(chic_x-j);
						min_onedist=Math.min(min_onedist, temp_dist);
					}
					dist+=min_onedist;
				}
			}
		}//for i
		min_dist=Math.min(min_dist, dist);
	}//calDistance
	
	static class dot{
		int y, x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}//dot
}
