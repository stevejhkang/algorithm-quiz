package dfs_and_bfs;

import java.util.ArrayList;

public class powersetSum {
	static int n =10;
	static int[] input= {1,2,3,4,5,6,7,8,9,10};
	static ArrayList<Integer> subset;
	static int sum=0;
	static boolean visit[] =new boolean[n];
	public static void main(String[] args) {
		subset= new ArrayList<>();
		powerset(0);
	}
	static void powerset(int k) {
	
		if(sum==10) {
			for(int i=0;i<subset.size();i++) {
				System.out.print(subset.get(i)+" ");
			}
			System.out.println("");
			return;
		}
		
		for(int i=0;i<10;i++) {
			if(!visit[i]) {
				visit[i]=true;
				sum+=input[i];
				if(sum<=10) {
					subset.add(input[i]);
					powerset(k+1);
					subset.remove(k);
				}
				sum-=input[i];
				visit[i]=false;
			}
			
		}
	}
}
