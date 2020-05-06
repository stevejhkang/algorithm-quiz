import java.util.Arrays;

import javax.management.loading.MLet;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 5. 오후 2:26:48
 * @category @level 3
 * @problem_description 2*2형태로 4개가 붙어있을 경우 사라지면서 점수를 얻는 게임
 * 
 * 
 *                      높이m, 폭n과 판의 배치정보 board가 들어온다. 2<=n,m<=30
 * 
 *                      모든 점에 대해서 실행을 한번하려면 1*30*30*4*30를 해주고 계속 있을 때까지 해주어야 한다.
 *                      900*30 = 27000*4 = 108000 완전탐색으로 풀어도 10만밖에 안된다.
 * 
 *                      첫 배치가 주어졌을 때, 지원지는 블록은 모두 몇개인지 판단하는 프로그램
 * @solving_description
 * 
 * 						1. 처음부터 계속 쭉쭉쭉 내려가는 것을 더이상 없을 때까지 반복을 해야되나?
 * 
 */

public class 카카오블라인드2018_프렌지4블록 {
	static int[] dy = { 1, 1, 0 };
	static int[] dx = { 0, 1, 1 };

	public static void main(String[] args) {
//		int m = 4; //높이
//		int n = 5; //넓이
//		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

		int m = 6;
		int n = 6;
		String[] board = { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" };

		System.out.println(new Solution().solution(m, n, board));
	}

	static class Solution {
		static int static_m;
		static int static_n;
		static String[] boards ;
		private static int[][] visit;
		
		public int solution(int m, int n, String[] board) {
			int answer = 0;
			static_m = m;
			static_n = n;
			System.out.println(static_m);
			System.out.println(static_n);
			boards = board.clone();
			boolean is_change = false;
			
			visit = new int[m][n];
			
			char[][] map = new char[m+1][n+1];
			for(int i=0;i<=m;i++) {
				Arrays.fill(map[i],' ');
			}
//			for (int i = 0; i < m; i++) {
//				for (int j = 0; j < n; j++) {
//					char c = board[i].charAt(j);
//					map[i+1][j+1] =c;
//				}
//			}
//			for (int i = 1; i <= m; i++) {
//				for (int j = 1; j <= n; j++) {
//			
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			int remove_cnt=0;
			while (true) {
				visit = new int[m][n];
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++) {
						char c = board[i].charAt(j);
						map[i+1][j+1] =c;
						boolean same = true;
						
						int move_cnt = move(i,j,0,0,c);
						
						if(move_cnt==3) {
							visit[i][j] = 1;
							is_change=true;
						}
					} // for j
				} // for i
				if (!is_change) {
					break;
				}
				else { //배열 전체 확인하면서 쭉 내려준다. 1이면 col을 1씩 내려준다.
					for(int i=0;i<m;i++) {
						for(int j =0;j<n;j++) {
							if(visit[i][j]==1) { //col별로 row 1씩 내려줘야한다.
								remove_cnt++;
								for(int k = i;k>=1;k--) {
									map[k][j] = map[k-1][j];
								}
							}
						}
					}
				}
				System.out.println();
				for (int i = 1; i <= m; i++) {
					for (int j = 1; j <= n; j++) {
				
						System.out.print(map[i][j]+" ");
					}
					System.out.println();
				}
				System.out.println();
			}//while
			answer= remove_cnt;
			return answer;
		}
		static int move(int y, int x, int same_cnt, int dir,char c) {
			if(dir==3) {
				return same_cnt;
			}
			//다음 것을 받아오고
			int ni = y + dy[dir];
			int nj = x + dx[dir];

			// 범위 체크
			if (ni >=static_m || nj >= static_n)
				return 0;

			int next_c = ((String) boards[ni]).charAt(nj);
			int cnt=0;
			if(next_c==c) {
				cnt = move(y, x, same_cnt+1, dir+1, c);
				if(cnt==3) {
					visit[ni][nj]= 1;
				}
				return cnt;
			}
			else 
				return 0;
		}
	}

}
