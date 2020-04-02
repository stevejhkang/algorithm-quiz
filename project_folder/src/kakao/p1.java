package kakao;

import java.util.Stack;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 28. 오후 2:03:44
 * @category 
* @problem_description 이차원배열로 선언하고 뽑아 쭉내려가면서 0이 아닌 것을 뽑아서 스택에 쌓는다
* 이때 스택 맨위의 것과 같으면 그것을 빼내고 +2를 해주면 된다. 
* @solving_description 
*/

public class p1 {
	public static void main(String[] args) {
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] moves= {1,5,3,5,1,2,1,4};
		
		Solution s = new Solution();
		int ans =s.solution(board, moves);
		System.out.println(ans);
	}
	
	
	static class Solution {
	    public int solution(int[][] board, int[] moves) {
	        int answer = 0;
	        int n = board.length;
//	        System.out.println(n);
	        Stack<Integer> stack = new Stack<>();
	        for(int i=0;i<moves.length;i++) {
	        	int next_col = moves[i]-1;
	        	int row = 0;
	        	boolean skip = false;
	        	int doll=0;
	        	while(true) {
	        		//해당 컬럼의 0부터 쭉 찾아내려간다.
	        		//범위 벗어나면 그냥 취소
	        		if(row>=n) {
	        			skip= true;
	        			break;
	        		}
//	        		System.out.println(row);
	        		//인형의 종류를 받아온다.
	        		doll = board[row][next_col];
	        		//인형이 0이 아니면
	        		if(doll!=0) {
	        			board[row][next_col]=0;
	        			break;
	        		}
	        		//0이면 계속 내려간다.
	        		row++;
	        	}
	        	//0이 아닌 인형을 가져왔으면 스택에 담아준다.
	        	if(!skip) {
//	        		System.out.println(next_col+1);
	        		
	        		//비었으면 넣어주기만 한다.
	        		if(stack.isEmpty()) {
	        			stack.push(doll);
	        			continue;
	        		}
	        		//먼저 가장 위의 것을 확인한다
	        		int top = stack.peek();
	        		//종류가 같으면 빼주고 +2 다르면 그냥 넣어줌
	        		if(top==doll) {
	        			answer+=2;
	        			stack.pop();
	        		}
	        		else {
	        			stack.push(doll);
	        		}
//	        		System.out.println("result: "+answer);
	        	}
	        }
	        
	        return answer;
	    }
	}
	
	
	
}
