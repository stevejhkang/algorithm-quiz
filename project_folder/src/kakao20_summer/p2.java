package kakao20_summer;

import java.util.ArrayList;

public class p2 {
	public static void main(String[] args) {
		String expression = "100-200*300-500+20";
//		String expression = 	"50*6-3*2";
		System.out.println(new Solution().solution(expression));
	}
	static class Solution {
	    static private ArrayList<String> usedExp;
		static private ArrayList<Long> answers;
		static private boolean[] visit;
		static private ArrayList<String> prior;
		static private String[] numberString;
		static private int max;

		public long solution(String expression) {
	        long answer = 0;
	        
	        numberString = expression.replace("-", ",-,")
	        		.replace("*",",*,").replace("+", ",+,").split(",");
	        int[] numbers= new int[numberString.length];
	        int index = 0;
	        for(int i=0;i<numberString.length;i++) {
	        	System.out.println(numberString[i]);
	        }
//	        for(int i=0;i<numberString.length;i++) {
//	        	if(numberString.)
//	        	numbers[i] = 
//	        }
//	        for(String s :numberString) {
//	        	numbers[index]= Integer.parseInt(s);
//	        	index++;
//	        }
	        String[] exp = {"+","-","*"};
	        
	        usedExp = new ArrayList<>();
	        for(String s : numberString) {
	        	for(int i=0;i<3;i++) {
	        		if(s.equals(exp[i])) {
	        			if(!usedExp.contains(s)) {
	        				usedExp.add(s);
	        			}
	        			break;
	        		}
	        	}
	        }
	        visit = new boolean[usedExp.size()];
	        prior = new ArrayList<String>();
	        max = Integer.MIN_VALUE;
	        dfs(0);
	        
	        System.out.println(max);
	        
	        return answer;
	    }
	    void dfs(int r) {
	    	if(r==usedExp.size()) {
	    		System.out.println(prior);
	    		calc();
	    		return;
	    	}
	    	for(int i=0;i<usedExp.size();i++) {
	    		if(!visit[i]) {
	    			visit[i]= true;
	    			prior.add(usedExp.get(i));
	    			dfs(r+1);
	    			prior.remove(r);
	    			visit[i]= false;
	    		}
	    	}
	    }
	   static void calc() {
		   int index=0;
		   String oper = prior.get(index);
		   ArrayList<String> copy = new ArrayList<String>();
		   for(String s: numberString) {
			   copy.add(s);
		   }
		   while(true) {
			   if(copy.size()==1) {
				   int result = Integer.parseInt(copy.get(0));
				   result = Math.abs(result);
				   if(max<result) {
					   max= result;
				   }
				   break;
			   }
			   int n = copy.size();
			   for(int i=0;i<n;i++) {
				   String now = copy.get(i);
				   if(oper.equals("+")) {
					   if(now.equals(oper)) { //앞뒤로 계산
						   Integer result= Integer.parseInt(copy.get(i-1))+
								   Integer.parseInt(copy.get(i+1));
						   copy.remove(i-1);
						   copy.remove(i-1);
						   copy.remove(i-1);
						   copy.add(result.toString());
						   System.out.println(result);
						   i=0;
						   n=copy.size();
					   }
				   }
				   else  if(oper.equals("-")) {
					   if(now.equals(oper)) { //앞뒤로 계산
						   Integer result= Integer.parseInt(copy.get(i-1))-
								   Integer.parseInt(copy.get(i+1));
						   copy.remove(i-1);
						   copy.remove(i-1);
						   copy.remove(i-1);
						   copy.add(result.toString());
						   System.out.println(result);
						   
						   i=0;
						   n=copy.size();
					   }
				   }
				   else if(oper.equals("-")) {
					   if(now.equals(oper)) { //앞뒤로 계산
						   Integer result= Integer.parseInt(copy.get(i-1))-
								   Integer.parseInt(copy.get(i+1));
						   copy.remove(i-1);
						   copy.remove(i-1);
						   copy.remove(i-1);
						   copy.add(result.toString());
						   System.out.println(result);
						   i=0;
						   n=copy.size();
					   }
				   }
			   }
		   }
	   }
	}
}
