package boj_march;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_4179 {
	private static int n;
	private static int m;
	private static char[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int startY;
	private static int startX;
	private static int endY;
	private static int endX;
	private static int devilY;
	private static int devilX;
	private static int[][] visit;
	private static boolean[][] visit2;
	private static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(bufferedReader.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			m = Integer.parseInt(stringTokenizer.nextToken());
			n = Integer.parseInt(stringTokenizer.nextToken());
			map = new char[n][m];
			Queue<dot> queue = new LinkedList<dot>();
			startY = 0; startX = 0;
			endY=0; endX=0;
			devilY= 0; devilX=0;
			visit = new int[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					visit[i][j] = Integer.MAX_VALUE;
				}
			}
			
			for(int i=0;i<n;i++) {
				String s = bufferedReader.readLine();
				for(int j=0;j<m;j++) {
					char c = s.charAt(j);
					map[i][j] = c;
					
					if(c=='@') {
						startY=i; startX=j;
					}
					else if(c=='*') {
						devilY=i; devilX=j;
						queue.offer(new dot(devilY,devilX));
						visit[devilY][devilX]=0;
					}
				}
			}
			
//			for(int i=0;i<n;i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
		
			min = Integer.MAX_VALUE;
			
			
			
			while(!queue.isEmpty()) {
				dot now = queue.poll();
				
				int y = now.y; int x = now.x;
				
				for(int k=0;k<4;k++) {
					int ny = y+dy[k]; int nx = x+dx[k];
					//범위
					if(ny<0||ny>=n||nx<0||nx>=m) continue;
					//땅일때만 퍼질 수 있음 방문한 적없고
					if(map[ny][nx]=='.'&&visit[ny][nx]==Integer.MAX_VALUE) {
						visit[ny][nx]=visit[y][x]+1;
						queue.offer(new dot(ny,nx));
					}
					
				}
			}
			//devil의 손아귀보다 이동페이스가 적어야 갈 수 있음. 
			//그리고 돌없어야하마
			Queue<moving> move = new LinkedList<>();
			move.offer(new moving(startY, startX,0));
			visit[startY][startX]=0;
			visit2 = new boolean[n][m];
			while(!move.isEmpty()) {
				moving now = move.poll();
				int y= now.y; int x =now.x; int movein = now.move;
				
				if(y==n-1||y==0||x==m-1||x==0) {
					min= Math.min(min, movein+1);
				}
				
				if(visit2[y][x]) continue;
				visit2[y][x] = true;
				
				for(int k=0;k<4;k++) {
					int ny = y+dy[k]; int nx = x+dx[k];
					if(ny<0||ny>=n||nx<0||nx>=m) continue;
					//한번도 방문한 적 없고 땅이고    현재무빙+1작거나 무한대이면(아예 악마손아귀가 옮긴게 아니면) 
					if(map[ny][nx]=='.'&&!visit2[ny][nx]&&(visit[ny][nx]>movein+1||visit[ny][nx]==Integer.MAX_VALUE)) {
						move.offer(new moving(ny, nx, movein+1));
					}
					
				}
				
			}//while move
			
			System.out.println((min==Integer.MAX_VALUE?"IMPOSSIBLE":min));
			
		}//tc
		
	}//main
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static class moving{
		int y,x,move;

		public moving(int y, int x, int move) {
			super();
			this.y = y;
			this.x = x;
			this.move = move;
		}
		
	}
}

