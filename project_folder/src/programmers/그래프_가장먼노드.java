package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 19. 오후 2:37:17
 * @category 
* @problem_description 가장 멀리 떨어진 노드란 최단 경로로 이동 했을 때 간선의 개수가 
* 가장 많은 노드들을 의미합니다.  1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return하도록 
* @solving_description 가장 먼노드이므로 BFS로 한단계씩 들어가서 결정을 한다. 언제가 최대 인지 모르니까
* 
*/

public class 그래프_가장먼노드 {
	public static void main(String[] args) {
		int n = 6;
		int[][] edge = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
		Solution s = new Solution();
		System.out.println(s.solution(n, edge));
		
	}
//	static 
//	static 
	
	static class dot{
		int idx,depth;

		public dot(int idx, int depth) {
			super();
			this.idx = idx;
			this.depth = depth;
		}
		
	}
	static class Solution {
	    private boolean[] visit;

		public int solution(int n, int[][] edge) {
	        
	        List[] list = new List[n+1];
	        for(int i=1;i<=n;i++) {
	        	list[i] = new ArrayList<Integer>();
	        }
	        //연결 표시 해주고
	        for(int i=0;i<edge.length;i++) {
	        	int from = edge[i][0];
	        	int to = edge[i][1];
	        	list[from].add(to);
	        	list[to].add(from);
	        }
	        
	        //1번부터 큐에 넣어준다. 그리고 제일 멀리 떨어진 노드를 찾아야 한다.
	        Queue<dot> queue = new LinkedList<dot>();
	        queue.add(new dot(1, 0));
	        visit = new boolean[n+1];
	        
	        int max_depth=0;
	        int cnt=0;
	        while(!queue.isEmpty()) {
	        	dot now = queue.poll();
	        	int index = now.idx;
	        	int depth = now.depth;
	        	
	        	if(visit[index]) continue; //이미 방문한 적이 있으면 그냥 넘어간다.
	        	visit[index] = true;
	        	
	        	if(max_depth<depth) { //현재보다 더 깊은 것이 나오면 초기화 해준다.
	        		max_depth=depth;
	        		cnt=1;
	        	}
	        	else if(max_depth==depth) { //같은 것이 나오면 ++해준다.
	        		cnt++;
	        	}
	        	
	        	for(int i=0;i<list[index].size();i++) {
	        		int next = (int) list[index].get(i);
	        		if(visit[next]) continue;
	        		queue.add(new dot(next, depth+1));
	        	}
	        }
	        
	        return cnt;
	    }
	}
}
