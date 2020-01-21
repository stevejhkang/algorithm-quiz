package algo_basic_day1;

import java.util.Scanner;

public class SWEA_2056 {

	public static int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
//		scanner = new Scanner("5\r\n" + 
//				"22220228\r\n" + 
//				"20150002\r\n" + 
//				"01010101\r\n" + 
//				"20140230\r\n" + 
//				"11111111");
		int t = scanner.nextInt();
		StringBuilder sBuilder = new StringBuilder();
		for(int i=1;i<=t;i++) {
			sBuilder.append("#").append(i).append(" ");
			String data = scanner.next();
			String year = data.substring(0,4);
			String month = data.substring(4,6);
			
			int month_int = Integer.parseInt(month);
			if(month_int<=0||month_int>12) {
				sBuilder.append("-1\n");
				continue;
			}
			
			String day = data.substring(6);
			int day_int = Integer.parseInt(day);
			if(day_int<=0||day_int>days[month_int]) {
				sBuilder.append("-1\n");
				continue;
			}
			
			sBuilder.append(year+"/");
			sBuilder.append(month+"/");
			sBuilder.append(day+"\n");
//			sBuilder.delete(0, sBuilder.length());
		}
		System.out.println(sBuilder);
	}
}
