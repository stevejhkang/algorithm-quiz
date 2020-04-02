package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 2. 오후 12:30:15
 * @category 
* @problem_description n을 n+1로 바꿀 수 있다 루트n이 정수 일때 n을 루트n으로 바꿀 수 있다.
* 게임의 목표는 n을 2로 만드는 것이다. n을 2로 만들기 위해 조작해야 하는 횟수의 최솟값. 
* @solving_description 
* 루트 씌우는 게 가능하면 루트를 씌우고 불가능하면 +1를 해준다. 
*/

public class SWEA_6782 {
	private static long n;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		StringBuilder stringBuilder =new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			n = Long.parseLong(bufferedReader.readLine());
			cnt =0;
			if(n==2){
				stringBuilder.append("#"+tc+" "+cnt+"\n");
				continue;
			}
			//아니면 계속 줄여나가야 된다.
			while(n!=2) {
				long k = (long) Math.sqrt(n);
				if(k*k==n) {
					n=k;
					cnt+=1;
				}
				else {
					k+=1;
					cnt+=Math.pow(k, 2)-n+1;
					n = k;
				}
			}
//			System.out.println(cnt);
			stringBuilder.append("#"+tc+" "+cnt+"\n");
		}
		System.out.println(stringBuilder);
	}
}
