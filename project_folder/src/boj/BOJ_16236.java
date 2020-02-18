package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import array.insertSort;

public class BOJ_16236 {
	private static int n;
	private static int[][] map;
	private static int sharkY;
	private static int sharkX;
	static int dy[] = {1,0,-1,0}; static int dx[] = {0,1,0,-1};
	private static int size;
	private static int[][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		
		map= new int[n][n];
		sharkY=0;
		sharkX=0;
		int fish_num=0;
		for(int i=0;i<n;i++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<n;j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j]=a; 
				if(a==9) { //시작점 저장
					sharkY=i; sharkX=j;
				}
				else if(a!=0&& a!=9) {
					fish_num++;
				}
			}
		}
		if(fish_num==0) {
			System.out.println(0);
			return;
		}
			
		//큐를 돌렸는데 잡아먹은게 하나도 없을때 그만두고 이동횟수를 출력한다.
		//현재 상태에서 먹을 수 있는 물고기를 다먹고 먹은 좌표를 피큐에 저장하고, 
		size= 2; 
		int lapsed_time=0;
		boolean dideat=false;
		int eat_time=0;
		out:do {
			dideat=false;//하나라도 먹었는지 체크
			//가장 가까운 물고기를 맨 위로 보낸다.
			PriorityQueue<fish> eaten_fishes = new PriorityQueue<>();
			visit= new int[n][n];
			Queue<shark> queue = new LinkedList<shark>();
			//y,x,size,eat
			queue.offer(new shark(sharkY, sharkX,2,0));
			int min_step = Integer.MAX_VALUE;
			out2: while(!queue.isEmpty()) {
				shark temp = queue.poll();
				int y = temp.y; int x = temp.x; int eat= temp.eat;
				System.out.println("");
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++) {
						System.out.print(visit[i][j]+" ");
					}
					System.out.println("");
				}
				for(int i=0;i<4;i++) {
					int ny = y+dy[i]; int nx = x+dx[i];
					//범위
					if(ny<0||ny>=n||nx<0||nx>=n) continue;
					//사이즈가 작다면
					if(size<map[ny][nx]) continue;
					//상어랑 사이즈 같거나 0이면 
					if(visit[ny][nx]==0&&(size==map[ny][nx]||map[ny][nx]==0)) {
						visit[ny][nx]=visit[y][x]+1;
						queue.offer(new shark(ny, nx, size, eat));
					}
					//상어가 사이즈 크면 먹는다.
					else if(visit[ny][nx]==0&&size>map[ny][nx]&&map[ny][nx]!=0) {
						visit[ny][nx]=visit[y][x]+1;
						dideat=true;
						if(min_step<visit[ny][nx])
							break out2;
						//먹은 물고기를 넣어준다.
						if(min_step>visit[ny][nx])
							min_step=visit[ny][nx];
						eaten_fishes.add(new fish(ny, nx, map[ny][nx]));
						queue.offer(new shark(ny, nx, size, eat+1));
					}
				}//for
				
			}//while queue
			if(dideat) {
				//가장 가까운 물고기를 꺼내고  상어 위치를 바꿔주고 먹은 개수를 최신화 시켜준다.
				map[sharkY][sharkX]=0;
				fish closest= eaten_fishes.poll();
				sharkY = closest.y; sharkX=closest.x;
				map[sharkY][sharkX]=0;
				System.out.println(sharkY+","+sharkX);
				eat_time++; fish_num--;
				//사이즈만큼 먹으면 사이즈업
				if(eat_time==size) {
					size++; eat_time=0;
					System.out.println("size!!!!!!!!!!!!!!!: "+size);
				}
				
				lapsed_time+=visit[sharkY][sharkX];
				System.out.println(visit[sharkY][sharkX]);
				System.out.println(lapsed_time);
				if(fish_num==0) {
					break out;
				}
				
					
			}
//			else if(!dideat) {
//				int max=Integer.MIN_VALUE;
//				for(int i=0;i<n;i++) {
//					for(int j=0;j<n;j++) {
//						if(max<visit[i][j])
//							max=visit[i][j];
//					}
//				}
//				lapsed_time+=max;
//			}
//			System.out.print(sharkY+","+sharkX+"->");
		}while(dideat);
		//더이상 먹을 수 없으면 출력
		System.out.println(lapsed_time);
		
		
		
		
	}//main
	static class fish implements Comparable<fish>{
		Integer y; Integer x; Integer size;

		//거리가 sharkY, sharkX와 거리가 가장 짧은 것을 위로 같다면 y값 y같다면 x값
		@Override
		public int compareTo(fish o) {
			// TODO Auto-generated method stub
			Integer this_dist = Math.abs(sharkY-this.y)+Math.abs(sharkX-this.x);
			Integer o_dist = Math.abs(sharkX-o.y)+Math.abs(sharkX-o.x);
			//거리가 같으면 y값으로
			if(this_dist.equals(o_dist)) {
				//y값이 같으면
				if(this.y.equals(o.y)) {
					return this.x.compareTo(o.x);
				}
				else {
					return this.y.compareTo(o.y);
				}
			}
			else {//거리가 다르면
				return this_dist.compareTo(o_dist);
			}
		}

		public fish(Integer y, Integer x, Integer size) {
			super();
			this.y = y;
			this.x = x;
			this.size = size;
		}
	}//fish
	static class shark {
		Integer y; Integer x; Integer size; Integer eat;



		public shark(Integer y, Integer x, Integer size, Integer eat) {
			super();
			this.y = y;
			this.x = x;
			this.size = size;
			this.eat = eat;
		}

		
		
	}
	static class dot{
		int y; int x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
