package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1927 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				int into1= Math.abs(o1.intValue()); int into2 = Math.abs(o2.intValue());
				if(into1>into2) {
					return 1;
				}
				else if(into1==into2) {
					if(o1.intValue()>o2.intValue()) {
						return 1;
					}
					else 
						return -1;
				}
				else return -1;
			}
		});
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bufferedReader.readLine());
		
		for(int i=0;i<n;i++) {
			int input = Integer.parseInt(bufferedReader.readLine());
			if(input==0) {
				if(pq.isEmpty()) {
					System.out.println(0);
				}
				else {
					System.out.println(pq.poll());
				}
			}
			else {
				pq.add(input);
			}
		}
	}
}
