import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 3. 오전 1:04:56
 * @category 
* @problem_description 크기가 작아서 완전탐색으로 풀 수 있다.
* 
* @solving_description 
* 중복을 없애기 위해서 set을 사용했다.
*/

public class p3 {
	public static void main(String[] args) {
//		String[] user_id= {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//		String[] banned_id= {"fr*d*", "abc1**"};
//		
//		String[] user_id= {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//		String[] banned_id= {"*rodo", "*rodo", "******"};
//		
		String[] user_id= {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id= {"fr*d*", "*rodo", "******", "******"};
		
		Solution s = new Solution();
		System.out.println(s.solution(user_id, banned_id));
	}
}
class Solution {
    private static int n;
	private static int cnt;
	private static boolean[] visit;
	private static Set<String> set;
	public int solution(String[] user_id, String[] banned_id) {
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
		for(int i=0;i<usize;i++) { //이렇게 해줘야 전부 선택이 된다.... 처음부터 개수를 뽑아버리면 안됐었어;;;
			if(banned_id[r].length()!=user_id[i].length() || visit[i]) continue; //길이가 다르거나 같아서 선택이 되어 있으면
			
			boolean toggle = true;
			for(int j=0;j<user_id[i].length();j++) { //i번째 userid와 r번째 bannedid를 비교한다.
				if(banned_id[r].charAt(j)=='*') { //*이면 그냥 넘어간다.
					continue;
				}
				if(user_id[i].charAt(j)!=banned_id[r].charAt(j)) { //다르면 다음 user id로 넘어간다.
					toggle = false;
					break;
				}
			}
			if(toggle) {
				visit[i] = true; //방문처리 해주고 길이를 늘려준다.
				dfs(user_id,banned_id,r+1);
				visit[i] = false;
			}
		}
	}
  
 
}