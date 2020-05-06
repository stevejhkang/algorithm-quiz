package kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 8. 오전 1:10:56
 * @category @problem_description
 * @solving_description 1. 먼저 고객에게 배정할 방이 빈 방이면 즉시 배정합니다. 이때, 배정된 방 번호를 노드로 만들어
 *                      준 후, 부모 노드는 단순히 현재 방 번호에 1을 더한 값으로 지정합니다. 만약 고객에게 배정할 방이
 *                      빈 방이 아니면 다음과 같이 배정할 빈 방을 탐색합니다. 1. 현재 노드의 방이 빈방이 아니면 빈방이
 *                      나올때까지 부모노드를 계속 방문합니다. 2. 빈방이 나오면 고객에게 배정하고, 배정된 방 번호를
 *                      노드로 만든 후, 부모 노드는 배정된 방 번호에 1을 더해준 값으로 지정 3. 빈방이 나오기 전까지
 *                      방문한 노드들의 부모 노드 또한 고객에게 배정한 방 번호에 1더한 값으로 수정
 */

public class p4_2 {
	public static void main(String[] args) {
		Solution s = new Solution();
		long k = 10;
		long[] room_number = { 1, 3, 4, 1, 3, 1 };

		System.out.println(Arrays.toString(s.solution(k, room_number)));
	}

	static class Solution {
		static Map<Long, Long> parent;
		public long[] solution(long k, long[] room_number) {
			long[] answer = new long[room_number.length];
			parent = new HashMap<Long,Long>();//HashMap을 선언해주고
			
			int idx=0;
			for(int i=0;i<room_number.length;i++) {
				
				long a = room_number[i]; // 번호
				long next = ufind(a); //넣을 수 있는 곳 찾는다.
				answer[idx++]=next; //정답을 넣어주고
				
				//next의 부모를 다음 가능한 곳으로 넣어주어야한다.
				parent.put(next, ufind(next+1)); //부모가 비어있으면 자기자신 안 비어있으면 빈방을 가리킨다.
				parent.put(a, parent.get(next)); //이미 찬 방이 또 들어왔을 때 빈방을 가리키도록 만들어준다.
				
			}
			
			return answer;
		}
		static long ufind(long a) {
			if(parent.get(a)==null) {
				parent.put(a, a);
				return parent.get(a);
			}
			else if(parent.get(a)!=a) {
				parent.put(a, ufind(parent.get(a)));
				return parent.get(a);
			}
			return parent.get(a);
		}
	}

	
}
