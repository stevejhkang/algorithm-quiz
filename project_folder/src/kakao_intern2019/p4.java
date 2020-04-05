package kakao_intern2019;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class p4 {
	public static void main(String[] args) {
		Solution s = new Solution();
		long k =10;
		long[] room_number= {1,3,4,1,3,1};
		
		System.out.println(Arrays.toString(s.solution(k, room_number)));
	}
	static class Solution {
	    public long[] solution(long k, long[] room_number) {
	        long[] answer = new long[room_number.length];
	        ArrayList<Long> al = new ArrayList<>();

	        Set<Long> set = new HashSet<>();
//	        set.add((long) 12);
//	        System.out.println(set.contains((long)12));
	        for(int i=0;i<room_number.length;i++) {
	        	long a = room_number[i];
	        	while(true) {
	        		//갖고 있지 않으면 넣는다.
	        		if(!set.contains(a)) {
	        			set.add(a);
	        			answer[i]=a;
	        			break;
	        		}
	        		a++;
	        	}
	        }
	        
	        return answer;
	    }
	}
}
