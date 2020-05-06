package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 6. 오후 6:05:17
 * @category DFS 완전탐색
 * @level 3
 * @problem_description
 * @solving_description
 */

public class BOJ_6987 {

	static int[] answer;
	static int[][] map;
	static int[][] test;
	// 모든 경기횟수에 따른 팀 인덱스를 담는 배열
	static int[] team1 = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	static int[] team2 = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };

	static void dfs(int tc, int games) {
		if (games == 15) {
			if (answer[tc] != 1) { // 이미 가능하다고 판별이 나지 않았으면

				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 3; j++)
						if (map[i][j] != test[i][j])
							return;
				}

				// 완전히 똑같다면 가능한 스코어
				answer[tc] = 1;
				return;
			} else
				return;
		}

		int t1 = team1[games];
		int t2 = team2[games];

		// 팀1이 이기면 팀2는 졌음
		test[t1][0]++;
		test[t2][2]++;
		dfs(tc, games + 1);
		test[t1][0]--;
		test[t2][2]--;

		// 팀1이 비기면 팀2도 비김
		test[t1][1]++;
		test[t2][1]++;
		dfs(tc, games + 1);
		test[t1][1]--;
		test[t2][1]--;

		// 팀1가 지면 팀2는 짐
		test[t1][2]++;
		test[t2][0]++;
		dfs(tc, games + 1);
		test[t1][2]--;
		test[t2][0]--;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		answer = new int[4];

		for (int tc = 0; tc < 4; tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			map = new int[6][3];
			test = new int[6][3];
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}

			dfs(tc, 0);
		}

		for (int i = 0; i < 4; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}
}