package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583 {
	private static int m;
	private static int n;
	private static int k;
	private static int[][] map;
	private static boolean[][] visit;
	private static int cnt;
	static int dy[]= {-1,1,0,0}; static int dx[] = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		m = Integer.parseInt(stringTokenizer.nextToken());
		n = Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());
		map= new int[m][n];
		
		
		for(int t=0;t<k;t++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int startX= Integer.parseInt(stringTokenizer.nextToken());
			int startY = Integer.parseInt(stringTokenizer.nextToken());
			int endX = Integer.parseInt(stringTokenizer.nextToken());
			int endY = Integer.parseInt(stringTokenizer.nextToken());
			for(int i=startY;i<endY;i++) {
				for(int j=startX;j<endX;j++) {
					map[i][j] =1;
				}
			}
			
			
		}//for t
		visit = new boolean[m][n];
		cnt=0;
		ArrayList<Integer> arr =new ArrayList<>();
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(!visit[i][j]&&map[i][j]==0) {//방문하지 않고 0이면 bfs시작
					cnt++;
					int big=0;
					Queue<dot> queue =new LinkedList<dot>();
					queue.offer(new dot(i, j));
					while(!queue.isEmpty()) {
						dot now = queue.poll();
						int y = now.y; int x =now.x;
						if(visit[y][x]) continue;
						visit[y][x]=true;
						big++;
						
						for(int k=0;k<4;k++) {
							int ny = y+dy[k]; int nx = x+dx[k];
							//범위
							if(ny<0||ny>=m||nx<0||nx>=n) continue;
							//방문하지 않고 0이면 다음걸로
							if(!visit[ny][nx]&&map[ny][nx]==0) {
								queue.offer(new dot(ny, nx));
							}
						}
					}//while
					//다끝나면 크기 넣는다.
					arr.add(big);
				}//방문하지않았고 0인
			}
		}//for i
		System.out.println(cnt);
		Collections.sort(arr);
		for(int i=0;i<arr.size();i++) {
			System.out.print(arr.get(i)+" ");
		}
	}//main
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
