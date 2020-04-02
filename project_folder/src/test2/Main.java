package test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 26. 오후 7:00:36
 * @category 
* @problem_description 제한시간 2초 메모리 256
* 길이가 n인 숫자로 이루어진 문자열, 이 중 연속하는 k개의 숫자로 만들 수 있는 수 중에서, 가장 큰 수를 찾는 프로그램 
* 예로들어 1414213이고 K=3 이면 141,414,142,421,213 중 421가 가장크다.
* 
* 첫 줄에 n,k 주어지고 n<100,000 k는 10이하
* 
* 불필요한 0은 출력하면 안된다.
* 
* @solving_description
* 앞에서 10개씩 숫자로 만들어서 비교해도 10만 *10이므로 100만이다. 그냥 완전탐색해도 될듯.
*  
*/

public class Main {
	private static int n;
	private static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());
		
		String s = bufferedReader.readLine();
		BigInteger max = new BigInteger("0");
		for(int i=0;i<=s.length()-k;i++) {
			//완전탐색으로 subString
			BigInteger now = new BigInteger(s.substring(i,i+k));
//			max=Math.max(now, max);
			if(max.compareTo(now)==-1) {
				max = now;
			}
		}
		System.out.println(max);
		System.exit(0);
	}
}
