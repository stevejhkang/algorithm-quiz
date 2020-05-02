package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 2. 오전 10:26:45
 * @category 
* @problem_description RxC 빈곳. 물찬곳* 돌X 비버 굴 디 위치 에스 
* 고슴도치는 인접네칸 중 한칸 이동가능 물도 매분마다 비어있는 칸으로 확장 
* 물이 있는 칸과 인접해있는 비어있는 칸은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다.
* 고슴도치는 물로 차있는 구역으로 이동 불가, 물도 비버 소굴 이동 불가 
* 고슴도치가 비버굴로 이동하기 위해 필요한 최소시간
* 
* 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉 물이랑 고슴도치랑 해당 위치에 동시에 도착하면
* 안된다.
* @solving_description 
* 물먼저 채워주고 물이 차는 것보다 더 빨리 갈때 들어갈 수 있게 했는데
* 그렇게되면 물이 아예 안간 곳은 0이되어서 항상 고슴도치가 이동한 시간보다 적게
* 되어서 고슴도치가 방문을 못하게 된다. 그것을 막기위해 아예 0인 곳은 들어 갈 수 있게
* 만들면 해결할 수 있다. 또 아예 점이 하나일때도 캑터스 출력해야함
* 즉 극단적인 케이스의 예외처리를 생각해낼 줄 알아야겠다.
*/
public class BOJ_3055 {
	private static int R;
	private static int C;
	private static char[][] map;
	private static int endY;
	private static int endX;
	private static int startY;
	private static int startX;
	private static int[][] water_visit_time;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean[][] visit;
	private static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new char[R][C];
		
		endY=-1; endX=-1;
		startY=-1; startX=-1;
		
		Queue<dot> water = new LinkedList<dot>();
		for(int i=0;i<R;i++) {
			String s = bufferedReader.readLine();
			for(int j =0;j<C;j++) {
				char c = s.charAt(j);
				if(c=='D') {
					endY= i; endX=j;
				}
				else if(c=='S') {
					startY = i; startX=j;
				}
				else if(c=='*') {
					water.offer(new dot(i, j));
				}
				map[i][j] = c;
			}
		}
		//water first
		water_visit_time = new int[R][C];
		while(!water.isEmpty()) {
			dot now = water.poll();
			int y = now.y; int x= now.x;
			
			
			for(int i=0;i<4;i++) {
				int ny = y+dy[i]; int nx = x+dx[i];
				//range
				if(ny<0||ny>=R||nx<0||nx>=C) continue;
				if(water_visit_time[ny][nx]==0&&(map[ny][nx]=='.'||map[ny][nx]=='S')) {
					water_visit_time[ny][nx]=water_visit_time[y][x]+1;
					water.offer(new dot(ny, nx));
				}
			}
		}
		water.clear();
//		for(int i=0;i<R;i++) {
//			for(int j=0;j<C;j++) {
//				System.out.print(water_visit_time[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		min = Integer.MAX_VALUE;
		//sonic move
		if(endY==-1||endX==-1||startY==-1||startX==-1) {
			System.out.println("KAKTUS");
			return;
		}
		Queue<dot2> queue = new LinkedList<>();
		queue.offer(new dot2(startY, startX,0));
		visit  = new boolean[R][C];
		
		while(!queue.isEmpty()) {
			dot2 now = queue.poll();
			int y = now.y; int x = now.x; int move = now.move;
//			System.out.println("y: "+y+", x: "+x);
			if(y==endY&&x==endX&&map[endY][endX]=='D') {
				min = Math.min(min, move);
			}
			
			if(visit[y][x]) continue;
			visit[y][x] =true;
			
			for(int i=0;i<4;i++) {
				int ny =y+dy[i]; int nx = x+dx[i];
				//range
				if(ny<0||ny>=R||nx<0||nx>=C) continue;
				if(!visit[ny][nx]&&(move+1<water_visit_time[ny][nx]||water_visit_time[ny][nx]==0)&&map[ny][nx]!='X') {
					queue.offer(new dot2(ny, nx, move+1));
				}
				else if(ny==endY&&nx==endX) {
					queue.offer(new dot2(ny,nx,move+1));
				}
			}
		}
		
		if(min==Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		}
		else {
			System.out.println(min);
		}
		
	}
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static class dot2{
		int y,x,move;

		public dot2(int y, int x, int move) {
			super();
			this.y = y;
			this.x = x;
			this.move = move;
		}
	}
}
