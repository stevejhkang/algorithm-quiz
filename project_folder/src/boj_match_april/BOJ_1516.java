package boj_match_april;
//진입차수가 0인 노드를 큐에 삽입합니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 25. 오후 9:59:09
 * @category 위상정렬
* @problem_description 건물의 종류 수 n 그 다음 줄에는 각 건물을 짓는데 걸리는 시간과 그 건물을 짓기 위해 먼저 지어져야 하는
* 건물들의 번호가 주어진다. 각 줄을 -1로 끝난다. 
* 여러개의 건물을 동시에 지을 수 있다. 
* @solving_description 
* 
*/
public class BOJ_1516 {
	private static int n;
	private static List[] graph;
	private static int[] degree;
	private static int[] times;
	private static int[] lapsed_times;
	private static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		
		graph =new List[n+1];
		for(int i=1;i<=n;i++) {
			graph[i] = new ArrayList<Integer>();
		}
		degree = new int[n+1];
		
		//각 건물을 짓는데 필요한 시간
		times = new int[n+1];
		for(int i=1;i<=n;i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int to =-3;
			while(true) {
				if(to==-3) { //처음입력은 걸린 시간
					to = Integer.parseInt(stringTokenizer.nextToken());
					times[i] = to; 
					continue;
				}
				to = Integer.parseInt(stringTokenizer.nextToken());
				//-1이면 그만 받는다.
				if(to==-1) break;
				//i를 짓기 위해 to를 먼저 지어야 한다.
				graph[to].add(i); //to와 i를 연결시킨다.
				degree[i]++; //i의 차수를 증가시켜준다.
				
			}
		}
		//누적시간 저장(정답배열)
		lapsed_times= new int[n+1];
		
		///n개의 건물이 완성되기 까지 걸리는 최소시간 출력
//		PriorityQueue<building> queue = new PriorityQueue<building>(new Comparator<building>() {
//
//			@Override
//			public int compare(building o1, building o2) {
//				// TODO Auto-generated method stub
//				if(o1.time>o2.time) {
//					return 1;
//				}
//				else if(o1.time<o2.time){
//					return -1;
//				}
//				else {
//					if(times[o1.num]>times[o2.num]) {
//						return 1;
//					}
//					else {
//						return -1;
//					}
//				}
//			}
//		});
		Queue<Integer> queue = new LinkedList<>();
		visit= new boolean[n+1];
		for(int i=1;i<=n;i++) {
			if(degree[i]==0) {
				queue.offer(i);
				lapsed_times[i] = times[i];
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
//			System.out.println("num:"+num);
			for(int i=0;i<graph[now].size();i++) {
				int to = (int) graph[now].get(i);
				//걸린시간은 (이전까지 걸린 시간과 해당 건물을 짓는 시간을 더한 값)과 현재 저장되어 있는 값 중 최댓값으로!
				lapsed_times[to]=Math.max(lapsed_times[now]+times[to],lapsed_times[to]);
				
				degree[to]--;
				if(degree[to]==0) {
					queue.offer(to);
					
				}
			}
			
		}
//		for(int i=1;)
		
		for(int i=1;i<=n;i++) {
			System.out.println(lapsed_times[i]);
		}
	}
	static class building {
		int num, time;

		public building(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
		
	}
}
