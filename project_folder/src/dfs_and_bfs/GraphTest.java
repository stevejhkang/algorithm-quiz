package dfs_and_bfs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

import jdk.internal.dynalink.beans.StaticClass;

public class GraphTest {
	static int v=6;
	static String src="1 2 2 1 5 7 2 5 5 4 7 2 4 4 3 4 6 2 3";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		StringTokenizer stringTokenizer = new StringTokenizer(src);
		int[][] input = new int[6+1][6+1];
		String[] splited =src.split(" ");
		for(int i=0;i<splited.length; i+=2) {
			int a =Integer.parseInt(splited[i]);
			int b =Integer.parseInt(splited[i+1]);
			input[a][b] =1;
//			input[b][a]=1; 
		}
	
//		for (int i = 1; i <= v; i++) {
//			for(int j=1;j<=v;j++) {
//				System.out.print(input[i][j]+" ");
//			}
//			System.out.println("");
//		}
		for(int[] row: input) {
			System.out.println(Arrays.toString(row));
		}
		
	}

}
