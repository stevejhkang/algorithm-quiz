package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17143 {
	private static int n;
	private static int m;
	private static shark[][] map;
	private static int how_many;
	private static shark[][] map_temp;
	//0, 상, 하, 우, 좌(1,2,3,4) //상하좌우가 헷갈리지 않고 좋다
	static final int UP=1; static final int DOWN=2; static final int LEFT=3; static final int RIGHT=4;
	static int dy[] = {0,-1,1,0,0}; static int dx[] = {0,0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		how_many = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new shark[n+1][m+1];
		//상어 숫자만큼 정보 받기
		for(int t=1;t<=how_many;t++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int r = Integer.parseInt(stringTokenizer.nextToken());
			int c = Integer.parseInt(stringTokenizer.nextToken());
			int s = Integer.parseInt(stringTokenizer.nextToken());
			int d = Integer.parseInt(stringTokenizer.nextToken());
			int big =Integer.parseInt(stringTokenizer.nextToken());
			shark temp = new shark(s, d, big);
			map[r][c] = temp;
		}
		
		//게임을 시작한다.
		int fisher_man_index= 0;
		int deep = 1;
		int big_total=0;
		while(fisher_man_index<m) {
//			System.out.println();
//			for(int i=1;i<=n;i++) {
//				for(int j =1;j<=m;j++) {
//					System.out.printf("%5s ",map[i][j]);
//				}
//				System.out.println();
//			}
			fisher_man_index++; //오른쪽 이동
			deep = 1; //깊이 1부터 ~ n까지
			//땅에서부터 젤 가까운 상어 찾기
			while(deep<=n) {
//				System.out.println(deep+","+fisher_man_index);
				if(map[deep][fisher_man_index]!=null) {//찾으면 break;
					break;
				}
				deep++;
			}
			
			//상어를 잡고 null로 만든다.
			if(deep<=n) { //찾았을 때만
				big_total += map[deep][fisher_man_index].big;
				map[deep][fisher_man_index] = null;
			}
			
			//상어들이 이동한다.
			map_temp=new shark[n+1][m+1]; //임시 값 저장
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=m;j++) {
					if(map[i][j] !=null) {//상어를 발견하면 이동시킨다. map_temp에
						shark temp = map[i][j];
						//방향이 상하이면 n-1
//						int dir = temp.d; //따로 저장하는 것보다 객체에 있는 값을 바로 갱신하는게 실수 안하기 좋다.
						int ny=i; int nx =j;
						if(temp.d==1||temp.d==2) {// 상하이면 ny만
							int time = (temp.s)%(2*(n-1));
							ny = i;
							while(time>0) {
								ny+=dy[temp.d]; 
								if(ny<=0||ny>n) {
									temp.d = temp.d==UP? DOWN:UP; //up이면 down으로 바꿔준다.
//									temp.d = ((temp.d+1)%2==0? 2:1);
									ny+=2*dy[temp.d];
									
								}
								time--;
							}
						}
						//좌우 이면 m-1, nx만
						else {
							int time = (temp.s)%(2*(m-1));
							nx = j;
							while(time>0) {
								nx+=dx[temp.d];
								if(nx<=0||nx>m) {
									//right이면 left로 바꾸고 left이면 right으로 바꾼다.
									temp.d = temp.d==RIGHT? LEFT:RIGHT;//방향 전환은 이렇게 짜는게 더 직관적인 듯하다.
//									temp.d = ((temp.d+1)%2==0? 4:3); 
									nx+=2*dx[temp.d];
								}
								time--;
							}
						}
						//이동 시키고 이제 이미 있는지 확인
						if(map_temp[ny][nx]!=null) { 
							//있으면 이미 있는 것과 크기 비교
							if(map_temp[ny][nx].big<temp.big) {
								//갱신할게 크면 갱신, 아니면 그냥 넘어감
								map_temp[ny][nx] = temp;
							}
						}
						else {//없으면 그냥 바로 넣기
							map_temp[ny][nx] = temp;
						}
					}//not null
				} //for j
			} //for i 
			//끝났으면 원래 map으로 복사해준다.
			for(int i=1;i<=n;i++) {
				map[i] = map_temp[i].clone();
			}
		}//while
		System.out.println(big_total);
		
	}//main
	
	static class shark{
		int s,d,big;

		public shark(int s, int d, int big) {
			super();
			this.s = s;
			this.d = d;
			this.big = big;
		}

		//클래스 자체를 출력하는 것은 힘들다 그래서 대표적인 요소만 출력하게 하는 것도 도움이 될듯!
		@Override
		public String toString() { 
			return Integer.toString(big);
		}
	}
}
