package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 23. 오후 3:32:10
 * @category 시뮬레이션
* @problem_description
* @solving_description 겹치는 부분을 잘 생각해야하는데
*  위로 움직일때는 c가 같으면 i가 낮은게 먼저 움직인다,
*  아래로 움직일때 c가 같으면 i가 높은게 먼저 움직인다,
*  오른쪽으로 움직일때 r이 같으면 j가 높은게 먼저움직인다,
*  왼쪽으로 움지일때 r이 같으면 j가 낮은게 먼저움직인다.
*  
*   함수를 어떻게 짜야될지 고민을 해야되는 문제이다.
*/
public class BOJ_13460_2 {
	private static int n;
	private static int m;
	private static int[][] map;
	private static int redr;
	private static int redc;
	private static int bluc;
	private static int blur;
	private static boolean[] visit;
	static int[] dy = {-1,1,0,0}; static int[] dx = {0,0,-1,1}; //상하좌우
	private static boolean flag_red;
	private static boolean flag_blue;
	private static LinkedList<info> queue;
	private static int total_cnt;
	static final int RED=1;
	static final int BLUE=2;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[n][m];
		
		for(int i=0;i<n;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			String line = stringTokenizer.nextToken();
			for(int j=0;j<m;j++) {
				char a = line.charAt(j);
				if(a=='#') {//벽
					map[i][j] = -1;
				}
				else if(a=='R') {
					redr = i; redc = j;
				}else if(a=='B') {
					blur = i; bluc = j;
				}
				else if(a=='O') {
					map[i][j] = 9;
				}
			}
		}//for i
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				System.out.printf("%2d ",map[i][j]);
//			}
//			System.out.println();
		
//		}
		
		total_cnt = 0;
		//그 후에 회전을 시작하는데 그냥 왼쪽으로 쭉 기울이고 오른쪽으로  쭉 위로쭉  아래로 쭉 기울인다.
		queue = new LinkedList<info>();
		queue.offer(new info(redr, redc, blur, bluc, 0));
		while(!queue.isEmpty()) {
			info now = queue.poll();
			ball red = new ball(now.rr, now.rc);
			ball blue = new ball(now.br,now.bc);
			
			if(now.cnt==10) {
				System.out.println(-1);
				return;
			}
//			*  위로 움직일때는 c가 같으면 i가 낮은게 먼저 움직인다,
//			*  아래로 움직일때 c가 같으면 i가 높은게 먼저 움직인다,
//			*  오른쪽으로 움직일때 r이 같으면 j가 높은게 먼저움직인다,
//			*  왼쪽으로 움지일때 r이 같으면 j가 낮은게 먼저움직인다.
			flag_red =false; flag_blue=false;
			red.y = now.rr; red.x = now.rc; blue.y = now.br; blue.x= now.bc;
			boolean total_flag=false;
			
			for(int i=0;i<4;i++) {
				
				//방향에 따라서 어디가 같았을때 어디를 비교하고 먼저 넣어줘야되는지 다르다.
				if(i==0&&red.x==blue.x&&red.y<blue.y) {//상
					total_flag=move(red.y,red.x,blue.y,blue.x,i,RED,now.cnt);
				}
				else if(i==1&&red.x==blue.x && red.y>blue.y) { //하
					total_flag=move(red.y,red.x,blue.y,blue.x,i,RED,now.cnt);
				}
				else if(i==2&&red.y==blue.x&&red.x<blue.x) { //좌: y값이 같을 경우 x값이 작은 것이 먼저
						total_flag=move(red.y,red.x,blue.y,blue.x,i,RED,now.cnt);
				}
				else if(i==3&&red.y==blue.y && red.x>blue.x){ //우: y값이 같을 경우 x값이 큰 것이 먼저 
						total_flag=move(red.y,red.x,blue.y,blue.x,i,RED,now.cnt);
				}
				else {
					total_flag=move(red.y,red.x,blue.y,blue.x,i,BLUE,now.cnt);
				}
			}//for i
			if(total_flag) {
				System.out.println(total_cnt);
				return;
			}
		}//while
		
	}//main
	static boolean move(int redy, int redx, int bluey, int bluex, int i,int first,int cnt) {
		flag_red =false; flag_blue=false;
		if(first==RED) { //빨강먼저
			while(map[redy][redx]==0) { //0이면 계속 이동시킨다.
				redy+=dy[i]; redx+=dx[i];
				if(map[redy][redx]==9) { //빨강 구멍에 들어갔을때
					redy=-1; redx=-1;
					flag_red=true;
					break;
				}
			}
			//벗어나면 다시 빽한번 해주고 종료
			if(!flag_red&&map[redy][redx]==-1) {
				redy-=dy[i]; redx-=dx[i];
			}
			
			//0이면 계속 이동시킨다. 빨강이랑 겹치는 지도 체크
			while(map[bluey][bluex]==0) { 
				bluey+=dy[i]; bluex+=dx[i];
				if(map[bluey][bluex]==9) { //빨강 구멍에 들어갔을때
					bluey=-1; bluex=-1;
					flag_blue=true;
					break;
				}
				if(bluey==redy&&bluex==redx) {
					break;
				}
			}

			if(!flag_blue&&(map[bluey][bluex]==-1||bluey==redy&&bluex==redx)) {
				bluey-=dy[i]; bluex-=dx[i];
			}
			if(flag_blue) {
				return false;
			}
			else if(flag_red&&!flag_blue) {
				total_cnt=cnt+1;
				return true;
			}
			else { //파랑색이 아직 안 담겼으면 
				//더이상 담지 않는다.
				queue.offer(new info(redy, redx, bluey, bluex, cnt+1));
				return false;
			}
			
		}
		else { //파랑 먼저
			while(map[bluey][bluex]==0) { //0이면 계속 이동시킨다.
				bluey+=dy[i]; bluex+=dx[i];
				if(map[bluey][bluex]==9) { //빨강 구멍에 들어갔을때
					bluey=-1; bluex=-1;
					flag_blue=true;
					break;
				}
			}
			if(!flag_blue&&map[bluey][bluex]==-1) {
				bluey-=dy[i]; bluex-=dx[i];
			}
			// 파랑이랑 겹치는 지도 체크
			while(map[redy][redx]==0) { //0이면 계속 이동시킨다.
				redy+=dy[i]; redx+=dx[i];
				if(map[redy][redx]==9) { //빨강 구멍에 들어갔을때
					redy=-1; redx=-1;
					flag_red=true;
					break;
				}
				if(bluey==redy&&bluex==redx) {
					break;
				}
			}
			//벗어나면 다시 빽한번 해주고 종료
			if(!flag_red&&(map[redy][redx]==-1||bluey==redy&&bluex==redx)) {
				redy-=dy[i]; redx-=dx[i];
			}
			
			if(flag_blue) {
				return false;
			}
			else if(flag_red&&!flag_blue) {
				total_cnt=cnt+1;
				return true;
			}
			else  { //파랑색이 아직 안 담겼으면 
				//더이상 담지 않는다.
				queue.offer(new info(redy, redx, bluey, bluex, cnt+1));
				return false;
			}
		}
	}
	//빨, 파랑 공 좌표랑 기울인 횟수
	static class info{
		int rr, rc,br,bc,cnt;

		public info(int rr, int rc, int br, int bc, int cnt) {
			super();
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
			this.cnt = cnt;
		}
		
	}//infor
	static class ball{
		int y, x;

		public ball(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}

