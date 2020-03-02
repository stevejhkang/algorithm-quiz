package boj_february;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1697 {
	private static int n;
	private static int k;
	private static int[] visit;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		k = scanner.nextInt();
		
		visit = new int[100001];
		for(int i=0;i<visit.length;i++) {
			visit[i]= Integer.MAX_VALUE; 
		}
		//큐에 클래스에 넣거나 혹은 visit에 넣거나
		Queue<Integer> queue =new LinkedList<Integer>();
		queue.offer(n);
		visit[n]=0;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(now==k) {
				System.out.println(visit[now]);
				break;
			}
			
			if(now+1<=100000&&visit[now+1]>visit[now]+1) {
				visit[now+1]=visit[now]+1; 
				queue.offer(now+1);
			}
			if(now-1>=0&&visit[now-1]>visit[now]+1) {
				visit[now-1]=visit[now]+1; 
				queue.offer(now-1);
			}
			if(2*now<=100000&&visit[2*now]>visit[now]+1) {
				visit[now*2]=visit[now]+1; 
				queue.offer(now*2);
			}
			
		}
	}
	
}
