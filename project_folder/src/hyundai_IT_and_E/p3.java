package hyundai_IT_and_E;

import java.util.ArrayList;
import java.util.Arrays;

public class p3 {
	public static void main(String[] args) {
//		int[][] board = {{2,4,8,2},{2,2,2,2},{0,4,2,4},{2,2,2,4}};
		int[][] board = {{2,4,8,2},{2,4,0,0},{2,0,0,0},{2,0,2,0}};
		
		System.out.println(new Solution().solution(board));
		
	}
	static class Solution{
		private static int n;
		private static String direction;
		private static int[][] map= {{0,0,0,0,0,0},{0,2,4,8,2,0},{0,2,2,2,2,0},{0,0,4,2,4,0},{0,2,2,2,4,0},{0,0,0,0,0,0}};
		static int[][] copy_map = new int[6][6];
		private static ArrayList<Integer> order_list; // 상하좌우
		private static int max;
		int solution(int[][] board) {
			int answer = Integer.MAX_VALUE;
			
			for(int i=0;i<=5;i++) {
				for(int j=0;j<=5;j++) {
					if(i==0||j==0||i==5||j==5) { //외곽지역은 0으로
						map[i][j]=0;
					}
					else { //안지역은 board로 채워준다.
						map[i][j] = board[i-1][j-1];
					}
				}
			}
			n=4;
			max=Integer.MIN_VALUE;
			order_list = new ArrayList<Integer>(); //순서 조합을 담는 리스트
			
			dfs(0); //조합을 시작한다.
			
			return max;
		}


		static void dfs(int r) {
			if (r == 5) {
				//복사를 해줘야됨.
				for(int i=0;i<5;i++) {
					copy_map[i]=map[i].clone();
				}
				//5번 게임하고
				for (int i = 0; i < 5; i++) {
					int index = order_list.get(i); //5번게임한다.
					game(index);
				}

//				//map을 탐색하면서 최댓값을 찾는다.
				for(int i=0;i<5;i++) {
//					System.out.println(Arrays.toString(copy_map[i]));
					for(int j=0;j<5;j++) {
						max=Math.max(max, copy_map[i][j]);
					}
				}
//				System.out.println(order_list);
				return;
			}
			for (int i = 0; i < 4; i++) {
				order_list.add(i);
				dfs(r + 1);
				order_list.remove(r);
			}
		}

