package kakao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 3. 오전 12:56:50
 * @category 
* @problem_description 
* 어짜피 처음 나온 것은 앞으로도 계속 나오니까 해당 숫자의 빈도수가 높은게 제일 앞에 오는게 된다.
* @solving_description 
* 일일이 검사해서 있으면 
*/

//한번 효율성 있게 짜보자

public class 겨울인턴19_2_튜플2 {
	public static void main(String[] args) {

		Solution s = new Solution();
		System.out.println(Arrays.toString(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
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
				
				for(num temp: al) {//일일이 리스트에 있는지 검사
					if(temp.equals(now)) { 
						temp.val++;
						is=true;
						break;
					}
				}
				if(!is) { //없으면 새로 추가
					al.add(now);
				}
			}
			Collections.sort(al); //빈도수(val) 대로 정렬시킨다.
			
			int[] answer =new int[al.size()];
			int idx=0;
			//순서대로 정답을 넣는다.
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
		public int compareTo(num o) {
			if (this.val > o.val)
				return -1;
			else
				return 1;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + idx;
			return result;
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
		public String toString() {
			return "num [idx=" + idx + "]";
		}

	}
	
}
