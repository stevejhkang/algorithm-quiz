package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 1. 오후 5:01:22
 * @category 
* @problem_description  뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 
* 아래로 떨어진다. 뿌요를 놓고 난 후, 같은 색 뿌요가 4개이상 상하좌우로 연결이 되어 있으면 
* 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면
* 역시 중력의 영향을 받아 차례대로 아래로 떨어진다. 아래로 떨어지고 나서 다시 같은 색의 뿌요들이
* 4개이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다
* 1연쇄씩 늘어난다.  터질 수 있는 뿌여가 여러 그룹이 있다면 동시에 터져야하고 여러 그룹이 
* 터지더라도 한번의 연쇄가 추가된다.
* 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하라
* @solving_description 12*6 RGNPY 하나도 터지지 않는다면 0을 출력하라. 
* 한방향이 아니라  인접 4개이므로 그냥 BFS/DFS 둘다 상관없음 터졋을때 위에 뭔가가 있으면 
* 다 아래로 빼주면 된다. 그런데 매번 모든 점을 방문해주어야 하나? 그래야할듯
* 그냥 첫 시작점만 체크해주자 
* 포인트는 DFS/BFS로 4개이상인 것을 체크해주고 어레이리스트에 좌표를 넣어준다음 
* 그게 4개 이상이면 .로 초기화 시켜주고 해당 좌표의 컬럼을 0부터 해당 높이만큼 
* 하나씩 아래로 내려주는게 포인트이다.
*/
public class BOJ_11559 {
	private static char[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean cnt;
	private static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[13][6];
		int first_row=0;
		for(int j=0;j<6;j++) {
			map[0][j] ='.';
		}
		for(int i=1;i<=12;i++) {
			String s= bufferedReader.readLine();
			for(int j=0;j<6;j++) {
				char c = s.charAt(j);
				map[i][j] = c;
				if(c!='.') {
					first_row=i;
				}
			}
		}
		
		int time=0;
		while(true) {
			visit = new boolean[13][6]; //방문 여부 체크
			cnt=false; //한 그룹이라도 지웠는지를 체크하는 배열
			for(int i=1;i<=12;i++) {
				for(int j=0;j<6;j++) {
					if(map[i][j]!='.'&&!visit[i][j]) {//방문하지 않았으면
						//큐에 넣어서 4개 이상 있는지 확인한다. 
						Queue<dot> queue = new LinkedList<dot>();
						queue.offer(new dot(i, j));
						//해당 좌표를 지우기 위해 저장하는 어레이 리스트
						ArrayList<dot> al = new ArrayList<>();
						char color = map[i][j];
						//4개 이상인지 체크하는 int
						int is_four=0;
						//BFS를 돌면서 4개 이상인지 확인하기
						while(!queue.isEmpty()) {
							dot now = queue.poll();
							int y = now.y; int x = now.x;
							
							if(visit[y][x]) continue;
							visit[y][x] =true;
							is_four++;
							al.add(new dot(y, x));
							
							for(int k=0;k<4;k++) {
								int ny = y+dy[k]; int nx = x+dx[k];
								//범위
								if(ny<=0||ny>12||nx<0||nx>=6) continue;
								//방문하지않았고 색이 같으면
								if(!visit[ny][nx]&&map[ny][nx]==color) {
									queue.offer(new dot(ny, nx));
								}
							}
						}
						//4개이상이면 없애고 그 위에 있는 것을 내려줘야 한다. 
						if(is_four>=4) {
							cnt=true; //한번이라도 지웠으면 true
							for(int i1=0;i1<al.size();i1++) {
								dot now = al.get(i1);
								int y = now.y; int x =now.x;
								map[y][x] = '.';
							}// .으로 만들어주고
							//해당 좌표를 쭉 내려준다.
							for(int i1=0;i1<al.size();i1++) {
								dot now = al.get(i1);
								int y  =now.y; int x =now.x;
								down(y,x);
							}
						}
					}
				} //for j
			}//for i
			if(cnt) {//다시 while문
				time++;
//				for(int i=1;i<=12;i++) {
//					System.out.println(Arrays.toString(map[i]));
//				}
			}
			else {
				break;
			}
		}//while
		System.out.println(time);
		
	}//main
	static void down(int y,int x) {
		//해당 높이에서부터 한칸씩 내려준다.
		for(int i=y;i>=1;i--) {
			map[i][x] = map[i-1][x];
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
