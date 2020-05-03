package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 4. 오전 12:50:26
 * @category BFS
* @level 3
* @problem_description 
* 체스 판의 말처럼 원숭이가 이동(장애물 점프가능)할 수 있지만 K번만 가능하다. 그외에는 4방향으로 움직일 수 있다.
* 원숭이는 맨 왼쪽위에서 맨 오른쪽 아래까지 가야한다. 
* 격자판이 주어졌을 때, 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법을 알아내라  
* 도착점에 갈 수 없을 경우에는 -1을 출력한다.
* @solving_description 
* K 주어지고 가로W 세로H가 주어진다. K는 30이하의 정수
* 최소한이니까 BFS일 확률이 높다.
* 
* 포인트!: BFS에서 해당 점을 방문할때만 방문체크를 하게 되면 방문체크 전까지
* 큐에 있던 점들이 해당 점을 다음 순번에 넣기 위해 해당점의 데이터를 큐에 계속해서
* 넣기 때문에 메모리를 많이 사용하게 되어 메모리 초과가 날 수 있다. 
*/

public class BOJ_1600 {
	private static int k;
	private static int w;
	private static int h;
	private static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int[] jumpy= {-2,-1,1,2,2,1,-1,2};
	static int[] jumpx = {1,2,2,1,-1,-2,-2,-1};
	private static int[][][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		
		k = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		w = Integer.parseInt(stringTokenizer.nextToken());
		h = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[h][w];
		for(int i=0;i<h;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<w;j++) {
				map[i][j]= Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		visit = new int[k+1][h][w];
		//그리고 MAX값으로 초기화를 해준다.
		for(int i=0;i<=k;i++) {
			for(int j=0;j<h;j++) {
				Arrays.fill(visit[i][j], Integer.MAX_VALUE);
			}
		}
		
		//0,0에서부터 BFS를 시작한다.
		Queue<monkey> queue = new LinkedList<>();
		queue.add(new monkey(0,0,k,0)); //남은 점프 횟수는 k 움직인 횟수는 0
		int min = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			monkey now = queue.poll();
			int y = now.y; int x = now.x;
			int k = now.k; int move =now.move;
			
			if(move>=min) continue; //지금까지 계산된 min 값보다 크면 더이상 갈 필요없다.
			
			//현재 저장된게 작거나 같으면 더이상 갈 필요가 없다
			if(visit[k][y][x]<move) continue;
			//아니면 갱신
			visit[k][y][x] =move;
			
			if(y==h-1&&x==w-1) { //끝에 도달했을 때 min값을 구해놓는다.
				min= Math.min(min, move);
				continue;
			}
			
			for(int dir=0;dir<4;dir++) { //일반 방향으로 갔을 때 이동횟수만 증가시킨다.
				int ny = y+dy[dir];
				int nx = x+dx[dir];
				//범위 체크
				if(ny<0||ny>=h||nx<0||nx>=w) continue;
				
				if(map[ny][nx]!=1&&visit[k][ny][nx]>move+1) { //이미 저장된 값보다 작을 때 들어간다.
					visit[k][ny][nx]=move+1;
					queue.add(new monkey(ny, nx, k, move+1));
				}
			}
			//k가 1이상인 경우는 점프도 해본다.
			if(k>=1) {
				for(int dir=0;dir<8;dir++) {
					int ny = y+jumpy[dir];
					int nx = x+jumpx[dir];
					
					if(ny<0||ny>=h||nx<0||nx>=w) continue;
					
					if(map[ny][nx]!=1&&visit[k-1][ny][nx]>move+1) { //이미 저장된 값보다 작을 때만 들어간다.
						visit[k-1][ny][nx]= move+1;
						queue.add(new monkey(ny, nx, k-1, move+1));
					}
				}
			}
		}//while
		min = Integer.MAX_VALUE;
		for(int i=0;i<=k;i++) {
			min = Math.min(min, visit[i][h-1][w-1]); //마지막 지점에 저장된 값 중에 최솟값을 찾는다.
		}
		System.out.println(min==Integer.MAX_VALUE? -1: min);
		
	}//main
	static class monkey{
		int y,x, k,move; //좌표, 남은 점프 횟수

		public monkey(int y, int x, int k, int move) {
			super();
			this.y = y;
			this.x = x;
			this.k = k;
			this.move = move;
		}

		
		
	}
}
