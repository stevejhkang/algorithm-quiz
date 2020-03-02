package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 20. 오전 11:44:49
 * @category 조합과 BFS 
 * @problem_description조합으로 바이러스를 m개 선택하고 그 후에 그 바이러스를 BFS를 통해 퍼뜨려서 전부 퍼뜨리는데 걸리는 최소 시간을  
 * 측정하는 문제
 * @solving_description
 * 핵심은 퍼뜨리는 케이스마다 맵복사, visit초기화, 빈길 개수를 복사해주어야 한다. 또 빈길 개수가 0일때 visit을 출력하는 것이 아닌
 * 큐에 남아 있는 것중에 최대를 출력하도록 해야한다. 또 최솟값을 구하기 위해서는 초기에 visit배열에 Max값으로 초기화 해주는 것도 기본이다.
 */
public class BOJ_17141 {
	private static int n;
	private static int m;
	private static int[][] map;
	private static int cnt;
	private static dot[] active_virus;
	private static ArrayList<dot> viruss;
	private static int min;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	private static int virus_place;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		virus_place=0;
		map = new int[n][n]; cnt =0;
		viruss = new ArrayList<dot>();
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<n;j++) {
				int a= Integer.parseInt(stringTokenizer.nextToken());
				if(a==2) {
					viruss.add(new dot(i, j));
					virus_place++;
				}
				else if(a==0)
					cnt++;
				map[i][j]=a; 
			}
		}//입력부분
		cnt+=virus_place-m;
		if(cnt==0) {
			System.out.println(0);
			return;
		}
		active_virus = new dot[m];
		min = Integer.MAX_VALUE;
		dfs(0,0);
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
		
	}//main
	//m개를 선택 후 퍼뜨리기를 시작한다.
	static void dfs(int k,int index) {
		if(k==m) {
			//m개를 선택하면 퍼뜨리기 시작한다.
			spread();
			return;
		}
		for(int i=index;i<viruss.size();i++) {
			active_virus[k] = viruss.get(i);
			dfs(k+1, i+1);
		}
	}//dfs
	
	static void spread() {// 배치한 후 퍼뜨린다.
		//하나의 케이스이므로 복사해주어야한다.
		int copy_cnt= cnt; 
		
		int visit[][] = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				visit[i][j]= Integer.MAX_VALUE; 
			}
		}
		int map_copy[][] = new int[n][n];
		for(int i=0;i<n;i++) {
			map_copy[i]= map[i].clone(); 
		}
		
		//활성화된 애들의 visit을 0으로 처리시켜준다.
		Queue<dot> queue = new LinkedList<dot>();
		for(int i=0;i<active_virus.length;i++) {
			dot temp = active_virus[i];
			queue.offer(temp);
			visit[temp.y][temp.x]= 0;
			map_copy[temp.y][temp.x]=-1;
		}
		while(!queue.isEmpty()) {
			dot now = queue.poll();
			int y = now.y; int x = now.x;
			if(visit[y][x]>min) {
				return;
			}
			//바이러스 개수가 0개가 되면 해당 좌표 출력
			if(copy_cnt<=0) {
				int max= Integer.MIN_VALUE;
//				for(int i=0;i<n;i++) {
//					for(int j=0;j<n;j++) {
////						if(visit[i][j]==Integer.MAX_VALUE)
////							System.out.print(-1+" ");
////						else System.out.print(" "+visit[i][j]+"  ");
//						if(visit[i][j]!=Integer.MAX_VALUE&&max<visit[i][j])
//							max=visit[i][j];
//					}
////					System.out.println();
//				}
//				System.out.println(max);
				queue.offer(now);
				while(!queue.isEmpty()) { //큐에 아직 남아있는 게 있을 수 있다 그것들 중의 visit값 최대인게 최종 걸린 시간이다.
					dot check_max = queue.poll();
					int ly = check_max.y; int lx = check_max.x;
					if(visit[ly][lx]!=Integer.MAX_VALUE&& max<visit[ly][lx]) {
						max=visit[ly][lx];
					}
				}
				
				if(min>max) {
					min=max;
//					System.out.println(min);
				}
				break;
			}
			
			for(int i=0;i<4;i++) {
				int ny = y+dy[i]; int nx = x+dx[i];
				//범위
				if(ny<0||ny>=n||nx<0||nx>=n) continue;
				//벽
				if(map_copy[ny][nx]==1) continue;
				//MAX이거나(방문안하거나) 
				if(visit[ny][nx]==Integer.MAX_VALUE) {//처음 방문하는 경우
					visit[ny][nx] =visit[y][x]+1;
//					map_copy[ny][nx]=2;
					queue.offer(new dot(ny, nx));
					copy_cnt--;
//					if(map_copy[ny][nx]==0) { //0일때만 길 개수를 줄인다.
//						copy_cnt--;
//					}
				}
				//MAX이 아닌데 현재 visit이 클때만 큐에 넣어줘야함 한번 방문한적이 있으므로 개수 줄이면 안된다.
				else if(visit[ny][nx]>visit[y][x]+1) {
					visit[ny][nx] =visit[y][x]+1;
//					map_copy[ny][nx]=2;
					queue.offer(new dot(ny, nx));
				
				}
			}
			
		}//queue
	}//spread
	
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}//dot
}
