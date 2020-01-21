package algo_basic_day1;

import java.io.IOError;
import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_5215 {

	static int max_good=-100;
	static int max_cal=0;
	static ArrayList<Integer> good = new ArrayList<Integer>();
	static ArrayList<Integer> cal = new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
//		scanner = new Scanner("1\r\n" + 
//				"5 1000\r\n" + 
//				"100 200\r\n" + 
//				"300 500\r\n" + 
//				"250 300\r\n" + 
//				"500 1000\r\n" + 
//				"400 400");
		int n = scanner.nextInt();
		StringBuilder sBuilder = new StringBuilder();
		for(int t=1;t<=n;t++) {
			sBuilder.append("#"+t+" ");
			int num = Integer.parseInt(scanner.next());
			max_cal=Integer.parseInt(scanner.next());
			
			
			for(int i=0;i<num;i++) {
				good.add(Integer.parseInt(scanner.next()));
				cal.add(Integer.parseInt(scanner.next()));
					
			}
			
			for(int i=1;i<=num;i++) {
				combination(0,i,0,0,0);
				// index, max_num, now, sum, sum_cal
			}
			sBuilder.append(Integer.toString(max_good)+"\n");
			max_good=-100; max_cal=0;
			good = new ArrayList<Integer>();
			cal = new ArrayList<Integer>();
		}
		System.out.println(sBuilder);
	}
	static void combination(  int index,int max_num,int now,int sum,int sum_cal ) {
		
		if( max_good<sum&&sum_cal<=max_cal) {
			max_good=sum;

		}
		if(sum_cal>max_cal || index>=max_num) {
			return;
		}
		if(now==max_num) {
			if(max_good<sum&&sum_cal<=max_cal) {
				max_good=sum;
			}
			return;
		}
		else {
			combination( index+1, max_num, now, sum, sum_cal);
			combination( index+1, max_num, now+1, sum+good.get(index),sum_cal+cal.get(index));
		}
	}

}

