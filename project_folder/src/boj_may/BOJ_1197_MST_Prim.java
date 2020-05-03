package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 3. 오후 4:53:50
 * @category MST
* @level 3
* @problem_description 그래프가 주어졌을 때, 최소 스패닝 트리를 구하는 프로그램을 작성하시오
* @solving_description 
* 
* Prim:
* 비트리 노드 집합, MST 노드 집합 설정
* 1. 비트리 노드 집합에서 임의 정점 하나(1번) 선택해서 큐에 넣어준다.
* 2. 큐에서 방문처리하고 해당 노드와 연결되어 있는 가장 작은 간선을 찾기 위해 모든 엣지를 우선순위 큐에 넣는다.
* 3. 그리고 우선순위에서 가장 먼저 나오는게 가장 가중치가 작은 노드이다.
* 4. 해당 노드를 방문하지 않았으면 다음 방문노드로 처리하기 위해 큐에 넣고
* 5. 가중치를 더하고 우선순위 큐를 중지시킨다.
* 6. 그다음 큐에서 해당 노드와 연결된 엣지들을 우선순위에 넣고
* 7. 이전단계와 이번 단계에서 넣은 엣지 중 가장 작은 것이 우선순위 큐로 나온다.
* 8. 3에서 다시 반복한다.
* 
* 
*/


public class BOJ_1197_MST_Prim {
	private static int v;
	private static int e;
	private static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		v = Integer.parseInt(stringTokenizer.nextToken()); //1만
		e = Integer.parseInt(stringTokenizer.nextToken()); //10만
		
		//인접행렬보다는 인접리스트가 나을듯
		//인접리스트 초기화 
//		List[] graph = new List[v+1];
//		for(int i=1;i<=v;i++) {
//			graph[i] = new ArrayList<int[]>();
//		}
		List<ArrayList<edge>> graph2 = new ArrayList<ArrayList<edge>>();
		for(int i=0;i<=v;i++) {
			graph2.add(new ArrayList<edge>());
		}
		
		for(int i=0;i<e;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			
//			graph[from].add(new int[] {to,weight});
			graph2.get(from).add(new edge(to, weight));
//			graph[to].add(new int[] {from, weight});
			graph2.get(to).add(new edge(from, weight));
		}
		
		int weight_sum=0;
		visit = new boolean[v+1];
		//임의노드(1번 노드)를 queue나 덱에 넣어준다.
		Queue<Integer> work_queue = new LinkedList<Integer>();
		PriorityQueue<edge> pq = new PriorityQueue<>();
		work_queue.add(1);
		
		while(!work_queue.isEmpty()) {
			int now = work_queue.poll();
			
			//방문처리
			visit[now]=true;
		
//			for(int i=0;i<graph[now].size();i++) { //연결된 모든 엣지들을 pq에 담는다.
				for(int i=0;i<graph2.get(now).size();i++) { //연결된 모든 엣지들을 pq에 담는다.
//				int to = ((int[])graph[now].get(i))[0]; 
//				int to = graph[now].get(i)[0];  //이건 에러난다 타입 지정해줘야됨
					int to = graph2.get(now).get(i).to; 
//				int weight = ((int[])graph[now].get(i))[1];
//				int weight = graph[now].get(i);
				int weight = graph2.get(now).get(i).weight;
				
				pq.add(new edge(to, weight));
			}
			while(!pq.isEmpty()) {
				//제일 가까운게 나온다.
				edge closest = pq.poll();
				int node = closest.to;
				int weight = closest.weight;
				
				//만약 방문한적이 없는 노드이면
				if(!visit[node]) {
					//다음 방문할 노드로 큐에 넣는다.
					work_queue.add(node);
					//가중치 합을 더해준다.
					weight_sum+=weight;
					break;
				}
			}
		}//while work_queue
		System.out.println(weight_sum);
	}//main
	static class edge implements Comparable<edge>{
		int to, weight;

		public edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		//오름차순으로
		@Override
		public int compareTo(edge o) {
			// TODO Auto-generated method stub
			if((new Integer(this.weight)).compareTo(new Integer(o.weight))==0) {
				return (new Integer(this.to)).compareTo(new Integer(o.to));
			}
			else
				return (new Integer(this.weight)).compareTo(new Integer(o.weight));
		}
		
	}
}
