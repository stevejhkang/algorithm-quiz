package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ_3109 {
	private static int r;
	private static int c;
	private static char[][] map;
	static int dy[]= {-1,0,1};
	private static int[][] visit;
	static List paths[];
	private static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sTokenizer = new StringTokenizer(bufferedReader.readLine());
		r = Integer.parseInt(sTokenizer.nextToken());
		c = Integer.parseInt(sTokenizer.nextToken());
		
		map= new char[r][c];
		for(int i=0;i<r;i++) {
			String string = bufferedReader.readLine();
			for(int j=0;j<c;j++) {
				map[i][j]=string.charAt(j);
			}
		}
		visit=new int[r][c];
		paths = new List[r];
		for(int i=0;i<r;i++) {
			paths[i]=new ArrayList<dot>();
		}
		cnt=0;
		for(int i=0;i<r;i++) {
			//0, r-1까지 맨 왼쪽에서 시작한다.
//			paths[i].add(new dot(i, 0));
			dfs(i, i,0);
//			paths[i].remove(0);
		}//for i
		if(cnt==0) {
			System.out.println(-1);
		}
		else {
			System.out.println(cnt);
		}
		
	}
	static boolean dfs( int start_row,int y, int x) {
		/*if(x==c-1) {
			//cnt++해주고 길은 전부 visit처리 해준다.
			cnt++;
			//이미 간 길들을 visit처리해준다.
//			for(int i=0;i<paths[start_row].size();i++) {
//				dot path = (dot) paths[start_row].get(i);
//				visit[path.y][path.x]=1;
//			}
//			for(int i=0;i<r;i++) {
//				for(int j=0;j<c;j++) {
//					System.out.print(visit[i][j]+" ");
//				}
//				System.out.println("");
//			}
			return true;
		}*/
		if(x==c-1) {
			cnt++;
//			for(int i=0;i<r;i++) {
//				for(int j=0;j<c;j++) {
//					System.out.print(visit[i][j]+" ");
//				}
//				System.out.println("");
//			}
//			System.out.println("");
			return true;
		}
		//dfs시작 세방향으로 탐색을 하는데 한 방법이 성공하면 나머지는 break시켜준다.
		for(int k=0;k<3;k++) {
			int ny = y+dy[k];
			int nx =x+1;
			//현재 visit이 1이면 이 점으로 이미 길이 생성된 것이므로 아예 종료
			//if(visit[y][x]==1) break; 
			//다음이 범위 벗어나면 다음으로
			if(ny<0||ny>=r||nx>=c) continue;
			//다음이 벽이면 넘어감
			if(map[ny][nx]=='x') continue;
			//다음을 이미 방문했으면
			
			if(visit[ny][nx]==1) continue;
			visit[ny][nx]=1;
			
			if(dfs(start_row, ny, nx)) {
				return true;
			}
			
		}
		return false;
		
	}
	static class dot{
		int y; int x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "dot [y=" + y + ", x=" + x + "]";
		}
		
	}
}
