package dfs_and_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs1 {
	public static void main(String[] args) {
		String string = "1 2 1 3 2 4 2 5 4 6 5 6 6 7 3 7";
		StringTokenizer stringTokenizer = new StringTokenizer(string);
		int[][] graph=new int[7+1][7+1];
		
		while(stringTokenizer.hasMoreTokens()) {
			int i = Integer.parseInt(stringTokenizer.nextToken());
			int j =Integer.parseInt(stringTokenizer.nextToken());
//			System.out.println(i+","+j);
			graph[i][j]=1; graph[j][i]=1;
		}
//		for(int i=0;i<8;i++) {
//			System.out.println(Arrays.toString(graph[i]));
//		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		ArrayList<Integer> arrayList =new ArrayList<>();
		boolean visit[]= new boolean[8];
		
		
		while(!queue.isEmpty()) {
			int a = queue.poll();
			if(visit[a]) {
				continue;
			}
			visit[a]=true;
			arrayList.add(a);
			System.out.println(a);
			for(int j=1;j<=7;j++) {
				if(graph[a][j]==1&&!visit[j]) {
					queue.add(j);
				}
			}
		}
		System.out.println(arrayList.toString());
	
	}
}
