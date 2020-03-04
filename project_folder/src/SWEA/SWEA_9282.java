package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 4. 오후 6:58:53
 * @category 
* @problem_description 가로m 세로n 건포도 초콜릿,  현수는 직사각형 전체를 일직선으로 자르는 행동만
* 한번 자를때마다 보상요구, 건포도를 지불하려한다. 
* 초콜릿 한 조각을 두 조각으로 자를 때마다 초기 큰 초콜릿에 있었던 건포도의 개수만큼 수익을 받아야한다.
* 동현이는 각 조각에 있는 건포도의 수를 알며, 현수가 잘라야 하는 건포도의 조각이나 위치
* 모두 동현이가 결정할 수 잇다.
* 동현이가 지불해야하는 건포도의 최소양은?
* @solving_description 자를때마다 자르기 전 큰 초콜릿에 있는 건포도의 개수를 줘야하므로
* 건포도가 많이 들어있는 포도를 최대한 초기에? 잘게 잘라야 될듯?
*/
public class SWEA_9282 {
	private static int n;
	private static int m;
	private static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		int T= Integer.parseInt(bufferedReader.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			
			map = new int[n][m];
			
		}
		
	}
}
