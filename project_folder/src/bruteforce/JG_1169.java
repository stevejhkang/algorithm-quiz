package bruteforce;

import java.util.Scanner;

public class JG_1169 {
	static int[] input;
	static int[] visit;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(); int m =scanner.nextInt();
		input=new int[n];
		visit= new int[7];
		
		switch(m) {
		case 1:
			bubun(0, n, "");
			break;
		case 2:
			combination(0, n, 1, "");
			break;
		case 3:
			permutation(0,n,"");
			break;
		}
//		
//		
		
	}

	static void bubun(int r, int n, String s) {
		if (n == r) {
			System.out.println(s);
			return;
		}
		for (int i = 1; i <= 6; i++) {
			bubun(r + 1, n, s + i + " ");
		}
	}

	static void combination(int r, int n, int idx, String s) {
		if (n == r) {
			System.out.println(s);
			return;
		}
		for (int i = idx; i <= 6; i++) {
			combination(r + 1, n, i, s + i + " ");
		}

	}

	static void permutation(int r, int n, String s) {
		if (n == r) {
			System.out.println(s);
			return;
		}
		for (int i = 1; i <= 6; i++) {
			if (visit[i] == 0) {
				visit[i] = 1;
				permutation(r + 1, n, s + i + " ");
				visit[i] = 0;
			}
		}
	}
}
