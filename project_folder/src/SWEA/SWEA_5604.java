package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 18. 오전 11:17:17
 * @category 
* @problem_description  a,b 사이의 포함되는 모든 정수의 각 자리를 합한 값
* @solving_description 
* 구간합이면 해당 숫자가 전부 1의 자리가 될 때까지 
* 나눠서 더해주면 되는데 그러면 시간초과가 난다.
* 그래서 해당 만약 111라 하면 
* 0~9, 10~19, 20~29, 이렇게 앞에 자리만 커지고 뒤에는 중복이 된다. 이걸 한번에 저장해서 다시 
* 쓰면 될듯...
* 십단위 백단위 천단위 등등을 해당 자리수까지 합을 구하면 될듯?
* 8,12이면 8까지 직접더하고 10까지 구한거 +11+12 이런 식으로?``
*/

public class SWEA_5604 {
	private static long[] jarihap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		
		//해당 자리까지의 합을 저장한다. 
		//0~9 10~99 100~999 1000~9999
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			long A= Long.parseLong(stringTokenizer.nextToken());
			long B= Long.parseLong(stringTokenizer.nextToken());
			
			long[] ans = new long[10];
			long point =1;
			while(A<=B) {
				while(B%10!=9 && A<=B) {
					cal(B,ans,point);
					B--;
				}
				if(B<A) break;
				while(A%10!=0&&A<=B) {
					cal(A,ans,point);
					A++;
				}
				A/=10;
				B/=10;
				for(int i=0;i<10;i++) {
					ans[i]+=(B-A+1)*point;
				}
				point*=10;
			}
			long sum =0;
			for(int i=0;i<10;i++) {
				sum+=(ans[i]*i);
			}
			System.out.println("#"+tc+" "+sum);
		}//tc
	}//main
	static void cal(long x, long[] ans, long point) {
		while(x>0) {
			String s = String.valueOf(x);
			int xx = s.charAt(s.length()-1)-'0';
			ans[xx]+=point;
			x/=10;
		}
	}
}

//1000000000000000