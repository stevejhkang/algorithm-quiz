package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_2 {
	static int n;
	// 0: 가로, 1: 세로, 2: 대각선
	static int[][] map;
	static boolean visit[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n + 1][n + 1]; // 벽확인
		visit = new boolean[3][n + 1][n + 1]; // 방향별로 탐색여부 체크

		// 벽입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][j] = a;
			}
		}
		int ans=0;
		ans= recursion(new dot(1, 2, 0));
		
	}
	static int recursion(dot temp) {
		int y = temp.y; int x = temp.x; int dir= temp.dir;
		if(y==n&&x==n) {
			return 1;
		}
		if(visit[dir][y][x]) return 0;
		for(int i=0;i<3;i++) {
			
		}
		
	}
}
class dot{
	int y;
	int x;
	int dir;
	public dot(int y, int x, int dir) {
		super();
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
	@Override
	public String toString() {
		return "[y=" + y + ", x=" + x + ", dir=" + dir + "]";
	}
	
	
}
