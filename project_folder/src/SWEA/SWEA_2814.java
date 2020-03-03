package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 2. 오후 1:59:24
 * @category 
* @problem_description 경로의 길이는 경로 상에 등장하는 정점의 개수
* @solving_description 
*/

public class SWEA_2814 {
	private static int n;
	private static int m;
	private static boolean[] visit;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bufferedReader.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			//전부 방문할때까지 DFS를 돌린다.
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			
			//n개의 점
			List[] list = new List[n+1];
			for(int i=1;i<list.length;i++) {
				list[i] = new ArrayList<Integer>();
			}
			
			//m개의 간선
			for(int i=0;i<m;i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int from = Integer.parseInt(stringTokenizer.nextToken());
				int to = Integer.parseInt(stringTokenizer.nextToken());
				list[from].add(to); list[to].add(from);
			}
			
			cnt = Integer.MIN_VALUE;
			visit = new boolean[n+1];
			for(int i=1;i<=n;i++) {
				if(!visit[i]) {
					int temp_cnt=1;
					Stack<Integer> stack = new Stack<>();
					stack.push(i);
					
					while(!stack.isEmpty()) {
						Integer now = stack.pop();
						if(visit[now]) continue;
						visit[now] = true;
						boolean flag= false;
						for(int k=0;k<list[now].size();k++) {
							int next = (int) list[now].get(k);
							if(!visit[next]) {
								if(!flag) {
									flag=true;
									temp_cnt++;
								}
								stack.push(next);
							}
						}
					}//stack
					cnt = Math.max(cnt, temp_cnt);
				}//!visit[i]
			}
			System.out.println("#"+tc+" "+cnt);
		}
	}
}
