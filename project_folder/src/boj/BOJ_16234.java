package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import boj.BOJ_16234.dot;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 25. 오후 4:40:31
 * @category 
* @problem_description 더이상 인구 이동이 없을때까지 인구이동이 지속된다.
* 국경선을 공유하는 두 나라의 인구차이가 L이상R이하라면 두나라간 국경선을 연다
* 이동을 시작한다. 국경선 열려있어 이동가능하면 오늘 하루 동안 연합이라고 한다.
* 연합을 이루고 있는 각 칸 인구수는 연합의 인구수/ 연합을 이루고 있는 칸의 개수가 된다.
* 소숫점 버린다
*  연합을 해체하고 국경선을 닫는다.
*  출력: 인구수가 주어졌을 때 인구 이동이 몇번 발생하는지 구하자
* @solving_description 
*/
public class BOJ_16234 {
	private static int n;
	private static int l;
	private static int r;
	private static int[][] map;
	private static int[][] copy_map;
	private static boolean[][] visit;
	private static ArrayList<dot> al;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	public static void main(String[] args) throws IOException {
		//DFS로 상하좌우 인접한 도시끼리 인구차이를 구하고 같으면 visit하고 전체 합을 계속 더해준다
		//그리고 더이상 연합이 없으면 인구수를 통일 합/갯수로 통일 시켜준다.
		//카피에 저장했다가 마지막에 복사를 시켜줄까?
		
		//더이상 이동이 일어나지 않을때까지
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		l = Integer.parseInt(stringTokenizer.nextToken());
		r = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n][n];
		
		//dfs로 차이가 l이상 r이하 차이이면 계속 들어가서 군집화시킨다.
//		copy_map= new int[n][n];
		int time=0;
		boolean check =false;
		while(true) {
			time++;
			copy_map = new int[n][n];
			visit = new boolean[n][n];
			int visit_time=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(!visit[i][j]) {
						visit_time++;

						bfs(i,j);

					}
				}
			}//for i
			System.out.println();
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					System.out.print(copy_map[i][j]+" ");
				}
				System.out.println();
			}
			for(int i=0;i<n;i++) {
				map[i] = copy_map[i].clone();
			}
			if(visit_time==n*n) { //하나하나 방문했으면 연합이 한번도 이뤄진적이 없는 것이므로
				//종료
				System.out.println(time-1);
				return;
			}
		}//while
	
	}//main
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static void bfs(int y, int x) {
		ArrayList<dot> union = new ArrayList<>();
		Queue<dot> queue = new LinkedList<dot>();
		queue.offer(new dot(y,x)); 
		union.add(new dot(y, x));
		//다 연결 될때까지 sum이랑 숫자 카운트하고 리스트에 넣는다.
		//큐 밖에서 리스트에 담긴 것들 전부 갱신시켜준다.
		int sum =0; int num=0;
		while(!queue.isEmpty()){
			dot temp = queue.poll();
			int temp_y= temp.y; int temp_x = temp.x;
			if(visit[y][x]) continue;
			visit[y][x] = true;
			sum+=map[y][x];
			num++;
			for(int k=0;k<4;k++) {
				int ny = temp_y+dy[k]; int nx = temp_x+dx[k];
				if(ny<0||ny>=n||nx<0||nx>=n) continue;
				if(!visit[ny][nx]&&Math.abs(map[ny][nx]-map[y][x])>=l&&
						Math.abs(map[ny][nx]-map[y][x])<=r) {
					queue.offer(new dot(ny, nx));	
				}
			}
		}//while
		for(int i=0;i<union.size();i++) {
			dot temp =union.get(i);
			System.out.println(sum/num);
			copy_map[temp.y][temp.x] = sum/num;
		}
	}//bfs
}
