package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra_PQ {

	
	static class Edge implements Comparable<Edge>{
		int v, weight;
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		@Override
		public String toString() {
			return  weight + " ";
		}
		
	}//edge
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		List<Edge>[] adj = new ArrayList[V];
		for(int i=0;i<V;i++) {
			adj[i] = new ArrayList<>();
		}
		for(int i=0;i<E;i++) {
			//출발 도착 가중치
			adj[sc.nextInt()].add(new Edge(sc.nextInt(),sc.nextInt()));
		}
		//dijkstra
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V];
		//시작점에서 해당 edge까지 가는 경로의 가중치 합
		Edge[] D = new Edge[V]; 
		
		for(int i=0;i<V;i++) {
			//원하는 출발지
			if(i==0) {
				D[i] = new Edge(i,0);
			}
			else {
				D[i] = new Edge(i,Integer.MAX_VALUE);
			}
			pq.add(D[i]);
		}
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();//제일 가까운게 튀어나온다.
			
			for(Edge next : adj[edge.v]) {
				//check되지 않았으면서
				//D[next.v]가 D[edge.v] + next.weight보다 더 크다면 갱신
				if(!check[next.v]&&D[next.v].weight>D[edge.v].weight+next.weight) {
					D[next.v].weight = D[edge.v].weight+next.weight;
					//decrese key
					//순서는 넣을때 정해지기때문에 최신화하면 다시 뺐다 넣어야 된다.
					pq.remove(D[next.v]); //없앴다가 다시넣어서 갱신
					pq.add(D[next.v]);
				}
			}
			check[edge.v] = true;
		}
		System.out.println(Arrays.toString(D));
	}
}
