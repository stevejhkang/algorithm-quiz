package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 26. 오후 7:15:46
 * @category 
* @problem_description 8개 톱니를 가진 톱니바퀴 T개 일렬로, 톱니바퀴에 번호가
* 가장 왼쪽이 1번 가장 오른쪽이 T번
* K번 회전시키려고 한다. 톱니바퀴 A를 회전할 때, 그 옆에 있는 톱니의 극이 다르면 
* A가 회전한 방향과 반대방향으로 회전하게 된다. 극이 같으면 회전하지 않는다.
* 2번이 회전하지 않았으므로 1번도 회전하지 않는다.
* @solving_description 톱니바퀴의 톱날을 어떻게 표현할 것인가?
*/
public class BOJ_14891 {
	private static int T;
	private static int[][] gear;
	private static int k;
	private static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		T = 4;
		
		gear = new int[T+1][8];
		
		for(int i = 1;i<=T;i++) {
			String a = bufferedReader.readLine();
			for(int j=0;j<a.length();j++) {
				char c = a.charAt(j);
				if(c=='1') {
					gear[i][j] = 1;
				}
			}
		}//입력받고
		
		//회전 횟수입력 후 톱니바퀴 번호와 방향(1 시계, -1 반시계)가 주어진다.
		//왼쪽이면 2번인덱스 확인 오른쪽이면 6번 인덱스 확인
		k = Integer.parseInt(bufferedReader.readLine());
		for(int t=0;t<k;t++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			int num = Integer.parseInt(stringTokenizer.nextToken());
			int dir= Integer.parseInt(stringTokenizer.nextToken());
			//회전을 시작한다. 회전할 톱니 번호와 방향을 함수로 넘겨주면 재귀사용 편할듯!
			visit =new boolean[T+1];//이미 회전했다는 것을 체크하는 배열
			rotate(num, dir);
//			System.out.println();
//			for(int i=1;i<=T;i++) {
//				for(int j=0;j<8;j++) {
//					System.out.print(gear[i][j]);
//				}
//				System.out.println();
//			}
			
		}
		int cnt=0;
		for(int i=1;i<=T;i++) {
			if(gear[i][0]==1) {
				cnt+=Math.pow(2, i-1);
			}
		}
		
		System.out.println(cnt);
		
	}//main
	static void rotate(int num, int dir) {
		visit[num]=true;
		//양쪽검사
		if(num-1>0) { //num의 6번과 num-1의 2번확인
			if(!visit[num-1]&&gear[num][6]!=gear[num-1][2]) {
				//다르면 돌린다.
				int new_dir= (dir==-1?1:-1);
				rotate(num-1, new_dir);
			}
		}
		if(num+1<=T) { //num의 2번과 num+1의 6번확인
			if(!visit[num+1]&&gear[num][2]!=gear[num+1][6]) {
				//다르면 돌린다.
				int new_dir= (dir==-1?1:-1);
				rotate(num+1, new_dir);
			}
		}
		//이제 회전시킨다. 1시계(오른쪽) -1 반시계(왼쪽)
		if(dir==1) {
			int last =gear[num][7];
			for(int i=7;i>=1;i--) {
				gear[num][i]=gear[num][i-1];
			}
			gear[num][0]=last;
		}
		else if(dir==-1) {
			int first = gear[num][0];
			for(int i=0;i<=6;i++) {
				gear[num][i] = gear[num][i+1];
			}
			gear[num][7] = first;
		}
	}
}
