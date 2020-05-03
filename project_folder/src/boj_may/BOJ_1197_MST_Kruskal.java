package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 3. 오후 6:17:56
 * @category MST
* @level 3
* @problem_description 크루스칼은 간선 중심의 연산이다.
* @solving_description 
* 1. 간선의 대한 정보를 다 받고 그것을 가중치 오름차순으로 정렬한다.
* 2. 그리고 오름차순으로 간선이 적은 순으로 탐색해나가는데 두 정점이 같은 부모를 가지고 있지 않으면
* 아직 두 정점은 연결이 된 것이 아니므로 해당 가중치를 더해주고 연결해준다. 유니언 연산으로
* 3. 이것을 모든 엣지에 대해 반복시켜준다.
*/

public class BOJ_1197_MST_Kruskal {
	private static int v;
	private static int e;
	private static boolean[] visit;
	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		v = Integer.parseInt(stringTokenizer.nextToken()); //1만
		e = Integer.parseInt(stringTokenizer.nextToken()); //10만
		parents = new int[v+1];
		int[][] edges = new int[e][3]; 
		
		for(int i=0;i<e;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			edges[i][0] = from; edges[i][1] = to; edges[i][2] = weight;
		}
		//간선들을 가중치(인덱스 2번) 오름차순으로 정렬
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[2],o2[2]);
				//뺐을 때 -1이면 앞 +1은 뒤가 빠른거
			}
		});
		// 각 정점에 대해 부모정점 초기화
		for(int i=0;i<v;i++) {
			parents[i] = i;
		}
		int weight_sum=0;
		for(int i=0;i<e;i++) {
			int from = edges[i][0];
			int to = edges[i][1];
			
			int root_from = find(from);
			int root_to = find(to);
			
			if(root_from!=root_to) { //root가 같지않으면 다른 것이므로 합을 더해주고 합쳐준다.
				int weight = edges[i][2];
				weight_sum+=weight;
				union(from, to);
			}
		}
		System.out.println(weight_sum);
	}//main
	static int find(int x) {
		if(parents[x]==x) {
			return x;
		}
		
		int root = find(parents[x]);
		parents[x] = root;
		return root;
	}
	static void union(int x, int y) {
		int root_x= find(x);
		int root_y = find(y);
		
		if(root_x!=root_y) {
			if(x<y) {
				parents[root_y]=x;
			}
			else {
				parents[root_x]=y;
			}
		}
	}
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
