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
* 1. 인접리스트를 초기화하고 연결상태를 넣어준다.
* 2. 그리고 시작점의 엣지에 대한 것을 시작점에서 해당 노드까지 가는 경로의 가중치합을 저장하는 D배열을 만들고
* 3. 시작점의 가중치를 0 나머지는 맥스값으로 초기화한다.
* 4. 그리고 그것을 전부 우선순위에 넣어준다.
* 5. 그리고 pq 탐색을 시작한다. 그러면 가장 가중치가 작은게 나오는데, 여기서 해당 인덱스와 가중치를 둘다 갖고 있어야 한다.
* 6. 그런데 가중치가 무한대이면 더이상 이어진게 없는 것이므로 break시키고
* 7. 만약 무한대가 아니면 해당 점과 이어진 모든점에 대해서 기존까지 계산된 시작점에서 나머지 점까지의 경로의 가중치의 합을 갱신한다.
* 	 그 값은 시작점에서 해당 점까지 가는 경로의 가중치의 합과 해당 점과 그 점과 연결된 점의 가중치를 더한 값이 
*    기존까지 계산된 시작점에서 나머지 점까지의 경로의 가중치합보다 작을때 갱신을하면 된다.
*    그 값이 변경이 된다면 D배열 뿐만 아니라 우선순위 큐에 저장된 값도 삭제했다가 다시 넣어주어야 한다.
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
		//연결상태 입력시켜준다.
		for(int i=0;i<E;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			
			adj[from].add(new Edge(to,weight));
		}
		
		//다익스트라를 시작한다.
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V+1]; 
		//시작점(edge)에서 해당 D[edge].v까지 가는 경로의 가중치 합 => D[edge].weight
		//그리고 해당 점의 인덱스도 나중에 쓸 수 있기 때문에 인덱스도 저장해주어야 한다.
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
			
			if(edge.weight==Integer.MAX_VALUE) //가장 가까운게 무한대이면 그 뒤는 이어진게 없는 것을 의미
				break;
			for(Edge next: adj[edge.v]) { //해당 정점과 연결된 모든 정점들을 확인한다.
				//시작 점에서 현재 점까지 가는 경로의 가중치합 + 현재 점에서 연결된 다른 점의 가중치의 합이
				//이미 저장된 시작점에서 연결된 다른 점까지 가는 경로의 가중치합보다 작으면 갱신한다.
				if(!check[next.v]&&D[edge.v].weight+next.weight<D[next.v].weight) { 
					//여기서 +가 되면 int범위 넘어가서 안된다. 그래서 위에서 무한대인 것을 없애주어야 한다.
					D[next.v].weight=D[edge.v].weight+next.weight;
					
					//우선순위 큐는 갱신되는게 아니므로 해당 객체를 지워주고(복사하는게 아니라) 그냥 객체 참조를 넣어줘도
					//지워진다. 그리고 다시 넣어야 된다.
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