		static void game(int index) {
			
			if (index==0) { // i==0
				for (int j = 1; j <= n; j++) {
					int i = 1;
					while (i < n) { // i==n일땐 합쳐질 수 없으므로 n-1까지만
						if (copy_map[i][j] == 0) { // 0인경우
							int idx = i;
							boolean can = false;
							for (; idx <= n; idx++) {
								if (copy_map[idx][j] != 0) {
									can = true;
								}
							}
							if (can) {
								move(0, i, j);
							} else {
								i++;
							}

						} else if (copy_map[i + 1][j] == 0) { // 다음이 i+1이라면
							// i+1부터 n까지 0이 아닌 수가 있는지 확인한다
							// 있으면 해주고 없으면 옮기지말고 i++해주고 끝낸다.
							int idx = i + 1;
							boolean can = false;
							for (; idx <= n; idx++) {
								if (copy_map[idx][j] != 0) {
									can = true;
								}
							}
							if (can) {
								move(0, i + 1, j);
							} else {
								i++;
							}
						} else if (copy_map[i][j] != 0 && copy_map[i][j] == copy_map[i + 1][j]) { // 같은 경우
							copy_map[i][j] += copy_map[i][j];
							// i+1부터 위로 쭉 땡긴다.
							move(0, i + 1, j);
							// 합쳐졌으므로 아랫것을 확인한다.
							i++;
						} else {
							i++;
						}
					} // while
				}
			} else if (index==1) { // i==n
				for (int j = 1; j <= n; j++) {
					int i = n;
					while (i > 1) { // i==1일땐 합쳐질 수 없으므로 1까지만
						if (copy_map[i][j] == 0) { // 0인경우
							int idx = i;
							boolean can = false;
							for (; idx >= 1; idx--) {
								if (copy_map[idx][j] != 0) {
									can = true;
								}
							}
							if (can) {
								move(1, i, j);
							} else {
								i--;
							}

						} else if (copy_map[i - 1][j] == 0) { // 다음이 i+1이라면
							// i+1부터 n까지 0이 아닌 수가 있는지 확인한다
							// 있으면 해주고 없으면 옮기지말고 i++해주고 끝낸다.
							int idx = i - 1;
							boolean can = false;
							for (; idx >= 1; idx--) {
								if (copy_map[idx][j] != 0) {
									can = true;
								}
							}
							if (can) {
								move(1, i - 1, j);
							} else {
								i--;
							}
						} else if (copy_map[i][j] != 0 && copy_map[i][j] == copy_map[i - 1][j]) { // 같은 경우
							copy_map[i][j] += copy_map[i][j];
							// i+1부터 위로 쭉 땡긴다.
							move(1, i - 1, j);
							// 합쳐졌으므로 아랫것을 확인한다.
							i--;
						} else {
							i--;
						}
					}
				}
			} else if (index==2) { // i==0
				for (int i = 1; i <= n; i++) {
					int j = 1;
					while (j < n) { // i==n일땐 합쳐질 수 없으므로 n-1까지만
						if (copy_map[i][j] == 0) { // 0인경우
							int idx = j;
							boolean can = false;
							for (; idx <= n; idx++) {
								if (copy_map[i][idx] != 0) {
									can = true;
								}
							}
							if (can) {
								move(2, i, j);
							} else {
								j++;
							}

						} else if (copy_map[i][j + 1] == 0) { // 다음이 i+1이라면
							// i+1부터 n까지 0이 아닌 수가 있는지 확인한다
							// 있으면 해주고 없으면 옮기지말고 i++해주고 끝낸다.
							int idx = j + 1;
							boolean can = false;
							for (; idx <= n; idx++) {
								if (copy_map[i][idx] != 0) {
									can = true;
								}
							}
							if (can) {
								move(2, i, j + 1);
							} else {
								j++;
							}
						} else if (copy_map[i][j] != 0 && copy_map[i][j] == copy_map[i][j + 1]) { // 같은 경우
							copy_map[i][j] += copy_map[i][j];
							// i+1부터 위로 쭉 땡긴다.
							move(2, i, j + 1);
							// 합쳐졌으므로 아랫것을 확인한다.
							j++;
						} else {
							j++;
						}
					} // while
				}
			} else if (index==3) { // i==n
				for (int i = 1; i <= n; i++) {
					int j = n;
					while (j > 1) { // i==1일땐 합쳐질 수 없으므로 1까지만
						if (copy_map[i][j] == 0) { // 0인경우
							int idx = j;
							boolean can = false;
							for (; idx >= 1; idx--) {
								if (copy_map[i][idx] != 0) {
									can = true;
								}
							}
							if (can) {
								move(3, i, j);
							} else {
								j--;
							}

						} else if (copy_map[i][j - 1] == 0) { // 다음이 i+1이라면
							// i+1부터 n까지 0이 아닌 수가 있는지 확인한다
							// 있으면 해주고 없으면 옮기지말고 i++해주고 끝낸다.
							int idx = j - 1;
							boolean can = false;
							for (; idx >= 1; idx--) {
								if (copy_map[i][idx] != 0) {
									can = true;
								}
							}
							if (can) {
								move(3, i, j - 1);
							} else {
								j--;
							}
						} else if (copy_map[i][j] != 0 && copy_map[i][j] == copy_map[i][j - 1]) { // 같은 경우
							copy_map[i][j] += copy_map[i][j];
							// i+1부터 위로 쭉 땡긴다.
							move(3, i, j - 1);
							// 합쳐졌으므로 아랫것을 확인한다.
							j--;
						} else {
							j--;
						}
					}
				}
			}
		}
		static void move(int index, int i, int j) {
			if (index==0) {
				// i부터 n까지 쭉 올린다.
				for (; i <= n; i++) {
					copy_map[i][j] = copy_map[i + 1][j];
				}
			} else if (index==1) {
				// i부터 1까지 쭉 내린다.
				for (; i >= 1; i--) {
					copy_map[i][j] = copy_map[i - 1][j];
				}
			} else if (index==2) {
				// j부터 n까지 쭉 왼쪽으로 옮긴다.
				for (; j <= n; j++) {
					copy_map[i][j] = copy_map[i][j + 1];
				}
			} else {
				// j부터 1까지 쭉 오른쪽으로 이동시킨다.
				for (; j >= 1; j--) {
					copy_map[i][j] = copy_map[i][j - 1];
				}
			}
		} // move
	}
}
