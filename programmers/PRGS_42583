import java.util.Queue;
import java.awt.print.Printable;
import java.util.LinkedList;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        int sum=0;
        int time=0;
        
        int i=0;
        while(i<truck_weights.length) {
        	time++;
        	int next=truck_weights[i];
        	if(q.size()==bridge_length) { //큐사이즈가 브릿지랑 같으면 마지막것을 꺼내서 합에서 빼준다.
        		int a=q.poll();
        		//System.out.println(a);
        		sum-=a;
        	}
        	if(sum+next<=weight) {//웨이트보다 작거나 같으면 넣고 무게합 증가
        		q.add(next);
        		sum+=next;
        		i++;
        	}
        	else { //웨이트보다 크면 0을 넣고 시간 추가
        		q.add(0);
        	}
        }
        answer=time+bridge_length;
        return answer;
    }
}
