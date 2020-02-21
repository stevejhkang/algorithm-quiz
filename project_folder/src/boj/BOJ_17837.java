package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17837 {
	private static int n, k;
	private static int[][] map;
	static List[][] pieces;
	static int dy[] = { 0, 0, 0, -1, 1 };
	static int dx[] = { 0, 1, -1, 0, 0 }; // 오른, 왼, 위, 아래
	private static piece[] order;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());

		// map에 이동판을 만든다.
		map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		// 각 영역에 말 번호와 방향을 저장할 수 있는 ArrayList를 초기화 한다.
		pieces = new List[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				pieces[i][j] = new ArrayList<piece>();
			}
		}

		// 말들의 정보를 담는다. 좌표랑 방향
//		ArrayList<piece> order = new ArrayList<piece>();
//		Queue<piece> order = new LinkedList<>();
		order = new piece[k+1];
		for (int i = 1; i <= k; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int y = Integer.parseInt(stringTokenizer.nextToken());
			int x = Integer.parseInt(stringTokenizer.nextToken());
			int dir = Integer.parseInt(stringTokenizer.nextToken());
			piece new_piece = new piece(y, x, dir, i); 
			pieces[y][x].add(new_piece);
			order[i]= new_piece;
			// 어떻게 할 것인가 일단 큐에 넣어보자. 큐에 넣기보다는 
			//배열에 넣어서 다같이 옮기면 같이 옮기는 것도 위치를 옮겨주자
		}

		int order_index=0;
		int turn=0;
		end: while (true) {
			
			if(turn>1000) {
				System.out.println(-1);
				return;
			}
			order_index=(order_index+1==k+1? 1 : order_index+1);
//			System.out.println(order_index);
			if(order_index==1) {
				turn++;
			}
			piece now = order[order_index];
			int y = now.y;
			int x = now.x;
			int dir = now.dir; //방향
			int num = now.num; //번호
			int ny = y + dy[dir];
			int nx = x + dx[dir];

			// 범위 벗어나거나 파란색이면 방향바꾸고 갈 수 있으면 아래로 내려가고
			//안되면 그냥 그 좌표에 넣어주고 그냥 놔둔다.
			if (ny <= 0 || ny > n || nx <= 0 || nx > n || map[ny][nx] == 2) {// 범위 벗어나면 반대방향으로 이동
				if (ny <= 0) { // 위로 갔는데 넘어가면 아래로 가본다.
					now.dir = 4; dir= 4;
					ny = y + dy[now.dir];
					if (ny > n || map[ny][nx] == 2) {
						// 바꾼데가 파랑이면 방향만 반대로 하고 그냥 다시 큐에 넣어주고 끝낸다.
						order[order_index]= now;
						continue;
					}
//					// 이동시킨 점과 바뀐 방향 추가
//					else {
//						order[order_index]= new piece(ny, nx, now.dir, num);
//					}
				} else if (ny > n) {// 아래로 갔는데 넘어가면 위로 가본다.
					now.dir = 3; dir =3;
					ny = y + dy[now.dir];
					if (ny <= 0 || map[ny][nx] == 2) {
						order[order_index] = now;//방향만 반대로
						continue;
					} 
//					else {
//						order[order_index] = new piece(ny, nx, now.dir, num);
//					}
				} else if (nx <= 0) { // 좌로 갔는데 넘어가면 우로 가본다.
					now.dir = 1; dir=1;
					nx = x + dx[now.dir];
					if (nx > n || map[ny][nx] == 2) { // 우로 갈때 범위 벗어나거나 파랑이면 그냥 넣어준다.
						order[order_index] = now;//방향만 반대로
						continue;
					} 
//					else {// 아니면 바뀐 좌표를 넘겨준다.
//						order[order_index] = new piece(ny, nx, now.dir, num);
//					}
				} else  if(nx>n){ // 우로 갔는데 안되면 좌로 가본다.
					now.dir = 2; dir=2;
					nx = x + dx[now.dir];
					if (nx <= 0 || map[ny][nx] == 2) { // 좌로 갈때 0에 도착하면 그냥 넣어준다.
						order[order_index] = now;//방향만 반대로
						continue;
					} 
//					else { // 범위나 파랑아니면 바뀐 좌표
//						order[order_index] = new piece(ny, nx, now.dir, num);
//					}
				}
				else { //map이 2일때
					if(dir==1||dir==2) {
						dir=((dir+1)%2==0?2:1); now.dir=dir; 
						ny = y+dy[dir]; nx =x+dx[dir];
					}
					else if(dir==3||dir==4) {
						dir=((dir+1)%2==0?4:3); now.dir=dir;
						ny=y+dy[dir]; nx = x+dx[dir];
					}
					if (ny <= 0 || ny > n || nx <= 0 || nx > n || map[ny][nx] == 2) {
						order[order_index]=now;
						continue;
					}
				}
			}
			//흰색
			if (map[ny][nx] == 0) { 
				int index = 0;
				for (int i = 0; i < pieces[y][x].size(); i++) {
					piece a = (piece) pieces[y][x].get(i);
					if (a.num == num) {// 숫자가 같으면 해당 인덱스에서부터 그 위에 것까지 전부 이동시킨다.
						index = i;
						break;
					}
				}
				// index에서부터 쭉 더해준다.
				for(int i=index;i<pieces[y][x].size();i++) {
					piece temp = (piece) pieces[y][x].get(i);
					int temp_num =temp.num;
					order[temp_num] =temp;
					temp.y= ny; temp.x= nx;
					pieces[ny][nx].add(temp);
					
					
				}
				if(pieces[ny][nx].size()>=4) {
					break end;
				}
				while (index < pieces[y][x].size()) {
					pieces[y][x].remove(index);// 이동시켜준거 삭제시킨다.
				}
			}
			// 빨강
			else if (map[ny][nx] == 1) {
				int index = 0;
				for (int i = 0; i < pieces[y][x].size(); i++) {
					piece a = (piece) pieces[y][x].get(i);
					if (a.num == num) {// 숫자가 같으면 맨 위부터 해당 인덱스까지 위에서부터 전부 이동시킨다.
						index = i;
						break;
					}
				}
				// index에서부터 쭉 더해준다.
				for (int i = pieces[y][x].size() - 1; i >= index; i--) {
					piece temp = (piece) pieces[y][x].get(i);
					int temp_num =temp.num;
					temp.y= ny; temp.x= nx;
					order[temp_num] =temp;
					pieces[ny][nx].add(temp);
					
				}
				if(pieces[ny][nx].size()>=4) {
					break end;
				}
				while (index < pieces[y][x].size()) {
					pieces[y][x].remove(index);// 이동시켜준거 삭제시킨다.
				}
			}
//			System.out.println();
//			for(int i=1;i<=n;i++) {
//				for(int j=1;j<=n;j++) {
//					System.out.print(pieces[i][j].size()+" ");
//				}
//				System.out.println();
//			}
		}
		System.out.println(turn);
	}// main

	static class piece implements Comparable<piece> {
		int y;
		int x;
		int dir;// 우, 좌, 상, 하
		int num;

		public piece(int y, int x, int dir, int num) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.num = num;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		@Override
		public String toString() {
			return "[dir=" + dir + ", num=" + num + "]";
		}

		@Override
		public int compareTo(piece o) {

			if (this.num < o.num) {
				return 1;
			} else if (this.num > o.num) {
				return -1;
			} else
				return 0;
		}

	}
}
