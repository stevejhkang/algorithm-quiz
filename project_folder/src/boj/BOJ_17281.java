package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author junhukang
 * @time 2020. 2. 3. 오전 12:16:33
 * @category dfs 완전탐색
* @problem_description n이닝동안 게임을 진행, 한 이닝에 3아웃발생시 이닝 종료, 타순은 이닝이 변경되어도 순서유지
* 2이닝에 6번타자 마지막이면 3이닝은 7번부터 1,2,3,4마다 1,2,3,4루식 진루 아웃은 모두 진루 못하고 아웃++
* **1번타자(0번)는 무조건 4번(3번타자다) 나머지를 결정하고 가장 많은 득점을 찾는 타순을 찾아 가장 고득점 점수 출력
* @solving_description 
*/
public class BOJ_17281 {
	static int[][] player; //player들의 이닝당 결과 받는 배열 [i][j] i가 플레이어 j가 이닝
	static int[] visit=new int[9]; //플레이어를 이미 선택했는지 판단하는 배열
	static int n; //이닝 수
	static ArrayList<int[]> li = new ArrayList<>(); //타순을 결정한 배열
	static int max;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt(); //이닝 수
		player = new int[9][n]; //9플레이어 n이닝
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 9; j++) {
				player[j][i] = scanner.nextInt(); //j플레이어 i이닝
			}
		}
		max = Integer.MIN_VALUE;
		
		recursion(0, 9);
		
		System.out.println(max);
	}

	static void recursion(int r, int m) {
		if (r == m - 1) {
			// 0번 선수를 3번인덱스에 껴넣는다.
			int[] temp = new int[n];
			for (int i = 0; i < player[0].length; i++) {
				temp[i] = player[0][i];
			}
			li.add(3, temp); //인덱스3번에 0번선수 추가
			// 여기서 연산을 시작한다.
//			System.out.println("");
//			for(int i=0;i<li.size();i++) {
//				for(int j=0;j<n;j++) {
//					System.out.print(li.get(i)[j]+" ");
//				}
//				System.out.println("");
//			}
			int now_player = -1; //처음 시작할때 +1하므로 -1부터 시작
			int sum = 0; //n이닝 돌렸을때 점수
			//n이닝까지 돌린다.
			for (int i = 0; i < n; i++) { 
				int out = 0;
				int[] mound = new int[3]; //마운드 표시 이닝마다 초기화
				while (out < 3) {
					now_player = (now_player + 1) % 9; // 마지막 아웃된 선수 다음이므로

					int[] arr = li.get(now_player); // 그 선수의 이닝별 결과
					// arr[i]=그 선수의 이닝별 결과 중 i이닝 결과
					if (arr[i] == 0) { //0이면 아웃
						out++;
						//쓰리아웃 체인지
						if (out == 3)
							break;
						//쓰리아웃 아니면 타자만 올라가서 바꿔준다.
						continue;
					}
					for (int k = 2; k >= 0; k--) {
						if (mound[k] != 0) { // 0,1,2 마운드에 사람이 있으면
							if (arr[i] + k >= 3) { // 현재 선수가 친 결과를 더했을때 3이상이되면 홈에 들어온 것이므로 sum++해주고 0처리한다.
								sum++;
								mound[k] = 0;
							} else { // 안넘으면 그냥 arr[i] 앞으로 보내준다.	
								mound[k] =1;
							}
						}
					}
					if (arr[i] == 4) {// 홈런이면 현재 타자도 ++
						sum++;
					}

				}
			}
			if (max < sum) {
				max = sum;
			}
			li.remove(3); // 4번타자인 0번선수 제거
			return;
		}
		//0번 선수는 인덱스 3에 들어갈 것이므로 1번 선수부터 8번선수까지 8명의 순서를 바꿔준다.
		for (int i = 1; i < 9; i++) {
			if (visit[i] == 0) {
				visit[i] = 1; //방문표시하고
				int[] temp = new int[n]; //해당 플레이어의 이닝별 결과를 리스트에 넣어준다.
				for (int j = 0; j < n; j++) {
					temp[j] = player[i][j];
				}
				li.add(temp);
				recursion(r + 1, m);
				li.remove(r);
				visit[i]=0;
			}
		}
	}
}
