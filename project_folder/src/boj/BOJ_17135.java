package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17135 {
	private static int n,m,d,enemy=0;
	static int kill=0;
	static int[][] map;
	static int[][] map_copy2;
	static boolean set_archer[];
	private static int[] archerX;
	static int dy[] = {0,-1,0}; static int dx[]= {1,0,-1};
	private static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		d = Integer.parseInt(stringTokenizer.nextToken()); //공격거리
		//map 만들기
		map=new int[n][m];
		map_copy2=new int[n][m];
		for(int i=0;i<n;i++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());
			for(int j=0;j<m;j++) {
				int a= Integer.parseInt(stringTokenizer.nextToken());
				if(a==1) {
					enemy++;
				}
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
		
		for(int i=0;i<map.length;i++) { //복사
			map_copy2[i]=map[i].clone();
		}
		
		
		while(time>0) {
			int map_copy[][] = new int[n][m]; 
			
			for(int i=0;i<map_copy2.length;i++) { //복사
				map_copy[i]=map_copy2[i].clone();
			}
			for(int i=0;i<3;i++) {
				System.out.print(archerX[i]+" ");
			}
			System.out.println("");
			
			for(int i=0;i<3;i++) {//한명씩 적을 죽인다. 죽이는 방향이 왼쪽이 먼저이므로 오른쪽부터 dfs 넣어준다.
				boolean visitTemp[][] = new boolean[n][m];
				int archX= archerX[i];
				Stack<dot> stack =new Stack<>();
				stack.push(new dot(n, archerX[i])); //궁수자리보다 y축으로 -1로 넣는다.
				while(!stack.isEmpty()) {
					dot temp = stack.pop();
					int y = temp.y; int x = temp.x;
					//y가 n일때는 위만 체크
					if(y==n) {
						stack.push(new dot(n-1, x));
						continue;
					}
					if(visitTemp[y][x]) continue;
					visitTemp[y][x]=true;
					if(map_copy2[y][x]==1) { //원본에서 1이면 맵카피에서 0으로 하고
//						enemy--; kill++;
						kill_temp++;
						map_copy[y][x]=0;
						break;
					}
					for(int j=2;j>=0;j--) {
						int ny =y+dy[j]; int nx=x+dx[j];
						//범위 벗어나면 패스
						if(ny<0||ny>=n||nx<0||nx>=m) continue;
						//만약 방문했으면 패스
						if(visitTemp[ny][nx]) continue;
						//거리보다 길면 패스
						if((n-ny)+(Math.abs(nx-archX))>d) continue;
						stack.push(new dot(ny, nx));
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
			//map_copy를 map에 복사
			for(int i=0;i<map_copy2.length;i++) { //복사
				map_copy2[i]=map_copy[i].clone();
			}
			time--;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					System.out.print(map_copy2 [i][j]+" ");
				}
				System.out.println("");
			}
			System.out.println("");
		}
		
		if(max<kill_temp) {
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
