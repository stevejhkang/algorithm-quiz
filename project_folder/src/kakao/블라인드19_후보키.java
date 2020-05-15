package kakao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 7. 오후 9:50:53
 * @category 모든 경우를 다 따져봐도 10만밖에 안되므로 완전탐색이다. 조합+집합API 
* @level 4
* @problem_description 후보키의 최대 개수를 구하라
* 모든 학생은 각자 유일한 학번을 가지고 있다. 따라서 학번은 릴레이션의 후보키가 될 수 있다.
* 또한 이름과 전공을 같이 사용하면 모든 튜플을 유일하게 식별가능하므로 후보키가 될 수 있다.
* 이름 전공 학년도 가능하지만 최소성을 만족하지 못한다.
* 컬럼의 길이는 1~8이하이다.
* 로우의 길이는 1 이상 20이하이다
* 문자열의 길이는 1이상 8이하이며, 알파벳 소문자와 숫자로만 이루어져있다.
* 모든 튜플은 유일하게 식별 가능하다. 중복되는 튜플은 없다.
* 
* @solving_description 
* 1개부터 8개까지 전부 컬럼을 선택하는 경우를 뽑아 전부를 비교해서 중복되는지를 확인한다.
* 그런데 1번부터 시작해서 k개로 만들어진 컬럼 집합에 1~k-1개 컬럼집합으로 만든 후보키가 전부 들어가 있으면
* 해당 케이스는 없는 것이다.
* 그러면 최소성을 어떻게 구하지... 이게 핵심
* 
* 그리고 candidates 집합에 넣기 위해 새로 객체를 생성해서 넣어줘야 나중에 조합때 삭제한게 반영이 안된다.
* 
*/

public class 블라인드19_후보키 {
	public static void main(String[] args) {
		String [][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, 
				{"300", "tube", "computer", "3"}, 
				{"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, 
				{"600", "apeach", "music", "2"}};
		System.out.println(new Solution().solution(relation));
	}
	static class Solution {
		static ArrayList<HashSet<Integer>> candidates; //현재까지 계산된 후보키 집합
		static int col_len=0; //총 컬럼 개수
		private static HashSet<Integer> collist; //키집합을 제작하기 위한 Set
		static String[][] relations;//static을 위해
		
	    public int solution(String[][] relation) {
	        int answer = 0;
	        candidates = new ArrayList<HashSet<Integer>>();
	        //데이터를 복사
	        relations = new String[relation.length][];
	        for(int i=0;i<relation.length;i++) {
	        	relations[i] = relation[i].clone();
	        }
	        
	        col_len = relation[0].length;
	        
	        //후보키를 컬럼의 개수에 따라 생성을 한다.
	        for(int col = 1;col<=col_len;col++) {
//	        	collist = new ArrayList<Integer>();
	        	collist = new HashSet<Integer>();
	        	dfs(0,col,0);
	        }
	        answer = candidates.size();
	        return answer;
	    }// solution
	    
	    static void dfs(int r,int col,int index) { //col개의 col조합을 만들어주는 함수  조합
	    	if(r==col) {
	    		// 여기서 collist에 이전 후보키와 중복되지 않는지 확인하고 여기서 containsAll를 사용한다.
	    		boolean can_search=true;

	    		for(HashSet<Integer> candidate: candidates) {
	    			if(collist.containsAll(candidate)) { //collist안에 지금까지 만든 후보키가 포함되어 있는지 확인
	    				can_search=false;
	    				break;
	    			}
	    		}

	    		//가능하면 collist를 통해서 튜플이 다다른지 확인한다.
	    		if(can_search) {
	    			//search: 하나라도 똑같은게 있으면 끝낸다.
	    			boolean iden = true;
	    			out: for(int i=0;i<relations.length;i++) {
	    				for(int j=0;j<relations.length;j++) {
	    					if(i==j) continue;
	    					int cnt=0;
	    					for(int col_num : collist) {
	    						
	    						//col 전부가 같으면 같은거가 하나라도 다른게 있으면 서로 다른 거임.
	    						if(relations[i][col_num].equals(relations[j][col_num])) { 
	    							cnt++;
	    						}
	    					}
	    					if(cnt==collist.size()) {//완전히 같으면 안된다.
	    						iden = false;
	    						break;
	    					}
	    				}
	    			}
	    			if(iden) { //식별가능하면 candidates set에 넣는다.
	    				HashSet<Integer> candidate = new HashSet<Integer>(collist);
	    				candidates.add(candidate);
	    			}
	    		}
	    		return;
	    	}
	    	for(int i=index;i<col_len;i++) {
	    		collist.add(i);
	    		dfs(r+1,col,i+1);
	    		collist.remove((Integer)i); //r번째 인덱스 삭제
	    	}
	    }
	}//class
}
