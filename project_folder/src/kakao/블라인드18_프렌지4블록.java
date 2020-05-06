package kakao;
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
 * 1. 처음부터 계속 쭉쭉쭉 내려가는 것을 더이상 없을 때까지 반복을 해야되나?
 * 
 * 배운점:
 * 1. 퍼즐같은 문제는 맞춘 후에 올리거나 내려야 되는 경우가 많은데 보통 이 경우에는 하나씩 올리거나 내리는 게 정석이다.
 * 
 * 
 */

public class 블라인드18_프렌지4블록 {


	public static void main(String[] args) {
		int m = 4; //높이
		int n = 5; //넓이
		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

//		int m = 6;
//		int n = 6;
//		String[] board = { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" };

		System.out.println(new Solution().solution(m, n, board));
	}
	static class Solution {

		private static int[][] visit;
		private static char[][] map;
		static int[] dy = { 1, 1, 0 };
		static int[] dx = { 0, 1, 1 };
		public int solution(int m, int n, String[] board) {
			int answer = 0;

			boolean is_change = false;
			
			map = new char[m+1][n+1];
			//아래로 내려주기 위해 0번째 인덱스를 만들어 준다.
			//빈칸으로 채운다.
			for(int i=0;i<=m;i++) {
				Arrays.fill(map[i],' ');
			}
			//그리고 map에 채워준다.
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					char c = board[i].charAt(j);
					map[i+1][j+1] =c; //1,1부터 시작
				}
			}
//			for (int i = 1; i <= m; i++) {
//				for (int j = 1; j <= n; j++) {
//			
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			//지운 개수를 저장하는 변수
			int remove_cnt=0;
			//더이상 셋트가 되어 사라지는게 없을때까지 무한반복
			while (true) {
				is_change=false;
				visit = new int[m+1][n+1];
				for (int i = 1; i <= m; i++) {
					for (int j = 1; j <= n; j++) {
						char c = map[i][j];
						
						//빈칸이 아닌 알파벳은 전부 오른쪽, 아래, 오른쪽아래 대각선이 같은지 확인한다.
						map[i][j] =c;
						if(c==' ') continue;

						int move_cnt = 1;
						
						for(int dir=0;dir<3;dir++) {
							int ny = i+dy[dir];
							int nx = j+dx[dir];
							
							//범위
							if(ny>m||nx>n) break;
							if(c==map[ny][nx]) {
								move_cnt++;
							}
							else {
								break;
							}
						}
						if(move_cnt==4) { //4개 모두 다 같으면 지워야할것으로 표시한다.
							visit[i][j] = 1;
							for(int dir=0;dir<3;dir++) {
								int ny = i+dy[dir];
								int nx = j+dx[dir];
								
								visit[ny][nx]=1;
							}
							is_change=true;
						}
						
					} // for j
				} // for i
				if (!is_change) {
					break;
				}
				else { //바꿀게 있다면 배열 전체 확인하면서 쭉 내려준다. 1이면 col을 1씩 내려준다.
					for(int i=1;i<=m;i++) {
						for(int j =1;j<=n;j++) {
							if(visit[i][j]==1) { //col별로 row 1씩 내려줘야한다.
								remove_cnt++;
								for(int k = i;k>=1;k--) {
									map[k][j] = map[k-1][j];
								}
							}
						}
					}
				}
//				System.out.println();
//				for (int i = 1; i <= m; i++) {
//					for (int j = 1; j <= n; j++) {
//						System.out.print(map[i][j]+" ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}//while
			answer= remove_cnt;
			return answer;
		}
	}
}
