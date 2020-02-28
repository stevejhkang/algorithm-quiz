package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 28. 오후 3:06:52
 * @category 
* @problem_description 1년동안 각 달의 이용계획 수립하여 가장 적은 비용으로 수영장 이용할 수 있는 방법
* 1일이용, 1달 이용 1일부터, 3달 이용권(11월,12월에도 사용가능), 1년 이용권 1월1일
* 각 이용권의 요금과 각 달의 이용계획이 입력으로 주어질 때, 
* @solving_description 
* 1년권을 맥스로 잡고, 매달 1일이용 하는 것과 1달 3달 이런식으로 dfs를 돌면서 가격을 확인한다.
*/
public class SWEA_1952 {
	private static int day_price;
	private static int month_price;
	private static int month3_price;
	private static int year_price;
	private static int min;
	private static int[] use;
	private static int[] price;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(bufferedReader.readLine());
		price = new int[3];
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			day_price = Integer.parseInt(stringTokenizer.nextToken());
			month_price = Integer.parseInt(stringTokenizer.nextToken());
			month3_price = Integer.parseInt(stringTokenizer.nextToken());
			year_price = Integer.parseInt(stringTokenizer.nextToken());
			price[0] = day_price; price[1] = month_price; price[2] = month3_price;
			min =year_price;
			
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			use = new int[12];
			for(int i=0;i<12;i++) {
				use[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			for(int i=0;i<12;i++) {
				if(use[i]!=0) {
					dfs(i,0);
					break;
				}
				
			}
//			System.out.println();
			System.out.println("#"+tc+" "+min);
		}
	}//main
	static void dfs(int now_month, int price) {
		if(now_month>=12) {
			min=Math.min(min, price);
			return;
		}
		for(int i=0;i<3;i++) {
			if(i==0) { //1일인 경우 1개월 추가하고 해당 월의 일수*하루 가격 곱한다
				dfs(now_month+1,price+day_price*use[now_month]);
			}
			else if(i==1) {
				dfs(now_month+1,price+month_price);
			}
			else if(i==2) {
				dfs(now_month+3,price+month3_price);
			}
			
		}
		
	}
	static class usage{
		int day, month,month3;

		public usage(int day, int month, int month3) {
			super();
			this.day = day;
			this.month = month;
			this.month3 = month3;
		}
		
	}//class usage
}
