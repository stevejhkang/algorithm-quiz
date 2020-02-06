package advance;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Backtracking
 * DFS 시뮬레이션 문제
 * 가지치기 안하면 시간터짐
 */

public class JG_1113_teacher {
	private static int N,n;
	private static int M,m;
	private static int map[][];
	private static int[][] visited;
	public static final int UP= 1;
	public static final int DOWN= 2;
	public static final int LEFT= 3;
	public static final int RIGHT= 4;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine()," ");
		M = Integer.valueOf(stringTokenizer.nextToken());
		N=Integer.parseInt(stringTokenizer.nextToken());
		
		 stringTokenizer = new StringTokenizer(br.readLine()," ");
		m=Integer.parseInt(stringTokenizer.nextToken());
		n=Integer.parseInt(stringTokenizer.nextToken());
		
		map= new int[M][N];
		for(int i=0;i<M;i++) {
			String str = br.readLine();
			for(int j=0, index =0;j<N;j++,index+=2) {
				map[i][j]= str.charAt(index)-'0'; 
			}
		}
		bfs();
		System.out.println(visited[m][n]);
	}
	public static void bfs() {
		Queue<Local> queue = new LinkedList<>();
		//무엇을 저장해야할 것인가? 좌표 y,x 방향, 턴한 횟수
		visited = new int [M][N];//턴의 개수로 방문여부를 체크
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				visited[i][j]=Integer.MAX_VALUE;
			}
		}
		visited[0][0]=0; //시작 정점 지정
		queue.offer(new Local(0, 0, 0, DOWN));
		queue.offer(new Local(0, 0, 0, RIGHT));
		while(!queue.isEmpty()) {
			Local local = queue.poll();
			int r = local.r; int c = local.c; //간접 참조보다 직접 참조가 낫다.
			int dir = local.dir; int turn=local.dir;
			
			int tempturn; //턴의 횟수가 증가하는지 여부를 체크해서 기록
			//상
			tempturn = turn+(dir==UP? 0:1);
			if(0<=r-1&&visited[r-1][c]>=tempturn &&map[r-1][c]==1) { //영역 범위 체크, 이미 저장된 턴의 횟수보다 작을때만 진입
				visited[r-1][c]=tempturn;
				queue.offer(new Local(r-1, c, tempturn, UP));
			}
			//하
			tempturn = turn+(dir==DOWN? 0:1);
			if(r+1<M&&visited[r+1][c]>=tempturn &&map[r+1][c]==1) { //영역 범위 체크, 이미 저장된 턴의 횟수보다 작을때만 진입
				visited[r+1][c]=tempturn;
				queue.offer(new Local(r+1, c, tempturn, DOWN));
			}
			//좌
			tempturn = turn+(dir==LEFT? 0:1);
			if(0<=c-1&&visited[r][c-1]>=tempturn &&map[r][c-1]==1) { //영역 범위 체크, 이미 저장된 턴의 횟수보다 작을때만 진입
				visited[r][c-1]=tempturn;
				queue.offer(new Local(r, c-1, tempturn, LEFT));
			}
			//우
			tempturn = turn+(dir==RIGHT? 0:1);
			if(c+1<N&&visited[r][c+1]>=tempturn &&map[r][c+1]==1) { //영역 범위 체크, 이미 저장된 턴의 횟수보다 작을때만 진입
				visited[r][c+1]=tempturn;
				queue.offer(new Local(r, c+1, tempturn, DOWN));
			}
						
		}//end of while
	}//end of bfs
	public static class Local{
		int r; int c; int turn; int dir;

		public Local(int r, int c, int turn, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.turn = turn;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "local [r=" + r + ", c=" + c + ", turn=" + turn + ", dir=" + dir + "]";
		}
		
	}
}
