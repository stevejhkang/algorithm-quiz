package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 19. 오후 4:08:10
 * @category  
 * @problem_description 로봇청소기가 주어졌을때 청소하는 영역의 개수를 구하는 프로그램
 * @solving_description 간단한 사방탐색 구현문제인데, 종료조건이 뒤쪽 방향이 벽이라 후진할
 * 수 없는 경우에 작동을 멈춘다고 했는데 나는 범위를 벗어날때만 종료처리를 해주어서 틀렸습니다가 
 * 나왔다. 현재위치 청소! 후 다른 것을 탐색하는 것이므로 그대로 따라주어야 구현이 편할 듯 하다.
 */
public class BOJ_14503 {
	private static int n;
	private static int m;
	private static int[][] map;
	private static int startY;
	private static int startX;
	private static int d;
	private static int cnt;
	static int[] dy = { -1, 0, 1, 0 }; //북동남서
	static int[] dx = { 0, 1, 0, -1 };
	private static int[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
	
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n][m];
		visit = new int[n][m];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		startY = Integer.parseInt(stringTokenizer.nextToken());
		startX = Integer.parseInt(stringTokenizer.nextToken());
		d = Integer.parseInt(stringTokenizer.nextToken()); //현재 바라보는 방향 북 동 남 서
		cnt = 0; //청소하는 칸 개수
		
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<m;j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken()); 
				map[i][j]= a;
				if(a==1) {
					visit[i][j]=1; 
				}
			}
		}
		//청소여부를 visit에 지정을 할 것인가 아니면 map에?
		//모든 외곽은 벽이다.
		Queue<dot> queue = new LinkedList<>();
		dot init = new dot(startY, startX, d);
		
		queue.offer(init);
		out:while(true) {
			dot now = queue.poll();
			int y = now.y; int x = now.x; int dir =now.dir;
			
			//1. 현재위치 청소
			//방문처리 및 청소횟수 ++해준다.
			if(visit[y][x]==0) {
				visit[y][x]=3;
				cnt++;
			}
//			System.out.println(cnt);
//			for(int i=0;i<n;i++) {
//				for(int j=0;j<m;j++) {
//					System.out.print(visit[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println(now.dir);
//			System.out.println(y+","+x);
			//2. 현재방향을 기준으로 왼쪽방향부터 차례대로 탐색
			// a. 왼쪽 방향 청소안되있으면 한칸 전진 후 1번부터 다시 진행  -> 전진 후 큐에 넣어준다.
			// b. 왼쪽 방향에 청소할 공간 없으면 그 방향으로 회전하고 2번으로 즉 왼쪽으로 회전후에 
			//왼쪽방향 탐색 -> 다시 회전
			// c. 네 방향 모두 청소가 이미 되있거나 벽인 경우, 바라보는 방향 유지한채로 
			//한칸 후진하고 다시 왼쪽 탐색
			// d. 모두 청소되고 벽이면서 뒤쪽 방향이 벽이라 후진 못하면 작동을 멈춘다.
			boolean flag= false;
			for(int k=0;k<4;k++) {
				dir= (dir-1<0?3:dir-1); //음수이면 3으로 바꿔준다.
				int ny = y+dy[dir]; int nx=x+dx[dir];
				//방문하지 않았고, 벽이 아니고, 범위내 면 이동한 것을 다시 큐에 담아준다.
				if(ny<n&&ny>=0&&nx<m&&nx>=0&&visit[ny][nx]==0&&map[ny][nx]==0) {
					queue.offer(new dot(ny, nx, dir));
					flag = true; //가능하면 true처리해주고 아래에서 false이면 후진처리한다.
					break;
				}
			}
			if(!flag) { //4방향 모두 안되면 처음 방향에서 후진
				int backY = y+dy[(dir+2)%4]; int backX = x+dx[(dir+2)%4];
				//벽이거나 범위를 벗어나면 종료 
				if(backY<0||backY>=n||backX<0||backX>=m||map[backY][backX]==1) { 
					break out;
				}else { //갈 수 있으면 감 처리한다.
					queue.offer(new dot(backY, backX, dir));
				}
			}
		}//while out
		System.out.println(cnt);
	}
	static class dot{
		int y,x,dir;

		public dot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
		
	}
}
