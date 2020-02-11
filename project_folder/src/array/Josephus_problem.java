package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Josephus_problem {
	public static void main(String[] args) {
		int n = 24;
//		ArrayList<Integer> soilder = new ArrayList<>();
//		for (int i = 0; i < n; i++) {
//			soilder.add(i);
//		}
//		int idx=0;
//		ArrayList<Integer> ans= new ArrayList<>();
//		while(soilder.size()>2) {
//			int a=soilder.remove(idx);
//			ans.add(a);
//			System.out.println(a);
//			idx=(idx+2)%(soilder.size());
//		}
//		System.out.println(soilder);
//		
		Queue<soldier> soldiers = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			soldiers.offer(new soldier(i));
		}
		
		
		while(soldiers.size()>2) {
			soldiers.poll();
			for(int i=0;i<3-1;i++) {
				soldiers.offer(soldiers.poll());
			}
		}
		while(!soldiers.isEmpty()) {
			soldier soldiertemp = soldiers.poll();
			System.out.print(soldiertemp.idx+"\t");
		}
		
	}
	static class soldier{
		int idx;

		public soldier(int idx) {
			super();
			this.idx = idx;
		}
		
	}
}
