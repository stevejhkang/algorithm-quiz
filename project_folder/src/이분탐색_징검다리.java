import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 1. 오전 10:05:19
 * @category 이분탐색
 * @level 4
 * @problem_description
 * @solving_description 이분탐색의 문제의 특징은 범위가 엄청 넓다는 점이다. 보통 10억건정도 된다. 이것은 거의 무조건
 *                      이분탐색이다. 그리고 이분탐색을 해결하는 방법은 무엇을 right 값으로 잡아서 좁혀나갈 것인가
 *                      이다. right값은 구하고자 하는 수치의 MAX범위, 혹은 배열에서 가장 큰 값을 가지고 있는
 *                      값이다.
 * 
 */

public class 이분탐색_징검다리 {
	public static void main(String[] args) {
		Solution s = new Solution();
		int distance = 25;
		int[] rocks = { 2, 14, 11, 21, 17 };
		Arrays.sort(rocks);
		int n = 2;
		System.out.println(s.solution(distance, rocks, n));
	}
}

class Solution {
	public int solution(int distance, int[] rocks, int n) {
		int answer = 0;

		int left =1;
		int right = distance;
		
		//최댓값을 n개를 없애서 최대한 큰 거리를 만들어야 한다.
		while(left<=right) {
			int mid = (left+right)/2;
			//이 값을 가지고 n개이하를 없애면서 mid값보다 큰 거리를 만들 수 있냐는 문제
			//만들 수 있으면 mid값을 늘리고 없으면 mid값을 낮춘다.
			int past = 0; 
			int cnt=0;
			for(int i=0;i<rocks.length;i++) {
				//mid 값보다 작으면 없애서( 스킵해서) 다음 거리를 확인한다.
				if(rocks[i]-past<mid) {
					cnt++;
				}
				else { //mid값보다 크면 그냥 다음 것을 탐색한다.
					past = rocks[i];
				}
			}
			//마지막으로 도착지점에서 직전 것의 거리를 확인한다.
			if(distance-rocks[rocks.length-1]<mid) {
				cnt++;
			}
			if(cnt<=n) { //cnt를 n개 이하로 사용하면 mid가능한것 그러면 더 큰 값을 확인
				left= mid+1;
				answer = Math.max(answer,mid);
			}
			else {
				right=mid-1;
			}
		}//while
		
		return answer;
	}
}