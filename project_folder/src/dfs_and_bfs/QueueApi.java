package dfs_and_bfs;

import java.util.LinkedList;
import java.util.Queue;

public class QueueApi {
	
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(100);
		queue.offer(200);
		queue.offer(300);
		System.out.println(queue.peek()+": "+queue.size());
	}
}

class Hero implements Comparable<Hero>{
	int power;
	String name;
	@Override
	public int compareTo(Hero o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}