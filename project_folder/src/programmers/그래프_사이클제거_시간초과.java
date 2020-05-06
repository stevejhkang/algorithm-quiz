package programmers;
import java.util.ArrayList;
import java.util.List;

public class 그래프_사이클제거_시간초과 {
	public static void main(String[] args) {
		int n = 8;
      int[][] edges = {
//              {1,2}, {1,3}, {2,3}, {2,4}, {3,4}
              {1,2}, {2,3}, {3,4}, {4,5}, {5,6},{6,7},{7,8},{8,1},{2,7},{3,6}
      };

      int answer = new Solution().solution(n, edges);
      System.out.println(answer);
	}
	static class Solution {

	    static int[] parent;

	    public static void main(String[] args) {
	        int n = 8;
	        int[][] edges = {
//	                {1,2}, {1,3}, {2,3}, {2,4}, {3,4}
	                {1,2}, {2,3}, {3,4}, {4,5}, {5,6},{6,7},{7,8},{8,1},{2,7},{3,6}
	        };

	        int answer = solution(n, edges);
	        System.out.println(answer);
	    }

	    static  int solution(int n, int[][] edges) {
	        int answer = 0;
//	        Edge_RemoveCycle[] edgesInfo = new Edge_RemoveCycle[edges.length];
	//
//	        for(int i=0; i<edges.length; ++i){
//	            edgesInfo[i] = new Edge_RemoveCycle(edges[i][0], edges[i][1]);
//	        }

	        //노드 1~n까지 하나씩 제거했을 때 사이클이 생기는지 안생기는지
	        for(int node=1; node<=n; ++node){
	            initParent(n);
	            boolean find = false;

	            for(int[] edge : edges){
	                if(edge[0] == node || edge[1] == node) continue;

	                if(!isSameParent(edge[0], edge[1])){
	                    union(edge[0], edge[1]);
	                }

	                else {
	                    find = true;
	                    break;
	                }
	            }

	            if(!find) {
	                answer += node;
	            }
	        }

	        return answer;
	    }

	    private static boolean isSameParent(int v1, int v2) {
	        v1 = findParent(v1);
	        v2 = findParent(v2);

	        if(v1 == v2) return true;
	        return false;
	    }

	    private static int findParent(int v) {
	        if(parent[v] == v) return v;
	        return parent[v] = findParent(parent[v]);
	    }

	    private static void union(int v1, int v2) {
	        v2 = findParent(v2);
	        parent[v2] = v1;
	    }

	    private static void initParent(int n) {
	        parent = new int[n+1];
	        for(int i=1; i<=n; ++i){
	            parent[i] = i;
	        }
	    }
	}

}


//class Edge_RemoveCycle {
//    public int v1, v2;
//
//    public Edge_RemoveCycle(int v1, int v2) {
//        this.v1 = v1;
//        this.v2 = v2;
//    }
//}



