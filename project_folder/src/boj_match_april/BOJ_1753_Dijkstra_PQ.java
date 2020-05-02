package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 10. 오후 12:42:43
 * @category 
* @problem_description 정점의 개수는 V(2만), 간선의 개수는 E(30만) 정점번호는 1부터 시작한다.
* 그리고 정점이 2만개면 정점 하나당 평균 15개의 간선을 가진다. 그러므로 인접행렬보다는 인접리스트가 나을 듯
* 입력은 u,v,w 순서 w는 10이하의 자연수 
* **두 정점 사이의 여러개의 간선이 존재할 수 있음에 유의.
* i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 시작점 자신은 0으로 출력하고 경로가 존재하지않으면  INF 출력
* @solving_description 
*/

public class BOJ_1753_Dijkstra_PQ {
	private static int V;
	private static int E;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		V = Integer.parseInt(stringTokenizer.nextToken());
		E = Integer.parseInt(stringTokenizer.nextToken());
		
		int initpoint= Integer.parseInt(bufferedReader.readLine());
		
		//그래프 초기화
		List<Edge> adj[] = new List[V+1]; //개수 선언해주고
		for(int i=1;i<=V;i++) {
			adj[i] = new ArrayList<>(); //초기화 시켜주고
		}
		//연결상태 입력
		for(int i=0;i<E;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			adj[Integer.parseInt(stringTokenizer.nextToken())]
					.add(new Edge(Integer.parseInt(stringTokenizer.nextToken()),Integer.parseInt(stringTokenizer.nextToken())));
		}
		
		//다익스트라를 시작한다.
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V+1];
		//시작점(edge)에서 해당 D[edge].v까지 가는 경로의 가중치 합 => D[edge].weight
		Edge[] D = new Edge[V+1];
		
		//초기화: 시작점을 0으로하고 나머지는 무한대로 해놓고 전부 pq에 담아준다.
		for(int i=1;i<=V;i++) {
			if(i==initpoint) {
				D[i] = new Edge(i, 0);
			}else {
				D[i] = new Edge(i, Integer.MAX_VALUE);
			}
			pq.add(D[i]);
		}
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll(); //가장 가까운게 튀어나온다.
			if(edge.weight==Integer.MAX_VALUE)
				break;
			for(Edge next: adj[edge.v]) { //해당 정점과 연결된 모든 정점들을 확인하여 짧으면 갱신하고 큐에 넣어준다.
				if(!check[next.v]&&D[next.v].weight>D[edge.v].weight+next.weight) { 
					//여기서 +가 되면 int범위 넘어가서 안된다.
					D[next.v].weight=D[edge.v].weight+next.weight;
					
					pq.remove(D[next.v]);
					pq.add(D[next.v]);
				}
			}
			//해당 엣지를 선택했으므로 방문처리 해준다.
			check[edge.v] = true;
		}
		
		for(int i=1;i<=V;i++) {
			int a = D[i].weight;
			if(a==Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(a);
			}
		}
		
	}//main
	
	static class Edge implements Comparable<Edge>{
		int v, weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
	}
}
