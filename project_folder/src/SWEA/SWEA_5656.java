package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 10. 오후 1:08:43
 * @category 순열인듯
* @problem_description 게임규칙은 다음과 같다.
* 1. 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨뜨릴 수 있다.
* 2. 벽돌은 숫자 1~9로 표현되며, 구슬이 명중한 벽돌은 상하좌우로 벽돌에 적힌 숫자-1 칸 만큼 
* 같이 제거된다.
* 부딪혀서 빈 공간이 생길 경우 벽돌은 밑으로 떨어지게 된다.
* N개의 벽돌을 떨어뜨려 최대한 많은 벽돌을 제거하려고 한다. 
* NWH가 주어졌을 때 남은 벽돌의 개수를 구하라
* @solving_description 
* 0부터 w-1까지 순서가 있게 선택을 한 후 떨어뜨려서 남은 벽돌개수를 헤아린다
*/

public class SWEA_5656 {
	private static int n;
	private static int w;
	private static int h;
	private static int[][] map;
	private static boolean[] visit;
	private static ArrayList<Integer> al;
	private static int cnt_block;
	private static int min;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	private static int[][] copy_map;
	private static int copy_cnt_block;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			w = Integer.parseInt(stringTokenizer.nextToken());
			h = Integer.parseInt(stringTokenizer.nextToken());
			
			map = new int[h][w];
			cnt_block=0;
			for(int i=0;i<h;i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for(int j=0;j<w;j++) {
					int a =Integer.parseInt(stringTokenizer.nextToken()); 
					map[i][j] = a;
					if(a!=0) {
						cnt_block++;
					}
				}
			}
			System.out.println(cnt_block);
			if(cnt_block==0) {
				System.out.println("#"+tc+" "+0);
				continue;
			}
			visit= new boolean[w];
			al = new ArrayList<Integer>();
			min= Integer.MAX_VALUE;
			permu(0);
			
			System.out.println("#"+tc+" "+min);
			
		}//for
	}//main
	static void permu(int r) {
		if(r==n) { //n개를 순서대로 선택했을 때 떨어뜨리기를 시작한다.
			drop_ball();
			return;
		}
		for(int i=0;i<w;i++) {
			al.add(i);
			permu(r+1);
			al.remove(r);
		}
	}//permu
	
	static void drop_ball() {
		copy_cnt_block=cnt_block;
		copy_map = new int[h][w];
		for(int i=0;i<h;i++) {
			copy_map[i] = map[i].clone();
		}
//		System.out.println(al);
		//순서대로 부순다.
		for(int i=0;i<al.size();i++) {
			int col = al.get(i); //구슬을 떨어뜨릴 열
			int row= 0;
			//0아닌 것을 만날때까지 아래로 이동
			while(row<h) {
				if(copy_map[row][col]!=0) {
					break;
				}
				row++;
			}
			if(row==h) { //부술게 없음
				return;
			}
			else {
				//주위 몇개를 부술지 생각한다.
				int a = copy_map[row][col];
				//부순다.
				
				if(a==1) {
					copy_map[row][col]=0;
					copy_cnt_block--;
				}
				else {
					break_block(row, col, a-1);
				}
				
			
			}
			//연쇄적으로 터지고 나서 공중에 떠있는건 아래로 내려준다.
			//여러 번 공중에 떨어질 수 있으므로 그냥 쭉내려간다.
			boolean flag = false;
			for(int i1=0;i1<w;i1++) {
				for(int j=0;j<h;j++) {
					if(copy_map[j][i1]!=0&&!flag) { //0이 아니고 flag false이면 트루로
						flag = true;
					}
					else if(copy_map[j][i1]==0&&flag) { //0이면서 트루이면 한칸씩 내려준다.
						//i부터 1까지
						for(int k=j;k>=1;k--) {
							copy_map[k][i1]= copy_map[k-1][i1];
						}
						
					}
				}
			}
		}//al.size
		
		//이제 남은 블럭 개수를 비교한다.
		System.out.println(copy_cnt_block+"=================================================");
		
//		System.out.println(copy_cnt_block);
		if(min>copy_cnt_block) {
			min =copy_cnt_block;
		}
		
		
	}//drop ball
	
	private static void break_block(int row, int col, int a) {
		// TODO Auto-generated method stub
		copy_map[row][col]= 0;
		copy_cnt_block--;
		if(copy_cnt_block<=0) return;
		for(int k=0;k<4;k++) {
			int cnt = a;
			int ny = row; int nx = col;
			while(cnt>0) {
				ny+=dy[k]; nx+=dx[k];
				//범위 벗어나면 부레이크
				if(ny<0||ny>=h||nx<0||nx>=w) break;
				
				//0이 아니면
				if(copy_map[ny][nx]!=0) {
					break_block(ny,nx, copy_map[ny][nx]-1);
				}
				cnt--;
			}
		}
		System.out.println();
		for(int k=0;k<h;k++) {
			System.out.println(Arrays.toString(copy_map[k]));
		}
		
		
	}//break block
}
