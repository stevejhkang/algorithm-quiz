package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_13549 {
	private static int n;
	private static int k;
	private static int[] visit;
	private static int min;

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
		min = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(now==k) {
				if(min>visit[now])
					min = visit[now];
//				System.out.println(visit[now]);
//				break;
			}
			if(2*now<=100000&&visit[2*now]>visit[now]) {
				visit[now*2]=visit[now]; 
				queue.offer(now*2);
			}
			if(now+1<=100000&&visit[now+1]>visit[now]+1) {
				visit[now+1]=visit[now]+1; 
				queue.offer(now+1);
			}
			if(now-1>=0&&visit[now-1]>visit[now]+1) {
				visit[now-1]=visit[now]+1; 
				queue.offer(now-1);
			}
			
			
		}
		System.out.println(min);
	}
	
}
