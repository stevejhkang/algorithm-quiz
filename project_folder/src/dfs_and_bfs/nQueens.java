package dfs_and_bfs;

import java.util.Arrays;

import sun.tools.jar.resources.jar;

public class nQueens {
	static int size=4;
	static int[][] map = new int[size][size];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dfs(0);
	}
	static void dfs(int row) {
		if(row==size) {
			if(isPromising()) {
				System.out.println("결과 출력");
				for(int[] rowarr: map) {
					System.out.println(Arrays.toString(rowarr));
				}
			}
			return;
		}
		for(int c=0;c<size;c++) {
//			//세로
//			int flag=1;
//			for(int i=0;i<row;i++) {
//				if(map[i][c]==1) {
//					flag=0;
//					break;
//				}
//			}
//			if(flag==0) {
//				continue;
//			}
			map[row][c]=1;
			dfs(row+1);
			map[row][c]=0; 
		}
	}
	static boolean isPromising() {
		for(int r=0;r<size;r++) {
			for(int c=0;c<size;c++) {
				if(map[r][c]==1 ) {
//					//내 왼쪽에 누가 없나?
//					for(int k=0;k<c;k++) {
//						if(map[r][k]==1) {
//							return false;
//						}
//					}
					for(int k=1;k<=r;k++) {
						//위쪽
						if(map[r-k][c]==1) {
							return false;
						}
						//왼쪽대각선
						if(c-k>=0 &&map[r-k][c-k]==1) {
							return false;
						}
						if(c+k<size&&map[r-k][c+k]==1) {
							return false;
						}
					}
				
				}
			}
		}
		return true;
	}
}
class node{
	int y;
	int x;
	public node(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}
	
}
