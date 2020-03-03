package boj_march;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 2. 오후 7:09:39
 * @category 
* @problem_description 로봇들에 m개의 명령을 내리려고 한다. 각각의 명령은 
* 순차적으로 실행된다. 즉 하나의 명령을 한 로봇에서 내렸으면, 그 명령이 완수될 때까지
* 그 로봇과 다른 모든 로봇에게 다른 명령을 내릴 수 없다. 각각의 로봇에 대해 수행하는
* 명령은 다음 세가지 
* L: 향하는 기준으로 왼쪽 90도 회전 R은 오른쪽 90도
* F: 로봇이 향하고 있는 방향을 기준으로 앞으로 한칸
* 
* 벽에 충돌하는 경우(밖으로 벗어나는 경우), x번 로봇이 움직이다가 Y번 로봇에 충돌하는 경우
* @solving_description 
* dir=(dir+time)%4;
* 방향을 -로 하면은 나머지 연산에서 -가 나오기 때문에
* dir= (4+(dir-time)%4)%4; 다음과 같이 (-해준 값의 % 크기)를 해준 값에 크기를 더해준 것의 %크기
* 를 해주면 된다.
* 
* 
* 현욱띠!!!!-> time자체를 4이하로 내려버리면 
* dir= (dir-time+4)%4
* dir=(dir+time+4)%4 이런 식으로 사용할 수 있다!
*/

public class BOJ_2174 {
	private static int A;
	private static int B;
	private static int[][] map;
	private static int n;
	private static int M;
	static final int W=0; static final int S=1;
	static final int E=2; static final int N=3;
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { -1, 0, 1, 0 };
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		A = Integer.parseInt(stringTokenizer.nextToken()); //가로
		B = Integer.parseInt(stringTokenizer.nextToken()); //세로
		
		map = new int[B+1][A+1];
		
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n= Integer.parseInt(stringTokenizer.nextToken());// N개줄 초기 위치
		M = Integer.parseInt(stringTokenizer.nextToken());// m개 명령
		
		ArrayList<robot> al = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int x= Integer.parseInt(stringTokenizer.nextToken()); //가로
			int y= Integer.parseInt(stringTokenizer.nextToken()); //세로
			char dir = stringTokenizer.nextToken().charAt(0);
			map[y][x] = i+1;
			
			if(dir=='W') {
				al.add(new robot(y, x, W));
			}
			else if(dir=='S') {
				al.add(new robot(y, x, S));
			}
			else if(dir=='N') {
				al.add(new robot(y, x, N));
			}
			else {
				al.add(new robot(y, x, E));
			}
		}
		
		for(int i=0;i<M;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			int num = Integer.parseInt(stringTokenizer.nextToken());
			char c = stringTokenizer.nextToken().charAt(0);
			int time = Integer.parseInt(stringTokenizer.nextToken());
			if(c=='L') { //실제로는 L이면 +1
				robot now = al.get(num-1);
				int dir = now.dir;
				time=time%4;
				dir=(dir+time)%4;
				al.get(num-1).dir=dir; //방향 갱신
			}
			else if(c=='R') { //R이면 -1
				robot now = al.get(num-1);
				int dir = now.dir;
				time=time%4;
				dir= (dir-time+4)%4;
				al.get(num-1).dir=dir; //방향 갱신
			}
			else { //F 이때만 부딪힐 수 있으므로
				robot now =al.get(num-1);
				int y = now.y; int x =now.x; int dir = now.dir;
				int ny = y; int nx=x;
				while(time>0) {
					time--;
					ny =ny+dy[dir];  nx =nx+dx[dir];
					if(ny<=0||ny>B||nx<=0||nx>A) {
						System.out.printf("Robot %d crashes into the wall\n",num);
						return;
					}
					else if(map[ny][nx]!=0) {
						System.out.printf("Robot %d crashes into robot %d\n",num,map[ny][nx]);
						return;
					}
				}
				//위치 갱신
				map[y][x]=0; map[ny][nx]= num; al.get(num-1).y=ny; al.get(num-1).x=nx;
			}//else f
			
		}//for m
		System.out.println("OK");
		
		
	}//main
	static class robot{
		int y,x,dir;

		public robot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
		
	}
}
