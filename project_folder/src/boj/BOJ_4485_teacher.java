package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 최단거리 알고리즘
 * Dijkstra 다익스트라: 특정 1개의 정점 ~ 나머지 모든 정점까지의 최단거리를 구하는 알고리즘(가중치 양수)
 * Bellman-ford 벨만포드: 특정 1개의 정점~ 나머지 모든 정점까지의 최단거리를 구하는 알고리즘(가중치 음수가능)
 * Floyd-Warshall 플로이드 워샬: 모든 정점들 간 최단경로
 * 
 * 이 문제는 다익스트라에 해당됨, 하지만 인접행렬은 메모리가 부족해서 사용불가
 * DFS는 가지치기가 많이 안되서 시간 터밎
 * BFS에 다익스트라 아이디어를 넣어서 구해보자
 * 
 */
public class BOJ_4485_teacher {
	private static int N;
	private static int[][] m;
	//memo[i][j]: [0][0]~[i][j]까지의 최단거리
	private static int[][] memo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		for(int tc =1;;tc++) { //가운데 비면 무한반복
			N=Integer.parseInt(bReader.readLine()); //동굴크기 2~125
			if(N==0)break;
			m=new int[N][N];
			memo= new int[N][N]; 
//			for(int i=0;i<N;i++) {
//				String string = bReader.readLine();
//				for(int j=0,index=0;j<N;j++,index+=2) {
//					m[i][j]=string.charAt(index)-'0';
//				}
//			}
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(bReader.readLine()," ");
				for(int j=0;j<N;j++) {
					m[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					memo[i][j]=Integer.MAX_VALUE;
				}
			}
//			Queue<int[]> queue = new LinkedList<int[]>();
			//우선순위 큐로 바꿔보기(나중에) 140ms로  줄 수 있다.
			PriorityQueue<int[]> queue =new PriorityQueue<>(new Comparator<int[]>() {
				//외부비교기가 내부비교기보다 먼저
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[2] -o2[2]; //뺏긴 돈이 적은 친구부터 앞으로
				}
			});
			
			//시작정점 지정
			memo[0][0]=m[0][0];
			queue.offer(new int[] {0,0,memo[0][0]});//r행, c열, 경로의 최솟값(어떻게 가지치기할지) (0,0)~(r,c)
			while(!queue.isEmpty()) {//큐가 빌때까지
				//큐에서 꺼냄
				int[] data=queue.poll();
				int r = data[0];
				int c = data[1];
				int cost = data[2]; //큐에 삽입 당시의 경로의 최솟값
				
				if(memo[r][c]<cost) continue;//이미 더 작은 값이 된 경우 가지치기 200ms준다.
				
				//비용이 개선되었을 때, 가지치기
				for(int i=0;i<dr.length;i++) {
					int nr = r+dr[i]; int nc = c+dc[i];
					//continue보다 그냥 범위, 조건 한꺼번에 체크하는게 편할듯!
					if(0<=nr&&nr<N&&0<=nc&&nc<N //비용이 더 적게 들면 업데이트해라
							&&memo[nr][nc]>memo[r][c]+m[nr][nc]) {
						memo[nr][nc]=memo[r][c]+m[nr][nc];
						queue.offer(new int[] 
								{nr,nc,memo[nr][nc]});
					}
				}
				
				//큐에서 꺼낸 정점의 모든 인접한 정점을 다시 큐에 넣는다.
			}
			System.out.println("Problem "+tc+": "+memo[N-1][N-1]);
		}//for tc
	}//main
	static int[] dr= {-1,1,0,0};//상하좌우
	static int[] dc= {0,0,-1,1};//상하좌우
}
