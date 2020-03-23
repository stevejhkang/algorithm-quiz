package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 21. 오후 2:00:22
 * @category 
* @problem_description 꽃을 피우려고한다. 초록색 배양액과 빨간색 배양액 배양액 뿌릴 수 있는 땅은 미리 정해져있다. 
* 0/ 배양액은 매초마다 이전에 배양액이 도달한 적이 없는 인접한 땅으로 퍼져간다. 이미 뭔가가 있으면 갈 수 없음
* 0. 황토색 칸은 처음 배양액을 뿌릴 수 있고 하얀색은 뿌릴 수 없는 땅이다. 
* 1. 꽃이 피어난 땅에는 배양액이 사라짐 
* 2. 초록색 배양액과 빨간색 배양액이 동시에 도달하면 꽃이 피어난다.
* 3. 주어진 배양액 모두 서로다른 곳에 모두 뿌려야한다.
* 4. 피울 수 있는 꽃의 최대 개수 구하라
* 
* @solving_description 입력: 세로 n 가로 m 초록색 배양액 g 빨간색 배양액 r이 주어지고
* 0은 호수 1은 배양액 못뿌리는 땅 2는 배양액을 뿌릴 수 있는 땅을 의미한다.
* 뿌릴 수 있는 땅의 개수는 10개 이하이다. 
* 1. 일단 2인 곳에 배양액을 따로 따로 뿌려야 한다. 그러면 10콤비r 10-r콤비g가 되겠군.
* 1-1 이중 combi사용하면 될듯?
* 2. 그리고 퍼져나가기 시작하는데 동시에 도착했는지를 알아보기 위해서 일단 
* G부터 원싸이클 돌리고 그다음 R원싸이클을 돌려서 같은게 있는지 알아볼까? 
* 한번하고 전부 체크하고 이런 식으로 해야될 거같음. 꽃생기면 더이상 나가면 안되니까. 그래서 -1처리해주면 큐에서 나와도 싸이클 더 못돌도록
* 설계를 하면 될듯 그다음 글로벌하게 체크++해주고
*/

public class p2 {
	
