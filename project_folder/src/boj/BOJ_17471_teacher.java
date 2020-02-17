package boj;

import java.util.List;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17471_teacher {
	private static int n;
	private static int[] popul;
	private static List[] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		popul = new int[n+1];
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		//인구 수 저장
		for(int i=0;i<n;i++) {
			popul[i]=Integer.parseInt(stringTokenizer.nextToken());
		}
		//연결리스트 생성 및 초기화
		graph = new List[n];
		for(int i=0;i<n;i++) {
			graph[i] = new ArrayList<Integer>(); 
		}
		//연결리스트 작성
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int C = Integer.parseInt(stringTokenizer.nextToken());
			for(int c=0;c<C;c++) {
				int to = Integer.parseInt(stringTokenizer.nextToken())-1;
				graph[i].add(to);
				graph[to].add(i);
			}
		}
		
		int min = Integer.MAX_VALUE;
		//두 개의 그룹으로 나눠보자
		for(int i=1;i<1<<n-1;i++) {
			List<Integer> g1 = new ArrayList<>();
			List<Integer> g2 =new ArrayList<>();
			for(int j=0;j<n;j++) {
				if((i&1<<j)>0) {
					g1.add(j); //그룹의 부분집합
				}
				else {
					g2.add(j); //그룹의 여집합
				}
			}
//			System.out.println(g1+" : "+ g2);
			
			//그룹별 탐색 후 최소 인구 차이 구하기
			int result1 = bfs(g1);
			if(result1>0) {
				int result2= bfs(g2);
				if(result2>0) {
					min=Math.min(min, Math.abs(result2-result1));
				}
			}
			
		}//for i
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
	}
	/**
	 * 각 그룹을 완탐한 후 그 그룹에 속한 인구 수 리턴, 단 완탐 실패시 -1리턴
	 * @param group
	 * @return 그룹의 인구 수 
	 */
	static int bfs(List<Integer> group) {
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(group.get(0));
		int cnt =0; //탐색한 노드의 수 - group.size() == cnt --> 완탐 성공
		int popSum=0; //인구수
		
		while(!queue.isEmpty()) {
			Integer front=queue.poll();
			if(visited[front]) {
				continue;
			}
			visited[front]=true;
			cnt++;
			popSum+=popul[front];
			//자식 찾아보기
			List<Integer> childs= graph[front];
			//연결되어있지만 같은 그룹이 아니라면 갈 수없다.////////////
			for(Integer child: childs) {
				//자식이 같은 그룹이고 미방문이면
				if(group.contains(child)&&!visited[child]) {
					queue.offer(child);
				}
			}
		}//while
		if(cnt==group.size()) {
			return popSum;
		}
		else {
			return -1;
		}
	}
}
