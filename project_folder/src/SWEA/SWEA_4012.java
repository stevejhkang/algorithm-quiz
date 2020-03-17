package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 17. 오후 1:28:23
 * @category 
* @problem_description 두명의 손님에게 음식제공 최대한 비슷한 맛의 음식 
* 각각의 재료를 n/2개씩 나누어 두개의 요리를 하려고 한다. 
* a음식과 b음식의 맛의 차이가 최소가 되도록 배분해야한다.
* i는 식재로 j와 같이 요리하게하면 궁합이 맞아 시너지 Sij가 발생한다.
* 각 음식의 맛은 음식을 구성하는 식재료들로부터 발생하는 시너지Sij의 합이다.
* * sij의 정보가 주어지고 가지고 있는 식재로를 이용해 A,B음식을 만들때 두 음식간의\
* 맛의 차이가 최소가 되는 경우를 찾고 그 최솟값을 출력
* @solving_description 
* n/2개의 식재료를 선택하여 A를 만들때 식재료 시너지 합을 구하고
* 나머지를 선택해서 B를 만들어 이 식재료의 시너지 합을 구하고 
* 그 값의 최솟값을 출력 
* n/2개를 어떻게 선택할 것인가? n의 최대는 16임 그래서 16C8이 최대 12870임
*/

public class SWEA_4012 {
	private static int n;
	private static int[][] map;
	private static ArrayList<Integer> a;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		
		StringBuilder sBuilder = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(bufferedReader.readLine());
			
			map = new int[n][n];
			for(int i=0;i<n;i++) {
				StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for(int j=0;j<n;j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			
			//n/2개를 선택해야한다. 0~n-1까지중에서
			a = new ArrayList<Integer>();
			min = Integer.MAX_VALUE;
			dfs(0,0);
			sBuilder.append("#"+tc+" "+min+"\n");
		}//for tc
		System.out.println(sBuilder);
	}//main
	static void dfs(int r, int idx) {
		if(idx==n&&r!=n/2) return;
		if(r==n/2) {
			//점수를 계산해준다.
			boolean[] visit = new boolean[n];
//			System.out.println();
			for(int i=0;i<a.size();i++) {
				
//				System.out.print(a.get(i).intValue()+" ");
				visit[a.get(i).intValue()]=true;
			}
			int sumA=0; int sumB=0;
			//13 true 02 false
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					//둘다 false이면 b에 true이면 a에 접수를 더해준다.
					if(i!=j&&visit[i]&&visit[j]) {
						sumA+=map[i][j]; 
					}
					else if(i!=j&&!visit[i]&&!visit[j]) {
						sumB+=map[i][j]; 
						
					}
				}
			}
			min=Math.min(min, Math.abs(sumA-sumB));
			return;
		}
		for(int i=idx;i<n;i++) {
			a.add(i);
			dfs(r+1,i+1);
			a.remove(r);
		}
	}
}