	private static int n;
	private static int m;
	private static int g;
	private static int r;
	private static int[][] map;
	private static ArrayList<dot> al;
	private static dot[] green;
	private static dot[] red;
	private static int flower;
	private static int[][] copy_map;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		g = Integer.parseInt(stringTokenizer.nextToken());
		r = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n][m];
		al = new ArrayList<dot>();
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j =0;j<m;j++) {
				int a =Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = a;
				if(a==2) {
					al.add(new dot(i, j, false));
				}
			}
		}//for i
		green = new dot[g];
		red = new dot[r];
		flower = Integer.MIN_VALUE;
		combiG(0, 0);
		
		System.out.println(flower);
		
	}//main
	static void combiG(int k, int idx) {
		if(k==g) {
			combiR(0, 0);
			return;
		}
		for(int i = idx;i<al.size();i++) {
			if(al.get(i).choice==false) {
				green[k] = al.get(i);
				al.get(i).choice=true;
				combiG(k+1, i+1);
				al.get(i).choice=false;
				green[k] = null;
			}
		}
	}//combiG
	static void combiR(int k, int idx) {
		if(k==r) {
			//퍼뜨리기를 시작한다.
			spread();
			return;
		}
		for(int i = idx;i<al.size();i++) {
			if(al.get(i).choice==false) {
				red[k] = al.get(i);
				al.get(i).choice=true;
				combiR(k+1, i+1);
				al.get(i).choice=false;
				red[k] = null;
			}
		}
	}//combiR
	
	static void spread() {
//		System.out.println();
//		System.out.println(Arrays.toString(green));
//		System.out.println(Arrays.toString(red));
//		System.out.println();
		int temp_flower=0;
		
		//맵 복사 일단하자
		copy_map = new int[n][m];
		for(int i=0;i<n;i++) {
			copy_map[i] = map[i].clone();
		}
		
		//퍼지는 시간을 체크하는 배열
		int[][] green_time = new int[n][m];
		int[][] red_time = new int[n][m];
		int[][] time = new int[n][m];
		
		//퍼질 배양액들을 저장한다. 그리고 시간이 같은 것을 체크하기 위해 배양액 잇는 시간도 넣어준다.
		ArrayList<dot2> next_green= new ArrayList<>();
		for(int i=0;i<g;i++) {
			int y = green[i].y; int x = green[i].x;
			next_green.add(new dot2(y, x));
			green_time[y][x] = 1;
			red_time[y][x]=-1;
		}
		
		ArrayList<dot2> next_red= new ArrayList<>();
		for(int i=0;i<r;i++) {
			int y = red[i].y; int x = red[i].x;
			next_red.add(new dot2(y, x));
			red_time[y][x] = 1;
			green_time[y][x] =-1;
		}
	
		//꽃이 핀자리 체크하는 배열
		boolean[][] check = new boolean[n][m];
		
		//둘다 퍼질 수 없어야 끝난다.
		while(!next_green.isEmpty()||!next_red.isEmpty()) {
			//다음 퍼질 것을 저장하는 배열
			ArrayList<dot2> temp_green = new ArrayList<>();
			//각 배양액을 4방향으로 퍼뜨린다.
			for(int i=0;i<next_green.size();i++) {
				int y = next_green.get(i).y; int x = next_green.get(i).x;
				
				//꽃을 피웠으면 0처리 해줫으므로 패스
				if(copy_map[y][x]==0) continue;
				
				for(int k=0;k<4;k++) {
					int ny = y+dy[k]; int nx =x+dx[k];
					//범위
					if(ny<0||ny>=n||nx<0||nx>=m) continue;
					//우물이면 패스
					if(copy_map[ny][nx]==0) continue;
					//방문한 적이 있으면 패스
					if(green_time[ny][nx]!=0||green_time[ny][nx]==-1) continue;
					//방문한다. 시간 체크하고 temp_green에 넣기
					green_time[ny][nx] = green_time[y][x]+1;
					temp_green.add(new dot2(ny, nx));
				}
			}
			//다음거 저장
			
			
			ArrayList<dot2> temp_red = new ArrayList<>();
			for(int i=0;i<next_red.size();i++) {
				int y = next_red.get(i).y; int x = next_red.get(i).x;
				
				//꽃을 피웠으면
				if(copy_map[y][x]==0) continue;
				
				for(int k=0;k<4;k++) {
					int ny = y+dy[k]; int nx =x+dx[k];
					//범위
					if(ny<0||ny>=n||nx<0||nx>=m) continue;
					//우물이면 패스
					if(copy_map[ny][nx]==0) continue;
					//방문한 적이 있으면 패스
					if(red_time[ny][nx]!=0||red_time[ny][nx]==-1) continue;
					//방문한다. 시간 체크하고 temp_green에 넣기
					red_time[ny][nx] = red_time[y][x]+1;
					temp_red.add(new dot2(ny, nx));
				}
			}
			

//			for(int i=0;i<n;i++) {
//				for(int j=0;j<m;j++) {
//					if(red_time[i][j]!=0&&!check[i][j]&&red_time[i][j]==green_time[i][j]) {
//						copy_map[i][j]=0;
//						check[i][j] = true;
//						temp_flower++;
//					}
//				}
//			}
			next_green=temp_green;
			next_red=temp_red;
			for(int i=0;i<next_green.size();i++) {
				dot2 green_one = next_green.get(i);
				int gr = green_one.y; int gc = green_one.x;
				for(int j=0;j<next_red.size();j++) {
					dot2 red_one = next_red.get(j);
					int rr = red_one.y; int rc= red_one.x;
					if(red_time[rr][rc]!=0&&rr==gr&&rc==gc&&red_time[rr][rc]==green_time[gr][gc]&&!check[rr][rc]) {
						copy_map[red_one.y][red_one.x]=0;
						check[red_one.y][red_one.x]=true;
						temp_flower++;
					}
				}
			}
			
		
		}//while
		
//		for(int i=0;i<n;i++) {
//			System.out.println(Arrays.toString(green_time[i]));
//		}
//		System.out.println();
//		for(int i=0;i<n;i++) {
//			System.out.println(Arrays.toString(red_time[i]));
//		}
//		System.out.println();
//		for(int i=0;i<n;i++) {
//			System.out.println(Arrays.toString(copy_map[i]));
//		}
		
		if(temp_flower>flower) {
			flower=temp_flower;
		}
	}//spread
	
	static class dot {
		int y, x;
		boolean choice;
		public dot(int y, int x, boolean choice) {
			super();
			this.y = y;
			this.x = x;
			this.choice = choice;
		}
		@Override
		public String toString() {
			return "[y=" + y + ", x=" + x + ", choice=" + choice + "]";
		}
		
		
	}//dot
	static class dot2{
		int y, x;

		public dot2(int y, int x) {
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
















