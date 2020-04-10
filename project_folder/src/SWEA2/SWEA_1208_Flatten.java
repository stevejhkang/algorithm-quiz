package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 9. 오전 2:02:36
 * @category 
* @problem_description 
* 높은 곳의 상자를 낮은 곳에 옮기는 방식으로 평탄화를 시킨다.
* 평탄화를 모두 수행하고 나면, 가장 높은 곳과 가장 낮은 곳의 차이가 최대 1이내가 된다.
* 
* 가로길이 항상 100 상자 높이는 100이하 덤프 횟수는 1000이하
* 만약 덤프횟수이내에 평탄화가 완료되면 높이차 반환
* @solving_description 
* 가로 항상 100 그러면 항상 100번을 돌면서 최대랑 최소를 구한다.
* 그리고 거기서 최대-1 최소+1 해준다. 차이를 저장한다.
* 횟수가 더 남으면 100돌려서 최대 최소 구하고 또 처리해준다.
* 100*1000 이니까 10만번밖에 안하므로 가능
*/

public class SWEA_1208_Flatten {
	private static int[] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder();
		for(int tc=1;tc<=1;tc++) {
			int num = Integer.parseInt(bufferedReader.readLine());
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			map = new int[100];
			for(int i=0;i<100;i++) {
				map[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			stringBuilder.append("#"+tc+" ");
			boolean end = false;
			int k=1;
			for( k=0;k<=num;k++) {
				int max=Integer.MIN_VALUE;
				int max_idx=-1;
				int min =Integer.MAX_VALUE;
				int min_idx=-1;
				for(int i=0;i<100;i++) {
					if(max<map[i]) {
						max_idx=i;
						max=map[i];
					}
					if(min>map[i]) {
						min_idx=i;
						min=map[i];
					}
				}
				//다했을 때 max,min이 정해진다.
				map[max_idx]-=1;
				map[min_idx]+=1;
				if(map[max_idx]==map[min_idx] ) { //차가 0 올 1 이 될때 그만둔다.
					stringBuilder.append(0+"\n");
					end = true;
					break;
				}
				else if(map[max_idx]-map[min_idx]==1 ) { //차가 0 올 1 이 될때 그만둔다.
					stringBuilder.append(1+"\n");
					end = true;
					break;
				}
				
				if(k==num&&!end) {
					stringBuilder.append((map[max_idx]-map[min_idx]+2)+"\n");
				}
			}
			
		}//tc
		System.out.println(stringBuilder);
	}
}
