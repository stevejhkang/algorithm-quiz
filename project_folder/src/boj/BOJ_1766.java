package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766 {
	private static int n;
	private static int m;
	private static int[] degree;
	private static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken()); //노드
		m = Integer.parseInt(stringTokenizer.nextToken()); //간선
		
		//인접리스트 선언 및 초기화
		List[] graph = new List[n+1];
		for(int i=1;i<=n;i++) {
			graph[i] = new ArrayList<Integer>();
		}
		degree = new int[n+1];
		
		for(int i=0;i<m;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			
			graph[from].add(to);
			degree[to]++;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=1;i<=n;i++) {
			if(degree[i]==0) {
				pq.offer(i);
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		visit = new boolean[n+1];
		while(!pq.isEmpty()) {
			int now = pq.poll();
			if(visit[now]) continue;
			ans.add(now);
			visit[now]=true;
			for(int i=0;i<graph[now].size();i++) {
				int to = (int) graph[now].get(i);
				degree[to]--;
				if(degree[to]==0) {
					pq.offer(to);
				}
			}
		}
		
		for(int i=0;i<ans.size();i++) {
			System.out.print(ans.get(i)+" ");
		}
	}//main
}
