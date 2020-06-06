package kakao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 3. 오전 1:04:56
 * @category 순열
* @problem_description 크기가 작아서 완전탐색으로 풀 수 있다.
* 불량사용자라는 이름의 목록을 전달한다. 그런데 그 목록이 개인정보때문에 일부 가려져서 전달.
* 사용자 아이디 목록과 제재 아이디 목록이 주어졌을 때 당첨에서 제외되어야 할 제재 아이디목록의 경우의 수 출력
* 
* @solving_description 
* 유저 아이디의 배열의 크기는 8이하이므로 dfs일 가능성이 크다.
* 각 아이디의 길이는 1에서 8이하 
* 불량 사용자 아이디 하나는 응모자 아이디 중 하나에 해당하고 같은 응모자 아이디가 중복해서 제재 아이디 목록에 들어가는 경우는 없다.
* 제재 아이디 목록들을 구했을 때 아이디들이 나열된 순서와 관계없이 아이디 목록의 내용이 동일하다면 같은것으로 처리
* 
* ####### 중복을 없앨지가 핵심인 문제이다!!! 여기서 내가 사용한 방법은 제재배열과 일치하는 배열이 완성시   #########
* ####### userid 배열에서 사용되었던(visit이 true) 인덱스를 문자열로 합쳐서 set에 넣으면 된다. 앞에서 처리가 되는 것이므로 
* ####### 중복을 처리할 수 있다.
* 
* 그리고 이 문제는 순열문제인데 아예 몇개를 다 뽑아서 하는게 아니라 조건이 맞는 것(r번째 제재 아이디와 일치하면)을 뒤에 것이 r+1과 일치 하는 것이 
* 있는 지를 확인해가면서 들어가다가
* 개수가 제재 목록과 같아졌을 때가 제재아이디 목록이 완성된 것이므로 그때 중복을 체크하는 방식으로 진행이 된다.
*/

public class 겨울인턴19_3_불량사용자 {
	public static void main(String[] args) {
		String[] user_id= {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id= {"fr*d*", "abc1**"};
//		
//		String[] user_id= {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//		String[] banned_id= {"*rodo", "*rodo", "******"};
//		
//		String[] user_id= {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//		String[] banned_id= {"fr*d*", "*rodo", "******", "******"};
		
		Solution s = new Solution();
		System.out.println(s.solution(user_id, banned_id));
	}
	static class Solution {
	    private static int n;
		private static int cnt;
		private static boolean[] visit;
		private static Set<String> set;
		public int solution(String[] user_id, String[] banned_id) {
			n = user_id.length;
	        cnt=0;
	        visit = new boolean[n];
	        set = new HashSet<String>(); 
	        dfs(user_id,banned_id,0);
	        cnt=set.size();
	        return cnt;
	    }
		static void dfs(String[] user_id,String[] banned_id, int r) {
			int usize= user_id.length;
			int bsize= banned_id.length;
			if(r>=bsize) { //사이즈가 같아지면 뽑힌 user_id의 인덱스를 Set<String>에 넣어 중복을 없애준다. 
				StringBuilder stringBuilder = new StringBuilder();
				for(int i=0;i<user_id.length;i++) {
					if(visit[i]) {
						stringBuilder.append(i);
					}
				}
				set.add(stringBuilder.toString());
				return;
			}
			for(int i=0;i<usize;i++) { //r번째 제재목록과 일치하는 것을 찾기 위해서 0번째부터 usize까지 탐색을 한다.
				//길이가 다르거나 이미 유저아이디가 다른 재재아이디로 선택이 되어 있으면 패스
				if(banned_id[r].length()!=user_id[i].length() || visit[i]) continue; 
				
				boolean toggle = true; //무슨 목적으로 사용이 되는 거지?
				for(int j=0;j<user_id[i].length();j++) { //i번째 userid와 r번째 bannedid를 인덱스j로 비교한다.
					if(banned_id[r].charAt(j)=='*') { //*이면 그냥 넘어간다.
						continue;
					}
					if(user_id[i].charAt(j)!=banned_id[r].charAt(j)) { //다르면 다음 user id로 넘어간다.
						toggle = false;
						break;
					}
				}
				//끝까지 쭉 가면 해당 제재목록에 적용이 된 것이므로 i번째 유저아이디를 방문 처리 해준다.
				if(toggle) {
					visit[i] = true; //방문처리 해주고 길이를 늘려준다.
					dfs(user_id,banned_id,r+1);
					visit[i] = false;
				}
			}
		}
	  
	 
	}
}
