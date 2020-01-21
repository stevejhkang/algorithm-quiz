package algo_basic_day1;

import java.util.Scanner;

public class SWEA_2058 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Scanner scanner = new Scanner(System.in);
//		scanner = new Scanner("6789");
	
		int num = Integer.parseInt(scanner.next());
		int sum = 0;
		while (num != 0) {
			int remain = num % 10;
			num = num / 10;
			sum += remain;
		}
		System.out.println(sum);
	}

}
