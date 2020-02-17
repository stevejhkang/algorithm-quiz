package greedy;

public class P50_01Knapsack_Full {
//	static int[][] goods = { { 25, 10 }, { 10, 9 }, { 10, 5 } };
	static int[][] goods= {{25,15},{10,9},{10,5}};
//	static int[][] goods={{5,50},{10,60},{20,140}};
	static int W = 30;
	private static boolean[] visit;
	private static int max;

	public static void main(String[] args) {
		// 완전 탐색 기법으로 0-1 knapsack 문제를 처리해보자.
		visit = new boolean[goods.length];
		max = Integer.MIN_VALUE;
		recursion(0, 0, 0);
		System.out.println(max);
	}

	static void recursion(int r, int weight, int price) {
		if (weight > W) {
			return;
		}
		if (r == goods.length || weight == W) {
			if (max < price)
				max = price;
			return;
		} else {
			if (max < price)
				max = price;
		}
		for (int i = 0; i < goods.length; i++) {
			if (!visit[i]) {
				visit[i] = true;
				recursion(r + 1, weight + goods[i][0], price + goods[i][1]);
				visit[i] = false;
			}
		}

	}

}