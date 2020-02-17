package Recursion;

public class P35_Total {
	static int input[] = { -1, 3, -9, 6, 7, -6, 1, 5, 4, -2 };
	static int result[] = new int[10];
	static boolean visit[] = new boolean[10];
	private static int max;
	private static int max2;

	public static void main(String[] args) {
		// 1번
//		for(int i=1;i<=10;i++) {
//			subset(0,i,0);
//		}
		// 2번
		max2 = Integer.MIN_VALUE;
		perm(0, 2);
		System.out.println(max2);
		// 3번
		max = Integer.MIN_VALUE;
		com(0, 2, 0);
		System.out.println(max);

	}

	static void subset(int k, int r, int index) {
		if (k == r) {
			int sum = 0;
			for (int i = 0; i < r; i++) {
				sum += result[i];
			}
			if (sum == 0) {
				for (int i = 0; i < r; i++) {
					System.out.print(result[i] + " ");
				}
				System.out.println();
			}
			return;
		}
		for (int i = index; i < input.length; i++) {
			if (!visit[i]) {
				visit[i] = true;
				result[k] = input[i];
				subset(k + 1, r, i + 1);
				visit[i] = false;
			}
		}
	}// subset

	static void com(int k, int r, int index) {
		if (k == r) {
			int sum = result[0] * 10 + result[1];
//			System.out.println(sum);
			if (max < sum) {
				max = sum;
			}
			return;
		}
		for (int i = index; i < input.length; i++) {
			result[k] = input[i];
			com(k + 1, r, i + 1);

		}
	}

	static void perm(int k, int r) {
		if (k == r) {
			int sum = result[0] + result[1];
//			System.out.println(sum);
			if (max2 < sum) {
				max2 = sum;
			}
			return;
		}
		for (int i = 0; i < input.length; i++) {
			if (!visit[i]) {
				visit[i] = true;
				result[k] = input[i];
				perm(k + 1, r);
				visit[i] = false;
			}

		}
	}
}
