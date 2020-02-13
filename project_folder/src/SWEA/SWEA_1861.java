package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861 {
	private static int n;
	private static int[][] map;
	static int dy[]= {1,0,-1,0};
	static int dx[] = {0,1,0,-1};
	private static int max;
	private static int idx;
	private static int startY;
	private static int startX;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int t  = Integer.parseInt(bReader.readLine());
		for(int tc=1;tc<=t;tc++) {
			n= Integer.parseInt(bReader.readLine());
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				StringTokenizer sTokenizer =new StringTokenizer(bReader.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(sTokenizer.nextToken());
				}
			}//fori
			max=Integer.MIN_VALUE;
			idx=0;
			startY=0; startX=0;
			for (int i = 0; i < n; i++) {
				for(int j=0;j<n;j++) {
					startY=i; startX=j;
					dfs(i,j,1);
				}
			}
			StringBuilder sBuilder =new StringBuilder();
			sBuilder.append("#"+tc+" "+idx+" "+max);
			System.out.println(sBuilder);
		} //for tc
		
	}//main
	static void dfs(int y, int x,int cnt) {
		boolean flag= false;
		
		for(int i=0;i<4;i++) {
			int ny = y+dy[i]; int nx= x+dx[i];
			if(ny<0||ny>=n||nx<0||nx>=n) continue;
			if(map[y][x]+1==map[ny][nx]) {
				flag=true;
				dfs(ny, nx, cnt+1);
			}
		
		}
		//4방향으로 아예 더이상 못갔으면
		if(!flag) {
			if(max<cnt) {
				max=cnt;
				idx=map[startY][startX];
			}
			else if(max==cnt&&idx>map[startY][startX]) {
				
					idx=map[startY][startX];
				
			}
			return;
		}
	}
}
