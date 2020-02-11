package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1231 {
	static node root;
	static node[] node_arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1;tc<=10;tc++) {
			int n = Integer.parseInt(bReader.readLine());
			node_arr=new node[n+1];
			for (int i = 0; i < n; i++) {
				StringTokenizer stringTokenizer =new StringTokenizer(bReader.readLine());
				int idx = Integer.parseInt(stringTokenizer.nextToken());
				String string = stringTokenizer.nextToken();
				int left=0; int right=0;
				if(stringTokenizer.hasMoreElements()) {
					left= Integer.parseInt(stringTokenizer.nextToken());
				}
				if(stringTokenizer.hasMoreElements()) {
					right= Integer.parseInt(stringTokenizer.nextToken());
				}
				node temp = getNode(idx);
				temp.string=string;
				temp.l=left;
				temp.r=right;
				
			}
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("#"+tc+" ");
			System.out.print(sBuilder);
			inOrder(1);
			System.out.println("");
		}
	}
	static node getNode(int v) {
		if(node_arr[v]==null) {
			node temp = new node(v, "", 0, 0);
			node_arr[v]=temp;
			return node_arr[v];
		}
		else 
			return node_arr[v];
	}
	static void inOrder(int v) {
		if(node_arr[v]==null) {
			return;
		}
		inOrder(node_arr[v].l);
		System.out.print(node_arr[v].string);
		inOrder(node_arr[v].r);
	}
	static class node{
		int idx;
		String string;
		int l;
		int r;
		public node(int idx, String string, int l, int r) {
			super();
			this.idx = idx;
			this.string = string;
			this.l = l;
			this.r = r;
		}
		
		
	}
}
