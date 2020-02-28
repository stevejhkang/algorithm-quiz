package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 17. 오전 11:00:26
 * @category dfs
 * @problem_description
 * @solving_description 무엇을 넘겨줄지, 무엇을 수정하고 다시 복구 할지를 고민해야되는 문제   종이 숫자를 변경하고, map을 변경하고 dfs를 넘겨준다.
 * 그리고 나서 종이 숫자와 map을 다시 바꿔준다.
 */

public class BOJ_17136_2x {
	private static boolean[][] map;
	private static int[] paper;
	private static int cnt=0;
	private static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[10][10];

		for (int i = 0; i < 10; i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
			for (int j = 0; j < 10; j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());				
				if(a==1) {
					cnt++;
					map[i][j]= true; 
				}
			}
		}
		if(cnt==0) {
			System.out.println(0);
			return;
		}
		// 종이 개수 저장 배열
		paper = new int[6];
		min = Integer.MAX_VALUE; //최소 색종이 사용 횟수 저장
		
		out:for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(map[i][j]) { //최초 1일때 dfs를 시작한다.
					dfs(i, j, cnt, 5, 0);
					break out;
				}
			}
		}
		
		if(min==Integer.MAX_VALUE) { //갱신이 안되었으면 -1 출력
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
	} // main

	// i,j에 해당 크기를 씌울 수 있는지 확인한다.
	static void dfs(int y, int x,int count,int type,int number) {
		//5개 이상 사용하거나, min값보다 클 경우 리턴
		if(paper[type]>5||number>min) { //basecase1
			return;
		}
		
		//1의 개수가 0일때  종이의 개수 비교 basecase2
		if(count==0) {
			if(min>number) {
				min=number;
			}
			return;
		}
		
		//recursion case
		for(int r=y;r<10;r++) {
			int c=0;
			if(r==y) {
				c=x;
			}
			for(; c<10;c++) {
				//1이면 크기 5의 색종이부터 대본다.
				if(map[r][c]) {
					boolean flag_cover = false; 
					//색종이를 한번이라도 사용한 적이 있는지 체크하는 flag
					for (int k = 5; k >= 1; k--) {
						boolean flag = true; //k크기의 색종이를 사용할 수 있는지 체크하는 flag
						// y부터 y+i까지 색칠을 할 수 있는지를 체크해야한다.
						outer: for (int i = 0; i < k; i++) {
							for (int j = 0; j < k; j++) {
								int ny = r + i;
								int nx = c + j;
								// 범위
								if (ny < 0 || ny >= 10 || nx < 0 || nx >= 10) {
									flag = false;
									break outer;
								}
								// k로 씌울 수 없으면 0이면 벗어난다.
								if (!map[ny][nx]) {
									flag =false;
									break outer;
								}
							}
						} // outer
						// 씌울 수 있으면 0으로 만들어 버리고 해당 크기 색종이로 덮는 처리를 해준다.
						if(flag) {
							flag_cover= true;
							for (int i = 0; i < k; i++) {
								for (int j = 0; j < k; j++) {
									int ny = r + i;
									int nx = c + j;
									map[ny][nx] = false;
								}
							}
							paper[k]++;
							dfs(r,c+k,count-(k*k),k,number+1); //1개수와 총 사용개수 갱신
							paper[k]--;
							for (int i = 0; i < k; i++) {
								for (int j = 0; j < k; j++) {
									int ny = r + i;
									int nx = c + j;
									map[ny][nx] = true;
								}
							}
						}//flag
					}// for k 
					return;  
					//r,c를 1로만들고 그 뒤를 탐색할 필요가 없기 때문에 그냥 리턴해준다. 그 뒤는 
					//안에 있는 dfs 가 처리해준다.
				}//copy[r][c]
			} //for j
		} //for i
	}//dfs
}