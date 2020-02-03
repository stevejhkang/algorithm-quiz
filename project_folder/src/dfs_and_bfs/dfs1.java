package dfs_and_bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class dfs1 {
	static Stack<Integer> stack = new Stack<>();;
	static int v = 7;
	static int[] visit = new int[v + 1];
	static List<Integer>[] graph = new List[v + 1];
	static List<Integer> path = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "1 2 1 3 2 4 2 5 4 6 5 6 6 7 3 7";
		String[] splited = data.split(" ");

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < splited.length; i += 2) {
			int a = Integer.parseInt(splited[i]);
			int b = Integer.parseInt(splited[i + 1]);
			graph[a].add(b);
			graph[b].add(a);

		}
		for (List<Integer> list : graph) {
			System.out.println(list);
		}

		dfsStack(1);
//		dfsRecur(1);
		System.out.println("path: "+path);
	}

	public static void dfsStack(int start) {

		// 출발점에서 시작
		stack.push(start);
		while (!stack.isEmpty()) {
			// top가져온 후 방문처리
			Integer top = stack.pop();
			// 이미 방문했었는지 확인
			if (visit[top] == 1) {
				continue;
			}
			// 미방문이었다면 -> 방문처리
			visit[top] = 1;
			path.add(top);
			// top을 통해서 갈 수 있는 다음 정점 탐색
			List<Integer> childs = graph[top]; // 현재 정점과 연결된 정점리스트를 받아온다.
			for (int i = childs.size() - 1; i >= 0; i--) {
				Integer child = childs.get(i); // 다음에 갈 정점
				if (visit[child] == 0) {
					stack.push(child);
				}
			}
		}
		System.out.println("경로는 " + path);
	}
	public static void dfsRecur(int start) {
		if(visit[start]==0) {
			visit[start]=1;
			//방문처리
			path.add(start);
			List<Integer> childs = graph[start];
			for(int i=0;i<childs.size();i++) {
				int child = childs.get(i);
				if(visit[child]==0) {
					dfsRecur(child);
				}
			}
		}
	}
}
