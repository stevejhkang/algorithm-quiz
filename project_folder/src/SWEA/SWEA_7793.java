package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 9. 오후 1:21:53
 * @category 
* @problem_description 악마의 손아귀스킬로 매 초마다 상하좌우 영역들을 부식
*  수연이는 살아남기 위해 여신님이 있는 곳으로 가야한다. N행 M열로 이루어진 지도 내에서
*  수연이는 말을 타고 1초에 동서남북으로 인접한 칸 이동 단 돌과 악마의 손아귀 피해서
*  여신님께 가는 데 걸리는 최소시간 D가 여신 S가 수연 X가 돌위치 *이 악마
* @solving_description 악마의 손아귀가 해당 좌표에 언제 도착하는 지를 visit에 저장해준다.
* 그것보다 더 빨리 가면서 최소시간 BFS로 풀면 될듯
*/

public class SWEA_7793 {
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
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
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
					if(c=='D') {
						endY=i; endX=j;
					}
					else if(c=='S') {
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
				
				if(y==endY&&x==endX) {
					min= Math.min(min, movein);
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
					else if(map[ny][nx]=='D') {
						move.offer(new moving(ny, nx, movein+1));
					}
				}
				
			}//while move
			
			System.out.println("#"+tc+" "+(min==Integer.MAX_VALUE?"GAME OVER":min));
			
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
