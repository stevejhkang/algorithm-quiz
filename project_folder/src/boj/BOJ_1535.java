package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 26. 오전 10:07:40
 * @category 
* @problem_description 생각해준 사람 총 N명 1번부터 N번, i번 사람에게 인사하면 L[i]만큼 체력을
* 잃고, J[i]만큼의 기쁨을 얻는다.  세준이는 각각 사람에게 최대 1번만 말할 수 있다. 
* 주어진 체력 내 최대한 기쁨, 체력은 100, 체력이 0이나 음수가 되면 죽어서 기쁨을 못느끼게 된다
* 세준이가 얻을 수 있는 최대 기쁨을 출력
* @solving_description 단순한 dfs문제 였음, 기쁨과 체력을 동시에 다뤄야 하는 문제
* 라이프가 0이하가 되었을때와 인덱스가 끝까지 갔을때가 basecase임.
*/
public class BOJ_1535 {
	private static int n;
	private static int[][] input;
	private static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		
		input = new int[n][2];
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		for(int i=0;i<n;i++) {
			input[i][0] = Integer.parseInt(stringTokenizer.nextToken()); //체력
		}
		stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		for(int i=0;i<n;i++) {
			input[i][1] = Integer.parseInt(stringTokenizer.nextToken()); //기쁨
		}
		
		max = Integer.MIN_VALUE;
		//dfs돌리면서 전체를 탐색한다. 근데 basecase가 뭐지? life가 0보다 작거나 혹은
		//n까지 전부 뽑았을때 맞는듯
		dfs(0,0,0,100);
		
		System.out.println(max);
		
	}//main
	static void dfs(int k, int index, int joy, int life) {
		if(life<=0||index==n) {//0보다 작거나 같으면 최근 사용한 것을 합에서 빼주고 갱신비교한다.
			if(life<=0) {
				max= Math.max(max,joy-input[index-1][1]);
			}
			else { //체력이 양수이면 현재까지 더한 것을 그냥 비교한다.
				max= Math.max(max,joy);
			}
			return; 				
		}
		for(int i=index;i<n;i++) {
			dfs(k+1,i+1,joy+input[i][1],life-input[i][0]);
		}
	}
}
	