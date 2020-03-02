package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17144 {
	static int map[][];
	static int r,c,t,upY,upX,downY,downX;
	static int dy[]= {1,0,-1,0};
	static int dx[]= {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bReader.readLine());
		
		r=Integer.parseInt(stringTokenizer.nextToken());
		c = Integer.parseInt(stringTokenizer.nextToken());
		t = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[r+1][c+1];
		boolean first=false;
		
		for(int i=1;i<=r;i++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());
			for(int j=1;j<=c;j++) {	
				int a=Integer.parseInt(stringTokenizer.nextToken());
				map[i][j]=a;
				if(a==-1&&!first) {
					first=true;
					 upY=i;  upX= j;
					 downY=i+1;  downX=j; 
				}
			}
		}
		for(int i=0;i<t;i++) {
			spread();
			wind();
			
		}
		int sum=0;
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				if(map[i][j]!=-1) {
					sum+=map[i][j];
				}
			}
		}
		System.out.println(sum);
		
		
	}
	static void spread() { //퍼뜨린다.
		int[][] map_copy = new int[r+1][c+1];
		for(int i=1;i<=r;i++) {
			map_copy[i]=map[i].clone();
		}
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				if(map[i][j]>=5) {
					int cnt=0;
					int dust= map[i][j]/5;
					int ny=0; int nx=0;
					for(int k=0;k<4;k++) {
						ny= i+dy[k]; nx = j+dx[k];
						if(ny<=0||ny>r||nx<=0||nx>c||map_copy[ny][nx]==-1) continue;
						map_copy[ny][nx]+=dust;
						cnt++;
					}
					map_copy[i][j]-=dust*cnt;
				}
			}
		}
		for(int i=1;i<=r;i++) {
			map[i]=map_copy[i].clone();
		}
//		for(int i=1;i<=r;i++) {
//			System.out.println(Arrays.toString(map_copy[i]));
//		}
	}
	static void wind() {
		//1
		for(int i=upY-1;i-1>=0;i--) {
			map[i][1]=map[i-1][1];
		}
		//2
		for(int j=0;j+1<=c;j++) {
			map[1][j]=map[1][j+1];
		}
		//3
		for(int i=0;i+1<=upY;i++) {
			map[i][c]=map[i+1][c];
		}
		//4
		for(int j=c;j-1>1;j--) {
			map[upY][j]=map[upY][j-1];
		}
		
		//1
		for(int i=downY+1;i+1<=r;i++) {
			map[i][1]=map[i+1][1];
		}
		//2
		for(int j=0;j+1<=c;j++) {
			map[r][j]=map[r][j+1]; 
		}
		//3
		for(int i=r;i-1>=downY;i--) {
			map[i][c]= map[i-1][c]; 
		}
		//4
		for(int j=c;j-1>=1;j--) {
			map[downY][j]=map[downY][j-1];
		}
		map[upY][upX+1]=0;
		map[downY][downX+1]=0;
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				System.out.print(map[i][j]+"  ");
			}
			System.out.println("");
		}
	}
}
