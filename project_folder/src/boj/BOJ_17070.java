package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_17070 {
	static int n;
	//0: 가로, 1: 세로, 2: 대각선
	static int[][] map;
	static boolean visit[][][];
	public static void main(String[] args) throws IOException {
//		Scanner scanner =new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		visit = new boolean[3][n+1][n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				int a=Integer.parseInt(st.nextToken());
				map[i][j]=a;
			}
		}
		int dir=0;
		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(1,2,dir));
		int cnt=0;
		while(!queue.isEmpty()) {
			dot temp = queue.poll();
			if(visit[temp.dir][temp.y][temp.x]) {
				continue;
			}
//			System.out.println(temp.y+":"+temp.x);
			if(temp.y==n&&temp.x==n) {
				cnt++;
				continue;
			}
			dir=temp.dir;
			int ny=temp.y,nx=temp.x;
			if(dir==0) { //가로 0,1
				for(int i=0;i<2;i++) {
					ny=temp.y;nx=temp.x;
					if(i==0) { //가로
						nx++;
					}
					else {
						ny++; nx++;
					}
					if(canMove(ny, nx, i)&&!visit[i][ny][nx]) {
						
						queue.offer(new dot(ny,nx,i));
					}
				}
			}
			else if(dir==2) { //세로 1,2
				for(int i=1;i<3;i++) {
					ny=temp.y;nx=temp.x;
					if(i==1) { //대각선
						ny++; nx++;
					}
					else {//세로
						ny++;
					}
					if(canMove(ny, nx, i)&&!visit[i][ny][nx]) {
						queue.offer(new dot(ny,nx,i));
					}
				}
			}
			else {//대각선 0,1,2
				for(int i=0;i<3;i++) {
					ny=temp.y;nx=temp.x;
					if(i==0) {
						nx++;
					}
					else if(i==1) {
						ny++;nx++;
					}
					else {
						ny++;
					}
					if(canMove(ny, nx, i)&&!visit[i][ny][nx]) {
						queue.offer(new dot(ny,nx,i));
					}
				}
			}
		}
		System.out.println(cnt);
	
	}
	static boolean canMove(int y,int x,int dir) { //dir 0가로, 1대각, 2세로
		boolean bound=(y<=n &&x<=n);
		boolean wall=true;
		if(bound) {
			if(dir==0) {
				if(map[y][x]==1)
					wall=false;
					
			}
			else if(dir==1) {
				if(map[y-1][x]==1||map[y][x]==1||map[y][x-1]==1)
					wall=false;
			}
			else {
				if(map[y][x]==1)
					wall= false;
			}
		}
		return bound&&wall;
	}
}
class dot{
	int y;
	int x;
	int dir;
	public dot(int y, int x, int dir) {
		super();
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
	@Override
	public String toString() {
		return "[y=" + y + ", x=" + x + ", dir=" + dir + "]";
	}
	
	
}
