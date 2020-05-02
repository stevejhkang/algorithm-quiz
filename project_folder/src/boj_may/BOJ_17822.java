package boj_may;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 2. 오후 5:57:49
 * @category 
* @level 3
* @problem_description 
* @solving_description 
* 계속해서 상태가 변하므로 아예 바뀌기 전 배열 상태를 따로 저장해서 활용하는 방법을 이용해야한다.
* 그리고 처리를 해주고 마지막에 바뀐 데이터를 갱신시켜 주어야 한다.
* 평균을 구하려면 sum의 데이터 타입이 실수(double)이여야 한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17822 {
	private static int n;
	private static int m;
	private static int t;
	private static int[][] map;
	private static int x;
	private static int d;
	private static int k;
	static int[] dy = { -1, 1, 0, 0 }; //상 하 좌 우
	static int[] dx = { 0, 0, -1, 1 };
	private static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		t = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n+1][m+1];
		
		for(int i=1;i<=n;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			for(int j=1;j<=m;j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = a;
			}
		}//for i
		
		for(int tc =0;tc<t;tc++) {//t번동안 반복한다.
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			x = Integer.parseInt(stringTokenizer.nextToken());
			d = Integer.parseInt(stringTokenizer.nextToken());
			k = Integer.parseInt(stringTokenizer.nextToken());
			
			// 여기서 k번만큼 돌리고 나서 
			while(k>0) {
//				System.out.println("x: "+x+", d: "+d);
				if(d==0) { //시계
					for(int i=x;i<=n;i+=x) {
						int last = map[i][m];
						for(int j=m;j>=2;j--) { //1번째는 임의로 
							map[i][j]= map[i][j-1];
						}
						map[i][1]= last;
					}
				}
				else {//반시계  1번째가 m번째로
					for(int i=x;i<=n;i+=x) {
						int first = map[i][1]; //처음이 마지막으로
						for(int j=1;j<=m-1;j++) {
							map[i][j] = map[i][j+1];
						}
						map[i][m] = first;
					}
				}
				k--;
			}// while k 회전
//			System.out.println();
//			System.out.println("------------");
//			System.out.println("회전 후");
//			for(int i=1;i<=n;i++) {
//				for(int j=1;j<=m;j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			//계산을 실시한다.
			int cnt=0;
			int sum =0;
			int many = 0;
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=m;j++) {
					int num = map[i][j];
					if(num==0) continue;
					sum+=num;
					many+=1;
				
					visit = new boolean[n+1][m+1];
					Queue<dot> queue = new LinkedList<>();
					queue.add(new dot(i, j,num));
					boolean is_same=false;
					while(!queue.isEmpty()) {
						dot now = queue.poll();
						
						int y = now.y;
						int x = now.x;
						int nownum = now.num;
						
						if(visit[y][x]) continue;
						visit[y][x] = true;
						
//						System.out.println("y,x: "+y+","+x);
						
						if((i!=y||j!=x)&&map[y][x]==nownum) { //다르고 값은 값다면 0으로 만들어 준다.
							map[y][x]=0;
							map[i][j]=0;
						}
						
						
						for(int dir = 0;dir<4;dir++) {
							int ny = y+dy[dir];
							int nx = x+dx[dir];
							//위 아래는 범위 넘으면 확인하지 않는다.
							if(ny<=0||ny>n) continue;
							//만약 nx==0이면 n번째를 확인
							if(nx==0) {
								nx = m;
							}
							//만약 nx==m이면 1번째를 확인한다. 
							else if(nx==m+1) {
								nx=1;
							}
							if(nownum==map[ny][nx]) {
								cnt++;
								is_same=true;
								queue.add(new dot(ny, nx, nownum));
							}
						}
					}
					if(is_same) {
						map[i][j] = 0;
					}
					
				}
			}//for i
			//만약 같은게 하나도 없으면
//			System.out.println("cnt: "+cnt);
			if(cnt==0) {
				double avg = (double)sum/ many;
//				System.out.println(sum);
				System.out.println(avg);
				for(int i=1;i<=n;i++) {
					for(int j=1;j<=m;j++) {
						if(avg<map[i][j]) {
							map[i][j]-=1;
						}
						else if(map[i][j]!=0&&avg>map[i][j]) {
							map[i][j]+=1;
						}
					}
				}
			}
		
//			System.out.println("연산 후");
//			for(int i=1;i<=n;i++) {
//				for(int j=1;j<=m;j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//			System.out.println("------------");
		}//for tc
		
		
		
		int sum =0;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
//				System.out.print(map[i][j]+" ");
				sum+=map[i][j];
			}
//			System.out.println();
		}
		System.out.println(sum);
	}
	static class dot{
		int y,x,num;

		public dot(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}
		
		

	
		
		
	}
}
