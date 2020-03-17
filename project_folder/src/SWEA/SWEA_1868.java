package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 16. 오후 12:45:56
 * @category 
* @problem_description 표의 각 칸을 클릭했을때 지뢰가 있으면 "파핑 파핑!"이라는 소리와 함께
* 게임은 끝난다. 없으면 주변 8개 사이의 숫자로 클릭한 칸에 표시된다. 
* 만약 이 숫자가 0이라면 근처 8방향에 지뢰가 없다는 것이 확정되고
* 그 8방향의 칸도 자동으로 숫자를 표시해준다.
* 
* 지뢰가 있는 칸을 제외한 다른 모든 칸의 숫자들이 표시되려면 최소 몇 번의 클릭을 해야 하는지
* 구하는 프로그램 작성
* @solving_description 
* 테케 T 정수 N<=300 N*N N길이의 문자열 *이 지뢰가 있다는 뜻
* 모든 점에서 시작을 시켜놓고 거기까지 도달하는게 
* 일단 8방향 중 한 곳에 별이 있으면 거기에서 시작하면 많이 클릭해야하므로 
* 
* 일단 8방향이 모두 지뢰가 없는 것을 큐에 넣어서 최대한 연쇄적으로 표시를 해준 후 
* visit하지 않은 남은 빈칸은 일일이 더해줘야 되므로
* 그 빈칸의 개수을 더해주면 답이 나오겠다.
*/

public class SWEA_1868 {
	private static int n;
	private static char[][] map;
	static int[] dy = { -1,-1,0,1,1,1,0,-1 }; //상 상우 우 우하 하 좌하 좌 좌상
	static int[] dx = { 0,1,1,1,0,-1,-1,-1 };
	private static int cnt;
	private static boolean[][] visit;
	private static int empty_cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc =1;tc<=T;tc++) {
			n = Integer.parseInt(bufferedReader.readLine());
			map = new char[n][n];
			
			empty_cnt=0;
			for(int i=0;i<n;i++) {
				String s = bufferedReader.readLine();
				for(int j=0;j<n;j++) {
					char c = s.charAt(j);
					map[i][j] = c;
					if(c=='.') {
						empty_cnt++;
					}
				}
			}
			
			cnt =0;
			visit = new boolean[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					//8방향이 모두 .인 곳을 큐에 넣어준다.
					if(map[i][j]=='.') {
						int check =0;
						for(int k=0;k<8;k++) {
							int ny = i+dy[k]; int nx = j+dx[k];
							//범위
							if(ny<0||ny>=n||nx<0||nx>=n) {
								check++;
								continue;
							}
							//방문했어도 상관없을 듯
							if(map[ny][nx]=='.') {
								check++;
							}
						}
						//8방향 모두 .이면
						if(check==8&&!visit[i][j]) {
							//방문한 적 없으면 큐에 넣고 방문처리 해준다.
							cnt++; //클릭횟수 더해주고
							bfs(i, j);
						}
					}
				}
			}//for i
			//8방향 모두하고 나면 연쇄 반응은 이제 없으므로 남은것 만 출력한다.
			//남은 빈공간은 empty_cnt이고 그것을 일일이 클릭해야하므로 
			//그만큼 cnt에 추가해주면된다.
			
			cnt+=empty_cnt;
			sb.append("#"+tc+" "+cnt+"\n");
		}//tc
		System.out.println(sb);
	}//main
	static void bfs(int startY, int startX) {
		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(startY, startX));
		while(!queue.isEmpty()) {
			dot now = queue.poll();
			int y = now.y; int x =now.x;
			if(visit[y][x]) continue;
			visit[y][x] = true;
			empty_cnt--; //빈 공간 개수를 줄여준다.
			
			int cnt_bomb=0;
			int empty=0;
			for(int i=0;i<8;i++) {
				int ny = y+dy[i]; int nx = x+dx[i];
				//범위
				if(ny<0||ny>=n||nx<0||nx>=n) {
					empty++;
					continue;
				}
				//폭탄 개수 체크
				if(map[ny][nx]=='*') { //폭탄이면
					cnt_bomb++;
					continue;
				}
				else if(map[ny][nx]=='.') { //일반이면
					empty++;
				}
			}
			//8방향이 모두 일반이면 방문 안한 것만 모두 큐에 넣는다. 
			if(empty==8) {
				for(int i=0;i<8;i++) {
					int ny = y+dy[i]; int nx = x+dx[i];
					if(ny<0||ny>=n||nx<0||nx>=n)  continue;
					if(!visit[ny][nx])
						queue.offer(new dot(ny, nx));
				}
			}
			//아니면 폭탄개수를 저장 할? 필요 없음 visit처리만 해주면 되니까
		}
	}
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}

//..*..
//..*..
//.*..*
//.*...
//.*...