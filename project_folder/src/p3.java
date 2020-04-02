import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 3. 오전 1:04:56
 * @category 
* @problem_description 크기가 작아서 완전탐색으로 풀 수 있다.
* 
* @solving_description 나는 굳이 이중 포문을 돌아서 둘다 비교해서 같으면 ++을 했는데 중복이 발생하기 때문에 이 방법은 옮지 않았던 것 같다.
* 크기가 작기때문에 조합으로 풀면 쉽게 풀리는 문제다.
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
	private static int k;
	private static ArrayList<Integer> al;
	private static int cnt;
	private static boolean[] visit;
	public int solution(String[] user_id, String[] banned_id) {
    	n = user_id.length;
    	k = banned_id.length;
        al = new ArrayList<Integer>();
        cnt=0;
        visit = new boolean[n];
        for(int i=0;i<k;i++) {
        	banned_id[i]=banned_id[i].replace('*', '.');
//        	System.out.println(banned_id[i]);
        }
        for(String s : banned_id) {
        	System.out.println(s);
        }
        dfs(0, 0,user_id,banned_id);
        return cnt;
    }
    static void dfs(int r, int idx, String[] user_id, String[] ban) {
    	if(r==k) {
    		find(user_id,ban);
    		return;
    	}
    	for(int i=idx;i<n;i++) {
    		
    			al.add(i);
        		dfs(r+1,i+1,user_id,ban);
        		al.remove(r);
        		
    		
    	}
    }
    static void find(String[] user_id, String[] ban) {
    	System.out.println();
    	boolean selected[] =new boolean[k];
    	int count=0;
    	for(int i=0;i<k;i++) {
    		for(int j=0;j<k;j++) {
    			if(!selected[j]&&user_id[al.get(i)].matches(ban[j])) {// 같으면 다음 원소를 
    				System.out.println("i,j"+al.get(i)+","+j);
//    				System.out.println(j);
    				selected[j]=true;
    				count++;
    				
    			}
    		}
    	}
    	if(count>=k) {
    		cnt++;
    	}
    }
}