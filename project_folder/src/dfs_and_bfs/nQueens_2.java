package dfs_and_bfs;

import java.util.Arrays;

public class nQueens_2 {
	static int size=4;
	static int[] map = new int[size];
	public static void main(String[] args) {
		dfs(0);
	}
	static void dfs(int row) {
		if(row==size) {
			System.out.println(Arrays.toString(map));
//			System.exit(0);
			return;
		}
		else {
			for(int c=0;c<size;c++) {
				map[row]=c;
				//c에 놓을 수 있는지
				if(isPromising(row)) {
					dfs(row+1);
				}
			}
		}
	}//dfs
	//인덱스가 row, 안에 들어 있는 값이 col
	static boolean isPromising(int index) {
		
		for(int i=0;i<index;i++) {
			if(map[i]==map[index]) {
				return false;
			}
			//대각선
			if(index-i==Math.abs(map[index]-map[i]))
				return false;
		}
		return true;
	}
}
