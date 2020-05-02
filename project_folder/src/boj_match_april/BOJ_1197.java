package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1197 {
   private static int[] parent;
   private static void makeSet(int n) {
      parent = new int[n+1];
      for (int i = 1; i <= n; i++) {
         parent[i] = i;
      }
   }
   private static int findSet(int x) {
      if (parent[x] == x)
         return x;
      else
         return parent[x] = findSet(parent[x]);
   }
   private static void union(int a, int b) {
      a = findSet(a);
      b = findSet(b);
      if (a < b)
         parent[b] = a;
      else
         parent[a] = b;
   }

  

   static int n, m;
   public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

      n = Integer.parseInt(stringTokenizer.nextToken());
      m = Integer.parseInt(stringTokenizer.nextToken());

      ArrayList<Edge> edges = new ArrayList<>();
      for (int i = 0; i < m; i++) {
         stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
         int a = Integer.parseInt(stringTokenizer.nextToken());
         int b = Integer.parseInt(stringTokenizer.nextToken());
         int c = Integer.parseInt(stringTokenizer.nextToken());
         edges.add(new Edge(a, b, c));
      }
      edges.sort(null);
      
      makeSet(n);

      int idx = 0;
      int sum = 0;
      
      for (Edge e : edges) {
         int pa = findSet(e.start);
         int pb = findSet(e.end);
            
         if (pa != pb) {
                idx++;
            sum += e.weight;
            union(pa, pb);
                if (idx == n-1) break;
         }
      }
      System.out.println(sum);
   }//main
   private static class Edge implements Comparable<Edge> {
	      int start, end;
	      int weight;

	      public Edge(int s, int e, int w) {
	         this.start = s;
	         this.end = e;
	         this.weight = w;
	      }

	      @Override
	      public int compareTo(Edge edge) {
	         return Integer.compare(this.weight, edge.weight);
	      }
	}//Edge
   
}
