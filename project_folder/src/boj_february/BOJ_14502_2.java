package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_2 {
	static int n, m; // 크기, 바이러스 개수
	// 0 빈칸 1벽 2 바이러스를 놓을 수 있는 위치
	static int[][] map;
	static int[][] map_copy;
	static int safe_zone;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] visit;
	static int[][] visit_copy;
	static ArrayList<dot> inactive_virus;
	static Queue<dot> virus;
	static boolean[] virus_choice;
	static int min_time=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n][n];
		visit = new int[n][n];
		safe_zone = 0;
		inactive_virus = new ArrayList<>();
		virus = new LinkedList<BOJ_14502_2.dot>();
		
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());
			for (int j = 0; j < n; j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());			
				if (a == 0) {
					safe_zone++;
					map[i][j] = a;
				} else if (a == 2) {
					inactive_virus.add(new dot(i, j));
					map[i][j] = a;
				}
				else {
					map[i][j]=-1;
				}
			}
		}
		virus_choice = new boolean[inactive_virus.size()];
		recursion(0);
		System.out.println(min_time);
	}

	//inactive_virus에서 m개의 활성 위치를 큐에 넣는 함수(중복없이)
	static void recursion(int k) {
		if (k == m) { // m개를 뽑았을 때 바이러스를 퍼뜨린다.
			spread();
			return;
		}
		for (int i = 0; i < inactive_virus.size(); i++) {
			if (!virus_choice[i]) {
				virus_choice[i]=true;
				int y=inactive_virus.get(i).y; 
				int x=inactive_virus.get(i).x;
				map[y][x]=0; //활성화할 위치를 0으로
				visit[y][x]=1; //방문처리 해준다.
				virus.offer(inactive_virus.get(i)); //큐에 넣는다.
				recursion(k+1);
				visit[y][x]=0;
				map[y][x]=2; 
				virus_choice[i]=false;
			}
		}
	}
	static void spread() {
		map_copy=new int[n][n];
		visit_copy=new int[n][n];
		//복사해준다.
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map_copy[i][j]=map[i][j];
				visit_copy[i][j]=visit[i][j];
			}
		}
		
		while(!virus.isEmpty()) {
			dot temp = virus.poll();
			int y= temp.y; int x = temp.x;
			
			if(min_time<(map_copy[y][x]+1)) {
				continue;
			}
			visit_copy[y][x]=1;
			for(int i=0;i<4;i++) {
				int ny=y+dy[i]; int nx = x+dx[i];
				if(ny<0||ny>=n||nx<0||nx>=n) continue; //범위
				if(visit[ny][nx]==1) continue; //이미 방문
				if(map_copy[ny][nx]==-1) continue; //벽
				if(map_copy[ny][nx]==0) { //0일때는 개수 줄이고
					safe_zone--;
					if(safe_zone==0) {
						if(min_time>map_copy[y][x]+1) {
							min_time=map_copy[y][x]+1;
						}
					}
				}
				virus.offer(new dot(ny, nx));
				map_copy[ny][nx]= map_copy[ny][nx]+1; 
			}
		}
	}

	static class dot {
		int y;
		int x;
		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}