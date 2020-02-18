package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 19. 오전 12:25:47
 * @category BFS
* @problem_description 아기 상어가 자기의 사이즈보다 작은 사이즈의 물고기를 먹으면서 성장하는데 더이상 물고기를 먹
* 을 수 없을 때까지 걸리는 시간을 구하는 문제
* @solving_description 
*/

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
		//잡아먹을 물고기가 0이면 0출력 후 리턴
		if(fish_num==0) {
			System.out.println(0);
			return;
		}
			
		//큐를 돌렸는데 잡아먹은게 하나도 없을때 그만두고 이동횟수를 출력한다.
		//현재 상태에서 먹을 수 있는 물고기를 다먹고 먹은 좌표를 우선순위큐에 저장하고, 
		size= 2; 
		int lapsed_time=0; //총 걸린 시간 측정
		boolean dideat=false; //한마리라도 먹었는지 확인하는 flag
		int eat_time=0; //먹은 횟수
		out:do {
			dideat=false;//하나라도 먹었는지 체크
			//가장 가까운 물고기를 맨 위로 보내기 위한 우선순위 큐
			PriorityQueue<fish> eaten_fishes = new PriorityQueue<>();
			visit= new int[n][n];
			Queue<shark> queue = new LinkedList<shark>();
			//y,x
			queue.offer(new shark(sharkY, sharkX));
			int min_step = Integer.MAX_VALUE;
			
			out2: while(!queue.isEmpty()) {
				shark temp = queue.poll();
				int y = temp.y; int x = temp.x; 
				
//				System.out.println("");
//				for(int i=0;i<n;i++) {
//					for(int j=0;j<n;j++) {
//						System.out.print(visit[i][j]+" ");
//					}
//					System.out.println();
//				}
//				System.out.println("---------------");
//				for(int i=0;i<n;i++) {
//					for(int j =0;j<n;j++) {
//						System.out.print(map[i][j]+" ");
//					}
//					System.out.println();
//				}
				for(int i=0;i<4;i++) {
					int ny = y+dy[i]; int nx = x+dx[i];
					//범위
					if(ny<0||ny>=n||nx<0||nx>=n) continue;
					//사이즈가 작다면
					if(size<map[ny][nx]) continue;
					//첫 시작 위치이면 
					if(map[ny][nx]==9) continue;
					//처음 방문하고, 사이즈가 같거나 길인 경우는 그냥 지나간다.
					if(visit[ny][nx]==0&&(size==map[ny][nx]||map[ny][nx]==0)) {
						visit[ny][nx]=visit[y][x]+1;
						//또 탐색을 위해 위치 갱신
						queue.offer(new shark(ny, nx));
					}
					//처음 방문하고, 상어의 사이즈가 더 크면 먹는다.
					else if(visit[ny][nx]==0&&size>map[ny][nx]) {
						visit[ny][nx]=visit[y][x]+1;
						dideat=true; //먹었다 처리
						
						if(min_step<visit[ny][nx]) //현재까지 먹기위해 움직인 스탭보다 더 먼 스탭은 가지 않는다.
							break out2;
						
						//만약 물고기를 먹는 스텝이 현재까지 저장된 스탭보다 작으면 갱신
						if(min_step>visit[ny][nx])
							min_step=visit[ny][nx];
						
						//먹은 물고기는 넣어준다.
						eaten_fishes.add(new fish(ny, nx, map[ny][nx]));
						//또 탐색을 위해 위치 갱신
						queue.offer(new shark(ny, nx));
					}
				}//for
				
			}//while queue
			if(dideat) {
				//가장 가까운 물고기를 꺼내고  상어 위치를 바꿔주고 먹은 개수를 최신화 시켜준다.
				map[sharkY][sharkX]=0;
				fish closest= eaten_fishes.poll();
				sharkY = closest.y; sharkX=closest.x;
				map[sharkY][sharkX]=9;
//				System.out.println(sharkY+","+sharkX);
				//먹은 물고기 수 증가, 남은 물고기 수 감소시키기
				eat_time++; fish_num--;
				//사이즈만큼 먹으면 사이즈업
				if(eat_time==size) {
					size++; eat_time=0;
//					System.out.println("size!!!!!!!!: "+size);
				}
				//잡아먹는데까지 걸린 시간을 총시간에 추가한다.
				lapsed_time+=visit[sharkY][sharkX]; 
//				System.out.println("move_step: "+visit[sharkY][sharkX]);
//				System.out.println("lapsed time: "+lapsed_time);
				//남은거 0개면 더이상 이동하지 않는다.
				if(fish_num==0) {
					break out;
				}		
			}
		}while(dideat);
		//마지막으로 탐색해서 더이상 먹을 수 없으면 출력
		System.out.println(lapsed_time);
	}//main
	static class fish implements Comparable<fish>{
		Integer y; Integer x; Integer size;

		//거리가 sharkY, sharkX와 거리가 가장 짧은 것을 위로 같다면 y값 y같다면 x값
		@Override
		public int compareTo(fish o) {
			// TODO Auto-generated method stub
			Integer this_dist = Math.abs(sharkY-this.y)+Math.abs(sharkX-this.x);
			Integer o_dist = Math.abs(sharkY-o.y)+Math.abs(sharkX-o.x);
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
		Integer y; Integer x;

		public shark(Integer y, Integer x) {
			super();
			this.y = y;
			this.x = x;
		} 
		
	}
}
