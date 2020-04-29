package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 29. 오후 5:07:41
 * @category 
* @problem_description 
* 1. 세벌의 옷을 사면 그 중 가장 저렴한 한 벌의 값을 내지 않아도 된다.
* 2. 세벌보다 많은 옷을 구매하는 경우에도 세벌씩 나눠서 계산하면 같은 방식의 할인 적용
* 가장 할인을 많이 받아서 구매할 수 있는 지 -> 출력값 지불해야할 최소 금액
* @solving_description 
*/

public class SWEA_4050 {
	private static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder =new StringBuilder();
		int T = Integer.parseInt(bufferedReader.readLine());
		for(int tc =1;tc<=T;tc++) {
			n = Integer.parseInt(bufferedReader.readLine());
			ArrayList<Integer> list = new ArrayList<Integer>();
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			for(int i=0;i<n;i++) {
				list.add(Integer.parseInt(stringTokenizer.nextToken()));
			}
			
			Collections.sort(list,Collections.reverseOrder());
			
			int ans = 0;
			for(int i=0;i<n;i++) {
				if(i%3!=2) {
					ans+=list.get(i);
				}
			}
			stringBuilder.append("#"+tc+" "+ans+"\n");
		}
		System.out.println(stringBuilder);
	}
}
