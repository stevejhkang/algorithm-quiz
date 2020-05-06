package kakao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 6. 오후 9:54:12
 * @category map + 시뮬레이션 + 문자열 처리
* @level 3
* @problem_description 
* answk
* 
* @solving_description 
*/

public class 블라인드18_압축 {
	public static void main(String[] args) {
//		String msg ="KAKAO";
//		String msg =	"TOBEORNOTTOBEORTOBEORNOT";
		String msg = 	"ABABABABABABABAB";
//		String msg =	"THATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTIS"
//				+ "NOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATT"
//				+ "HATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATT"
//				+ "HATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTIST"
//				+ "HATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHAT"
//				+ "ISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHAT"
//				+ "ISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATIT"
//				+ "ITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTI"
//				+ "SNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTH"
//				+ "ATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTH"
//				+ "ATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTIS"
//				+ "THATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHAT"
//				+ "ISNOTISNOTISTHATITITISTHATTHATISIST"
//				+ "HATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITIS";
		System.out.println(Arrays.toString(new Solution().solution(msg)));
//		msg.replace("KA","" );
		
//		System.out.println(msg);
	}
	static class Solution {
		public int[] solution(String msg) {
			int[] answer = {};
			ArrayList<Integer> ans = new ArrayList<>();
			//사전 등록 및 초기화
			HashMap<String, Integer> dic = new HashMap<String, Integer>();
			char c = 'A';
			for(int i=1;i<=26;i++) {
//				System.out.println(c);
				Character c_wrapper  = new Character(c);
				String key = new String(c_wrapper.toString());
				dic.put(key, i);
				c++;
			}
			// substring해서 맨 뒤에서 부터 있는 지 확인한다.
			while(msg.length()>0) {
//				System.out.print("msg: "+msg+" ");
				for(int last=msg.length();last>0;last--) {
					
					String key = msg.substring(0,last);
					if(dic.containsKey(key)) { //키를 갖고 있으면 그 값을 리스트에 넣고 해당 문자열을 삭제한다.
//						System.out.print("key: "+key+" ");
						ans.add(dic.get(key));
						msg=msg.replaceFirst(key, "");
						if(msg.length()!=0) { //입력에 남아있는 문자열이 있으면 한자 더 붙여서 사전에 등록한다
							key= key+msg.charAt(0);
//							System.out.println("reg: "+key);
							//사전에 등록한다.
							dic.put(key, dic.size()+1);
						}
						break;
					}
					
				}
			}
			answer = new int[ans.size()];
			for(int i=0;i<ans.size();i++) {
				answer[i] = ans.get(i);
			}
			
			return answer;
		}
	}
}

