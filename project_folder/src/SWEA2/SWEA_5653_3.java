package SWEA2;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 29. 오후 3:25:01
 * @category 우선순위 큐로 해결
* @problem_description 
* @solving_description 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653_3 {
	static class Cell implements Comparable<Cell>{
		int i,j,x; 
		int time;//활성화되는 시간(시점)

		public Cell(int i, int j, int x, int time) {
			super();
			this.i = i;
			this.j = j;
			this.x = x; //생명력
			this.time = time; //시간
		}

		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			if(time!=o.time) {
				return Integer.compare(time, o.time); //오름차순이면 자기꺼부터 -> 활성화되기까지 남은 시간이 짧은 순으로
			}
			else {
				return Integer.compare(x, o.x)*-1; //같으면 생명력이 큰거로
			}
		}
	}
	static int n,m,k;
	static int[][] map;
	static List<ArrayList<Cell>> list; //줄기세포 생명력 별 저장(인덱스0~9사용)
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder();
		int T = Integer.parseInt(bufferedReader.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			PriorityQueue<Cell> pq = new PriorityQueue<>();
			boolean[][] visit = new boolean[700][700];
			
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			k = Integer.parseInt(stringTokenizer.nextToken());
			
			int ans=0;
			for(int i=350;i<n+350;i++) { //배열의 중간부터 시작 (350,350)
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for(int j=350;j<m+350;j++) {
					int x = Integer.parseInt(stringTokenizer.nextToken()); //생명력
					if(x!=0) {
						visit[i][j] = true; //방문처리하고 
						pq.add(new Cell(i,j,x,x+1)); //번식이 시작되는 시간은 활성화 후 +1 시간때니까.
						if(x*2>k) { //비활성화 대기, 활성화 시간 합이 2x 이게 배양시간 K보다 크면 k시간때 살아남는 다는 것을 의미하므로.
							ans++;
						}
					}
				}
			}
			//처리
			int time = 0;
			while(time<=k) {
				Cell c = pq.poll();
				time = c.time;
				if(time>k) break;
				for(int d=0;d<4;d++) {
					int ni = c.i+dy[d];
					int nj = c.j+dx[d];
					if(!visit[ni][nj]) { //방문하지 않았으면
						visit[ni][nj]= true; //방문처리 해주고
						pq.add(new Cell(ni,nj,c.x,time+c.x+1)); // 현재 시간에다가 하나 더 더하면
						if(time+c.x*2>k) {  //기존 경과시간에다가 새롭게 넘겨받은 셀의 생명력*2가 설정된 배양시간k보다 크면 정답에 추가한다.
							ans++;
						}
					}
				}
			}
			//출력
			stringBuilder.append("#"+tc+" "+ans+"\n");
		}
		System.out.println(stringBuilder);
	}

	
}
