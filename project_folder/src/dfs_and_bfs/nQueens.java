package dfs_and_bfs;

import java.util.Arrays;


public class nQueens {
	static int size=4;
	static int[][] map = new int[size][size];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dfs(0);
	}
	static void dfs(int row) {
		if(row==size) {
//			if(isPromising()) {
//				System.out.println("결과 출력");
//				for(int[] rowarr: map) {
//					System.out.println(Arrays.toString(rowarr));
//				}
//				System.exit(0);
//			}
			System.out.println("결과 출력");
			for(int[] rowarr: map) {
				System.out.println(Arrays.toString(rowarr));
			}
//			System.exit(0);
			return;
		}
		for(int c=0;c<size;c++) {
			map[row][c]=1;
			if(isPromising2(row+1))
				dfs(row+1);
			map[row][c]=0; 
		}
	}
	static boolean isPromising2(int row) {
		for(int r=0;r<row;r++) {
			for(int c=0;c<size;c++) {
				if(map[r][c]==1 ) {
					for(int k=1;k<=r;k++) {
						//위쪽
						if(map[r-k][c]==1) {
							return false;
						}
						//왼쪽대각선  위로 올라간 만큼 -x방향으로 이동 그런데 범위 오버 체크해야한다.
						if(c-k>=0 &&map[r-k][c-k]==1) {
							return false;
						}//오른쪽 대각선 위로 올라간만큼 +x방향으로 이동, 그런데 범위 오버체크해야한다.
						if(c+k<size&&map[r-k][c+k]==1) {
							return false;
						}
					}
				
				}
			}
		}
		return true;
	}
	static boolean isPromising() {
		for(int r=0;r<size;r++) {
			for(int c=0;c<size;c++) {
				if(map[r][c]==1 ) {
					for(int k=1;k<=r;k++) {
						//위쪽
						if(map[r-k][c]==1) {
							return false;
						}
						//왼쪽대각선  위로 올라간 만큼 -x방향으로 이동 그런데 범위 오버 체크해야한다.
						if(c-k>=0 &&map[r-k][c-k]==1) {
							return false;
						}//오른쪽 대각선 위로 올라간만큼 +x방향으로 이동, 그런데 범위 오버체크해야한다.
						if(c+k<size&&map[r-k][c+k]==1) {
							return false;
						}
					}
				
				}
			}
		}
		return true;
	}
	static class node{
		int y;
		int x;
		public node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}

