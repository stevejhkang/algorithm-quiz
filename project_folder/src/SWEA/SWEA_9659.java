package SWEA;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 3. 오후 10:05:18
 * @category 
* @problem_description xi의 범위가 998244353이다  m 즉 i의 범위는 50이다. n의 범위도 50이다.
* @solving_description 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_9659{
	private static int n;
	private static long[] tarr;
	private static long[] fun;
	private static int m;
	private static int[] aarr;
	private static int[] barr;
	private static Map map[];
	private static long[] ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {

			n = Integer.parseInt(bufferedReader.readLine());
			tarr = new long[n + 1];
			fun = new long[n + 1];
			aarr = new int[n + 1];
			barr = new int[n + 1];

			for (int i = 2; i <= n; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int t = Integer.parseInt(stringTokenizer.nextToken());
				int a = Integer.parseInt(stringTokenizer.nextToken());
				int b = Integer.parseInt(stringTokenizer.nextToken());
				tarr[i] = t;
				aarr[i] = a;
				barr[i] = b;
			}

			m = Integer.parseInt(bufferedReader.readLine());
			ans = new long[51];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			stringBuilder.append("#" + tc + " ");
			for (int i = 1; i <= m; i++) {
				int number = Integer.parseInt(stringTokenizer.nextToken());

				ans[0] = 1;
				ans[1] = number; // 초기조건
				for (int j = 2; j <= n; j++) {
					if (tarr[j] == 1)
						ans[j] = (ans[aarr[j]] + ans[barr[j]]) % 998244353;
					else if (tarr[j] == 2)
						ans[j] = (aarr[j] * ans[barr[j]]) % 998244353;
					else if (tarr[j] == 3)
						ans[j] = (ans[aarr[j]] * ans[barr[j]]) % 998244353;
				}

				stringBuilder.append(ans[n] + " ");
			}
			stringBuilder.append("\n");
		} // tc
		System.out.println(stringBuilder);

	}// main

}
