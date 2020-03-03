package boj_march;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 2. 오후 6:31:51
 * @category 
* @problem_description . #울타리 o양 v늑대 영역 안의 양의 수가 늗개의 수보다 많다면 이기게 된다.
* 그렇기 않다면 늑대가 그 지역 안의 모든 양을 먹는다.
* 아침이 도달했을 때 살아남은 양과 늑대의 수를 출력하는 프로그램
* @solving_description for문을 돌면서 처음 .이 나오면 DFS를 돌면서 양의 개수와 늑대의 개수를 세준다
* 만약 양이 이기면 늑대의 개수를 빼주고 늑대가 이기면 양의 개수를 빼줘서 최종적으로 
* 남은 양과 늑대 마리수를 출력한다.
*/

public class BOJ_3184 {
	private static int R;
	private static int C;
	private static char[][] map;
	private static int sheep;
	private static int wolf;
	private static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new char [R][C];
		
		sheep = 0;
		wolf = 0;
		
		for(int i=0;i<R;i++) {
			String s = bufferedReader.readLine();
			for(int j=0;j<C;j++) {
				char c = s.charAt(j);
				map[i][j] = c;
				
				if(c=='v') {
					wolf++;
				}
				else if(c=='o') {
					sheep++;
				}
			}// for
		} //for R
		
		visit = new boolean[R][C];
		//돌면서 다 덩어리를 확인한다.
		for(int i=0;i<R;i++) {
			for(int j =0;j<C;j++) {
				if(!visit[i][j]&&map[i][j]!='#') {
//					System.out.println("i,j: "+i+","+j);
					dfs(i,j);
				}
			}
		}//for i
		System.out.println(sheep+" "+wolf);
	}//main
	static void dfs(int i,int j) {
		int sheep_onearea=0;
		int wolf_onearea=0;
		Stack<dot> stack = new Stack<>();
		stack.push(new dot(i, j));
		
		while(!stack.isEmpty()) { 
			dot now = stack.pop();
			int y = now.y; int x = now.x;
			
			if(visit[y][x]) continue;
			visit[y][x] = true;
			
			if(map[y][x]=='v') {
				wolf_onearea++;
			}
			else if(map[y][x]=='o') {
				sheep_onearea++;
			}
			
			for(int k=0;k<4;k++) {
				int ny = y+dy[k]; int nx = x+dx[k];
				//범위 벽이거나
				if(ny<0||ny>=R||nx<0||nx>=C||map[ny][nx]=='#') continue;
				//방문한적없으면 이동
				if(!visit[ny][nx]) {
					stack.push(new dot(ny, nx));
				}
			}
		}//while
		if(sheep_onearea>wolf_onearea) { //전체에서 빼준다.
			wolf-=wolf_onearea;
		}
		else {
			sheep-=sheep_onearea;
		}
		//여기서 비교
	}
	static class dot{
		int y, x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
