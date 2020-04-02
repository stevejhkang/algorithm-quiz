package kakao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class p2 {
	public static void main(String[] args) {

		Solution s = new Solution();
		System.out.println(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}").toString());
	}
	static class Solution {
		public int[] solution(String s) {
//			int[] answer = {};
			String ans = s.replaceAll("\\{|\\}", "");
//			System.out.println(ans);
			
			StringTokenizer stringTokenizer =new StringTokenizer(ans,",");
			ArrayList<num> al =new ArrayList<>();
			while(stringTokenizer.hasMoreElements()) {
				int a= Integer.parseInt(stringTokenizer.nextToken());
				num now = new num(a, 1);
				boolean is=false;
				for(num temp: al) {
					if(temp.equals(now)) {
						temp.val++;
						is=true;
						break;
					}
				}
				if(!is) {
					al.add(now);
				}
			}
			Collections.sort(al);
//			System.out.println(al);
			int[] answer =new int[al.size()];
			int idx=0;
			for(num temp: al) {
				answer[idx++]=temp.idx;
			}
//			for(int i=0;i<al.size();i++) {
//				System.out.print(answer[i]+" ");
//			}
			return answer;
		}
	}

	static class num implements Comparable<num> {
		int idx;
		int val;

		public num(int idx, int val) {
			super();
			this.idx = idx;
			this.val = val;
		}

		@Override
		public int hashCode() {
			return idx;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			num other = (num) obj;
			if (idx != other.idx)
				return false;
			return true;
		}

		@Override
		public int compareTo(num o) {
			if (this.val > o.val)
				return -1;
			else
				return 1;
		}

		@Override
		public String toString() {
			return "num [idx=" + idx + "]";
		}

	}
	
}
