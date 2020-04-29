package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653_2 {
	static int n,m,k;
	static int[][] map;
	static List<ArrayList<Cell>> list; //줄기세포 생명력 별 저장(인덱스0~9사용)
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			k = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[n+k][m+k];
			for(int i=k/2;i<n+(k/2);i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for(int j=k/2;j<m+k/2;j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
					if(map[i][j]!=0) {
						int idx = map[i][j]-1; //리스트 작업시 0~9 까지
						list.get(idx).add(new Cell(i,j,map[i][j],map[i][j],k,0));
					}
				}
			}
		}
		//처리
		bfs();
		//출력
		
	}
	static class Cell{
		int i,j,x;
		int life; //활성화까지 시간 -> 살아있는 시간(시간이 지나면서 감소됨)
		int time; //배양시간(시간이 지나면서 감소됨)
		int flag; //
		public Cell(int i, int j, int x, int life, int time, int flag) {
			super();
			this.i = i;
			this.j = j;
			this.x = x;
			this.life = life;
			this.time = time;
			this.flag = flag;
		}
		
	}
	static void bfs() {
		Queue<Cell> q = new LinkedList<>();
		//생명력이 큰 순서로 번식한다.
		for(int x=9;x>=0;x--) { //생명력이 큰 순서로 번식
			for(int s=0;s<list.get(x).size();s++) {
				q.offer(list.get(x).get(s));
			}
		}
		while(!q.isEmpty()) {
			Cell c =q.poll();
			if(c.life==0&&c.flag==1) { //활성화된 상태이고 활성화된 기간이 다 종료 되었음
				map[c.i][c.j]=-1; //life==0 && c.flag==1이면 종료
				continue;
			}
			if(c.time==0)continue; //배양시간이 지나면 넘어감.
			if(c.life!=0) { //life!=0이면 비활성화, 활성화에 관계 없이 계속 진행
				q.offer(new Cell(c.i, c.j, c.x, c.life-1, c.time-1, c.flag));
				continue;
			}
			//c.life==0 이고 flag==0인 경로 최초에 활성화된 상태 -> 상하좌우로 번식
			q.offer(new Cell(c.i, c.j, c.x, c.x, c.time, 1));
			for(int d=0;d<4;d++) {
				int ni= c.i+dy[d];
				int nj = c.j+dx[d];
				if(0<=ni&&ni<n+k&&0<=nj&&nj<m+k&&map[ni][nj]==0) {
					map[ni][nj]=c.x;
					
				}
			}
		}
	}
}
