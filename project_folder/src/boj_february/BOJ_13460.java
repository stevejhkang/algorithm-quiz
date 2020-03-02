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
 * @category 
* @problem_description
* @solving_description 겹치는 부분을 잘 생각해야하는데
*  위로 움직일때는 c가 같으면 i가 낮은게 먼저 움직인다,
*  아래로 움직일때 c가 같으면 i가 높은게 먼저 움직인다,
*  오른쪽으로 움직일때 r이 같으면 j가 높은게 먼저움직인다,
*  왼쪽으로 움지일때 r이 같으면 j가 낮은게 먼저움직인다. 
*/
public class BOJ_13460 {
	private static int n;
	private static int m;
	private static int[][] map;
	private static int redr;
	private static int redc;
	private static int bluc;
	private static int blur;
	private static boolean[] visit;
	static int[] dy = {-1,1,0,0}; static int[] dx = {0,0,-1,1}; //상하좌우
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
		
		//그 후에 회전을 시작하는데 그냥 왼쪽으로 쭉 기울이고 오른쪽으로  쭉 위로쭉  아래로 쭉 기울인다.
		Queue<info> queue = new LinkedList<info>();
		queue.offer(new info(redr, redc, blur, bluc, 0));
		while(!queue.isEmpty()) {
			info now = queue.poll();
			ball red = new ball(now.rr, now.rc);
			ball blue = new ball(now.br,now.bc);
			
			if(now.cnt==10) {
				System.out.println(-1);
				return;
			}

//			*  아래로 움직일때 c가 같으면 i가 높은게 먼저 움직인다,
//			*  오른쪽으로 움직일때 r이 같으면 j가 높은게 먼저움직인다,
//			*  왼쪽으로 움지일때 r이 같으면 j가 낮은게 먼저움직인다.
			boolean flag_red =false; boolean flag_blue=false;
			for(int i=0;i<4;i++) {
				//매번 초기화 해줘야한다.
				red.y = now.rr; red.x = now.rc; blue.y = now.br; blue.x= now.bc;
//				*  위로 움직일때는 c가 같으면 i가 낮은게 먼저 움직인다,
				if(i==0) {//상///////////////////////////////////////////////////////////////////////////
					flag_red =false; flag_blue=false;
					if(red.x==blue.x) {
						if(red.y<blue.y) { //빨강먼저
							while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
								red.y+=dy[i]; red.x+=dx[i];
								if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
									red.y=-1; red.x=-1;
									flag_red=true;
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
							if(!flag_red&&map[red.y][red.x]==-1) {
								red.y-=dy[i]; red.x-=dx[i];
							}
//							if(map[blue.y][blue.x]==-1) {
//								blue.y-=dy[i]; blue.x-=dx[i];
//							}
							
							
							//0이면 계속 이동시킨다. 빨강이랑 겹치는 지도 체크
							while(map[blue.y][blue.x]==0) { 
								blue.y+=dy[i]; blue.x+=dx[i];
								if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
									blue.y=-1; blue.x=-1;
									flag_blue=true;
									break;
								}
								if(blue.y==red.y&&blue.x==red.x) {
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
//							if(map[red.y][red.x]==-1) {
//								red.y-=dy[i]; red.x-=dx[i];
//							}
							if(!flag_blue&&(map[blue.y][blue.x]==-1||blue.y==red.y&&blue.x==red.x)) {
								blue.y-=dy[i]; blue.x-=dx[i];
							}
							
							
						}else { //파랑먼저
							while(map[blue.y][blue.x]==0) { //0이면 계속 이동시킨다.
								blue.y+=dy[i]; blue.x+=dx[i];
								if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
									blue.y=-1; blue.x=-1;
									flag_blue=true;
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
//							if(map[red.y][red.x]==-1) {
//								red.y-=dy[i]; red.x-=dx[i];
//							}
							if(!flag_blue&&map[blue.y][blue.x]==-1) {
								blue.y-=dy[i]; blue.x-=dx[i];
							}
							
							// 파랑이랑 겹치는 지도 체크
							while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
								red.y+=dy[i]; red.x+=dx[i];
								if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
									red.y=-1; red.x=-1;
									flag_red=true;
									break;
								}
								if(blue.y==red.y&&blue.x==red.x) {
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
							if(!flag_red&&(map[red.y][red.x]==-1||blue.y==red.y&&blue.x==red.x)) {
								red.y-=dy[i]; red.x-=dx[i];
							}
//							if(map[blue.y][blue.x]==-1) {
//								blue.y-=dy[i]; blue.x-=dx[i];
//							}
						}
					}
					else {//아무거나 겹칠일 없음
//						System.out.println(red.y+","+red.x);
						while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
							red.y+=dy[i]; red.x+=dx[i];
							if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
								red.y=-1; red.x=-1;
								flag_red=true;
								break;
							}
						}
						//벗어나면 다시 빽한번 해주고 종료
						if(!flag_red&&map[red.y][red.x]==-1) {
							red.y-=dy[i]; red.x-=dx[i];
						}
//						if(map[blue.y][blue.x]==-1) {
//							blue.y-=dy[i]; blue.x-=dx[i];
//						}
						
						//0이면 계속 이동시킨다. 빨강이랑 겹치는 지도 체크
						while(map[blue.y][blue.x]==0) { 
							blue.y+=dy[i]; blue.x+=dx[i];
							if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
								blue.y=-1; blue.x=-1;
								flag_blue=true;
								break;
							}
						}
						//벗어나면 다시 빽한번 해주고 종료
//						if(map[red.y][red.x]==-1) {
//							red.y-=dy[i]; red.x-=dx[i];
//						}
						if(!flag_blue&&(map[blue.y][blue.x]==-1||blue.y==red.y&&blue.x==red.x)) {
							blue.y-=dy[i]; blue.x-=dx[i];
						}
					}
					if(flag_red&&!flag_blue) {
						System.out.println(now.cnt+1);
						return;
					}
					else if(!flag_red&&!flag_blue) { //파랑색이 아직 안 담겼으면 
						//더이상 담지 않는다.
						queue.offer(new info(red.y, red.x, blue.y, blue.x, now.cnt+1));
					}
				}//상
				else if(i==1) {//하/////////////////////////////////////////////////////////////////
					flag_red =false; flag_blue=false;
					if(red.x==blue.x) { //y값 큰게 먼저
						if(red.y>blue.y) { //빨강먼저
							while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
								red.y+=dy[i]; red.x+=dx[i];
								if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
									red.y=-1; red.x=-1;
									flag_red=true;
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
							if(!flag_red&&map[red.y][red.x]==-1) {
								red.y-=dy[i]; red.x-=dx[i];
							}
//							if(map[blue.y][blue.x]==-1) {
//								blue.y-=dy[i]; blue.x-=dx[i];
//							}
							
							//0이면 계속 이동시킨다. 빨강이랑 겹치는 지도 체크
							while(map[blue.y][blue.x]==0) { 
								blue.y+=dy[i]; blue.x+=dx[i];
								if(blue.y==red.y&&blue.x==red.x) {
									break;
								}
								if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
									blue.y=-1; blue.x=-1;
									flag_blue=true;
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
//							if(map[red.y][red.x]==-1) {
//								red.y-=dy[i]; red.x-=dx[i];
//							}
							if(!flag_blue&&(map[blue.y][blue.x]==-1||blue.y==red.y&&blue.x==red.x)) {
								blue.y-=dy[i]; blue.x-=dx[i];
							}
							
						}else { //파랑먼저
							while(map[blue.y][blue.x]==0) { //0이면 계속 이동시킨다.
								blue.y+=dy[i]; blue.x+=dx[i];
								if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
									blue.y=-1; blue.x=-1;
									flag_blue=true;
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
//							if(map[red.y][red.x]==-1) {
//								red.y-=dy[i]; red.x-=dx[i];
//							}
							if(!flag_blue&&map[blue.y][blue.x]==-1) {
								blue.y-=dy[i]; blue.x-=dx[i];
							}
							
							// 파랑이랑 겹치는 지도 체크
							while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
								red.y+=dy[i]; red.x+=dx[i];
								if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
									red.y=-1; red.x=-1;
									flag_red=true;
									break;
								}
								if(blue.y==red.y&&blue.x==red.x) {
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
							if(!flag_red&&(map[red.y][red.x]==-1||blue.y==red.y&&blue.x==red.x)) {
								red.y-=dy[i]; red.x-=dx[i];
							}
//							if(map[blue.y][blue.x]==-1) {
//								blue.y-=dy[i]; blue.x-=dx[i];
//							}
						}
					}
					else {//아무거나 겹칠일 없음
						while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
							red.y+=dy[i]; red.x+=dx[i];
							if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
								red.y=-1; red.x=-1;
								flag_red=true;
								break;
							}
						}
						//벗어나면 다시 빽한번 해주고 종료
						if(!flag_red&&map[red.y][red.x]==-1) {
							red.y-=dy[i]; red.x-=dx[i];
						}
						
						//0이면 계속 이동시킨다. 빨강이랑 겹치는 지도 체크
						while(map[blue.y][blue.x]==0) { 
							blue.y+=dy[i]; blue.x+=dx[i];
							if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
								blue.y=-1; blue.x=-1;
								flag_blue=true;
								break;
							}
						}
						//벗어나면 다시 빽한번 해주고 종료
//						if(map[red.y][red.x]==-1) {
//							red.y-=dy[i]; red.x-=dx[i];
//						}
						if(!flag_blue&&(map[blue.y][blue.x]==-1||blue.y==red.y&&blue.x==red.x)) {
							blue.y-=dy[i]; blue.x-=dx[i];
						}
					}
					//큐에 넣어준다.
					if(flag_red&&!flag_blue) {
						System.out.println(now.cnt+1);
						return;
					}
					else if(!flag_red&&!flag_blue) { //파랑색이 아직 안 담겼으면 
						//더이상 담지 않는다.
						queue.offer(new info(red.y, red.x, blue.y, blue.x, now.cnt+1));
					}
				}
				else if(i==2) { //좌/////////////////////////////////////////////////////////////////
					flag_red =false; flag_blue=false;
					if(red.y==blue.y) { //같으면 x값 작은게 먼저
						if(red.x<blue.x) { //빨강먼저
							while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
								red.y+=dy[i]; red.x+=dx[i];
								if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
									red.y=-1; red.x=-1;
									flag_red=true;
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
							if(!flag_red&&map[red.y][red.x]==-1) {
								red.y-=dy[i]; red.x-=dx[i];
							}
							
							//0이면 계속 이동시킨다. 빨강이랑 겹치는 지도 체크
							while(map[blue.y][blue.x]==0) { 
								blue.y+=dy[i]; blue.x+=dx[i];
								if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
									blue.y=-1; blue.x=-1;
									flag_blue=true;
									break;
								}
								if(blue.y==red.y&&blue.x==red.x) {
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
//							if(map[red.y][red.x]==-1) {
//								red.y-=dy[i]; red.x-=dx[i];
//							}
							if(!flag_blue&&(map[blue.y][blue.x]==-1||blue.y==red.y&&blue.x==red.x)) {
								blue.y-=dy[i]; blue.x-=dx[i];
							}
							
						}else { //파랑먼저
							while(map[blue.y][blue.x]==0) { //0이면 계속 이동시킨다.
								blue.y+=dy[i]; blue.x+=dx[i];
								if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
									blue.y=-1; blue.x=-1;
									flag_blue=true;
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
//							if(map[red.y][red.x]==-1) {
//								red.y-=dy[i]; red.x-=dx[i];
//							}
							if(!flag_blue&&map[blue.y][blue.x]==-1) {
								blue.y-=dy[i]; blue.x-=dx[i];
							}
							
							// 파랑이랑 겹치는 지도 체크
							while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
								red.y+=dy[i]; red.x+=dx[i];
								if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
									red.y=-1; red.x=-1;
									flag_red=true;
									break;
								}
								if(blue.y==red.y&&blue.x==red.x) {
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
							if(!flag_red&&(map[red.y][red.x]==-1||blue.y==red.y&&blue.x==red.x)) {
								red.y-=dy[i]; red.x-=dx[i];
							}
//							if(map[blue.y][blue.x]==-1) {
//								blue.y-=dy[i]; blue.x-=dx[i];
//							}
						}
					}
					else {//아무거나 겹칠일 없음
						while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
							red.y+=dy[i]; red.x+=dx[i];
							if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
								red.y=-1; red.x=-1;
								flag_red=true;
								break;
							}
						}
						//벗어나면 다시 빽한번 해주고 종료
						if(!flag_red&&map[red.y][red.x]==-1) {
							red.y-=dy[i]; red.x-=dx[i];
						}
//						if(map[blue.y][blue.x]==-1) {
//							blue.y-=dy[i]; blue.x-=dx[i];
//						}
						
						//0이면 계속 이동시킨다. 빨강이랑 겹치는 지도 체크
						while(map[blue.y][blue.x]==0) { 
							blue.y+=dy[i]; blue.x+=dx[i];
							if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
								blue.y=-1; blue.x=-1;
								flag_blue=true;
								break;
							}
						}
						//벗어나면 다시 빽한번 해주고 종료
//						if(map[red.y][red.x]==-1) {
//							red.y-=dy[i]; red.x-=dx[i];
//						}
						if(!flag_blue&&(map[blue.y][blue.x]==-1||blue.y==red.y&&blue.x==red.x)) {
							blue.y-=dy[i]; blue.x-=dx[i];
						}
					}
					//큐에 넣어준다.
					if(flag_red&&!flag_blue) {
						System.out.println(now.cnt+1);
						return;
					}
					else if(!flag_red&&!flag_blue) { //파랑색이 아직 안 담겼으면 
						//더이상 담지 않는다.
						queue.offer(new info(red.y, red.x, blue.y, blue.x, now.cnt+1));
					}
					
					
				}
				else if(i==3) {//우//////////////////////////////////////////////////////////////////////
					flag_red =false; flag_blue=false;
					if(red.y==blue.y) { //x값 큰게 먼저
						if(red.x>blue.x) { //빨강먼저
							while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
								red.y+=dy[i]; red.x+=dx[i];
								if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
									red.y=-1; red.x=-1;
									flag_red=true;
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
							if(!flag_red&&map[red.y][red.x]==-1) {
								red.y-=dy[i]; red.x-=dx[i];
							}
//							if(map[blue.y][blue.x]==-1) {
//								blue.y-=dy[i]; blue.x-=dx[i];
//							}
							
							//0이면 계속 이동시킨다. 빨강이랑 겹치는 지도 체크
							while(map[blue.y][blue.x]==0) { 
								blue.y+=dy[i]; blue.x+=dx[i];
								if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
									blue.y=-1; blue.x=-1;
									flag_blue=true;
									break;
								}
								if(blue.y==red.y&&blue.x==red.x) {
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
//							if(map[red.y][red.x]==-1) {
//								red.y-=dy[i]; red.x-=dx[i];
//							}
							if(!flag_blue&&(map[blue.y][blue.x]==-1||blue.y==red.y&&blue.x==red.x)) {
								blue.y-=dy[i]; blue.x-=dx[i];
							}
							
						}else { //파랑먼저
							while(map[blue.y][blue.x]==0) { //0이면 계속 이동시킨다.
								blue.y+=dy[i]; blue.x+=dx[i];
								if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
									blue.y=-1; blue.x=-1;
									flag_blue=true;
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
//							if(map[red.y][red.x]==-1) {
//								red.y-=dy[i]; red.x-=dx[i];
//							}
							if(!flag_blue&&map[blue.y][blue.x]==-1) {
								blue.y-=dy[i]; blue.x-=dx[i];
							}
							
							// 파랑이랑 겹치는 지도 체크
							while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
								red.y+=dy[i]; red.x+=dx[i];
								if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
									red.y=-1; red.x=-1;
									flag_red=true;
									break;
								}
								if(blue.y==red.y&&blue.x==red.x) {
									break;
								}
							}
							//벗어나면 다시 빽한번 해주고 종료
							if(!flag_red&&(map[red.y][red.x]==-1||blue.y==red.y&&blue.x==red.x)) {
								red.y-=dy[i]; red.x-=dx[i];
							}
//							if(map[blue.y][blue.x]==-1) {
//								blue.y-=dy[i]; blue.x-=dx[i];
//							}
						}
					}
					else {//아무거나 겹칠일 없음
						while(map[red.y][red.x]==0) { //0이면 계속 이동시킨다.
							red.y+=dy[i]; red.x+=dx[i];
							if(map[red.y][red.x]==9) { //빨강 구멍에 들어갔을때
								red.y=-1; red.x=-1;
								flag_red=true;
								break;
							}
						}
						//벗어나면 다시 빽한번 해주고 종료
//						System.out.println(red.y+","+red.x);
						if(!flag_red&&map[red.y][red.x]==-1) {
							red.y-=dy[i]; red.x-=dx[i];
						}
//						if(map[blue.y][blue.x]==-1) {
//							blue.y-=dy[i]; blue.x-=dx[i];
//						}
						
						//0이면 계속 이동시킨다. 빨강이랑 겹치는 지도 체크
						while(map[blue.y][blue.x]==0) { 
							blue.y+=dy[i]; blue.x+=dx[i];
							if(map[blue.y][blue.x]==9) { //빨강 구멍에 들어갔을때
								blue.y=-1; blue.x=-1;
								flag_blue=true;
								break;
							}
						}
						//벗어나면 다시 빽한번 해주고 종료
//						if(map[red.y][red.x]==-1) {
//							red.y-=dy[i]; red.x-=dx[i];
//						}
						if(!flag_blue&&(map[blue.y][blue.x]==-1||blue.y==red.y&&blue.x==red.x)) {
							blue.y-=dy[i]; blue.x-=dx[i];
						}
					}
					if(flag_red&&!flag_blue) {
						System.out.println(now.cnt+1);
						return;
					}
					else if(!flag_red&&!flag_blue) { //파랑색이 아직 안 담겼으면 
						//더이상 담지 않는다.
						queue.offer(new info(red.y, red.x, blue.y, blue.x, now.cnt+1));
					}
				}
				
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
