package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247 {
	private static int n;
	private static int startY;
	private static int startX;
	private static int endY;
	private static int endX;
	private static int min;
	private static spot[] input;
	private static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bufferedReader.readLine());
		StringBuilder sBuilder = new StringBuilder();
		for(int tc=1;tc<=t;tc++) {
			n = Integer.parseInt(bufferedReader.readLine());
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			
			startY = Integer.parseInt(stringTokenizer.nextToken());
			startX = Integer.parseInt(stringTokenizer.nextToken());
			endY = Integer.parseInt(stringTokenizer.nextToken());
			endX = Integer.parseInt(stringTokenizer.nextToken());
			min = Integer.MAX_VALUE;
			input=new spot[n];
			for(int i=0;i<n;i++) {
				int y = Integer.parseInt(stringTokenizer.nextToken());
				int x = Integer.parseInt(stringTokenizer.nextToken());
				input[i]= new spot(y, x); 
			}
			visit = new boolean[n];
			calcDist(startY, startX, 0, 0);
			sBuilder.append("#"+tc+" "+min+"\n");
			
		}
		System.out.println(sBuilder);
	}
	static void calcDist(int y, int x,int k,int dist) {
		if(dist>min) {
			return;
		}
		if(k==n) { //dist계산
			dist+=Math.abs(endX-x)+Math.abs(endY-y);
			min = Math.min(min, dist);
		}
		for(int i=0;i<n;i++) {
			if(!visit[i]) {
				visit[i]= true;
				int ny= input[i].y;
				int nx= input[i].x;
				calcDist(ny, nx , k+1, dist+Math.abs(ny-y)+Math.abs(nx-x));
				visit[i]= false; 
			}
			
		}
	}
	static class spot{
		int y; int x;

		public spot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
