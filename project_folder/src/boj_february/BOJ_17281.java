package boj_february;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author junhukang
 * @time 2020. 2. 3. 오후 11:48:42
 * @category 재귀를 통한 완전탐색
* @problem_description 
* @solving_description 9명의 선수를 0번 선수를 인덱스 3번 타자로 넣고 나머지는 완전탐색하여 순서를 정해서 최대 점수를 계산한다.
* 포인트는 다음이닝은 전에 아웃당한 타자 다음타자가 가야되고, 출루한 주자들이 결과에 따라 점수를 획득하는 방법을 고민해보아야 한다.
*/
public class BOJ_17281 {
	static int n;
	static int[][] play;
	static ArrayList<int[]> order_list = new ArrayList<>();
	static boolean visit[] = new boolean[9];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		play = new int[9][n];
		for (int i = 0; i < n; i++) { // 이닝 별로 각 선수들의 결과를 저장
			for (int j = 0; j < 9; j++) {
				play[j][i] = scanner.nextInt();
			}
		}
		set_order(0);
		System.out.println(max);
	}

	static void set_order(int r) { // 9개에서 8개를 뽑아서 정렬, 중복 안됨
		if (r == 8) {
			// 0번 선수를 인덱스 3에 배치시킨다.
			int[] temp = new int[n];
			for (int i = 0; i < n; i++) {
				temp[i] = play[0][i];
			}
			order_list.add(3,temp);
			// 이제 경기시작
			int point = 0;
			int order = -1;
//			File file = new File("res\\test.txt");
//			try {
//				FileWriter fw = new FileWriter(file,true);
//				fw.write("\n");
//				fw.close();
//			}
//			catch (IOException e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			System.out.println("");
			for (int i = 0; i < n; i++) {
				int out = 0;
				int mound[] = new int[3];
				while (true) {
					order = (order + 1) % 9;
					int result = order_list.get(order)[i];
//					System.out.print(result+" ");
//					try {
//						FileWriter fw = new FileWriter(file,true);
//						fw.write(result+" ");
//						fw.close();
//					}
//					catch (IOException e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
					if (result == 0) {// 현재 순서의 i이닝 선수의 결과가 아웃이면
						out++;
						if (out == 3) {
							break;
						}
						continue;
					}
					// 1,2,3,4인경우 1,2,3루에 있는 사람을 옮겨준다.
					for (int j = 2; j >= 0; j--) {
						// 해당 마운드에 사람이 있으면
						if (mound[j] != 0) {
							if (j + result >= 3) {// 해당 마운드에 있는 사람이 홈에 들어가면 +1해주고 초기화
								point++;
								mound[j] = 0;
							} else {// 홈에 못들어가면
								mound[j + result] = 1;// 이동시키고
								mound[j] = 0; // 초기화
							}
						}
						
					}
					if (result == 4) {// 4이면 현재 주자도 점수 얻으므로
						point++;
					}else {
						mound[result-1]=1;
					}
				}
			}
			if(max<point) {
				max=point;
			}

			// 0번 선수를 인덱스 3에서 뺀다.
			order_list.remove(3);
			return;
		}
		// 0번은 이미 순서 정해져서 1번선수부터 해야됨
		for (int i = 1; i < 9; i++) {
			if (!visit[i]) {
				visit[i] = true;
				int[] temp = new int[n];
				for (int j = 0; j < n; j++) {
					temp[j] = play[i][j];
				}
				order_list.add(temp);
				set_order(r + 1);
				order_list.remove(r);
				visit[i] = false;
			}
		}
	}
}
