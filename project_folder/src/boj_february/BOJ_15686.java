package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686 {
	private static int n;
	private static int m;
	private static int[][] map;
	private static ArrayList<dot> chicken_list;
	private static int home_cnt;
	private static dot[] active_chicken;
	private static int min;
	private static ArrayList<dot> home_list;

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bReader.readLine());
		n= Integer.parseInt(stringTokenizer.nextToken());
		m= Integer.parseInt(stringTokenizer.nextToken());
		
		map= new int[n+1][n+1];
		chicken_list = new ArrayList<dot>();
		home_list= new ArrayList<dot>();
		home_cnt=0;
	
		for(int i=1;i<=n;i++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());
			for(int j=1;j<=n;j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				if(a==2) {
					chicken_list.add(new dot(i,j));
				}
				if(a==1) {
					home_cnt++;
					home_list.add(new dot(i, j));
				}
				map[i][j]=a;
			}
		}//for i
		active_chicken=new dot[m];
		min=Integer.MAX_VALUE;
		
		com(0,0);
		
		System.out.println(min);
		
	}
	
	static void com(int k,int index) {
		if(k==m) {
			//m개의 치킨집을 골랐을 때 집과의 거리를 구해야한다.
			min=Math.min(min, calculateDist());
			return;
		}
		for(int i=index;i<chicken_list.size();i++) {
			active_chicken[k]=chicken_list.get(i);
			com(k+1, i+1);/////////i+1!!!!!!!!!
			active_chicken[k]=null;
		}
	}//com
	
	static int calculateDist() {
		int dist=0;
		int copy_home = home_cnt;
		for(int i=0;i<home_list.size();i++) {
			copy_home--;
			int min_dist=Integer.MAX_VALUE;
			int yi = home_list.get(i).y;
			int xj = home_list.get(i).x;
			for(int k=0;k<m;k++) {
				int temp_dist=0;
				dot one_chicken = active_chicken[k];
				int y=one_chicken.y; int x=one_chicken.x;
				temp_dist+=Math.abs(y-yi);
				temp_dist+=Math.abs(x-xj);
				min_dist= Math.min(temp_dist, min_dist);
			}
			dist+=min_dist;
			if(dist>min)
				return Integer.MAX_VALUE;
		}
		return dist;
	}//calculateDist
	
	static class dot{
		int y; int x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
