package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_17069 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
		
	static int n;
	static int map[][];
	static long dp[][][];
	
	static int dx[] = {0,1,1};
	static int dy[] = {1,0,1};
	
	static final int Right=0;
	static final int Down=1;
	static final int Cross =2;
	
	public static void main(String args[]) throws IOException {
		 n  =Integer.parseInt(br.readLine());
		 
		 map = new int[n][n];
		 dp = new long[n][n][3];
		 for(int i=0; i<n; i++) {
			 for(int j=0; j<n; j++) {
				 for(int k=0; k<3; k++) {
					 dp[i][j][k]=-1;
				 }
			 }
		 }
		 
		 
		 for(int i=0; i<n; i++) {
			 st= new StringTokenizer(br.readLine());
			 for(int j=0; j<n; j++) {
				 map[i][j] = Integer.parseInt(st.nextToken());
			 }
		 }//insert
		 
		 System.out.println(dfs(0,1,0));
	 
		

	}

	private static long dfs(int x, int y, int dir) {
		// TODO Auto-generated method stub
		if(dp[x][y][dir] !=-1)return dp[x][y][dir];
		if(x == n-1 && y == n-1)return 1;
		
		dp[x][y][dir]=0;
		
		if(dir==Right) {
			int nx = x+ dx[dir];
			int ny = y+ dy[dir];
			
			if(canMove(nx,ny)) {
				if(map[nx][ny]==0) {
					dp[x][y][dir]+=dfs(nx,ny,dir);
				}
			}
			
			nx = x +dx[2];
			ny = y +dy[2];
			if(canMove(nx,ny))	{
				if(map[nx][ny]==0 && map[x][y+1]==0 && map[x+1][y]==0) {
					dp[x][y][dir] +=dfs(nx,ny,Cross);
				}
			}
		}
		
		else if(dir==Down) {
			int nx = x +dx[Down];
			int ny = y+ dy[Down];
			
			if(canMove(nx,ny)) {
				if(map[nx][ny]==0) {
					dp[x][y][dir]=dp[x][y][dir]+dfs(nx,ny,dir);
				}
			}
			nx = x +dx[2];
			ny = y +dy[2];
			if(canMove(nx,ny))	{
				if(map[nx][ny]==0 && map[x][y+1]==0 && map[x+1][y]==0) {
					dp[x][y][dir] +=dfs(nx,ny,Cross);
				}
			}
			 
		}
		else if(dir == Cross) {
			int nx = x +dx[Cross];
			int ny = y +dy[Cross];
			if(canMove(nx,ny)) {
				if(map[nx][ny]==0 && map[x][y+1]==0 && map[x+1][y]==0) {
					dp[x][y][dir] = dp[x][y][dir] +dfs(nx,ny,dir);
				}
			}
			nx = x +dx[Right];
			ny = y +dy[Right];
			
			if(canMove(nx,ny)) {
				if(map[nx][ny]==0) {
					dp[x][y][dir] +=dfs(nx,ny,Right);
				}
			}
			
			nx = x +dx[Down];
			ny = y +dy[Down];
			
			if(canMove(nx,ny)) {
				if(map[nx][ny]==0) {
					dp[x][y][dir] +=dfs(nx,ny,Down);
				}
			}	
		}
		return dp[x][y][dir];
	}


	private static boolean canMove(int nx, int ny) {
		// TODO Auto-generated method stub
		if(nx>=0 && ny>=0 && nx<n && ny<n)return true;
		return false;
	}

}


