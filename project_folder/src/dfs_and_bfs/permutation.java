package dfs_and_bfs;

import java.util.Arrays;
import java.util.Scanner;

public class permutation {
	static int[] set; static int n,r;
	static int[] truefalse= {0,1};
	static boolean[] bool_set;
	static int[] nums= {1,2,3};
	static boolean[] visit;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		 n = scanner.nextInt();
		 r= scanner.nextInt();
		set= new int [r];
		bool_set = new boolean[n];
		visit= new boolean[n];
		permutation(0);
	}
	static void permutation(int k) {
		if(k==r) {
			System.out.println(Arrays.toString(set));
			return;
		}
		for(int i=0;i<n;i++) {
			if(!visit[i]) {
				visit[i]=true;
				set[k]=nums[i];
				permutation(k+1);
				visit[i]=false;
				
			}
		}
	}
}
