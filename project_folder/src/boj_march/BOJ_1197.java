package boj_march;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 12. 오후 1:22:53
 * @category 
* @problem_description 가중치가 음수일 수 있다.
* @solving_description 
*/

public class BOJ_1197 {
	private static int v;
	private static int e;
	private static int[][] graph;
	static int[] parent;
	private static boolean[] visit;
	private static int result;
	private static PriorityQueue<link> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		v = Integer.parseInt(stringTokenizer.nextToken()); //정점의 개수
		e = Integer.parseInt(stringTokenizer.nextToken()); //간선의 개수
		
		parent = new int[v+1]; //부모노드를 저장하는 배열
		visit = new boolean[v+1]; 
		result =0; //최솟값을 저장
		
		pq = new PriorityQueue<link>();
		String[] tempStr;
		for(int i=0;i<e;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int value = Integer.parseInt(stringTokenizer.nextToken());
			//모든 간선에 대해 시작, 끝, 비용을 가진 클래스로 우선순위 큐에 저장
			pq.add(new link(from, to, value)); 
			
		}
		//유니온 파인드 초기화는 자기의 부모를 자기 자신으로 저장
		for(int i=1;i<=v;i++) {
			parent[i] = i;
		}
		
		for(int i=0;i<e;i++) {
			link onelink = pq.poll(); //현재 큐에 있는 모든 간선중 가장 작은 간선이 poll
			
			int start = onelink.start; int end = onelink.end; int value = onelink.value;
			int start_root = find(start);
			int end_root = find(end);
			
			//start의 root와 end의 루트가 같으면 연결해버리면 사이클이 되버린다.
			//그러면 안되므로 패스
			if(start_root==end_root) continue;
			
			//되면은 연결시켜주고 비용을 추가해준다.
			union(start, end);
			result+=value;
		}
		System.out.println(result);
		
	}//main
	static class link implements Comparable<link>{
		int start,end,value;

		public link(int s, int e, int value) {
			super();
			this.start = s;
			this.end = e;
			this.value = value;
		}

		@Override
		public int compareTo(link o) {
			// TODO Auto-generated method stub
			return this.value>o.value? 1:-1;
		}
	}//class link
	static int find(int a) {
		if(a==parent[a]) 
			return a;
		//find할 때마다 부모는 최상위 부모로 설정(성능향상)
		//한번 구했으면 다음에할때는 위에 if문에서 끝남
		parent[a] = find(parent[a]);
		return parent[a];
	}//find
	static void union(int a, int b) {
		int root_a = find(a);
		int root_b = find(b);
		
		if(root_a!=root_b) { //다르면 b의 부모를 a로 바꿔주어 연결시켜준다.
			parent[root_b] = a;
		}
	}
}
