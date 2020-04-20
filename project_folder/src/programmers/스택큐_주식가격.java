package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class 스택큐_주식가격 {
	public static void main(String[] args) {
		Solution s = new Solution();
//		int[] prices = {1,2,3,2,3};
//		int[] prices = {1,2,3,2,3,1};
		int[] prices = {3,1};
		System.out.println(Arrays.toString(s.solution(prices)));
	}
	static class Solution {
	    private ArrayList<stock> answerlist;
		public int[] solution(int[] prices) {
	        int[] answer;
	        
	        Stack<stock> stack = new Stack<stock>();
	        answerlist = new ArrayList<stock>();
	        
	        for(int i=0;i<prices.length;i++) {
	        	if(i==0) { //처음이면 그냥 넣어주고
	        		stack.push(new stock(i, prices[i]));
	        		continue; //끝내고
	        	}
	        	while(!stack.isEmpty()) {
	        		stock top = stack.peek();
	        		int value = top.value;
	        		int index = top.index;
	        		
	        		if(prices[i]>=value) { //현재 값이 과거값보다 크거나 같으면 그냥 종료한다.
	        			break;
	        		}
	        		else {
	        			//아니면 계속 빼내서 정답리스트에 걸린 시간과 인덱스를 넣어준다.
	        			stack.pop();
	        			answerlist.add(new stock(index, i-index));
	        		}
	        	}
	        	stack.push(new stock(i, prices[i])); //그 다음 스택에 넣어준다.
	        }
	        while(!stack.isEmpty()) { //끝까지 남아 있는 애들은 마지막 인덱스에서 그 놈의 인덱스를 빼준다.
	        	stock top = stack.pop();
	    		int value = top.value;
	    		int index = top.index;
	    		answerlist.add(new stock(index, (prices.length-1)-index)); //살아 남은 시간
	        }
	        
	        Collections.sort(answerlist);
	        int size = answerlist.size();
	        answer = new int[size];
	        
	        for(int i=0;i<size;i++) {
	        	answer[i] = answerlist.get(i).value;
	        }
	        
	        return answer;
	    }
	}
	static class stock implements Comparable<stock>{
		int index, value;

		public stock(int index, int value) {
			super();
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(stock o) {
			// TODO Auto-generated method stub
			return (new Integer(this.index)).compareTo((new Integer(o.index))); //인덱스 순으로 정렬해서 그대로 넣어준다.
		}
		
	}
}

