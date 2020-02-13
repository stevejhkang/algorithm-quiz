package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17472 {
	private static int n;
	private static int m;
	private static int[][] map;
	static int dy[]= {-1,0,1,0}; static int dx[]= {0,1,0,-1}; //상우하좌
	private static int[][] visit;
	private static int[][] land_list;
	static ArrayList<Integer> bridge_lens = new ArrayList<>();
	private static int min;
	private static int idx;
	private static boolean[] link;
	 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		idx=0;
		visit = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				if(visit[i][j]==0&&map[i][j]==1) {//번호 표시
					idx++;
					Queue<land> q = new LinkedList<land>();
					q.offer(new land(i,j));
					
					while(!q.isEmpty()) {
						land now = q.poll();
						int y = now.y; int x = now.x;
						map[y][x]=idx;
						visit[y][x]=1;
						for(int k=0;k<4;k++) {
							int ny = y+dy[k]; int nx=x+dx[k];
							//범위 벗어나거나 방문하거나 섬이 아닐때
							if(ny<0||ny>=n||nx<0||nx>=m) continue;
							if(visit[ny][nx]==1) continue;
							if(map[ny][nx]==0) continue;
							//나머지는 넣는다.
							q.offer(new land(ny, nx));
						}
					}//while
					
				}
				
			}
		}//fori
		land_list=new int[idx+1][idx+1];
		for(int i=1;i<idx+1;i++) {
			for(int j=1;j<idx+1;j++) {
				land_list[i][j]=Integer.MAX_VALUE;
			}
		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println("");
//		}
		visit = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]!=0) {
					int startLand=map[i][j];
					for(int k=0;k<4;k++) {
						int ny = i+dy[k]; int nx=j+dx[k];
						//범위를 벗어나거나 같은 섬이면  
						if(ny<0||ny>=n||nx<0||nx>=m) continue;
						if(map[ny][nx]==startLand) continue;
						//바다0가 나오면
						if(map[ny][nx]==0) {
							int len=1;
							int goY =ny; int goX=nx;
							while(true) {
								goY+=dy[k]; goX+=dx[k];
								if(goY<0||goY>=n||goX<0||goX>=m) break;
								if(map[goY][goX]==0) {
									len++;
									continue;
								}
								if(map[goY][goX]!=0) {
									if(len>=2) {
										int endLand=map[goY][goX];
										if(land_list[endLand][startLand]>len) {
											land_list[endLand][startLand]=len;
											land_list[startLand][endLand]=len;
										}
										break;
									}
									else {
										break;
									}
								}
								
							}//while
						}
					}
				}
			}//forj
		}//fori
		
		for(int i=1;i<idx+1;i++) {
			for(int j=i+1;j<idx+1;j++) {
				if(land_list[i][j]!=Integer.MAX_VALUE) {
					bridge_lens.add(land_list[i][j]);
				}
			}
//			System.out.println("");
		}
//		for(int i=1;i<idx+1;i++) {
////			for(int j=i+1;j<idx+1;j++) {
//				for(int j=1;j<idx+1;j++) {
//					if(land_list[i][j]==Integer.MAX_VALUE)
//						System.out.print(0+" ");
//					else {
//						System.out.print(land_list[i][j]+" ");
//					}
//				}
//				System.out.println("");
//		}
		
		if(bridge_lens.size()<idx-1) {
			System.out.println(-1);
			return;
		}
		min=Integer.MAX_VALUE;
		com(0,0,0);
		//dfs
		link=new boolean[idx+1];
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		link[1]=true;
		while(!stack.isEmpty()) {
			int a = stack.pop();
			for(int i=1;i<idx+1;i++) {
				if(land_list[i][a]!=Integer.MAX_VALUE&&!link[i]) {
					link[i]=true;
					stack.push(i);
				}
			}
		}
		boolean flag =true;
		for(int i=1;i<idx+1;i++) {
			if(link[i]==false) {
				flag=false;
			}
		}
		if(!flag) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
		
		
	}
	
	static void com(int k,int sum,int index) {
		if(k==idx-1) {
			if(min>sum) {
				min=sum;
			}
			return;
		}
		for(int i=index;i<bridge_lens.size();i++) {
			int now_len=bridge_lens.get(i);
			if(min<sum+now_len)
				continue;
			com(k+1, sum+now_len, i+1);
		}
	}
	static class land{
		int y;
		int x;
		public land(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	static class bridge{
		int land1; 
		int land2;
		int len;
		public bridge(int land1, int land2, int len) {
			super();
			this.land1 = land1;
			this.land2 = land2;
			this.len = len;
		}
	}
}
