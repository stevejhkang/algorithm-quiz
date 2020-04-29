package programmers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 28. 오후 11:28:41
 * @category @problem_description
 * @solving_description hashmap을 사용해서 String과 우선순위큐를 사용해서 넣으면 좋을 것 같다.
 * 	문자열을 비교할 때 ==으로 잘못 비교해서 망했음... 정신차리자...
 */
public class DFSBFS_여행경로 {
	public static void main(String[] args) {
		Solution s = new Solution();
//		String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		String[][] tickets = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
				{ "ATL", "SFO" } };
		System.out.println(Arrays.toString(s.solution(tickets)));
	}
	static class Solution {
		static ArrayList<String> list = null;
		static int ticketsize = 0;
		static String[][] ticketstatic;
		static boolean[] visit;
		static ArrayList<String> ans ;
		static boolean success;
		
		public String[] solution(String[][] tickets) {
			String[] answer = {};
			ticketsize = tickets.length;
			list = new ArrayList<String>();
			
//			System.out.println(ticketsize);
			Arrays.sort(tickets, new Comparator<String[]>() {

				@Override
				public int compare(String[] o1, String[] o2) {
					// TODO Auto-generated method stub
					if (o1[0].compareTo(o2[0]) == 0) {
						return o1[1].compareTo(o2[1]);
					} else {
						return o1[0].compareTo(o2[0]);
					}
				}

			});
			
			
			ticketstatic = new String[tickets.length][2];
			visit = new boolean[tickets.length];
			ans = new ArrayList<String>();
			success=false;
			
//			for (int i = 0; i < tickets.length; i++) {
//				System.out.println(tickets[i][0] + " " + tickets[i][1]);
//			}
			
			for (int i = 0; i < ticketstatic.length; i++) {
				ticketstatic[i][0] = tickets[i][0];
				ticketstatic[i][1] = tickets[i][1];
			}
			
			list.add("ICN");
			DFS("ICN", 1);
			answer = new String[ans.size()];
			for(int i=0;i<ans.size();i++) {
				answer[i] = ans.get(i);
				System.out.println(ans.get(i));
			}
			
			return answer;
		}

		static void DFS(String start, int size) {
			if (size == ticketsize+1) {
				success=true;
				for(int i=0;i<size;i++) {
					ans.add(list.get(i));
				}
				return;
			}
			for (int i = 0; i < ticketstatic.length; i++) {
				if (!success&&ticketstatic[i][0].equals(start) && !visit[i]) {
					visit[i] = true;
					list.add(ticketstatic[i][1]);
					DFS(ticketstatic[i][1], size + 1);
					list.remove(size);
					visit[i] = false;
				}
			}
		}
	}
}

