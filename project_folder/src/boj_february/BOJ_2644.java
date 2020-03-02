package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2644 {
	private static int n;
	private static int start;
	private static int end;
	private static int m;
	private static List[] link;
	private static boolean[] visit;
	private static int depth;
	private static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		m= Integer.parseInt(st.nextToken());
		link= new List[n+1];
		
		for(int i=1;i<=n;i++) {
			link[i]= new ArrayList<>(); 
		}
		
		for(int i=0;i<m;i++) {
			st =new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			link[from].add(to); link[to].add(from);
		}
		visit = new boolean[n+1];
		depth=0;
		flag= false;
		dfs(start,0);
		if(!flag) {
			System.out.println(-1);
		}
		
		
	}//main
	static void dfs(int now,int depth) {
		if(now==end) {
			System.out.println(depth);
			flag= true;
			return;
		}
		visit[now]=true;
		for(int i=0;i<link[now].size();i++) {
			int next = (int) link[now].get(i);
			if(!visit[next]) {
				dfs(next,depth+1);
			}
		}
	}
}
