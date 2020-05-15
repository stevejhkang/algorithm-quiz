package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252 {
	private static int n;
	private static int m;
	private static int[] degree;
//	private static int[][] map;
	private static boolean[] visit;

	public static void main(String[] args) throws IOException  {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken()); //정점개수
		m = Integer.parseInt(stringTokenizer.nextToken()); //간선 개수
		
		//차수를 저장하는 배열
		degree = new int[n+1];
		//인접행렬 -> 메모리 때문에 인접리스트를 사용하자
//		map = new int[n+1][n+1];
		//인접리스트 선언 및 초기화
		List<Integer>[] graph = new List[n+1];
		for(int i=1;i<=n;i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<m;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int a = Integer.parseInt(stringTokenizer.nextToken());
			int b = Integer.parseInt(stringTokenizer.nextToken());
			
			graph[a].add(b);
			degree[b]++; //차수도 더해준다.
		}
//		System.out.println(Arrays.toString(degree));
		
		Queue<Integer> queue = new LinkedList<Integer>();
		//순서를 저장할 리스트
		ArrayList<Integer> al = new ArrayList<Integer>();
		//차수가 0인 것을 전부 큐에 넣어준다.
		for(int i=1;i<=n;i++) {
			if(degree[i]==0) {
				queue.offer(i);
			}
		}
//		System.out.println("hh");
		visit = new boolean[n+1];
		while(!queue.isEmpty()) {//큐가 빌때까지 반복
			int now = queue.poll();
			if(visit[now]) continue;
			al.add(now); //처리한 노드는 순서대로 리스트에 저장
			visit[now]=true;
			//현재 정점과 연결된 모든 노드들의 차수를 -1시켜준다.
			//********원래 DAG임을 확인하려면 큐가 비었을 때 모두 visit했는지를 확인해야한다.
			for(int i=0;i<graph[now].size();i++) {
				int to = graph[now].get(i);
				degree[to]--;
				if(degree[to]==0&&!visit[to]) {//차수가 0이 되면 큐에 넣어준다.
					queue.offer(to);
				}
			}
		}//while
		
		for(int i=0;i<al.size();i++) {
			System.out.print(al.get(i)+" ");
		}
		
		
		
	}//main
}