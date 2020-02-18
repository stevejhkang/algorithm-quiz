package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2817 {
	private static int n;
	private static int[] input;
	private static int final_sum;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bufferedReader.readLine());
		for(int tc=1;tc<=t;tc++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			final_sum = Integer.parseInt(stringTokenizer.nextToken());
			
			input= new int[n];
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			for(int i=0;i<n;i++) {
				input[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
//			System.out.println(Arrays.toString(input));
			cnt=0;
			Recursion(0, 0, 0);
			StringBuilder sBuilder =new StringBuilder();
			sBuilder.append("#"+tc+ " "+cnt);
			System.out.println(sBuilder);
		}
		
	}
	//1개이상 선택시 K가 되는지 확인 
	// K초과가 되면 하지 않는다. 순서 상관없으니 조합임
	static void Recursion(int k,int sum,int index) {
		//(sum!=final_sum&&index==input.length-1)
		if(sum>final_sum) {
			return;
		}
		if(sum==final_sum) {
			cnt++;
			return;
		}
		for(int i=index;i<input.length;i++) {
			Recursion(k+1, sum+input[i], i+1);
		}
	}
}
