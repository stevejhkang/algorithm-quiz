package jungol;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 15. 오후 4:59:23
 * @category DFS+시뮬레이션 문제
* @problem_description
* @solving_description  다음 탐색할 곳에 저장된 턴 수보다 현재 탐색하고 
* 있는 것이 더 작으면 들어가서 갱신해야 한다.
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
		M = Integer.valueOf(stringTokenizer.nextToken()); //세로
		N=Integer.parseInt(stringTokenizer.nextToken()); //가로
		
		stringTokenizer = new StringTokenizer(br.readLine()," ");
		m=Integer.parseInt(stringTokenizer.nextToken()); //목표점 세로
		n=Integer.parseInt(stringTokenizer.nextToken()); //목표점 가로
		
		map= new int[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0, index =0;j<M;j++,index+=2) { //띄어쓰기를 무시하기위해 index를 2씩 증가해준다.
				map[i][j]= str.charAt(index)-'0'; 
			}
		}
		System.out.println("");
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) { //띄어쓰기를 무시하기위해 index를 2씩 증가해준다.
				System.out.print(map[i][j]+" ");
			}
			System.out.println("");
		}
		//그렇게해서 i,j까지 도달하는데 최소한의 턴을 BFS통해 갱신하고
		bfs();
		//visit[n][m]에 저장된 최소를 출력한다.
		System.out.println(visited[n][m]);
	}
	public static void bfs() {
		Queue<Local> queue = new LinkedList<>();
		//무엇을 저장해야할 것인가? 좌표 y,x 방향, 턴한 횟수
		
		//해당 좌표에서 턴한 횟수의 최솟값을 저장한다.
		visited = new int [N][M];//턴의 개수로 방문여부를 체크
		//최솟값을 저장해야하므로 max값으로 초기화
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				visited[i][j]=Integer.MAX_VALUE;
			}
		}
		
		visited[0][0]=0; //시작 정점은 0으로 지정
		//오른쪽과 아래로만 시작할 수 있으므로
		queue.offer(new Local(0, 0, 0, DOWN));
		queue.offer(new Local(0, 0, 0, RIGHT));
		while(!queue.isEmpty()) {
			Local local = queue.poll();
			int r = local.r; int c = local.c; //간접 참조보다 직접 참조가 낫다.
			int dir = local.dir; int turn=local.dir;
			
			int tempturn; //턴의 횟수가 증가하는지 여부를 체크해서 기록
			//다음 탐색할 곳에 저장된 턴 수보다 현재 탐색하고 있는 것이 더 작으면 들어가서 갱신해야 한다.
			//상
			tempturn = turn+(dir==UP? 0:1);
			if(0<=r-1&&visited[r-1][c]>=tempturn &&map[r-1][c]==1) { //영역 범위 체크, 이미 저장된 턴의 횟수보다 작을때만 진입
				visited[r-1][c]=tempturn;
				queue.offer(new Local(r-1, c, tempturn, UP));
			}
			//하
			tempturn = turn+(dir==DOWN? 0:1);
			if(r+1<N&&visited[r+1][c]>=tempturn &&map[r+1][c]==1) { //영역 범위 체크, 이미 저장된 턴의 횟수보다 작을때만 진입
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
			if(c+1<M&&visited[r][c+1]>=tempturn &&map[r][c+1]==1) { //영역 범위 체크, 이미 저장된 턴의 횟수보다 작을때만 진입
				visited[r][c+1]=tempturn;
				queue.offer(new Local(r, c+1, tempturn, RIGHT));
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
