package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 14. 오전 12:18:35
 * @category BFS+그래프+Kruskal+Union find
* @problem_description 섬들을 전부 잇는 최소의 다리길이 구하기
* @solving_description 먼저 섬들을 구분짓기 위해 BFS를 통해서 섬번호를 마스킹한다. 그 후 전체적으로 돌면서 섬을 발견하면 사방탐색으로 자기섬이 아닌
* 바다를 발견하면 다른 섬을 만날때까지 길이를 측정하면서 한 방향으로 이동한다. 그렇게 섬을 발견하면 bridge 리스트에 추가해준다.
* 그 후에 유니온 파인드 방법을 통해 모든 섬이 같은 부모를 가졌는지(모두 이어졌는지 확인하고) 거리를 출력하게 한다.
*/
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
	static int[] parent;
	static ArrayList<Node> bridge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		bridge = new ArrayList<>();
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
											bridge.add(new Node(startLand, endLand, len));
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

		min=Integer.MAX_VALUE;

		parent = new int[idx+1];
		for(int i=1;i<=idx;i++) {
			parent[i]=i;
		}
		kruskal();
		
	}//main
	static void kruskal() {
		Collections.sort(bridge);
		int cnt=0;
		int dis=0;
		for(Node node: bridge) {
			if(find(node.r)!=find(node.c)) { //부모가 같지 않으면 
				union(node.r, node.c); //같게 한다.
				dis+=node.d;
				cnt++;
			}
		}
		//그렇게 전체 다 같게 섬개수-1개만큼 안되면 같은 합쳐진게 아니므로 -1
		if(cnt!=idx-1)System.out.println(-1);
		else System.out.println(dis);
	}

	static int find(int a) {
		if(parent[a]==a) {
			return a;
		}
		return parent[a]=find(parent[a]);
	}
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootB] = rootA;
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
	static class Node implements Comparable<Node> {
		int r, c, d;
		
		Node(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}
}