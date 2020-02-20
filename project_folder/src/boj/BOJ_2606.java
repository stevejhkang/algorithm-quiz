package boj;

import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2606 {
	private static int n;
	private static int k;
	private static List[] link;
	private static boolean[] visit;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(bufferedReader.readLine());
		k = Integer.parseInt(bufferedReader.readLine());
		
		link = new List[n+1];
		for(int i=1;i<=n;i++) {
			link[i]= new ArrayList<Integer>(); 
		}
		for(int i=0;i<k;i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			
			link[from].add(to);
			link[to].add(from);
		}
		
		visit = new boolean[n+1];
		cnt =-1;
		dfs(1);
//		Stack<Integer> stack = new Stack<>();
//		stack.push(1);
//		while(!stack.isEmpty()) {
//			int now = stack.pop();
//			
//			if(visit[now]) continue;
//			visit[now]=true;
//			cnt++;
//			
//			for(int i=0;i<link[now].size();i++) {
//				//Integer이므로 int로 형변환 해주어야 한다.
//				int next = (int) link[now].get(i);
//				if(!visit[next]) {
//					stack.push(next);
//				}
//			}
//		}//while
		System.out.println(cnt);
		
	}
	static void dfs(int now) {
		if(visit[now]) {
			return;
		}
		cnt++;
		visit[now]=true;
		for(int i=0;i<link[now].size();i++) {
			//Integer이므로 int로 형변환 해주어야 한다.
			int next = (int) link[now].get(i);
			if(!visit[next]) {
				dfs(next);
			}
		}
	}
}
