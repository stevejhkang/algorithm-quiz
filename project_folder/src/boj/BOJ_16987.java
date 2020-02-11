package boj;

import java.util.Scanner;

import dfs_and_bfs.dfs1;

public class BOJ_16987 {
	static int n;
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		n = scanner.nextInt();
		
	}
	static void permutation(int k) {
		if(k==n) {
			
		}
		
	}
	static class egg{
		int endure;
		int weight;
		public egg(int endure, int weight) {
			super();
			this.endure = endure;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "egg [endure=" + endure + ", weight=" + weight + "]";
		}
		
	}
}
