import java.awt.print.Printable;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;


class Solution {
   public int solution(int stock, int[] dates, int[] supplies, int k) {
	   int answer=0;
	   PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	   int start=0;
	   for(int i=0;i<k;i++) {
		   stock--;
		   if(stock==0) {
               if(i==k-1){
                   return answer;
               }
			   int j= start;
			   while(j<dates.length&&dates[j]<=i+1) {
				   pq.offer(supplies[j]);
				   j++;
			   }
			   start =j;
               
			   answer++;
			   stock+=pq.poll();
		   }
	   }
	   return answer;
   }
}
