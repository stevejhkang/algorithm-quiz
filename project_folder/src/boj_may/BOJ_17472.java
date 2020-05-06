package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 6. 오후 12:11:24
 * @category 
* @level 3
* @problem_description 
* 세로 n 가로 m 
* 다리의 길이는 다리가 격자에서 차지하는 칸의 수. 다리를 연결해서 모든 섬을 연결하려고 한다.
* 섬A에서 다리를 통해 섬 B로 갈 수 있을 대 A,B는 연결되었다고 한다. 
* 1. 다리는 한 다리의 방향이 중간에 바뀌면 안되고 다리의 길이는 2이상이어야한다.
* 2. 방향이 가로인 다리의 양끝이 가로방향으로 섬과 인접해야하고 세로는 세로와 인접해야한다.
*  		즉 직진으로 쭉 가야된다는 말.
* 3. a와 b를 연결하는 다리가 중간에 C와 인접한 바다를 지나가는 경우도 연결된 것 아님
* 		직접 연결되어야 된다는 의미인듯
* 4. 한 연결다리를 통해서 3개이상 연결되는 일이 없으므로 이것은 그래프로 보면 된다.
* 5. 교차하는 경우는 각칸이 중복해서 길이를 모두 포함해야한다.
* @solving_description 
* 
* 번호를 매기고 다리를 연결하는데
* 1. 범위를 벗어나거나 자기숫자가 나오면 멈추고
* 2. 다른 숫자가 나오고 길이가 2이상 되었을 때 간선을 추가한다.
* 3. 크루스칼을 이용해본다.
* 
*/

public class BOJ_17472 {
	private static int n;
	private static int m;
	private static int[][] map;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean[][] visit;
	private static int[] parents;
	private static boolean[] visit2;
	
	public static void main(String[] args) throws IOException {
		//
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n][m];
		
		//입력받고
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}//for i
		
		//섬에 번호를 매겨준다.
		Queue<dot> queue = new LinkedList<>();
		int cnt = 1;
		visit = new boolean[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==1&&!visit[i][j]) {
					map[i][j] = cnt;
					queue.add(new dot(i, j));
					while(!queue.isEmpty()) {
						dot now = queue.poll();
						int y = now.y; int x = now.x;
						
						if(visit[y][x]) continue;
						visit[y][x]=true;
						
						for(int dir=0;dir<4;dir++) {
							int ny = y+dy[dir];
							int nx = x+dx[dir];
							
							if(nx<0||nx>=m||ny<0||ny>=n) continue;
							
							if(map[ny][nx]==1&&!visit[ny][nx]) {
								map[ny][nx] = cnt;
								queue.add(new dot(ny, nx));
							}
						}
					}
					cnt++;
				}
			}
		}//for i
		
		//섬들을 연결할 수 있는 모든 간선을 만들어서 우선순위 큐에 저장한다.
		PriorityQueue<edge> pq = new PriorityQueue<>();
//		ArrayList<edge> edges= new ArrayList<>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]!=0) {
					int from = map[i][j];
					
					//4방향으로 모두 쭉 이동해 본다.
					for(int dir=0;dir<4;dir++) {
						int len = 0;
						int ny = i; int nx = j;
						boolean can_connect=true;
						while(true) {
							ny += dy[dir];
							nx += dx[dir];
							//1. 범위를 벗어나거나 자기숫자가 나오면 멈추고
							if(ny<0||ny>=n||nx<0||nx>=m||map[ny][nx]==from) {
								can_connect=false;
								break;
							}
							//2. 그냥 바다일 때는 계속 이어준다.
							else if(map[ny][nx]==0) {
								len++;
								continue;
							}
							//3. 다른 숫자가 나오고 길이가 2이상 되었을 때 간선을 추가한다.
							else if(map[ny][nx]!=from&&len>=2) {
								int to =map[ny][nx];
//								System.out.println("from: "+ from +", to: "+to+", len: "+len);
//								edges.add(new edge(from,to, len));
								pq.add(new edge(from,to,len));
								break;
							}
							else if(map[ny][nx]!=from&&len<2) {
								break;
							}
//							len++;
						}
					}
				}//map[i][j]!=0
			}//for j
		}//for i
//		System.out.println();
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
////		
		
		//크루스칼로 풀어봅시다 - 간선 중심
		//가중치 오름차순으로 정렬
//		Collections.sort(edges);
//		for(int i=0;i<edges.size();i++) {
//			System.out.println(edges.get(i));
//		}
		//부모 배열 정렬
		parents = new int[cnt];
		for(int i=1;i<cnt;i++) {
			parents[i] =i;
		}
		int weight_sum=0;
		visit2 = new boolean[cnt+1];
		int union_cnt=0;
		while(!pq.isEmpty()) {
			edge now = pq.poll();
			int from = now.from; int to =now.to;
			int weight = now.weight;
			
			int root_from = find(from);
			int root_to = find(to);
			
			//방문
			if(root_from!=root_to) { //루트가 같지 않으면 같은 연결되어 있는게 아니므로
				visit2[from] =true;
				visit2[to]=true;
				weight_sum+=weight;
				union(from, to);
				union_cnt++;
			}
		}
//		for(int i=0;i<edges.size();i++) {
//			int from = edges.get(i).from;
//			int to = edges.get(i).to;
//			int weight = edges.get(i).weight;
//			
//			int root_from = find(from);
//			int root_to = find(to);
//			
//			//방문
//			if(root_from!=root_to) { //루트가 같지 않으면 같은 연결되어 있는게 아니므로
//				visit2[from] =true;
//				visit2[to]=true;
//				weight_sum+=weight;
//				union(from, to);
//				union_cnt++;
//			}
//		}
//		System.out.println(cnt);
//		for(int i=1;i<cnt;i++) {
//			System.out.println(visit2[i]);
//		}
		boolean all=true;
		
		for(int i=1;i<cnt;i++) {
			int root = find(i);
			if(root!=1) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(weight_sum);
//		System.out.println("island cnt: "+(cnt-1));
//		if(union_cnt==cnt-2) {
//			System.out.println(weight_sum);
//		}
//		else 
//			System.out.println(-1);

		
		
	}//main
	static int find(int x) {
		if(parents[x]==x) {
			return x;
		}
		int parent_root = find(parents[x]);
		parents[x] = parent_root;
		return parent_root;
	}//find
	static void union(int x, int y) {
		int root_x= find(x);
		int root_y=find(y);
		
		if(root_x<root_y) { //x가 더 작으면 x를 루트로 삼는다.
			parents[root_y]=x;
		}
		else {
			parents[root_x]=y;
		}
	}
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}//dot
	static class edge implements Comparable<edge>{
		int from, to, weight;
		public edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		@Override
		public String toString() {
			return "edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
		
	}//edget
}
