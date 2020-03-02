package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * @author junhukang
 * @time 2020. 2. 9. 오전 11:01:48
 * @category 
* @problem_description n+1에 궁수 3명을 배치해서 최대로 죽일 수 있는 적의 개수를 출력하라 -> 최대 -> 가지치기
* @solving_description 
*/
public class BOJ_17135 {
	private static int n,m,d;
	static int kill=0;
	static int[][] map;
	static boolean set_archer[];
	private static int[] archerX;
	static int dy[] = {0,-1,0}; static int dx[]= {1,0,-1}; //우, 상, 좌
	private static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		d = Integer.parseInt(stringTokenizer.nextToken()); //공격거리
		//map 만들기
		map=new int[n][m];
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());
			for(int j=0;j<m;j++) {
				int a= Integer.parseInt(stringTokenizer.nextToken());
//				if(a==1) {
//					enemy++;
//				}
				map[i][j]=a;
			}
		}
		//궁수 배치 [n][0~m-1]까지중 3명
		set_archer = new boolean[m];
		archerX= new int[3];
		max= Integer.MIN_VALUE;
		recursion(0);
		System.out.println(max);
	}
	static void recursion(int k) {
		if(k==3) {
			//뽑았을때 n초 만큼 쭉 진행해야한다.
			game();
			return;
		}
		for(int i=m-1;i>=0;i--) {
			if(!set_archer[i]) {
				set_archer[i]=true;
//				map[n][i]=2;//궁수는 2
				archerX[k]=i; 
				recursion(k+1);
//				map[n][i]=0;
				archerX[k]=0; 
				set_archer[i]=false;
			}
		}
	}// recursion
	static void game() {
		int time =n;
		int kill_temp=0;
		
		int map_copy[][] = new int[n][m]; 
		
		for(int i=0;i<map.length;i++) { //복사
			map_copy[i]=map[i].clone();
		}
		
		while(time>0) {
			
//			for(int i=0;i<3;i++) {
//				System.out.print(archerX[i]+" ");
//			}
//			System.out.println("");
			
			ArrayList<dot> setzero = new ArrayList<BOJ_17135.dot>();
			for(int i=0;i<3;i++) {//한명씩 스택에 담아 적을 죽인다. 죽이는 방향이 왼쪽이 먼저이므로 오른쪽부터 dfs 넣어준다.
				int visitTemp[][] = new int[n][m]; //0방문안함 1방문 2죽인적있음
				int archX= archerX[i];
				Queue<dot> queue =new LinkedList<>();
				queue.offer(new dot(n-1, archerX[i])); //궁수자리보다 y축으로 -1로 넣는다.
				while(!queue.isEmpty()) {
					dot temp = queue.poll();
					int y = temp.y; int x = temp.x;
					//y가 n일때는 위만 체크
//					if(y==n) { //이때도 왼쪽을 체크해야함...
//						queue.offer(new dot(n-1, x));
//						continue;
//					}
					
					if(visitTemp[y][x]==1||visitTemp[y][x]==2) continue;
					visitTemp[y][x]=1;
					
					if(visitTemp[y][x]==1&&map_copy[y][x]==1) { //처음 죽이면 숫자 세고
						visitTemp[y][x]=2;
						kill_temp++;
						map_copy[y][x]=0;
						//한명 죽이면 끝이므로 break;
						break;
					}
					else if(visitTemp[y][x]==2) { //두번째이면 죽인 숫자 세지 않는다.
						break;
					}
					for(int j=2;j>=0;j--) { //인덱스 2가 왼쪽임
						int ny =y+dy[j]; int nx=x+dx[j];
						//범위 벗어나면 패스
						if(ny<0||ny>=n||nx<0||nx>=m) continue;
						//거리보다 길면 패스
						if((n-ny)+(Math.abs(nx-archX))>d) continue;
						if(visitTemp[ny][nx]==2) { 
							//다음 탐색할 곳이 이미 죽인적 있으면 그 궁수는 같은적을
							//죽이는 것이므로 break;
							break;
						}
						queue.offer(new dot(ny, nx));
					}
				}
			}
			//적을 이동시킨다.
			for(int i=n-1;i-1>=0;i--) {
				map_copy[i]= map_copy[i-1].clone();
			}
			//첫줄 0으로 만든다.
			for(int i=0;i<m;i++) {
				map_copy[0][i]=0;
			}
			time--;
//			for(int i=0;i<n;i++) {
//				for(int j=0;j<m;j++) {
//					System.out.print(map_copy [i][j]+" ");
//				}
//				System.out.println("");
//			}
//			System.out.println("");
		}
		
		if(max<kill_temp) {
			
//			System.out.println(kill_temp);
			max=kill_temp;
		}
	}//game
	static class dot{
		int y;
		int x;
		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
