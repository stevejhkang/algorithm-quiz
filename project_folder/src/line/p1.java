package line;
//import java.util.Stack;
//
//public class p1 {
//	public static void main(String[] args) {
//		Solution s = new Solution();
//		
////		String inputString = "if (Count of eggs is 4.) {Buy milk.}";
////		String inputString = "Hello, world!";
////		String inputString ="line [plus]";
//		String inputString = ">_<";
//		System.out.println(s.solution(inputString));
//	}
//	static //여는 괄호랑 닫는 괄호만 짝지으면 된다 종류 상관없음
//	class Solution {
//	    public int solution(String inputString) {
//	        int answer = -1;
//	        //정상적인 괄호가 몇개 있는지 확인하는 거
//	        Stack<Character> stack = new Stack<Character>();
//	        int idx=0;
//	        int cnt[]= {0,0,0,0};
//	        int can=0;
//	        while(idx<inputString.length()) {
//	        	char now = inputString.charAt(idx);
//	        	if(now=='('||now=='{'||now=='['||now=='<') { //여는 괄호일 경우
////	        		if(stack.isEmpty()) { //빈경우 그냥 넣고 끝
////	            		stack.push(now);
////	            		cnt++;
////	            		continue;
////	            	}
////	        		//맨위를 확인해서 여는 것일때만 가능하다.
////	        		char top = stack.peek();
////	        		if(top==')'||top=='}'||top==']'||top=='>') {
////	        			stack.pop();
////	        			
////	        		}
//	        		if(now=='(') {
//	        			
//	        			cnt[0]++;
//	        		}
//	        		else if(now=='{') {
//	        			cnt[1]++;
//	        		}
//	        		else if(now=='[') {
//	        			cnt[2]++;
//	        		}
//	        		else {
//	        			cnt[3]++;
//	        		}
//	        		stack.push(now);
//	        	}
//	        	else if(now==')'||now=='}'||now==']'||now=='>'){ //닫히는 괄호인 경우
//	        		if(stack.isEmpty()) { 
//	        			answer=-1;
//	        			return -1;
//	        		}
//	        		
//	        		//맨위가 여는 것일 때만 넣을 수 있다.
//	        		char top = stack.peek();
//	        		if(top=='('||top=='{'||top=='['||top=='<') {
//	        			if(top=='(') {
//	        				if(cnt[0]!=0) {
//	        					cnt[0]--;
//	        				}
//	        			}
//	        			if(top=='{') {
//	        				if(cnt[0]!=0) {
//	        					cnt[0]--;
//	        				}
//	        			}
//	        			if(top=='[') {
//	        				if(cnt[0]!=0) {
//	        					cnt[0]--;
//	        				}
//	        			}
//	        			if(top=='(') {
//	        				if(cnt[0]!=0) {
//	        					cnt[0]--;
//	        				}
//	        			}
//	        			stack.pop();
//	        		}
//	        	}
//	        	idx++;
//	        }//while
//	        //다끝났을 때 비어있지 않으면 false;
//	        if(!stack.isEmpty()) {
//	        	return -1;
//	        }
//	        return cnt;
//	    }
//	}
//}
