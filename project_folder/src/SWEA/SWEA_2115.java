package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 28. 오후 1:43:25
 * @category 
* @problem_description N*N 꿀의 양 
* 꿀 채집 방법: 두명 일꾼 꿀을 채취할 수 있는 벌통의 수 M이 주어질때 각각의 일꾼은 가로로 연속이되도록
* M개의 벌통을 선택하고, 선택한 벌통에서 꿀을 채취할 수 있다. 단 두명의 일군이 선택한 벌통은 서로 겹치면 안된다.
* 그리고 일꾼이 채취할 수 있는 꿀양이 정해져있어서 그 양을 넘지 않게 m개 중에서 선택을 해야한다.
* 이때 두 일꾼이 꿀을 채취하여 얻을 수 있는 수익의 합이 최대가 되는 경우를 출력하고 그때의 최대 수익을 출력하라8
* @solving_description 0번째부터 시작해서 m개를 리스트에 넣고 최댓값 c를 넘지 않게 조합을 고른다
* 그런데 어떻게하면 두명의 최댓값을 고를 수 있을까 노란색 먼저 이중포문으로 뽑고 오렌지색은 그 뒤에 것을 뽑으면 된다.
*/
public class SWEA_2115 {
	private static int n;
	private static int m;
	private static int c;
	private static int[][] map;
	private static int first_man;
	private static int second_man;
	private static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			//n m c
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			c = Integer.parseInt(stringTokenizer.nextToken());
			
			map = new int[n][n];
			for(int i=0;i<n;i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for(int j=0;j<n;j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			
			max=Integer.MIN_VALUE;
			for(int i=0;i<n;i++) {
				for(int j=0;j<=n-m;j++) {
					//j+m이 n이 될 경우 범위 넘어감
					//k개를 어레이 리스트에 넣고 c를 넘어가기 전까지 더해준다
					// 그 중에서 최대를 찾아준다. 제곱수의 합이기 때문에 따로 유지해줘야할 듯...
					//dfs를 통해서 합이 넘지 않을때까지 합을 더해준다. 그리고 최댓값 갱신
					
					first_man = Integer.MIN_VALUE;
					//j부터 j+m-1까지 선택 //
					dfs(i,j, 0, 0, j);
					
//					System.out.println(first_man);
					second_man = Integer.MIN_VALUE;
					//그 이후부터 다른 일꾼이 벌꿀칸을 선택한다.
					for(int q=i;q<n;q++) {
						if(q==i) { //같은 row일땐 이후부터
							for(int w = j+m;w<=n-m;w++) {
								dfs2(q, w, 0, 0, w);
							}
						}
						else {
							for(int w=0;w<=n-m;w++) {
								dfs2(q, w, 0, 0, w);
							}
						}
					}
//					System.out.println(second_man);
					max= Math.max(max, first_man+second_man);
				}
			}
			System.out.println("#"+tc+" "+max);
		}//for tc
	}//main
	static void dfs(int row, int j, int sum, int profit, int index) {
		if(index>=j+m) {
			first_man= Math.max(first_man, profit);
			return;
		}
		first_man= Math.max(first_man, profit);
		for(int k=index; k<j+m; k++) {
			if(sum+map[row][k]<=c) { //c를 넘지 않게 
				dfs(row,j,sum+map[row][k],profit+(map[row][k]*map[row][k]),k+1);
			}
		}
	}//dfs
	static void dfs2(int row, int j, int sum, int profit, int index) {
		if(index>=j+m) {
			second_man= Math.max(second_man, profit);
			return;
		}
		second_man= Math.max(second_man, profit);
		for(int k=index; k<j+m; k++) {
			if(sum+map[row][k]<=c) { //c를 넘지 않게 
				dfs2(row,j,sum+map[row][k],profit+(map[row][k]*map[row][k]),k+1);
			}
		}
	}
}
