package boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1260 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());
		int v = Integer.parseInt(stringTokenizer.nextToken());
		
		List<Integer> list[] = new List[n+1];
		for(int i=1;i<n+1;i++) {
			list[i]= new ArrayList<Integer>();
		}
		for(int i=0;i<m;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			list[start].add(end);
			list[end].add(start);
		}
		
//		for(int i=1;i<n+1;i++) {
//			System.out.println(list[i].toString());
//		}
		//DFS
		for(int i=1;i<n+1;i++) {
			Collections.sort(list[i],Collections.reverseOrder());
		}
		
		boolean visit[] = new boolean [n+1];
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		while(!stack.isEmpty()) {
			int now = stack.pop();
			if(visit[now]) continue;
			visit[now]=true;
			System.out.print(now+" ");
			for(int i=0;i<list[now].size();i++) {
				if(visit[list[now].get(i)]) continue;
				stack.push(list[now].get(i));
			}
		}
		System.out.println("");
		
		//BFS
		for(int i=1;i<n+1;i++) {
			Collections.sort(list[i]);
		}
//		for(int i=1;i<n+1;i++) {
//			System.out.println(list[i].toString());
//		}
		visit= new boolean [n+1];
		Queue<Integer> queue  = new LinkedList<Integer>();
		queue.offer(v);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(visit[now]) continue;
			visit[now]=true;
			System.out.print(now+" ");
			for(int i=0;i<list[now].size();i++) {
				if(visit[list[now].get(i)]) continue;
				queue.offer(list[now].get(i));
			}
		}
		System.out.println("");
	}
}
