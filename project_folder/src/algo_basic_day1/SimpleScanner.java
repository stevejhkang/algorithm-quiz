package algo_basic_day1;

import java.util.Scanner;


public class SimpleScanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		read3();
		SplitTest.split1();
	}
	private static String data =" hello java world x";
	public static void read0() {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String str = scanner.next();
			System.out.println(str);
			if(str.equalsIgnoreCase("x"))
				break;
		}	
	}
	public static void read1() {
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(data);
		while(scanner.hasNext()) {
			String str = scanner.next();
			System.out.println(str);
			if(str.equalsIgnoreCase("x"))
				break;
		}
	}
	public static void read2() {
		String data2= "1, 2, 3, 4, 5";
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(data2);
		scanner.useDelimiter(", ");
		int ans=0;
		while(scanner.hasNextInt()) {
			int str = scanner.nextInt();
			ans+=str;
//			System.out.println(str);
			if(str == 5)
				break;
		}
		System.out.println(ans);
	}
	public static void read3() {
		StringBuilder sb = new StringBuilder();
		String data2= "1, 2, 3, 4, 5";
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(data2);
		scanner.useDelimiter(", ");
		int ans=0;
		while(scanner.hasNextInt()) {
			int str = scanner.nextInt();
			ans+=str;
			sb.append(str).append("\n");
			
			if(str == 5)
				break;
		}
		System.out.println(ans);
		System.out.println(sb);
	}
}