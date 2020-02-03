package stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

public class Queue_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<dot> queue = new LinkedList<>();
		queue.offer(new dot(0, 0));
		queue.offer(new dot(1, 3));
		dot aDot= queue.poll();
		System.out.println(aDot.y+" : "+aDot.x);
		aDot= queue.poll();
		System.out.println(aDot.y+" : "+aDot.x);
	}

}
class dot{
	int x;
	int y;
	public dot(int x, int y) {
		this.y=y;
		this.x=x;
	}
}