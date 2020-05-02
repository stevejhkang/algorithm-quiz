package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_1826 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());
		
		PriorityQueue<Integer> min_pq = new PriorityQueue<Integer>();
		PriorityQueue<Integer> max_pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			int input = Integer.parseInt(bufferedReader.readLine());
			
			if(min_pq.size()>=max_pq.size()) {
				max_pq.add(new Integer(input));
			}
			else {
				min_pq.add(new Integer(input));
				
			}
			if(!min_pq.isEmpty()&&!max_pq.isEmpty()) {
				int min = min_pq.peek().intValue(); int max = max_pq.peek().intValue();
				if(min<max) {
					int temp = min;
					min = max;
					max= temp;
					min_pq.poll(); min_pq.add(min);
					max_pq.poll(); max_pq.add(max);
				}
				
			}
			System.out.println(max_pq.peek());
		}
	}
}
