package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 2. 오후 4:10:08
 * @category @problem_description 9종류의 카드 핸드를 출력한다. SDHC A123456789TJQK
 * 
 * @solving_description a,1,2,3,4,5,6,이런식으로 이걸 다 배열에 저장해놓는다. 4*13 1. 스트레이트 플러쉬:
 *                      같은 슈트에 (랭크)값이 연속적 5. 스트레이트: 다른 슈트(종류)가 섞여있으며 값이 모두
 *                      연속적이다.
 * 
 *                      이거는 엮을 수 있을 것 같음 4. 플러쉬: 5장이 모두 동일한 슈트(종류) 2. 포오브어카인드:
 *                      다섯장 중 4개의 값이 같다. 3. 풀 하우스: 3장의 동일한 값, 다른 값이 동일한 2장 6.
 *                      쓰리오브어카인드: 동일한 값이 3장
 * 
 *                      7. 투페어: 동일한 값 2장씩, 2개 랭크이다. 8. 원페어: 동일한 값이 2장이다. 9.
 *                      하이카드: 나머지
 */

public class SWEA_9760 {
	private static int[][] cards;
	static final int S = 0;
	static final int D = 1;
	static final int H = 2;
	static final int C = 3;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer;
		end:for (int tc = 1; tc <= T; tc++) {
			cards = new int[4][14];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			while (stringTokenizer.hasMoreTokens()) {
				String s = stringTokenizer.nextToken();
				// S 0 D 1 H 2 C 3
				char suit = s.charAt(0);
				System.out.println(suit);
				String rank = s.substring(1);
				int idx = -1;
				if (suit == 'S') {
					idx = 0;
				} else if (suit == 'D') {
					idx = 1;
				} else if (suit == 'H') {
					idx = 2;
				} else if (suit == 'C') {
					idx = 3;
				}

				if (rank.equals("A")) {
					rank = "1";
				} else if (rank.equals("T")) {
					rank = "10";
				} else if (rank.equals("J")) {
					rank = "11";
				} else if (rank.equals("Q")) {
					rank = "12";
				} else if (rank.equals("K")) {
					rank = "13";
				}
				System.out.println(idx + ", " + rank);
				cards[idx][Integer.parseInt(rank)] = 1;
			} // while
			for (int i = 0; i < 4; i++) {
				System.out.println(Arrays.toString(cards[i]));
			}

			// 1. Straight Flush: 같은 슈트에 (랭크)값이 연속적
			straight_flush: for (int i = 0; i < 4; i++) {
				for (int j = 1; j <= 10; j++) {
					boolean straight_flush = true;
					if (j == 10) {
						for (int k = 0; k < 4; k++) {
							if (cards[i][j + k] != 1) {
								straight_flush = false;
								break;
							}
						}
						if (cards[i][1] != 1) {
							straight_flush = false;
							break;
						}
					}
					for (int k = 0; k < 5; k++) {
						if (cards[i][j + k] != 1) {
							straight_flush = false;
							break;
						}
					} // for k
					if (straight_flush) {
						System.out.println("Straight Flush");
						break end;
					}
				} // for j
			}
			
			//2. 4오브 카인드: 값 모두 같은
			int cnt=0;
			Four:for(int i=1;i<=13;i++) {
				if(cards[0][i]==1&&cards[1][i]==1&&cards[2][i]==1&&cards[3][i]==1) {
					System.out.println("Four of a Kind");
					break end;
				}
			}
			//3. 풀하우스: 3장 동일값 2장의 동일값
			for(int i=1;i<=13;i++) {
				int result =cards[0][i]+cards[1][i]+cards[2][i]+cards[3][i];
				int sum=0;
				if(result==3||result==2) {
					sum+=result;
				}
				if(sum==5) {
					System.out.println("Full House");
					break end;
				}
			}
			//4. flush 모두 같은 색
			Flush:for(int j=0;j<4;j++) {
				int sum=0;
				for(int i=1;i<=13;i++) {
					sum+=cards[j][i];
				}
				if(sum==5) {
					System.out.println("Flush");
					break end;
				}
			}
			// 5. 스트레이트: 다른 슈트(종류)가 섞여있으며 값이 모두 연속적이다.
			straight: for (int j = 1; j <= 10; j++) {
				boolean straight = true;
				if (j == 10) {
					for (int k = 0; k < 4; k++) {
						if ((cards[0][j + k] == 1 || cards[1][j + k] == 1 || cards[2][j + k] == 1
								|| cards[3][j + k] == 1) == false) { // 적어도 하나가 1이 아니면 false;
							straight = false;
							break;
						}
					} // for k
					if ((cards[0][1] == 1 || cards[1][1] == 1 || cards[2][1] == 1
							|| cards[3][1] == 1) == false) {
						straight = false;
						break;
					}
				}
				for (int k = 0; k < 5; k++) {
					if ((cards[0][j + k] == 1 || cards[1][j + k] == 1 || cards[2][j + k] == 1
							|| cards[3][j + k] == 1) == false) { // 적어도 하나가 1이 아니면 false;
						straight = false;
						break;
					}
				} // for k
				if (straight) {
					System.out.println("Straight Flush");
					break end;
				}
			}//straight
			//6. Three of a kind: 동일한 값이 3장
			cnt=0;
			for(int i=1;i<=13;i++) {
				int result =cards[0][i]+cards[1][i]+cards[2][i]+cards[3][i];
				if(result==3) {
					System.out.println("Three of a Kind");
					break end;
				}
			}
			//7. Two Pair: 동일 값이 2장씩 두개 랭크 2,6,6,3,3
			for(int i=1;i<=13;i++) {
				int result =cards[0][i]+cards[1][i]+cards[2][i]+cards[3][i];
				int sum=0;
				if(result==2) {
					sum+=result;
				}
				if(sum==4) {
					System.out.println("Two Pair");
					break end;
				}
			
			}
			//8. One Pair: 동일 값이 1개
			for(int i=1;i<=13;i++) {
				int result =cards[0][i]+cards[1][i]+cards[2][i]+cards[3][i];
				int sum=0;
				if(result==2) {
					sum+=result;
				}
				if(sum==2) {
					System.out.println("One Pair");
					break end;
				}
			
			}
			//9. 나머지는 High card
		} // tc
	}
}
