package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 1. 오후 12:08:45
 * @category DFS
* @level 3
* @problem_description 
* 가장 높은 봉우리에서 시작해서 딱 한 곳을 정해서 최대 K깊이 만큼 지형을 깎는 공사를
* 할 수 있다. 이때 만들 수 있는 가장 긴 등산로를 찾아 그 길이를 출력하는 프로그램을 작성하라.
*  
* @solving_description 
* 상하좌우를 DFS하면서 현재보다 큰 값이 나오면은 현재-그값(이게 K보다 작은지부터 판단)부터 k까지 깎은 후에 DFS를 한다.
* 만약 안 깎았으면 다음 번에 마주칠때 깎아준다. 
* 즉 아예 안깎고 스킵하는거랑 1번깎고 움직이는거 두개를 넣어줘야 하는데
* 깎는 부분에서 얼마를 깎을 지도 계산을 해서 깎아주어야 한다.
* 
*/

public class SWEA_1949_등산로조성 {
	private static int n;
	private static int k;
	private static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean[][] visit;
	private static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T =Integer.parseInt(bufferedReader.readLine());
		StringBuilder stringBuilder = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			k = Integer.parseInt(stringTokenizer.nextToken());
			
			map = new int[n][n];
			
			int max_num = 0;
			for(int i=0;i<n;i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for(int j=0;j<n;j++) {
					int num =  Integer.parseInt(stringTokenizer.nextToken());
					map[i][j] =num;
					if(max_num<num) {//최댓값을 찾는다.
						max_num=num;
					}
				}
			}//for i
			
			ArrayList<dot> list = new ArrayList<>();
			
			//최댓값과 같은 값들을 리스트에 넣어준다.
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(max_num==map[i][j]) {
						list.add(new dot(i,j));
					}
				}
			}//for
			max=0;
			visit = new boolean[n][n];
			for(int i=0;i<list.size();i++) {
				dfs(list.get(i).y, list.get(i).x,1,false);
			}
			stringBuilder.append("#"+tc+" "+max+"\n");
		}
		System.out.println(stringBuilder.toString());
	}//main
	
	static void dfs(int y,int x, int len, boolean cut) {
		if(max<len) max=len; //len값 갱신
		visit[y][x] = true; //방문처리 해주고
		
		for(int d=0;d<4;d++) { //4방향 탐색
			int ny = y+dy[d];
			int nx = x+dx[d];
			//범위 벗어나면
			if(ny<0||ny>=n||nx<0||nx>=n) continue;
			
			if(!visit[ny][nx]&&map[y][x]>map[ny][nx]) { //방문하지 않았고 다음 것이 작으면 그냥 넣는다.
				dfs(ny,nx,len+1,cut);
			}
			else if(!visit[ny][nx]&&map[y][x]<=map[ny][nx]) { //방문하지 않았고 다음 것이 크거나 같으면 깎는다.
				if(!cut&&(map[ny][nx]-map[y][x])<k) { //만약 깎은 적이 없고 깎을 수 있는 높이 차이면 84번
					int num = map[ny][nx];
					map[ny][nx]-=(num-map[y][x]+1);
					//아
					dfs(ny, nx, len+1, true);
					map[ny][nx]=num;
//					for(int i=map[ny][nx]-map[y][x];i<k;i++) {
//						map[ny][nx]-=(i+1); //해당 맵을 깎고
//						dfs(ny,nx,len+1,true); //길이를 늘리고 깎음 처리해준다.
//						map[ny][nx]+=(i+1);
//					}
				}
				//아니면 이미 깎았으면 넘어가지 못함.
			}
		}
		
		visit[y][x] = false; //방문 처리 취소해준다.
	}
	
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			dot other = (dot) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
		
	}
}
