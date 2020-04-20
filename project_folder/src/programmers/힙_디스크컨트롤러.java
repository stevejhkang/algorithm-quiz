package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 19. 오후 7:33:08
 * @category 
* @problem_description 
* @solving_description
* 1. 디스크 컨트롤러는 먼저 들어온 작업 순서대로 처리한다.
* 2. 그런데 대기 작업 중에서 작업이 완료되는데 걸리는 시간이 가장 작은 작업이 우선적으로 처리한다.
* 왜냐하면 만약에 긴 것을 한다고 할때 긴것이 걸리는 시간+ 짧은 것이 긴것이 다되기를 기다리는 시간이 동시에 들기 때문에
* 최대한 짧은 것을 넣어주여야 한다.
* 3. 대기큐에 있던 작업 수행과 새 작업은 계산 방법이 조금 다르다.  
*/

public class 힙_디스크컨트롤러 {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] jobs = {{0,3},{1,9},{2,6}};
		System.out.println(s.solution(jobs));
	}
	static class Solution {
	    public int solution(int[][] jobs) {
	        int answer = 0;
	        
	     
	        
	        PriorityQueue<job> pq = new PriorityQueue<>();
	        List<job> jobList = new ArrayList<>();
	        for(int i=0;i<jobs.length;i++) {
	        	jobList.add(new job(jobs[i][0], jobs[i][1]));
	        }
	        
	        Collections.sort(jobList, new Comparator<job>() {

				@Override
				public int compare(job o1, job o2) {
					// TODO Auto-generated method stub
					int start_time = (new Integer(o1.start)).compareTo(new Integer(o2.start));
					if(start_time==0 ) {
						return (new Integer(o1.time).compareTo(o2.time));
					}
					else {
						return start_time;
					}
				}
			});
	        
	        int sum = 0;
	        int  prev_end=0;
	        
	        while(!jobList.isEmpty() || !pq.isEmpty()) {
	        	boolean isNew = false;
	        	
	        	//요청시간이 이전에 끝난 시간보다 더 크면 waiting이 아님.
	        	Iterator<job> iter = jobList.iterator();
	        	while(iter.hasNext()) {
	        		job now = iter.next();
	        		//제일 최근에 끝난 시간보다 뒤에 있으면은 이미 있는 대기 요청을 처리하던가 대기 요청이 없으면
	        		// 새로 넣기 위해 break를 한다.
	        		if(now.start>prev_end) break; 
	        		
	        		//제일 최근에 끝난 시간보다 더 이전에 작업할당이 되었으면 추가한다.
	        		pq.add(now);
	        		iter.remove();
	        	}
	        	
	        	//대기 요청이 없으면 가장 먼저 들어온 작업 수행!
	        	if(pq.size()==0) {
	        		pq.add(jobList.get(0));
	        		jobList.remove(0);
	        		isNew=true;
	        	}
	        	
	        	job to_work = pq.poll();
	        	if(isNew) { //새거이면 
	        		sum+= to_work.time; //걸린 시간만 추가해주고
	        		prev_end = to_work.start+to_work.time; //끝시간은 시작시간+걸린 시간
	        	}
	        	else {
	        		sum+=((prev_end-to_work.start)+to_work.time); //최근 끝난시간 - 시작시간 만큼 더 기다려야 되고, 
	        		// 거기에 일하는 시간 추가해주어야 된다.
	        		prev_end+=to_work.time;
	        		//끝나는 시간은 그냥 일하는 시간 추가해주는거고
	        	}
	        }
	        
	        answer = (int)sum/jobs.length;
	        
	        
	        return answer;
	    }
	    
	    static class job implements Comparable<job>{
	    	int start, time;

			public job(int start, int time) {
				super();
				this.start = start;
				this.time = time;
			}

			@Override
			public int compareTo(job o) {
				// TODO Auto-generated method stub
				return (new Integer(this.time)).compareTo((new Integer(o.time)));
			}
	    	
	    }
	}
}
