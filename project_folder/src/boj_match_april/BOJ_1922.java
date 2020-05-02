package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 12. 오후 1:51:40
 * @category 
* @problem_description MST문제
* @solving_description 
*/
public class BOJ_1922 {
	private static int n;
	private static int m;
	private static int[] parent;
	private static PriorityQueue<link> pq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine()); //정점
		m = Integer.parseInt(bufferedReader.readLine()); //간선
		parent = new int[n+1];
		pq = new PriorityQueue<link>();
		StringTokenizer stringTokenizer;
		for(int i=0;i<m;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int value = Integer.parseInt(stringTokenizer.nextToken());
			
			pq.add(new link(from, to, value));
		}
		
		for(int i=1;i<=n;i++) {
			parent[i]=i;
		}
		int min=0;
		for(int i=0;i<m;i++) {
			link temp = pq.poll(); //맨 위에거 꺼내고
			int start = temp.start; int end = temp.end; int value = temp.value;
			int root_start=find(start);
			int root_end = find(end);
			
			if(root_end==root_start)continue;
			union(start, end);
			
			min +=value;
		}
		System.out.println(min);
	}//main
	static class link implements Comparable<link>{
		int start,end, value;

		public link(int start, int end, int value) {
			super();
			this.start = start;
			this.end = end;
			this.value = value;
		}

		@Override
		public int compareTo(link o) {
			// TODO Auto-generated method stub
			return this.value>o.value?1:-1;
		}
		
	}//link
	
	static int find(int a) {
		if(parent[a]==a) 
			return a;
		parent[a]= find(parent[a]); //계속올라가서 부모를 구하고 그거를 저장해 놓는다.
		//그러면 다음에 계산 안해도 된다.
		
		return parent[a];
	}//find
	static void union(int a, int b) {
		int roota= find(a);
		int rootb= find(b);
		if(roota!=rootb) {
			parent[roota]=b;
		}
	}
}
