package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 11. 오전 11:11:23
 * @category DFS  
 * @problem_description 각 좌표마다 숫자가 있는데 지날때마다 합산된다. 그렇게해서 n-1,n-1까지 도달할때 제일 합산이 적은 값을 출력
 * @solving_description  현재 값이 도달했을때 min값보다 작으면 가지치기는 기본이고,
 * visit[ny][nx]는 ny,nx까지 도달하는데 합해진 값의 최소를 저장하는 값이다. 
 * 현재까지 이동하면서 받은 합과 map[ny][nx]값을 더한 값보다 visit[ny][nx]가 작으면은 더이상 탐색을 진행하지 않는다.
 */

public class BOJ_4485 {
	private static int[][] map;
	static int dy[]= {1,0,-1,0};
	static int dx[]= {0,1,0,-1};
	static int min=Integer.MAX_VALUE;
	static int visit[][];
	private static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int tc=0;
		while(true) {
			min=Integer.MAX_VALUE;
			n = Integer.parseInt(bReader.readLine());
			if(n==0) {
				return;
			}
			map = new int[n][n];
			visit = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(bReader.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					visit[i][j]=Integer.MAX_VALUE;
				}
			}
			dfs(0,0,map[0][0]);
			tc++;
			StringBuilder sBuilder= new StringBuilder();
			sBuilder.append("Problem "+tc+": ").append(min);
			System.out.println(sBuilder);
		}//while
		
	}
	static void dfs(int y,int x, int money) {
		if(y==n-1&&x==n-1) {
			if(min>money) {
				min=money;
			}
			return;
		}
		for(int i=0;i<4;i++) {
			int ny = y+dy[i]; int nx = x+dx[i];
			//범위
			if(nx<0||nx>=n||ny<0||ny>=n) continue;
			//현재 방문하려는 곳에 저장된 최소 값보다 크면은 갈 필요없음
			if(visit[ny][nx]<=money+map[ny][nx]) continue;
			//이미 구한 최솟값보다 크면 더이상 할 필요없음
			if(min<money+map[ny][nx]) continue;
			
			visit[ny][nx]=money+map[ny][nx];
			dfs(ny,nx,money+map[ny][nx]);
		}
	}
}
