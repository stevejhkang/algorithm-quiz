package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 2. 오후 12:16:37
 * @category 
* @problem_description 
* 수준 엠 강의를 들으면 (a+m)/2가 된다.
* n개 강의 찾았고 k개를 순서로 선택해 시청, 성수가 가질 수 있는 
* 실력 수치 최대. 
* @solving_description  n개를 배열에다가 저장하고
* 퍼뮤테이션으로 저장하면 터지겠지;;
* 그냥 정렬때리고 작은거 부터 큰거까지 선택하면 될듯!
* 근데 올림을 어떻게 처리할지 나중에 고민해보자.
*/

public class SWEA_6719 {
	private static int n;
	private static int k;
	private static int[] input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder stringBuilder =new StringBuilder();
		int T = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer;
		for(int tc=1;tc<=T;tc++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			k = Integer.parseInt(stringTokenizer.nextToken());
			input = new int[n];
			stringTokenizer= new StringTokenizer(bufferedReader.readLine());
			for(int i=0;i<n;i++) {
				input[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			//정렬
			Arrays.sort(input);
			
			double sum=0;
			for(int i=input.length-k;i<input.length;i++) {
				sum+=input[i];
				sum/=2;
			}
			stringBuilder.append("#"+tc+" "+sum+"\n");
//			System.out.println(sum);
		}
		System.out.println(stringBuilder);
	}
}
