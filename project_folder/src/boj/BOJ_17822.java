package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17822 {
	private static int n;
	private static int m;
	private static int t;
	private static int[][] map;
	static int dy[] = { 1, 0, -1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken()); // 50
		m = Integer.parseInt(stringTokenizer.nextToken()); // 50
		t = Integer.parseInt(stringTokenizer.nextToken()); // 50
		map = new int[n + 1][m];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		// t 횟수만큼 명령을 받고 회전시킨다.
		for (int tc = 1; tc <= t; tc++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int x = Integer.parseInt(stringTokenizer.nextToken());// x: x의 배수의 판을 돌리고,
			int d = Integer.parseInt(stringTokenizer.nextToken());// d가 0시계 1반시계
			int k = Integer.parseInt(stringTokenizer.nextToken());// k몇칸 돌릴지
			// 회전시킨다. 시계방향을 ->로 반시계는 <-로
			for (int i = x; i <= n; i += x) {
				if (d == 0) { // ->
					int k_copy = k;
					while (k_copy > 0) {
						int temp = map[i][m - 1];
						for (int j = m - 2; j >= 0; j--) {
							map[i][j + 1] = map[i][j];
						}
						map[i][0] = temp;
						k_copy--;
					}
				} else if (d == 1) { // <-
					int k_copy = k;
					while (k_copy > 0) {
						int temp = map[i][0];
						for (int j = 1; j < m; j++) {
							map[i][j - 1] = map[i][j];
						}
						map[i][m - 1] = temp;
						k_copy--;
					}
				}
			} // for i
//			System.out.println("");
//			for(int i=1;i<=n;i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("-------------");
			
			
			
			// 인접한 것들 0으로 만들어준다. 숫자는 1<=숫자<=1000
			boolean entire_flag = false;
			double total =0; int cnt=0;
			for (int i = 1; i <= n; i++) {
				for (int j = 0; j < m; j++) {
					int a = map[i][j];
					if(a!=0) cnt++;
					total+=a;
					boolean flag = false;
					for (int q = 0; q < 4; q++) {
						int ni = i + dy[q];
						int nj = j + dx[q];
						if (ni <= 0 || ni > n)
							continue;
						if (nj == -1) {// m-1과 비교
							if (a!=0&&a == map[ni][m - 1]) {// 같으면 0으로
								flag = true;
								
								map[ni][m - 1] = 0;
							}
						} else if (nj == m) { // 0과 비교
							if (a!=0&&a == map[ni][0]) {
								flag = true;
								map[ni][0] = 0;
							}
						} else {
//							System.out.println(ni+","+nj);
							if (a!=0&&a == map[ni][nj]) {
								flag = true;
								map[ni][nj] = 0;
							}
						}
						if (flag) { // 인접한게 같은게 있으면 그것도 0으로 마지막에 만든다.
							map[i][j] = 0;
						}
						entire_flag = entire_flag||flag;
					} // for k
				} // for j
			} // for i
			if(cnt==0) {
				System.out.println(0);
				return;
			}
			if(!entire_flag) {//모든 것이 인접한게 안 같아서 안바퀴면 다 더한거의 평균과 비교
				double avg = total/cnt;
//				System.out.println(avg);
				for (int i = 1; i <= n; i++) {
					for (int j = 0; j < m; j++) {
						if(map[i][j]!=0&&avg<map[i][j]) {
							map[i][j]--;
						}
						else if(map[i][j]!=0&&avg>map[i][j]) {
							map[i][j]++;
						}
					}
				}
			}
//			System.out.println("");
//			for(int i=1;i<=n;i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
		} // for tc
		int final_sum=0;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				final_sum+=map[i][j];
			}
		}
		System.out.println(final_sum);


		
//		for (int i = 1; i <= n; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}// main
}
