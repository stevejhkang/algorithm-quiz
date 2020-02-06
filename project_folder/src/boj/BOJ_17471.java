package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17471 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());

		popul = new int[n + 1];
		// 그래프 선언
		ll = new List[n + 1];
		visit = new boolean[n+1];
		for (int i = 1; i <= n; i++) {
			ll[i] = new ArrayList<>();
		}

		// 인구 표시
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= n; i++) {
			int a = Integer.parseInt(stringTokenizer.nextToken());
			popul[i] = a;
		}

		// 연결 표시
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int k = Integer.parseInt(stringTokenizer.nextToken());
			// i번 구역과 연결된 구역을 k번 입력받는다.
			for (int j = 0; j < k; j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				ll[i].add(a); // 연결된 것을 표시해준다.
			}
		}
	
//		System.out.println(n);
//		recursion(0, 1);
		
		
}
