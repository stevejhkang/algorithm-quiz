package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 28. 오후 4:05:23
 * @category 
* @problem_description N*N 크기에 사탕을 채워놓는다 색 다 같이 않을 수도 있다. 
* 사탕의 색이 다른 인접한 두 칸을 고른다. 그 다음 고른 칸에 들어있는 사탕을 서로 교환
* 이제 모두 같은 색으로 이루어져 있는 가장 긴 연속부분을 고른다음 그 사탕을 모두 먹는다.
* 
* 사탕이 채워진 상태가 주어졌을 때, 상근이가 먹을 수 있는 사탕의 최대 개수 구하라
* @solving_description 아래나 오른쪽과 바꾼 후 전체 배열에서 연속되는 것을 구하고 최대 개수를 구한다.
* 최악의 경우 50*50 하고 처음부터하지말고 거기서부터 오른쪽으로 쭉 구하면 될듯!
*/

public class BOJ_3085 {
	private static int n;
	private static char[][] input;
	static int[] dy = { -1, 0, 0, 1 };  //상 좌 우 하
	static int[] dx = { 0, -1, 1, 0 };
	private static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		
		input = new char[n][n];
		for(int i=0;i<n;i++) {
			String s = bufferedReader.readLine();
			for(int j = 0;j<n;j++) {
				input[i][j] = s.charAt(j);
			}
		}
		
//		System.out.println();
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(input[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		max = Integer.MIN_VALUE;
		//처음부터 오른쪽, 아래와 바꿔가고 거기서부터 오른쪽으로 쭉 내려가보면서 사탕개수를 찾아본다.
		//같은 방향이면 쭉 가서 개수를 세본다.
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int k=2;k<4;k++) {
					int ni=i+dy[k]; int nj = j+dx[k];
					if(ni>=n||nj>=n) continue;
					//오른쪽이나 아래와 바꾼다.
					char temp = input[i][j];
					input[i][j] = input[ni][nj];
					input[ni][nj]= temp;
					
					//바꾸고 바꾼 시점부터 아래로 확인한다.
					checkChain(i);
					
					//다시 원래대로 해준다.
					temp = input[i][j];
					input[i][j] = input[ni][nj];
					input[ni][nj]= temp;
				}
			}
		} //for i
		System.out.println(max);
	}//main
	static void checkChain(int y) {
		
		for(int i=y;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<4;k++) {
					int ni =i+dy[k]; int nj=j+dx[k];
					if(ni<0||ni>=n||nj<0||nj>=n) continue;
					if(input[i][j]==input[ni][nj]) { 
						int length = 2;
						while(true) {//그쪽 방향으로 쭉간다.
							ni+=dy[k]; nj+=dx[k];
							
							//범위를 벗어나거나 다를때까지
							if(ni<0||ni>=n||nj<0||nj>=n) break;
							if(input[i][j]!=input[ni][nj]) break;
							length++;
						}
						//길이 갱신한다.
						max=Math.max(max, length);
					}
				}
			}
		}
	}
}
