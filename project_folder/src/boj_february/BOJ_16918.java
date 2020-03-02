package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 27. 오전 11:45:48
 * @category 
* @problem_description R*C 폭탄이 있늩 칸은 3초가 지난 후에 폭발하고, 인접한 네 칸도 함께 폭발,
* 인접한 칸에 폭탄이 있는 경우 연쇄반응 없이 폭탄만 사라짐.
* 봄버맨은 가장처음 일부칸에 폭탄 설치, 설치된 시간 같다. 
* 다음 1초동안 아무것도 하지 않음
* 3. 다음 1초동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉 모든 칸은 폭탄을 가지게됨
* 그리고 그 폭탄은 모두 동시에 설치했다고 가정
* 4. 1초가 지난 후에 3초전에 설치된 폭탄이 모두 폭발한다.
* 3,4를 반복한다. N초가 흐른 후 격자판 상태를 구하려고 한다.
* 
* 초기설치1초 
* 1차 빈칸 채워지기 2초 0차 빈칸설치터짐 3초 
* 2차 빈칸 채워지기 4초 1차 빈칸설치터짐 5초
* 3차 빈칸 채워지기 6초 2차 빈칸설치터짐 7초 
* @solving_description n초가 지난 후의 격자판 상태를 출력, 음 여러번의 포문을 돌면서 
* 연쇄 반응은 없으니 그냥 사방으로 터지는 것만 하면될듯 근데 터지는 타이밍이 두개로 나눠져 있어서
* 어느게 터지는지에 대한 구분을 지어줘야될 듯
*/
public class BOJ_16918 {
	private static int R;
	private static int C;
	private static int N;
	private static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static List[] list;
	private static int[][] copy_map;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		N = Integer.parseInt(stringTokenizer.nextToken());
		
		map= new int[R][C];
		//빈공간을 9로 두고 처음을 0 다음 폭탄을 1로 두자
		list = new List[10];
		for(int i=0;i<=9;i++) {
			list[i] = new ArrayList<dot>();
		}
		for(int i=0;i<R;i++) {
			String  s = bufferedReader.readLine();
			for(int j=0;j<C;j++) {
				char c = s.charAt(j);
				if(c=='.') {
					map[i][j] = 9;
					
				}
				else if(c=='O') {
					map[i][j] =0;
					list[0].add(new dot(i, j));
				}
			}
		}//for i
		
		if(N==1) { //초기상태 출력
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(map[i][j]==9) {
						System.out.print('.');
					}
					else {
						System.out.print('O');
					}
				}
				System.out.println();
			}
			return;
		}
		
		int time =1; //빈칸설치
//		* 초기설치 0초 
//		* 1차 빈칸 채워지기 2초 0차 빈칸설치터짐 3초 
//		* 2차 빈칸 채워지기 4초 1차 빈칸설치터짐 5초
//		* 3차 빈칸 채워지기 6초 2차 빈칸설치터짐 7초 
		//큐에 넣어야 하나? 그냥 시간에 따른 큐를 넣고 클리어 시켜주는 건 어떨까 그게 나을듯?
		
		while(true) {
			//빈칸이 9 폭탄이 0이다.
			copy_map = new int[R][C];
			time++;
			//빈칸채우기
			if(time%2==0) {// 폭탄이 아닌 곳에 폭탄 채우기
				for(int i=0;i<R;i++) {
					for(int j=0;j<C;j++) {
						if(map[i][j]==9) {
							
							//폭탄으로 채워준다.
							map[i][j]=0;
						}
					}
				}
			}
			
			else if(time%2==1) { //홀수 일때 터진다. 현재시간 -3초의 리스트에 있는것들이
				//mapcopy해줘야한다.
				
				for(int i=0;i<R;i++) {
					copy_map[i] = map[i].clone();
				}
				for(int i=0;i<list[(time-3)%10].size();i++) {
					dot now = (dot) list[(time-3)%10].get(i);
					int y = now.y; int x = now.x;
					//이전 단계에서 폭탄이 사라져 있으면 그냥 지나간다.
					if(map[y][x]!=0) continue;
					copy_map[y][x]=9;
					for(int k=0;k<4;k++) {
						int ny = y+dy[k]; int nx = x+dx[k];
						//범위 
						if(ny<0||ny>=R||nx<0||nx>=C) continue;
						copy_map[ny][nx]= 9; //터뜨려준다.
					}
				}
				//일단 터뜨리고 다음 차례에 리스트에 넣자
				for(int i=0;i<R;i++) {
					map[i] = copy_map[i].clone();
				}
				for(int i=0;i<R;i++) {
					for(int j =0;j<C;j++) {
						if(map[i][j]==0) {
							list[(time-1)%10].add(new dot(i, j));
						}
					}
				}
//				System.out.println("time-1: "+list[time-1].size());
				list[(time-3)%10].clear();
			}
		
			if(time==N) {
				for(int i=0;i<R;i++) {
					for(int j=0;j<C;j++) {
						if(map[i][j]==9) {
							System.out.print('.');
						}
						else {
							System.out.print('O');
						}
					}
					System.out.println();
				}
				return;
			}
//			else {
//				System.out.println();
//				
//				for(int i=0;i<R;i++) {
//					for(int j=0;j<C;j++) {
//						if(map[i][j]==9) {
//							System.out.print('.');
//						}
//						else {
//							System.out.print('O');
//						}
//					}
//					System.out.println();
//				}
//				System.out.println(list[time].size());
//				System.out.println(time);
//			}
		}//while true
		
	}//main
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
