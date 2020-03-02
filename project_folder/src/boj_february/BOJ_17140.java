package boj_february;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 24. 오후 3:27:12
 * @category 
* @problem_description r,c,k가 주어진다 a[r][c] ==k 가되는 최소 시간을 구하라 100 넘어가면 -1출력
* @solving_description 
*/
public class BOJ_17140 {
	private static int r;
	private static int c;
	private static int k;
	private static int[][] input;
	private static number[][] to_sort;

	public static void main(String[] args) throws IOException {
		//r연산: 모든 행에 대해서 정렬 수행 r의 개수>= 열의 개수인 경우 적용
		//c연산: 모든 열에 대해서 정렬 수행, r의개수<c의 개수인 경우 적용
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader( System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		r = Integer.parseInt(stringTokenizer.nextToken());
		c = Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());
		
		input = new int[101][101];
		
		for(int i=1;i<=3;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			for(int j =1;j<=3;j++) {
				input[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		//r연산부터 시작한다.
		int time=1;
		int r_len =3;
		int c_len=3;
		while(time<=1) {
			
			//행연산
			if(r_len>=c_len) {
				to_sort= new number[c_len+1][101];
//				for(int i=0;i<=100;i++) {
//					to_sort[i]
//				}
				for(int i=1;i<=c_len;i++) {
					for(int j=1;j<=r_len;j++) {
						int key = input[i][j];
						if(to_sort[i][key]==null) {
							to_sort[i][key] = new number(key, 1);
						}
						else {
							to_sort[i][key].how++;
						}
					}
				}
				for(int i=1;i<=c_len;i++) {
					Arrays.sort(to_sort[i]);
				}
//				System.out.println(to_sort);
				for(int i=1;i<=c_len;i++) {
					for(int j=1;j<=100;j++) {
						if(to_sort[i][j].how!=0)
							System.out.println(to_sort[i][j]);
					}
				}
			}
			//열 연산
			else {
				
			}
			
			
			time++;
		}
		if(time==101) {//넘어가는 경우 -1
			System.out.println(-1);
		}
		
	}//main
	static class number implements Comparable<number>{
		int num,how;
		
		public number(int num, int how) {
			super();
			this.num = num;
			this.how = how;
		}

		@Override
		public int compareTo(number o) {
			// TODO Auto-generated method stub
			if(this.how==o.how) {//같으면 숫자가 작은게 먼저
				if(this.num>o.how) { //1이면 바꾼다.
					return 1;
				}
				else {
					return -1;
				}
			}
			else {
				if(this.how>o.how) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}

		@Override
		public String toString() {
			return "[num=" + num + ", how=" + how + "]";
		}
		
	}
	
}
