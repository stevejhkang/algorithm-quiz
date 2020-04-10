package boj;

import java.util.Scanner;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 3. 오전 9:46:59
 * @category 
* @problem_description 매일 약 반알 먹는다. N개의 약을 주었다.
* 첫째 날에 약 하나를 꺼낸다. 그 다음 그 약을 반으로 쪼개서 한 조각은 먹고 다른 조각은 다시 병에 넣는다.
* 다음 날 부터 종수는 병에서 약을 하나 꺼낸다. 약이 반 조각이라면 그 약을 먹고
* 아니라면 반을 쪼개서 한 조각을 먹고 다른 조각은 다시 병에 넣는다. 
* 
* 한 조각을 꺼낸 날에는 W를 반조각을 꺼낸 날에는 H를 보낸다 2N일이 지나면 길이가 2N문자열이 만들어지게 된다
* 이때 가능한 서로다른 문자열의 개수는 총 몇 개일까?
* @solving_description 
* 2N일이 될때까지 하루하루 반알 한알 다 넣어준다 근데 알약의 개수가 정해져있으므로 전부 반개로 쪼개지면
* 그 이후부터는 전부 반알만 나오기 때문에 반알의 개수를 파악해주어야 한다.
* 
* 포인트1: 범위 조심 int 넘어간다.
* 포인트2: 재귀는 구했던 것을 또 구하는 특징을 가지고 있다 그래서 이미 구한 것을 배열에 저장을 해서
* 다음 똑같은 것을 구한다 할때 또 구하지 말고 배열의 저장된 값을 리턴하도록 만들어 시간을 줄인다.
* 
*/

public class BOJ_4811 {
	private static int N;
	private static long cnt;
	private static long[][][] save_data;
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		
		while(true) {
			N = scanner.nextInt();
			
			if(N==0) {
				return;
			}
			
			cnt=0;
			save_data= new long[2*N+1][N+1][2*N+1];
			for(int i=0;i<=2*N;i++) {
				for(int j=0;j<=N;j++) {
					for(int k=0;k<=2*N;k++) {
						save_data[i][j][k] = -1;
					}
				}
			}
			
			//처음은 무조건 쪼개서 넣는다.
			cnt =dfs(1,N-1,1);
			
			System.out.println(cnt);
		}
		
	}//main
	static long dfs(int day, int one_pill, int half) { 
		if(save_data[day][one_pill][half]!=-1) { 
			return save_data[day][one_pill][half];
		}
		long sum=0;
		if(day==2*N) { //어디다 저장을 해야할까?
			return 1;
		}
		if(one_pill>0) { //한개의 개수가 0보다 크면 멀쩡한 한개를 쪼개거나 반개를 쪼갤 수 있다.
			sum+= dfs(day+1,one_pill-1,half+1); //멀쩡한 한쪽 집으면 쪼개기 때문에 반개짜리 추가
			if(half>0) {
				sum+=dfs(day+1,one_pill,half-1); //반쪽짜리 사용
			}
		}
		else {
			sum+=dfs(day+1,one_pill,half-1); //더이상 쪼갤 한개짜리가 없으므로 반쪽자리만 사용
		}
		save_data[day][one_pill][half]=sum; //몇번째 날 몇개의 멀쩡한 한개와 몇개의 반쪽자리를 가지고
		//만들 수 있는 경우의 수를 저장한다.
		return sum;
	}
}
