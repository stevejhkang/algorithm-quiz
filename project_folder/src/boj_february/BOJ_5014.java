package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 25. 오후 2:21:38
 * @category 
* @problem_description f층으로(max) 이루어진 사무실, 스타트링크는 G층에 있다.
* 강호는 S층 엘베타고 g층으로 가려한다. U버튼 위로 U층으로 가는 버튼
* D버튼은 아래로 D층을 가는 버튼
* 만약 u나 d가 없으면 엘베 움직이지 않는다.
* 출력 G층에 도착하려면 버튼을 적어도 몇 번 눌러야 하는지 구하는 프로그램
* 못가면 use the stairs출력
* @solving_description 엘리베이터 내려가거나 올라가는 u,d가 0일때는 해주면 안된다!
* 전형적인 BFS임!
*/
public class BOJ_5014 {
	private static int f;
	private static int s;
	private static int g;
	private static int u;
	private static int d;
	private static int[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		f = Integer.parseInt(stringTokenizer.nextToken()); //1000000
		s = Integer.parseInt(stringTokenizer.nextToken());
		g = Integer.parseInt(stringTokenizer.nextToken());
		u = Integer.parseInt(stringTokenizer.nextToken()); //1000000
		d = Integer.parseInt(stringTokenizer.nextToken()); 
		//1층부터 f층
		//bfs를 해야겠지! 최솟값이니까
		visit = new int[f+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(s);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			if(now==g) { //목표층에 도착하면
				System.out.println(visit[now]);
				return;
			}
			
			//up
			//범위 체크 및 방문여부
			if(now+u<=f&&visit[now+u]==0) {
				if(now+u!=now) {
					visit[now+u]=visit[now]+1;
					queue.offer(now+u);
				}
			}
			//down
			if(now-d>=1&&visit[now-d]==0) {
				if(now-d!=now) {
					visit[now-d] = visit[now]+1;
					queue.offer(now-d);
				}
			}
			
		}//while
		System.out.println("use the stairs");
		
	}
}
