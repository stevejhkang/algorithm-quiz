package naver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 12. 오전 12:02:41
 * @category 위상정렬
* @level 3
* @problem_description 
* n은 2이상 1000이하
* 각 소요시간은 1이상 1000이하인 자연수
* 
*  k단계를 수행하기 전에 반드시 먼저 완료해야 하는 이전 단계 개수와 k단계를 완료하는데 최소 몇 분이
*  걸리는지를 return 하시오.
* @solving_description 
* 위상정렬이긴한데 Map을 써서 해당 일을 하기위해서 전제되어야 하는 일의 총합의 
* 최대값과 자신의 일값을 더해서 저장할 수 있게 해야한다. 
* 그리고 두번째값에는 그 일을 하기 전에 처리되어야 하는 일들을 중복되지 않게 저장을 해두어야 한다.
* 맵을 두개써야될듯... 
* 걸리는 일의 값과 전에 처리되어야할 일의 집합들
* 내가 생각하고자 했던게 맞았다.
* 걸리는 일의 값과 처리되어야 할 일의 set을 따로 저장하였다. 
* 
*/

public class p3 {
	public static void main(String[] args) {
		int[] cook_times = {5,30,15,30,35,20,4};
		int[][] order = {{2,4},{3,4},{2,5},{3,5},{1,6},{4,6},{5,6},{6,7}}; 
		//{a,b} b를 수행하기 위해선 a가 선행되어야 한다.
		System.out.println(Arrays.toString(new Solution().solution(7, cook_times, order)));
	}
}
class Solution {
    private static int[] degree;
	public int[] solution(int k, int[] cook_times, int[][] order) {
        int n = cook_times.length; //요리 일 사이즈
        HashMap<Integer, Integer> times = new HashMap<>(); //해당 단계 수행 시간
        //해당 단계를 수행하기위해 전제되어야 할 일 set: 자료구조이므로 초기화 해야한다.
        HashMap<Integer,HashSet<Integer>> pre_work = new HashMap<>();
        for(int i=1;i<=n;i++) { 
        	pre_work.put(i, new HashSet<>());
        }
        
        //위상정렬의 연결상태를 저장할 인접리스트
        List[] graph = new List[n+1];
        for(int i=1;i<=n;i++) {
        	graph[i] = new ArrayList<Integer>();
        }
        degree = new int[n+1]; //해당 노드의 차수를 저장하는 배열
        
        for(int i=0;i<order.length;i++) {
        	int from = order[i][0];
        	int to = order[i][1];
        	graph[from].add(to); //from이 선행되야 to가 실행될 수 있다.
        	degree[to]++;
        }
        
        PriorityQueue<Integer> pq = new  PriorityQueue<Integer>();
        for(int i=1;i<=n;i++) {
        	if(degree[i]==0) {
        		pq.offer(i);
        	}
        }
        
        while(!pq.isEmpty()) {
        	int now = pq.poll();
        	
        	if(!times.containsKey(now)) { //갖고 있지 않으면 자기값넣는다.
        		times.put(now,cook_times[now-1]);
        	}
        	else { //이미 있으면 이전 것값 + 자기값 처리해준다.
        		times.put(now, times.get(now)+cook_times[now-1]);
        	}
        	int time = times.get(now);
        	
        	for(int i=0;i<graph[now].size();i++) { 
        		//이어진 모든 것들의 차수를 낮추고 해당 맵에 값도 갱신한다.
        		int to = (int) graph[now].get(i);
        		degree[to]--;
        		if(times.containsKey(to)) {
        			if(times.get(to)<time) { //저장되어 있는 값보다 크면 갱신
        				times.put(to, time);
        			}
        		}else { //저장되어 있는게 없으면 그냥 넣는다.
        			times.put(to, time);
        		}
        		//현재것이랑 현재것을 하는데 해야되는 전재일을 모두 넣어준다.
        		pre_work.get(to).add(now);
        		pre_work.get(to).addAll(pre_work.get(now)); //전제 일을 하는데 필요한 전제일을 모두 넣어준다.
        		
        		//해당 to가 degree가 0이 되면 큐에 넣는다.
        		if(degree[to]==0) {
        			pq.offer(to);
        		}
        	}
        }//while
        
        for(int i: times.keySet()) {
        	System.out.println(times.get(i));
        }
        System.out.println();
        for(int i: pre_work.keySet()) {
        	System.out.println(pre_work.get(i));
        }
        
        return new int[] {times.get(k),pre_work.get(k).size()};
    }
}
