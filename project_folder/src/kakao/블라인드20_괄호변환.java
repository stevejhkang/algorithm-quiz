package kakao;
import java.util.Stack;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 8. 오전 10:09:07
 * @category 재귀와 스택으로 올바른 괄호 찾기
* @level 2
* @problem_description 
* 짝 수를 어떻게 맞출 것인가 닫힌개수 열린개수 체크를 한다. 그래서 0이 되는 순간 -> 균형잡힌 괄호문자열
* 
* 
* @solving_description 
* 분리할 수 없는 균형잡힌 문자열 만드는 방법: 열린 괄호가 나오면 +1 닫힌건 -1 해서 0이 될때 해당 문자열 서브스트링한다.
* 올바른 괄호문자열 체크하는 방법: 열린괄호면 스택에 넣고 닫힌 괄호면 스택이 비어있으면 false 안 비어있을 때 stack의
* 탑이 열린괄호가 아니면 false;
* 
*/

public class 블라인드20_괄호변환 {
	public static void main(String[] args) {
//		String p =	"(()())()";
//		String p = ")(";
		String p = 	"()))((()";
//		new Solution().solution(p);
		System.out.println(new Solution().solution(p));
	}
	static class Solution {
	    public String solution(String p) {
	        String answer = "";
	        
	        //균형잡힌 괄호 문자열을 계산해서 분리해서 넣어주어야 한다.
	        
	        answer = dfs("",p);
	        
	        return answer;
	    }
	    
	    static String dfs(String u, String v) {
	    	//u는 균형잡힌 문자열이다.
	    	//이제 올바른 괄호 문자열인지 확인해야한다. 스택을 사용하면 될듯?
	    	//여는 괄호이면 push
	    	//닫는 괄호이고 스택 탑이 여는 괄호다 그러면 해당 여는 괄호를 팝시킨다.,
	    	//없으면 false인데 이럴일은 없을듯..
	    	
	    	
	    	if(u.equals("")) { //u가 비었으면 분리 불가능한 균형잡힌 u를 계산한다.
	            int balance=0;
	            for(int i=0;i<v.length();i++) {
	            	if(v.charAt(i)=='(') {
	            		balance++;
	            	}
	            	else if(v.charAt(i)==')') {
	            		balance--;
	            	}
	            	if(balance==0) {
	            		//0부터 현재 인덱스까지를 subString하고 dfs에 담아준다.
	            		u = v.substring(0,i+1);
	            		v = v.substring(i+1,v.length());
//	            		System.out.println(u);
//	            		System.out.println(v);
	            		return dfs(u,v);
	            	}
	            }
	            
	    	}
	    	else { //u는 무조건 분리할 수 없는 균형 문자열이다. 그러면 올바른 문자열인지 확인해야한다.
	    		Stack<Character> stack = new Stack<Character>();
	        	
	        	boolean correct=true;
	        	for(int i=0;i<u.length();i++) {
	        		char c = u.charAt(i);
	        		
	        		if(c=='(') {
	        			stack.push(c);
	        		}
	        		else {
	        			if(!stack.isEmpty()) {
	        				char top = stack.peek();
	        				if(top=='(') { //top이 여는 괄호이면
	        					stack.pop();
	        				}
	        			}
	        			else { //비어있으면 false이다.
	        				correct=false;
	        				break;
	        			}
	        		}
	        	}//for i - u
	        	//만약 u가 올바른 문자열이라면 v에 대해 처리를 해준후 u에 붙힌 후 반환한다.
	        	if(correct) {
	        		return u+dfs("",v);
	        	}
	        	else { //올바른 문자열이 아니라면
	        		StringBuilder stringBuilder = new StringBuilder();
	        		stringBuilder.append("("+dfs("",v)+")");
	        		//u의 첫번째와 마지막 문자를 제거하고 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
	        		u = u.substring(1,u.length()-1);
//	        		System.out.println(stringBuilder.toString());
	        		for(int i=0;i<u.length();i++) {
	        			if(u.charAt(i)=='(') {
	        				stringBuilder.append(')');
	        			}
	        			else {
	        				stringBuilder.append('(');
	        			}
	        		}
	        		return stringBuilder.toString();
	        	}
	    	}
			return v;
	    	
	    	
	    	
	    }
	}
}
