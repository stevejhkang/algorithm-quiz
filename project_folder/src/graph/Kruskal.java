package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
	static int[] parents;
	static int[] rank;
	//입력은 첫줄에 정점의 개수와 간선의 개수가 들어오고
	//그 다음 줄 부터 간선의 정보가 정점1 정점2 가중치로 간선 개수
	//만큼 들어옴
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		int[][] edges = new int[E][3]; 
		for(int i=0;i<E;i++) {
			edges[i][0] = sc.nextInt();
			edges[i][1] = sc.nextInt();
			edges[i][2] = sc.nextInt();
		}
		//간선들을 가중치 오름차순 정렬, 정렬 기준은 인덱스2번
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				//o1[2] o2[2]
				//-1이면은 앞 +1은 뒤가 빠른거
				//그러나 빼면 오버플로가 날 수 있어서 
				//이것을 쓴다.
				return Integer.compare(o1[2],o2[2]); 
			}
		});
		//각 정점에 대해 유니온파인드 연산 준비
		for(int i=0;i<V;i++) {
			makeSet(i);
		}
		int result=0;
		int cnt=0;
		for(int i =0;i<V;i++) {
			int a = findSet(edges[i][0]);
			int b = findSet(edges[i][1]);
			if(a==b) {
				continue;
			}
			
			union(a, b);
		}
		//정점의 갯수 -1번 반복하면서
		
		//간선이 연결하는 두 정점이 같은 팀이 아니라면 한팀으로
		//함쳐주고 간선선택 같은 팀이라면 패스
		
		
	}
	static void makeSet(int x) {
		parents[x]=x;
	}
	static int findSet(int x) {
		if(x==parents[x]) { 
			//자기가 자기자신이면 팀의 식별자
			return x;
		}
		//패스트 컴프레션
		else {
			//다르면 부모가 식별자
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		//rank부분
		if(rank[px]>rank[py]) {
			//px가 더 크면 py의 부모를 px로 잡아준다.
			parents[py] = px;
		}
		else {
			//py가 부모가 된다.
			parents[px]=py;
			if(rank[px]==rank[py]) {
				rank[py]++;
			}
		}
	}
}
