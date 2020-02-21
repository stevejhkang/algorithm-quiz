package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 21. 오후 3:56:48
 * @category 완전탐색과 구현
* @problem_description 지도를 만드는 문제
* @solving_description i,j,d1,d2를 전부 범위 내에서 전부 해보면서
* 범위를 넘지 않으면 맵을 만들어야한다. 문제에 조건이 자세하게 주어졌는데
* 그 순서대로
* 이를 이용해서 5구역의 경계선을 만들고 그 안을 5를 채워주고 나머지를
* 범위내에 해당하는 것의 선거구번호를 해주면 된다.
*/
public class BOJ_17779 {
	private static int[][] map;
	private static int n;
	private static int[][] visit;
	private static int[] cnt;
	private static int total_min;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bReader.readLine());
		map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		} // 기본 입력
		visit = new int[n + 1][n + 1];
		//선거구 개수 체크 배열
		cnt= new int[6];

		total_min=Integer.MAX_VALUE;
		// 2,1에서부터 n-1,1까지
		for (int i = 1; i <= n; i++) { // i가 x세로
			for (int j = 1; j <= n; j++) { // j가 y가로
				for (int d1 = 1; d1 <= n; d1++) {
					for (int d2 = 1; d2 <= n; d2++) {
						// 범위 안에 들면
						if (1 <= i && i + d1 + d2 <= n && 1 <= j - d1 && j + d2 <= n) {
							// map을 만들어 보자
							cnt=new int[6];
							visit= new int[n+1][n+1];
							setBoundary(i, j, d1, d2);
							setMap(i, j, d1, d2);
							int min = Integer.MAX_VALUE; int max = Integer.MIN_VALUE;
//							boolean flag = true;
							cnt=new int[6];
							
							for(int q=1;q<=n;q++) {
								for(int w=1;w<=n;w++) {
									if(visit[q][w]==1) {
										cnt[1]+=map[q][w];
									}
									if(visit[q][w]==2) {
										cnt[2]+=map[q][w];
									}
									if(visit[q][w]==3) {
										cnt[3]+=map[q][w];
									}
									if(visit[q][w]==4) {
										cnt[4]+=map[q][w];
									}
									if(visit[q][w]==5) {
										cnt[5]+=map[q][w];
									}
								}
							}
//							System.out.println(total_min);
							for(int k=1;k<6;k++) {
								
								if(cnt[k]==0)
									break;
								if(min>cnt[k]) {
									min=cnt[k];
								}
								if(max<cnt[k]) {
									max=cnt[k];
								}
								
								
							}
							if(total_min>max-min) {
								total_min=(max-min);
							}
//							System.out.println(max-min);
//							System.out.println(Arrays.toString(cnt));
//							System.out.println(total_min);
						}
					}
				}
			}
		}//for i
		System.out.println(total_min);
	}// main

	static void setBoundary(int x, int y, int d1, int d2) {
		visit[x][y] = 5;
		int adder1 = 0;
		int adder2 = 0;

		while (++adder1 <= d1) {
			visit[x + adder1][y - adder1] = 5;// 왼쪽으로 내려감
			cnt[5]+=map[x + adder1][y - adder1] ;
		}
			
		while (++adder2 <= d2) {
			visit[x + adder2][y + adder2] = 5; // 오른쪽으로 내려감
			cnt[5]+=map[x + adder2][y + adder2] ;
		}

		adder1 = 0;
		adder2 = 0;
		while (++adder2 <= d2) {
			visit[x + d1 + adder2][y - d1 + adder2] = 5; //오른쪽으로 내려감
			cnt[5]+=map[x + d1 + adder2][y - d1 + adder2] ;
		}
		while (++adder1 <= d1) {
			visit[x + d2 + adder1][y + d2 - adder1] = 5; //왼쪽으로 내려감
			cnt[5]+=map[x + d2 + adder1][y + d2 - adder1];
		}

		// 쭉내려가면서 
		for (int r = 1; r <= n; ++r) {
			int left = -1;
			int right = -1;

			//왼쪽부터 탐색하면서 5를 처음 발견하면 그것을 왼쪽으로 설정하고
			int idx = 1; 
			while (idx <= n) {
				if (visit[r][idx] == 5) {
					left = idx;
					break;
				}
				idx++;
			}

			//오른쪽부터 탐색하면서 5를 처음 발견하면 그것을 오른쪽으로 설정하고
			idx = n;
			while (idx >= 0) {
				if (visit[r][idx] == 5) {
					right = idx;
					break;
				}
				idx--;
			}

			//왼쪽부터 오른쪽까지 5로 처리해준다.
			if (left != right) {
				for (int i = left; i < right; ++i) {
					visit[r][i] = 5;
					cnt[5]+=map[r][i];
				}
					
			}
		}
	}// setBoundary

	static void setMap(int x, int y, int d1, int d2) {
		for (int i = 1; i <= n; i++) { // i가 x세로
			for (int j = 1; j <= n; j++) { // j가 y가로
				if(visit[i][j]!=0) continue;
				if (1 <= i && i < x + d1 && 1 <= j && j <= y) { // 1의 범위 일때
					visit[i][j] = 1;
					cnt[1]+=map[i][j];
				} else if (1 <= i && i <= x + d2 && y < j && j <= n) { // 2의 범위 일때
					visit[i][j] = 2;
					cnt[2]+=map[i][j];
				} else if (x + d1 <= i && i <= n && 1 <= j && j < y - d1 + d2) {
					visit[i][j] = 3;
					cnt[3]+=map[i][j];
				} else if (x + d2 < i && i <= n && y - d1 + d2 <= j&& j <= n) {
					visit[i][j] = 4;
					cnt[4]+=map[i][j];
				}

			}
		} // for i
//		for (int i = 1; i <= n; i++) { // i가 x세로
//			for (int j = 1; j <= n; j++) { // j가 y가로
//
//			}
//		}
//		System.out.println();
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= n; j++) {
//				System.out.print(visit[i][j] + " ");
//			}
//			System.out.println();
//		}
	}// setMap

//	static void initMap() {
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= n; j++) {
//				visit[i][j] = 5;
//			}
//		}
//	}
}