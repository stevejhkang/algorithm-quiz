import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algo_서울_14반_강준후 {
	private static boolean[][] map;
	private static boolean[][] visit;
	static int[] dy = {-1,-1,0,1,1,1,0,-1 };
	static int[] dx = { 0,1,1,1,0,-1,-1,-1 };
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bufferedReader.readLine());
		StringBuilder stringBuilder = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			map = new boolean[10][10];
			for(int i=0;i<10;i++) {
				StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
				for(int j=0;j<10;j++) {
					int a = Integer.parseInt(stringTokenizer.nextToken());
					if(a==1)
						map[i][j]=true;
				}
			}
			visit = new boolean[10][10];
			int cnt=0;
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(!visit[i][j]&&map[i][j]) { //방문하지 않았고 1이면은 탐색한다.
						cnt++;
						bfs(i,j);
					}
				}
			}//for i
			stringBuilder.append("#"+tc+" "+cnt+"\n");
		}
		System.out.println(stringBuilder);
		
		
	}//main
	static void bfs(int i, int j) {
		
		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(i, j));
		while(!queue.isEmpty()) {
			dot now = queue.poll();
			int y = now.y; int x = now.x;
			
			if(visit[y][x]) continue;
			visit[y][x] = true;
			
			for(int k=0;k<8;k++) {
				int ny = y+dy[k]; int nx = x+dx[k];
				//범위
				if(ny<0||ny>=10||nx<0||nx>=10) continue;
				
				if(!visit[ny][nx]&&map[ny][nx]) {
					queue.offer(new dot(ny, nx));
				}
			}
		}
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
