package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;



public class BOJ_17472_2 {
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
	private static boolean[] visit2;
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
		//섬마다 번호를 표시하는 과정
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
		//연결상태와 다리길이의 최솟값을 저장하는 배열 선언 및 최대로 초기화
		land_list=new int[idx+1][idx+1];
		for(int i=1;i<idx+1;i++) {
			for(int j=1;j<idx+1;j++) {
				land_list[i][j]=Integer.MAX_VALUE;
			}
		}
		
//		System.out.println("");
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println("");
//		}
//		System.out.println("");
		
		//전체 배열을 돌면서 0이 아닌게 나타났을때 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				if(map[i][j]!=0) {
					
					//시작 섬으로 정해준다.
					int startLand=map[i][j];
					
					for(int k=0;k<4;k++) {
						int ny = i+dy[k]; int nx=j+dx[k];
						//범위를 벗어나거나 같은 섬이면 패스
						if(ny<0||ny>=n||nx<0||nx>=m) continue;
						if(map[ny][nx]==startLand) continue;
						//바다0가 나오면 같은 방향으로 쭉 간다.
						if(map[ny][nx]==0) {
							int len=1;
							int goY =ny; int goX=nx;
							while(true) {
								goY+=dy[k]; goX+=dx[k];
								//범위 체크
								if(goY<0||goY>=n||goX<0||goX>=m) break;
								if(map[goY][goX]==0) {//바다면 다리길이 추가
									len++;
									continue;
								}
								//0이 아니면서 다른 섬일때
								else if(map[goY][goX]!=startLand) {//
									if(len>=2) {
										int endLand=map[goY][goX];
										//시작섬과 끝섬의 다리길이가 저장된것보다 작으면 갱신하고 break;
										if(land_list[endLand][startLand]>len) {
											land_list[endLand][startLand]=len;
											land_list[startLand][endLand]=len;
										}
										break;
									}
									else { //길이가 1보다 짧으면 그냥 break
										break;
									}
								}
								//0이아니면서 같은 섬이면 벗어난다.
								else if(map[goY][goX]==startLand) {
									break;
								}
								
							}//while
						}//map[ny][nx]==0
					}
				}//map[i][j]!=0
				
			}//forj
		}//fori
		
//		for(int i=1;i<idx+1;i++) {
//			for(int j=i+1;j<idx+1;j++) {
//				if(land_list[i][j]!=Integer.MAX_VALUE) {
//					//정점의 정보를 저장해주어야 한다. 연결 상태를 나타내기위해
//					bridge_lens.add(land_list[i][j]);
//				}
//			}
//			System.out.println("");
//		}
		for(int i=1;i<idx+1;i++) {
//			for(int j=i+1;j<idx+1;j++) {
				for(int j=1;j<idx+1;j++) {
					if(land_list[i][j]==Integer.MAX_VALUE)
						System.out.print(0+" ");
					else {
						System.out.print(land_list[i][j]+" ");
					}
				}
				System.out.println("");
		}
//		if(bridge_lens.size()<idx-1) {
//			System.out.println(-1);
//			return;
//		}
//		min=Integer.MAX_VALUE;
//		com(0,0,0);
//		System.out.println(min);
		min=Integer.MAX_VALUE;
		for(int i=1;i<=idx;i++) {
			visit2 = new boolean[idx+1];
			visit2[i]=true;
			dfs(0,0,i);
			visit2[i]=false;	
		}
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else
			System.out.println(min);
		
	}//main
	static void dfs(int k, int sum,int now) {
		if(k==idx-1) {
			if(min>sum) {
				min=sum;
			}
		}
		for(int i=1;i<=idx;i++) {
			int len = land_list[now][i];
			if(len!=Integer.MAX_VALUE&&!visit2[i]) {//max 값이 아니고(연결되어 있고) 방문하지 않았으면
				visit2[i]=true;
				dfs(k+1, sum+len, i);
				visit2[i]=false;
			}
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
