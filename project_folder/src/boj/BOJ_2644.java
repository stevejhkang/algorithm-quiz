package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2644 {
	private static int n;
	private static int start;
	private static int end;
	private static int m;
	private static ArrayList[] num;
	private static int link;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		m= Integer.parseInt(st.nextToken());
		num= new ArrayList[n+1];
		
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			if(num[parent]==null) {
				num[parent]= new ArrayList<Integer>();
				num[parent].add(child);
			}
			else {
				num[parent].add(child);
			}
		}
		link = 0;
		if(start>end) {
			int temp =start;
			start = end;
			end= temp;
		}
		dfs(start,0);
		if(link==0) {
			System.out.println(-1);
		}
		else {
			System.out.println(link);
		}
		
	}//main
	static void dfs(int now, int sum) {
		if(now==end) {
			link=sum;
			return;
		}
		for(int i=0;i<num[now].size();i++) {
			int child = (int) num[now].get(i);
			dfs(child,sum+1);
		}
	}
}
