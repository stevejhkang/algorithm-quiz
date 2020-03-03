package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 3. 오전 10:39:44
 * @category 
* @problem_description
* 1행1열 알파벳 섬의 명물, 같은 알파벳은 같은 명물  명물 볼때마다 요금 지금 최대한 명물을 많이보되 
* 요금을 지급하지 않는 방법, 같은 ㅇ라파벳 명물을 두번이상 보지 ㅇ낳도록 여행을 떠난 방법 중에 볼 수 있는
* 명물의 최대 개수
* @solving_description 한번 입장했을때는 같은 알파벳끼리 이동이 자유롭다. 그러나 같은 알파벳을 벗어났을때는
* 다른 알파벳으로 갔다가 다시 한번 방문했던 알파벳을 또 방문할 수 는 없다. DFS로 풀면 좋을듯?
* 그리고 방문여부를 26개의 배열에 저장해 놔야될듯!
*/

public class SWEA_7699 {
	private static int R;
	private static int C;
	private static int[][] map;
	private static boolean[] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int max;
	private static boolean[][] visit2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bufferedReader.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			R = Integer.parseInt(stringTokenizer.nextToken());
			C = Integer.parseInt(stringTokenizer.nextToken());
			
			map = new int[R][C];
			visit = new boolean [26];
			visit2 = new boolean[R][C];
			
			for(int i=0;i<R;i++) {
				String s = bufferedReader.readLine();
				for(int j=0;j<C;j++) {
					map[i][j] = s.substring(j,j+1).charAt(0) - 'A'; //알파벳에 대응하는 숫자를 넣는다.
				}
			}
			
//			for(int i=0;i<R;i++) {
//				System.out.print(Arrays.toString(map[i]));
//			}
			max = Integer.MIN_VALUE;
			//DFS로 넣어준다.
			visit[map[0][0]] =true;
			visit2[0][0] = true;
			
			dfs(0,0,1);
			
			System.out.println("#"+tc+" "+max);
		}//for tc
	}//main
	static void dfs(int y,int x, int cnt) {
		boolean check = false;
		for(int i=0;i<4;i++) {
			int ny = y+dy[i]; int nx = x+dx[i];
			//범위
			if(ny<0||ny>=R||nx<0||nx>=C) continue;
			//방문하지 않았으면 방문
			if(!visit[map[ny][nx]]&&!visit2[ny][nx]) { //다음 곳이 방문하지 않았던 종류이면 그냥 방문
				check = true;
				visit[map[ny][nx]]= true;//방문처리 해주시고
				visit2[ny][nx] = true;
				dfs(ny, nx, cnt+1);
				visit[map[ny][nx]]=false;
				visit2[ny][nx] = false;
			}
//			//같은 명물이면 그냥 방문 카운트 해주지않는다.
//			else if(map[ny][nx]==map[y][x]&&!visit2[ny][nx]) {
//				check= true;
//				visit2[ny][nx] = true;
//				dfs(ny,nx,cnt);
//				visit2[ny][nx] = false;
//			}
		}
		//basecase 전부 탐색했는데 아무것도 더이상 갈데가 없을때 최댓값 검색한다.
		if(!check) {
			max= Math.max(max, cnt);
			return;
		}
	}
	static class dot{
		int y, x;
		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}//dot
}
