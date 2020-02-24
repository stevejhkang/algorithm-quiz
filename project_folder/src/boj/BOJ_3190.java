package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3190 {
	private static int n;
	private static int k;
	private static int[][] map;
	static final int L =-1; static final int D =1;
	static int dy[] = {0,1,0,-1};
	static int dx[] = {1,0,-1,0};
	private static int time;
	private static int[] direction;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		k = Integer.parseInt(bufferedReader.readLine());
		
		map = new int[n+1][n+1];
		for(int i=0;i<k;i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int y = Integer.parseInt(stringTokenizer.nextToken());
			int x = Integer.parseInt(stringTokenizer.nextToken());
			map[y][x] = 1; 
			//사과위치 1
		}
		direction = new int[10002];
		
		int turn_time = Integer.parseInt(bufferedReader.readLine());
		for(int i=0;i<turn_time;i++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			int num = Integer.parseInt(stringTokenizer.nextToken());
			char turn = stringTokenizer.nextToken().charAt(0);
			if(turn =='L') {
				direction[num]=-1;
			}
			else if(turn=='D') {
				direction[num]=1;
			}
		}
		
		int dir = 0;
		
		Deque<body> snake =  new LinkedList<>();
		snake.offer(new body(1, 1));
		map[1][1]=2;
		time = 0;
		while(true) {
			time++;
			body now = snake.peekLast();
			int y = now.y; int x=now.x;
			int ny = y+dy[dir]; int nx = x+dx[dir];
			//범위를 벗어나거나 몸체가 있으면 초 출력하고 끝
			if(ny<=0||ny>n||nx<=0||nx>n||map[ny][nx]==2) {
				System.out.println(time);
				return;
			}
		
			
			if(map[ny][nx]!=1) {//사과가 아니면 꼬리를 움직여준다.
				body erase = snake.poll();
				map[erase.y][erase.x] = 0;
//				System.out.println(snake.poll());
			}
			//안 부딪히면 넣어 덱에 넣고 map에 칠한다.
			snake.offer(new body(ny, nx));
			map[ny][nx]=2;
			
			//범위 벗어나면 반대쪽으로 옮겨준다.
			dir=(dir+direction[time]<0?3:dir+direction[time]);
			dir=(dir>3?0:dir);
			
//			for(int i=1;i<=n;i++) {
//				for(int j=1;j<=n;j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}//while
	}//main
	static class body{
		int y,x;

		public body(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "[y=" + y + ", x=" + x + "]";
		}
		
	}
	
}
