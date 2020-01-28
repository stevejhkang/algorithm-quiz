import java.util.Stack;
//연속 두개로 )) 오면 하나 추가
//()오면 현재 스택의 길이만큼 추가
class Solution {
    public int solution(String arrangement) {
        int answer = 0;
        Stack<Character> stk= new Stack<>();
        
        for(int i=0;i<arrangement.length();i++) {
        	char c=arrangement.charAt(i);
        	if(c=='(') {
        		stk.push(c);
        	}
        	else if(c==')') {
        		char p=stk.pop();
        		if(p=='('&&arrangement.charAt(i-1)=='(') {
        			answer+=stk.size();
        		}
        		else if(arrangement.charAt(i-1)==')') {
        			answer++;
        		}
        	}
        }
        
        return answer;
    }
}
