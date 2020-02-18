package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1244_2 {
	private static String string;
	private static boolean[] visit;
	private static char[] arr;
	private static int time;
	private static int[] idx;
	private static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bReader.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
			string = stringTokenizer.nextToken();
			arr = string.toCharArray();
			time = Integer.parseInt(stringTokenizer.nextToken());
			visit = new boolean[string.length()];
			idx = new int[2];
			max = Integer.MIN_VALUE;
			// 제일 오른쪽에서 큰값을 찾아서 그것보다 제일 왼쪽부터 그값보다 작은 것과 바꿔준다.
			// 제일 큰값찾아준다.
			dfs(0, 0);

			System.out.println("#" + tc + " " + max);
		}
	}

	static void dfs(int index, int k) {
		if (k == time) {
			int num = charArrayToInteger(arr);
			if (max < num) {
				max = num;
			}
			return;
		}
		for (int i = index; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] <= arr[j]) {
					char temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					dfs(i, k + 1);
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	static int charArrayToInteger(char[] array) {
		String arr = new String(array);
		int number = Integer.parseInt(arr);

		return number;
	}
}