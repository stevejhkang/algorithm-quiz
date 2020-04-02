package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p3 {
   static int N,M,root;
   static ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
   
   static int checkChild(int first, int second){
      Queue<Integer> q = new LinkedList<Integer>();
      boolean[] visit = new boolean[N+1];

      q.offer(first);
      
   
      while(!q.isEmpty()) {
         int tmp= q.poll();
      
         int size = arr.get(tmp).size();
         for(int i=0;i<size;i++) {
            int num=arr.get(tmp).get(i);
            if(visit[num] == false){
               q.offer(num);
               visit[num] = true;
            }
         }
      }
      
      q.offer(second);
      while(!q.isEmpty()) {
         int tmp = q.poll();
         int size = arr.get(tmp).size();
         for(int i=0;i<size;i++) {
            int num = arr.get(tmp).get(i);
            q.offer(num);
            if(visit[num])
               visit[num]=false;
         }
         
      }
      int count =0;
      int size = visit.length;
      for(int i=1;i<size;i++) {
         if(visit[i])
            count++;
      }
      
      return count;
      
   }

   public static void main(String[] args) throws IOException {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      boolean[] count = new boolean[N+1];
      
      for(int i=0;i<N+1;i++)
         arr.add(new ArrayList<Integer>());
      
      st = new StringTokenizer(br.readLine());
      
      for(int i=1;i<=N;i++) {
         int tmp = Integer.parseInt(st.nextToken());
         if(tmp == 0) root=i;
         arr.get(tmp).add(i);
      }
      
      int ans =0;
      for(int i=0;i<M;i++) {
         
         st = new StringTokenizer(br.readLine());
         int first = Integer.parseInt(st.nextToken());
         int second = Integer.parseInt(st.nextToken());
         
         int tmp = checkChild(first, second);
         ans +=tmp;
      }
      System.out.println(ans);
      
   }
}