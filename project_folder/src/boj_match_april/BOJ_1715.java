package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(bufferedReader.readLine());
		
		long times =0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0;i<n;i++) {
			int a = Integer.parseInt(bufferedReader.readLine());
			pq.add(new Integer(a));
		}
		while(pq.size()>=2) {
			int sum = pq.poll().intValue()+pq.poll().intValue();
			
			times+=sum;
			pq.add(new Integer(sum));
		}
		System.out.println(times);
	}
}
