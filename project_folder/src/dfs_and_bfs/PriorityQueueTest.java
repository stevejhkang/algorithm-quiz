package dfs_and_bfs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<String> pQueue = new PriorityQueue<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if(o1.length()<o2.length()) {
					return 1;
				}
				else if(o1.length()>o2.length()) {
					return -1;
				}
				else {
					return o1.compareTo(o2)*-1;
				}
				
			}
		});

			
		
		pQueue.offer("true");
		pQueue.offer("dream");
		pQueue.offer("is");
		pQueue.offer("come");
		
		while(!pQueue.isEmpty()) {
			System.out.println(pQueue.poll());
		}
		
	}
}
//if(o1.length()>o2.length()) {
//	return 1;
//}
//else if(o1.length()<o2.length()) {
//	return -1;
//}
//else {
//	o1.compareTo(o2);
//}