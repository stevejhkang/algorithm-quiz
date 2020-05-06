package kakao;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 4. 오전 1:27:47
 * @category 
* @problem_description  배열의 크기는 1이상 20만이하, 각 원소들의 합은 2억까지이면 인트범위 k는 stones의 길이 20만이하 
* @solving_description 합이 2억이면은 완전탐색은 안된다. 백트레킹도 안돼,,, 그러면 이분탐색, 디피는 결국 만약 그래도 2억번을 해야될 수 있어서 이분탐색
*/

public class p5_2 {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k =3;
		System.out.println(s.solution(stones, k));
	}
}

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int low = 1; int high = 200000000;
        while(low<=high) {
        	int mid = (low+high)/2;
        	
        	boolean move_low=false;
        	int cnt=0;
        	for(int i=0;i<stones.length;i++) {
        		//계속돌면서 mid보다 작은 값이 k번이상 나오면 low~mid사이 값으로 바꾼다.
        		if(stones[i]<=mid) { //0이면 이 사람은 못건너니까
        			cnt++;
        		}
        		else {
        			cnt=0;
        		}
        		if(cnt>=k) {
        			move_low=true;
        			break;
        		}
        	}
        	//계속 돌면서 mid보다 작은 값이 k번이상 안나오면 mid~high로 바꿔본다.
        	if(move_low) {
        		high= mid-1;
        	}
        	else {
        		low=mid+1;
        	}
        }
        answer=low;
        return high+1;
    }
}
