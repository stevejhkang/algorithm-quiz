package kakao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 7. 오후 10:32:30
 * @category HashMap, 문자열 토크나이져
* @level 2
* @problem_description 
* 
* @solving_description
* 
* 정답 사이즈를 미리 구해서 answer 배열에 바로 넣느냐 아니면 리스트를 만들어서 하나하나 add하느냐를 고민했던 문제인데
* 그렇게 차이가 안나는 것 같다.
*/

public class 블라인드19_오픈채팅방 {
	public static void main(String[] args) {
		String[] record  = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
		System.out.println(Arrays.toString(new Solution().solution(record)));
	}
	static class Solution {
	    public String[] solution(String[] record) {
	        String[] answer = {};
	        
	        HashMap<String,String> userdb = new HashMap<>();
	        
	        StringTokenizer stringTokenizer = null;
	        int answer_size=0;
	        for(String rec: record) {
	        	stringTokenizer = new StringTokenizer(rec," ");
	        	
	        	String order = stringTokenizer.nextToken();
	        	String uid = stringTokenizer.nextToken();
	        	
	        	String nickname=null;
	        	if(stringTokenizer.hasMoreElements()) { //있으면 받아온다.
	        		nickname = stringTokenizer.nextToken();
	        	}
	        	
	        	if(order.equals("Enter")) {
	        		userdb.put(uid, nickname);
//	        		if(userdb.containsKey(uid)) { //이미 갖고 있는 경우 받은 값으로 업데이트한다.
//	        			
//	        		}
//	        		else {
//	        			
//	        		}
	        		answer_size++;
	        	}
	        	else if(order.equals("Change")) {
	        		userdb.put(uid, nickname);
	        	}
	        	else {
	        		answer_size++;
	        	}
	        }
	        answer = new String[answer_size];
//	        ArrayList<String> list  = new ArrayList<>();
	        int index=0;
	        for(String rec: record) {
	        	stringTokenizer = new StringTokenizer(rec," ");
	        	
	        	String order = stringTokenizer.nextToken();
	        	String uid = stringTokenizer.nextToken();
	        	
	        	String nickname=null;
	        	if(stringTokenizer.hasMoreElements()) { //있으면 받아온다.
	        		nickname = stringTokenizer.nextToken();
	        	}
	        	
	        	if(order.equals("Enter")) {
//	        		list.add(userdb.get(uid)+"님이 들어왔습니다.");
	        		answer[index]= userdb.get(uid)+"님이 들어왔습니다.";
//	        		userdb.put(uid, nickname);
//	        		if(userdb.containsKey(uid)) { //이미 갖고 있는 경우 받은 값으로 업데이트한다.
//	        			
//	        		}
//	        		else {
//	        			
//	        		}
	        		index++;
	        	}
	        	else if(order.equals("Leave")) {
//	        		userdb.put(uid, nickname);
//	        		list.add(userdb.get(uid)+"님이 나갔습니다.");
	        		answer[index]=userdb.get(uid)+"님이 나갔습니다.";
	        		index++;
	        	}
	        	
	        }
//	        answer = new String[list.size()];
//	        for(int i=0;i<list.size();i++) {
//	        	answer[i] = list.get(i);
//	        }
	        return answer;
	    }
	}
}
