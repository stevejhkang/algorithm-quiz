package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 12. 오후 3:02:09
 * @category 
* @problem_description n*n 2차원 인접조건은 길이차가 1이고 문명의 최초발상지는 문명지역이며,
* 만약 문명 최초 발상지끼리 인접해 있다면, 이들은 처음부터 하나로 결합된다. 
* 한 해가 지날때마다 문명지역은 자신과 인접한 지역에 문명을 전파한다. 만약 두 인접하는 지역에
* 다른 문명이 전파되었거나 한 지역에 둘이상의 다른 문명이 전파된다면 이 문명들은 결합된다.
* 문명이 하나로 결합될때까지 걸리는 최소 햇수를 구하는 프로그램
* @solving_description BFS를 하면서 이미 방문되어 있는 칸들과 다 union해준다.  자신이 속한 집단의 
* 크기가 모든 방문된 칸의 개수와 일치하다면 모든 문명이 하나의 상태
*/

public class BOJ_14868 {
	private static int n;
	private static int k;
	private static int[] parent;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int[][] map;
	private static dot[] al;
	private static boolean[][] visit;
	private static int[] rank;
	private static int year;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());
		
		parent =new int[k+1];
		rank = new int[k+1];
		map = new int[n+1][n+1];
		year=0;
		Queue<dot> queue = new LinkedList<>();
		Queue<dot> queue2 = new LinkedList<>();
		
		for(int i=1;i<=k;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			int x = Integer.parseInt(stringTokenizer.nextToken());
			int y = Integer.parseInt(stringTokenizer.nextToken());
			map[y][x] = i;
			parent[i] =i;
			queue.offer(new dot(y, x, i));
		}
		
		while(true) {
			while(!queue.isEmpty()) {
				dot now = queue.poll();
				int y = now.y; int x =now.x;
				queue2.offer(now);
				for(int i=0;i<4;i++) {
					int ny = y+dy[i]; int nx = x+dx[i];
					if(ny<=0||ny>n||nx<=0||nx>n) continue;
					if(map[ny][nx]!=0&&map[ny][nx]!=map[y][x]) {
						if(!union(map[ny][nx], map[y][x])) {
							k--;
						}
					}
				}
			}//queue
			if(k==1) {
				System.out.println(year);
				return;
			}
			while(!queue2.isEmpty()) {
				dot now = queue2.poll();
				int y = now.y; int x =now.x;
				
				for(int i=0;i<4;i++) {
					int ny = y+dy[i]; int nx = x+dx[i];
					if(ny<=0||ny>n||nx<=0||nx>n) continue;
					if(map[ny][nx]==0) {
						map[ny][nx]=map[y][x];
						queue.offer(new dot(ny, nx, map[y][x]));
					}
					else if(map[ny][nx]!=map[ny][nx]) {
						if(!union(map[ny][nx], map[y][x])) {
							k--;
						}
					}
				}
			}
			year++;
		}
		
	}//main
	static int find(int a) {
		if(parent[a]==a) {
			return a;
		}
		return parent[a]=find(parent[a]);
	}//find
	static boolean union(int a, int b) {
		int roota=find(a);
		int rootb = find(b);
		
		if(roota==rootb) {
			return true;
		}
		else {
//			parent[rootb]= a;
//			return false;
//		}
			if(rank[a]>rank[b]) { //b가 a밑으로 들어가야한다.
				parent[rootb] =a;
			}
			else if(rank[a]<rank[b]) {
				parent[roota]=b;
			}
			else { //같으면
				rank[a]++; 
				parent[rootb]=a;
			}
			return false;
		}
	}//union
	
	static class dot{
		int y,x,num;

		public dot(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}
		
	}
}
