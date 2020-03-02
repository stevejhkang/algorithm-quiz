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
 * @time 2020. 2. 15. 오후 1:50:21
 * @category BFS
* @problem_description 모든 토마토가 익을 때까지 최소 며칠이 걸리는 지 계산하는 문제
* 이미 모든 토마토가 익어있는 상태이면 0, 모두 익지 못하는 상황이면 -1
* @solving_description 빈공간이면 큐에 넣지말고, 이미 익은 토마토도 큐에 넣으면 안됨.
* 핵심은 최소 이동거리(날짜)를 구하기 위해서 visit에 담아둔다. 최소 날짜를 넣어두려면
* visit배열을 Integer.MAX_VALUE로 바꿔줘야 한다.
*/
public class BOJ_7576_2 {
	private static int m;
	private static int n;
	private static boolean[][] visit= new boolean[1001][1001];
	private static int raw_tom;
	static int dy[] = {1,0,-1,0}; //하 우 상 좌
	static int dx[] = {0,1,0,-1};
	static Queue<int[]> queue =new LinkedList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		m= Integer.parseInt(stringTokenizer.nextToken());
		n= Integer.parseInt(stringTokenizer.nextToken());
//		visit = new int[n][m];
		
		raw_tom= 0;
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine()," ");
			for(int j=0;j<m;j++) {
				int a =Integer.parseInt(stringTokenizer.nextToken());
				if(a==1) {
					queue.offer(new int[] {i,j,0});
					visit[i][j]=true;
				}
				//안 익은 토마토의 개수도 세준다.
				else if(a==0) {
					raw_tom++;
				}
				else {
					visit[i][j]=true;
				}
			}
		}//for i
		if(raw_tom==0) {
			System.out.println(0);
			return;
		}
		int max =0;
		while(!queue.isEmpty()) {
			
			int[] now = queue.poll();
			int y = now[0]; int x = now[1]; int d =now[2];
			
			//상			
			if(y-1>=0&&!visit[y-1][x]) {
				visit[y-1][x]=true;
				queue.add(new int[] {y-1,x,d+1});
				raw_tom--;
				if(raw_tom==0) {
					max=d+1;
					break;
				}
			}
			//하
			if(y+1<n&&!visit[y+1][x]) {
				visit[y+1][x]=true;
				queue.add(new int[] {y+1,x,d+1});
				raw_tom--;
				if(raw_tom==0) {
					max=d+1;
					break;
				}
			}
			//좌
			if(x-1>=0&&!visit[y][x-1]) {
				visit[y][x-1]=true;
				queue.add(new int[] {y,x-1,d+1});
				raw_tom--;
				if(raw_tom==0) {
					max=d+1;
					break;
				}
			}
			//우
			if(x+1<m&&!visit[y][x+1]) {
				visit[y][x+1]=true;
				queue.add(new int[] {y,x+1,d+1});
				raw_tom--;
				if(raw_tom==0) {
					max=d+1;
					break;
				}
			}
//			for(int i=0;i<4;i++) {
//				int ny = y+dy[i]; int nx = x+dx[i];
//				//범위 벗어나면 이미 방문했으면 
//				if(0>nx||nx>=m||0>ny||ny>=n) continue;
//				//0이면 익은 토마토
//				if(!visit[ny][nx]) {
//					visit[ny][nx]=true;
//					queue.add(new int[] {ny,nx,d+1});
//					raw_tom--;
//					if(raw_tom==0) max=d+1;
//				}
//			}
		}//while
		if(max==0) {
			System.out.println(-1);
		}
		else {
			System.out.println(max);
		}	
	}//main
}