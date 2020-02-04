package dfs_and_bfs;

import java.util.Scanner;

public class powerset {
	static int[] set; static int n;
	static int[] truefalse= {0,1};
	static boolean[] bool_set;
	static boolean[] childs= {true,false};
	static char[] charr= {'A','B','C'};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		 n = scanner.nextInt();
		set= new int [n];
		bool_set = new boolean[n];
		recursion(0);
	}
	static void recursion(int k) {
		if(k==n) {
			for(int i=0;i<n;i++) {
//				System.out.print(set[i]+" ");
				if(bool_set[i]) {
					System.out.print(charr[i]+" ");
				}
			}
			System.out.println("");
			return;
		}
		for(int i=0;i<2;i++) {
			bool_set[k]=childs[i]; //따로 저장
			recursion(k+1);
		}
	}
}
