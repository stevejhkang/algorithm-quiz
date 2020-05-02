package SWEA2;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 1. 오전 10:49:47
 * @category 시뮬레이션
* @level 3
* @problem_description 
* @solving_description 오른쪽으로 밀려면 
* 
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6109_추억의2048게임 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int N;
	private static int[][] map;
	private static boolean[][] visit; //이미 합쳐진 이력이 있는지를 확인하는 배열
	static String s ;
	
	public static void move(int i, int j, int d) { //오른쪽
		int ni = i+dy[d];
		int nj = j+dx[d];
		if(0<=ni&&ni<N&&0<=nj&&nj<N && !visit[ni][nj]) {
			if(map[i][j]!=0 && map[i][j]==map[ni][nj]&&!visit[i][j]) {
				//현재가 0이 아니고 값이 값고 현재 것을 방문을 안 했다 치면
				map[ni][nj] = map[i][j]*2; //오른쪽 것을 두배로 만들어주고
				map[i][j]=0; //이동시킨 값을 0으로 만들고
				visit[ni][nj] = true; 
			}
			else if(map[ni][nj]==0) { //해당 값이 0이면 그냥 이동시킨다.
				map[ni][nj] = map[i][j];
				map[i][j]=0;
			}
			move(ni,nj,d); //재귀를 통해서 계속 한 방향으로 이동시킨다.
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder();
		int T = Integer.parseInt(bufferedReader.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine()," ");
			N= Integer.parseInt(stringTokenizer.nextToken());
			s = stringTokenizer.nextToken();
			map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			visit = new boolean[N][N];
			switch(s) {
			case "up":
				for(int j=0;j<N;j++) { //y축
					for(int i=1;i<N;i++) {
						move(i,j,0);
					}
				}
				break;
			case "down":
				for(int j=0;j<N;j++) {
					for(int i=N-2;i>=0;i--) {
						move(i, j, 1);
					}
				}
				break;
			case "left":
				for(int i=0;i<N;i++) { //y축
					for(int j=1;j<N;j++) {
						move(i,j,2);
					}
				}
				break;
			case "right":
				for(int i=0;i<N;i++) { //y축
					for(int j=N-2;j>=0;j--) { //오른쪽 제일 끝에서 부터 오른쪽으로 옮겨준다.
						//그렇게해서 0에서 n까지 이동시켜 본다.
						move(i,j,3);
					}
				}
				break;
			}
			stringBuilder.append("#"+tc+"\n");
			for(int[] ma: map) {
				for(int m: ma) {
					stringBuilder.append(m+" ");
				}
				stringBuilder.append("\n");
			}
		}
		System.out.println(stringBuilder.toString());
		bufferedReader.close();
	}
}
