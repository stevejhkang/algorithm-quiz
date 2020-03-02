package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_12100 {
	private static int n;
	private static int[][] map;
	static int dy[] = {-1,0,1,0}; //상우하좌
	static int dx[] = {0,1,0,-1};
	private static ArrayList<Integer> order;
	private static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		map = new int[n][n];
		
		int init_max=Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j=0;j<n;j++) {
				int a= Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = a;
				if(init_max<a)
					init_max=a;
			}
		}
		//0123을 다섯번 중복가능하게 순서상관있게 선택한다. 중복순열
		order = new ArrayList<Integer>();
		max = Integer.MIN_VALUE;
		Math.max(max, init_max);
		setOrder(0);
//		order.add(0); order.add(1); order.add(1);order.add(0);order.add(1);
//		playGame();
		System.out.println(max);
		
	}
	static void setOrder(int r) {
		if(r==5) {
			//여기서 order를 가지고 게임을 돌려본다.
			playGame();
			
			return;
		}
		for(int i=0;i<4;i++) {
			order.add(i);
			setOrder(r+1);
			order.remove(r);
		}
	}
	static void playGame() {
		int[][] map_copy = new int[n][n];
		//1번의 케이스를 위한 map 복사
		for(int i=0;i<n;i++) {
			map_copy[i] = map[i].clone();
		}
//		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		boolean visit[][];
		for(int time =0;time<order.size();time++) {
			//이동시킬때마다 합쳐진 적 있는지 체크하는 배열 초기화
			visit= new boolean[n][n];
			//해당시간의 방향을 가져온다.
			int dir = order.get(time);
			//전체를 옮긴다. 그런데 똑같은 수가 세개인 경우
			//이동하려는 쪽에 있는 칸이 먼저 합쳐지므로 이동하려는 칸쪽 것부터 옮겨주어야한다.
			
			int temp=0; 
			//상우하좌
			if(dir==0) {//상일때는 위에서부터 i=0 아래서 위로
				for(int j=0;j<n;j++) {
					for(int i=1;i<n;i++) { 
						if(map_copy[i][j]!=0) { 
							int index=i-1;
							//어떻게든 옮겨지니까 그냥 임시 저장해놓고
							//0으로 미리 만들어 두자.
							temp = map_copy[i][j];
							map_copy[i][j] = 0;
							//index가 0이 되거나 값이 0이 아닌 것을 찾을 때까지 인덱스 이동
							while(index>=0&&map_copy[index][j]==0) {
								index+=dy[dir];
							}
							if(index<0) {
								map_copy[0][j] = temp;
							}
							else if(temp==map_copy[index][j]&&!visit[index][j]) {
								//같은 수이면 합친다.
								map_copy[index][j] = 2*temp;
								visit[index][j] = true;
							}
							else {//다른 수이면 그 아래에 쌓는다.
								map_copy[index+1][j] =temp;
							}
						}// 0이 아닌 거 찾기
					} //for i
				}// for j
			} //dir=0
			else if(dir==1) { //우일땐 j==n-1부터 왼쪽에서 오른쪽으로
				for(int i=0;i<n;i++) {
					for(int j=n-2;j>=0;j--) {
						if(map_copy[i][j]!=0) { 
							int index=j+1; //시작은 n-2
							temp = map_copy[i][j];
							map_copy[i][j] = 0;
							//index가 n-1이 되거나 값이 0이 아닌 것을 찾을 때까지 인덱스 이동
							while(index<n&&map_copy[i][index]==0) {
								index+=dx[dir];
							}
							if(index==n) {
								map_copy[i][n-1] = temp;
							}
							else if(temp==map_copy[i][index]&&!visit[i][index]) {
								//같은 수이면 합친다.
								map_copy[i][index] =2*temp;
								visit[i][index] = true;
							}
							else {//다른 수이면 그 아래에 쌓는다.
								map_copy[i][index-1] = temp;
							}
						}// 0이 아닌 거 찾기
					} //for i
				}// for j
			}
			else if(dir==2) { //하일땐 i==n-1때부터 위에서 아래로
				for(int j=0;j<n;j++) {
					for(int i=n-2;i>=0;i--) {
						if(map_copy[i][j]!=0) { 
							int index=i+1; //시작은 n
							temp= map_copy[i][j];
							map_copy[i][j] =0;
							//index가 n-1이 되거나 값이 0이 아닌 것을 찾을 때까지 인덱스 이동
							while(index<n&&map_copy[index][j]==0) {
								index+=dy[dir];
							}
							if(index==n) {
								map_copy[n-1][j] = temp;
							}
							else if(temp==map_copy[index][j]&&!visit[index][j]) {
								//같은 수이면 합친다.
								map_copy[index][j] = 2*temp;
								visit[index][j] = true;
							}
							else {//다른 수이면 그 아래에 쌓는다.
								map_copy[index-1][j] = temp;
							}
						}// 0이 아닌 거 찾기
					} //for i
				}// for j
			}
			else {//좌일땐 j==0qnxjjjjj 오른쪽에서 왼쪽으로
				for(int i=0;i<n;i++) { 
					for(int j=1;j<n;j++) {
						if(map_copy[i][j]!=0) { //이러면 0까지 쭉가게 한다.
							int index=j-1;
							temp = map_copy[i][j];
							map_copy[i][j] = 0;
							//index가 0이 되거나 값이 0이 아닌 것을 찾을 때까지 인덱스 이동
							while(index>=0&&map_copy[i][index]==0) {
								index+=dx[dir];
							}
							//jjjjjjjjjjjj
							if(index<0) {
								map_copy[i][0] = temp;
							}
							else if(temp==map_copy[i][index]&&!visit[i][index]) {
								//같은 수이면 합친다.
								map_copy[i][index] =2*temp;
								visit[i][index] = true;
							}
							else {//다른 수이면 그 아래에 쌓는다.
								map_copy[i][index+1] = temp;
							}
						}// 0이 아닌 거 찾기
					} //for i
				}// for j
			}
//			System.out.println();
//			for(int i=0;i<n;i++) {
//				for(int j=0;j<n;j++) {
//					System.out.printf("%2d ",map_copy[i][j]);
//				}
//				System.out.println();
//			}
		} //for time
		//게임이 끝난 후 map_copy내에서 최댓값 찾기
		int temp_max=Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(temp_max<map_copy[i][j]) {
					temp_max=map_copy[i][j];
				}
			}
		}
		if(max<temp_max) {
			max= temp_max;
//			System.out.println(max);
		}
//		System.out.println("------------------------");
	}//playGame
	
}
