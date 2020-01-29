package algo_basic_day1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class SWEA_3307 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		scanner = new Scanner("2\r\n" + 
				"5\r\n" + 
				"1 3 2 5 4 \r\n" + 
				"6\r\n" + 
				"4 2 3 1 5 6");
		int n = scanner.nextInt();
//		StringBuilder sBuilder = new StringBuilder();
		for(int t=1;t<=n;t++) {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("#"+t+" ");
			int m=scanner.nextInt();
			ArrayList<Integer> arrayList = new ArrayList<>();
			for(int i=0;i<m;i++) {
				arrayList.add(Integer.parseInt(scanner.next().substring(0,1)));
			}
			int[] dp = new int[m];
			dp[0]=1;
			
			for(int i=1;i<arrayList.size();i++) {
				for(int j=i;j>=0;j--) {
					if(arrayList.get(i)>arrayList.get(j)&&dp[i]<dp[j]) {
						dp[i]=dp[j];
					}
				}
				dp[i]+=1;
//				System.out.println(dp[i]);			
			}
//			System.out.println("\n");
			int max=-1;
			for(int i=0;i<m;i++) {
				if(max<dp[i]) {
					max=dp[i];
				}
			}
//			sBuilder.append(Integer.toString(max)+"\n");
			
			////////////////////////////////초기화///////////////////
			System.out.print(sBuilder);
			System.out.println(max);
			
		}
//		System.out.println(sBuilder);
	}

}
