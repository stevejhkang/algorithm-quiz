package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 23. 오후 1:09:51
 * @category 
* @problem_description 재료에 대한 점수와 칼로리가 주어졌을 때
* 정해진 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거 조합해주는 프로그램
*  
* @solving_description 
*/

public class SWEA_5215 {
	private static int n;
	private static int l;
	private static int[][] food;
	private static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bufferedReader.readLine());
		
		for(int tc =1;tc<=T;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			l = Integer.parseInt(stringTokenizer.nextToken());
			
			food = new int[n+1][2];
			for(int i=1;i<=n;i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				food[i][0] = Integer.parseInt(stringTokenizer.nextToken()); //점수
				food[i][1] = Integer.parseInt(stringTokenizer.nextToken()); //칼로리
			}
			dp = new int[n+1][l+1]; //만족도를 저장하는 배열 
			//1부터 L까지의 만족도를 측정하면서 각 재료들이 들어갔을 때와 안들어 갔을 때 최대 만족도를
			//하나씩 구해나가기 시작하자
			int max = -1;
	
			for(int i=1;i<=n;i++) { //해당 재료
				for(int j=1;j<=l;j++) { //칼로리
					if(j-food[i][1]<0) { //칼로리 포함 시킬 수 없는 경우 전의 것을 더한다.
						dp[i][j] = dp[i-1][j];
					}
					else { //새로 포함시킬 수 있는 경우
						//전값과 포함시킬 음식의 만족도와 그 전 만족도를 비교해서 최대를 넣는다.
						dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-food[i][1]]+food[i][0]);
						if(max<dp[i][j]) max = dp[i][j];
					}
				}
			}//for i
			System.out.println(dp[n-1][l]);
		}
	}
}
