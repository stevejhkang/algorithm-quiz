package dfs_and_bfs;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;



public class graph4 {
	static int v =7;
	static String src ="1 2 1 3 1 6 1 7 6 4 6 5 5 4 7 5";
	
	public static void main(String[] args) {
		List<Integer>[] graph = new List[7+1];
		String[] strings = src.split(" ");
		
		for (int i = 0; i < graph.length; i++) {
			graph[i]= new ArrayList<Integer>(); 
		}
		 
		for(int i=0;i<strings.length;i+=2) {
			int a= Integer.parseInt(strings[i]);
			int b = Integer.parseInt(strings[i+1]);
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for(int i=0;i<graph.length;i++) {
			for(int j=0;j<graph[i].size();j++) {
				System.out.print(graph[i].get(j)+" ");
			}
			System.out.println("");
		}
	}
	
	
	
}
