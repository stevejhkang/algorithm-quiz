package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 10. 오후 4:13:30
 * @category 
* @problem_description 소지금을 최소로 잃으면서 [n-1][n-1]까지 이동할때 잃은 최소 금액을 구하라
* 이건 마치 0,0부터 n-1,n-1까지 가는 경로중 최소 가중치의합을 구하는 문제와 같다. (다익스트라)
* @solving_description 
* 그래프로 연결되어 있는 것이 아니라 상하좌우가 연결되어 있는 것이므로 4방향을 
* 검사해서 기존 저장된 경로 값보다 작으면 갱신시켜줘야 한다.
*/

public class BOJ_4485_Dijkstra_PQ {
	private static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		
		int tc =1;
		while(true) {
			int n = Integer.parseInt(bufferedReader.readLine());
			if(n==0) break;
			map = new int[n][n];
			Edge[][] D = new Edge[n][n];
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for(int i=0;i<n;i++) {
				StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
				for(int j=0;j<n;j++) {
					map[i][j] =  Integer.parseInt(stringTokenizer.nextToken()); //가중치 저장
					if(i==0&&j==0) {
						D[i][j] = new Edge(i, j, map[i][j]);
					}
					else {
						D[i][j] = new Edge(i, j, Integer.MAX_VALUE);
					}
					pq.add(D[i][j]);
				}
			}
			//상하좌우만 다 연결되어 있다. 그래서 갱신할 때 상하좌우만 갱신하면 될듯!
			
			//00에서 다익스트라를 시작한다. 이거는 인접행렬이다.
			//노드의 인덱스를 저장하는 것이 아닌 해당 좌표 y,x를 저장해야한다.
			//i부터 n까지가는 최소 경로를 저장하는 배열 D
			boolean[][] check  = new boolean[n][n];
			
			while(!pq.isEmpty()) {
				Edge edge = pq.poll();
				int y = edge.y; int x =edge.x;
				if(edge.weight==Integer.MAX_VALUE) continue; //MAX면 연결이 안되어 있음을 의미
				for(int k=0;k<4;k++) {
					int ny = y+dy[k]; int nx = x+dx[k];
					//범위
					if(ny<0||ny>=n||nx<0||nx>=n) continue;
					//방문하지 않았고 현재값보다 작으면 갱신
					if(!check[ny][nx]&&D[y][x].weight+map[ny][nx]<D[ny][nx].weight) {
						D[ny][nx].weight=D[y][x].weight+map[ny][nx];
						pq.remove(D[ny][nx]);
						pq.add(D[ny][nx]);
					}
				}
				check[y][x] = true;
			}//while
			System.out.println("Problem "+tc+": "+D[n-1][n-1].weight);
			tc++;
		}
	}//main
	static class Edge implements Comparable<Edge>{
		int y,x, weight;

		

		public Edge(int y, int x, int weight) {
			super();
			this.y = y;
			this.x = x;
			this.weight = weight;
		}



		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
	}
}
