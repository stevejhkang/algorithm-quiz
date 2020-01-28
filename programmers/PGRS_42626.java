import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        if(scoville.length==1 && scoville[0]<K) //길이가 1개이고 K보다 작으면 안됨
        	return -1;
        PriorityQueue<Integer> pa = new PriorityQueue<Integer>();
        
        for(int i=0;i<scoville.length;i++) {
        	pa.add(scoville[i]);
        }
        while(pa.peek()<K) { //제일 작은게 K보다 작으면 더 맵게 해야한다.
        	int first=pa.poll();
        	int second=pa.poll();
        	int result=first+2*second;
        	pa.add(result);
        	answer++;
            if(pa.size()==1&&pa.peek()<K)
        		return -1;
            
        }
        return answer;
    }
}
