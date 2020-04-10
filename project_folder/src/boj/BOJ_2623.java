package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 24. 오후 9:44:35
 * @category 
* @problem_description 가수 순서 정하려한다. 
* 1. 세 보조가 정해온 순서를 모두 만족해야한다. 
* 2. 순서 정하는 거 불가능하면 0을 출력 
* @solving_description 
*/

public class BOJ_2623 {
	private static int n;
	private static int m;
	private static int[] degree;
	private static List[] graph;
	private static boolean[] visit;
	private static ArrayList<Integer> al;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		//차수와 인접그래프 생성 및 초기화
		degree = new int[n+1];
		graph = new List[n+1];
		for(int i=1;i<=n;i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<m;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			
			int from =-1; int to =0;
			int num = Integer.parseInt(stringTokenizer.nextToken());
			while(stringTokenizer.hasMoreTokens()) {
				if(from==-1) {
					from =Integer.parseInt(stringTokenizer.nextToken());
				}
				to = Integer.parseInt(stringTokenizer.nextToken());
				
				//연결 및 차수 갱신
				graph[from].add(to);
				degree[to]++;
				
				from = to;
			}
		}
		
		//차수가 0인 것을 큐에 넣는다.
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1;i<=n;i++) {
			if(degree[i]==0) {
				queue.offer(i);
			}
		}
		
		visit = new boolean[n+1];
		al = new ArrayList<Integer>();
		while(!queue.isEmpty()) {
			int now = queue.poll();
			//방문 했었으면 스킵
			if(visit[now])continue;
			visit[now]=true;
			//정답리스트에 담아준다.
			al.add(now);
			//연결되어 있는 것들 전부 차수 -1해준다.
			for(int i=0;i<graph[now].size();i++) {
				int to = (int) graph[now].get(i);
				
				degree[to]--;
				if(degree[to]==0) {
					queue.offer(to);
				}
			}
		}
		//전부 방문했는지 체크한다.
		boolean all=true;
		for(int i=1;i<=n;i++) {
			if(!visit[i])
				all= false;
		}
		if(!all) {
			System.out.println(0);
		}
		else {
			for(int i=0;i<al.size();i++) {
				System.out.println(al.get(i));
			}
		}
		
		
	}
}
