package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 22. 오후 4:42:55
 * @category 
* @level 3
* @problem_description 
* 오른이 왼 무게보다 커져선 안된다.
* 오른쪽이 크거나 같아야 한다.
* 
* n개의 개수는 9개 
* @solving_description 
* 현재 왼쪽에 쌓은 무게가 남은 무게추의 무게보다 크면은 그 뒤는 무조건 되는 케이스이므로 
* 그것의 경우를 한번에 계산해서 끝내버린다.
*/

public class SWEA_3234 {
	private static int n;
	private static int[] weight;
	private static int cnt;
	private static boolean[] visit;
	private static int left_weight;
	private static int right_weight;
	private static int dfs_cnt;
	private static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		StringBuilder stringBuilder = new StringBuilder();
		for(int tc =1;tc<=T;tc++) {
			n = Integer.parseInt(bufferedReader.readLine());
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			weight = new int[n];
			sum = 0;
			for(int i=0;i<n;i++) {
				weight[i] = Integer.parseInt(stringTokenizer.nextToken());
				sum+=weight[i];
			}
			cnt = 0;
			//올리는 순서도 다르다. -> 조합이 아니라 순열
			visit = new boolean[n];
			left_weight = 0;
			right_weight=0;
			
			dfs_cnt=0;
			dfs(0);
//			System.out.println(dfs_cnt);
//			System.out.println("#"+tc+" "+cnt);
			stringBuilder.append("#"+tc+" "+cnt+"\n");
		}
		System.out.println(stringBuilder);
	}//main
	static void dfs(int r) {
		dfs_cnt++;
		if(r==n) {
			//여기까지 들어오면 가능한 것이므로
			cnt++;
			return;
		}
		if(left_weight>=sum-left_weight) { 
			//전체에서 왼쪽것을 뺀 값이 왼쪽 값보다 작다면 하나도 안가도, 전부 오른쪽으로 가도 무조건 가능하다.
			//그냥 남은 경우수를 전체에 추가해준다.
			int fact =1;
			int remain  = n-r;
			while(remain>0) {
				fact*=remain;
				remain--;
			}
			cnt+= Math.pow(2, n-r)*fact;
			return;
		}
		for(int i=0;i<n;i++) {
			if(!visit[i]) {
				
				visit[i]=true;
				//왼쪽에 추가하고 dfs가본다.
				left_weight+=weight[i];
				dfs(r+1);
				left_weight-=weight[i];
				
				//오른쪽에 추가했을 때 왼쪽보다 커지면 안된다. 만족할때만 dfs를 진행하도록 한다.
				if(left_weight>=right_weight+weight[i]) {
					right_weight+=weight[i];
					dfs(r+1);
					right_weight-=weight[i];
				}
				visit[i] = false;
			}
		
		}
		
	}
}
