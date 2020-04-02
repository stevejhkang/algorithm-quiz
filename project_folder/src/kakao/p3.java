package kakao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class p3 {
	public static void main(String[] args) {
//		String[] user_id= {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//		String[] banned_id= {"fr*d*", "abc1**"};
		
		String[] user_id= {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id= {"*rodo", "*rodo", "******"};
//		
//		String[] user_id= {"frodo", "fradi", "crodo", "abc123", "frodoc"};
//		String[] banned_id= {"fr*d*", "*rodo", "******", "******"};
		
		Solution s = new Solution();
		System.out.println(s.solution(user_id, banned_id));
	}
//	static class Solution {
//	    public int solution(String[] user_id, String[] banned_id) {
//	        int answer = 0;
//	        
//	        
//	        
//	        List[] li=  new List[banned_id.length];
//	        for(int i=0;i<li.length;i++) {
//	        	li[i] = new ArrayList<Integer>();
//	        }
//	        
//	        for(int i=0;i<banned_id.length;i++) {
//	        	banned_id[i]=banned_id[i].replaceAll("\\*", "\\.");
////	        	System.out.println(banned_id[i]);
//	        	for(int j=0;j<user_id.length;j++) {
//	        		if(user_id[j].matches(banned_id[i])) {
//	        			li[i].add(j+1);
//	        		}
//	        	}
//	        }
//	        HashSet<String> getSize = new HashSet<String>();
//	        for(int i=0;i<banned_id.length;i++) {
//	        	Collections.sort(li[i]);
//	        	StringBuilder s = new StringBuilder();
//	        	for(int j=0;j<li[i].size();j++) {
//	        		s.append(li[i].get(j));
//	        	}
//	        	String stemp= new String(s);
//	        	getSize.add(stemp);
//	        }
//	        answer=getSize.size();
//	       
//	      
//	        return answer;
//	    }
//	}
}
class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        
        
        List[] li=  new List[banned_id.length];
        for(int i=0;i<li.length;i++) {
        	li[i] = new ArrayList<Integer>();
        }
        
        for(int i=0;i<banned_id.length;i++) {
//        	banned_id[i]=banned_id[i].replaceAll("\\*", "\\.");
//        	System.out.println(banned_id[i]);
        	for(int j=0;j<user_id.length;j++) {
//        		if(user_id[j].matches(banned_id[i])) {
//        			
//        		}
        		boolean can= true;
        		int idx=0;
        		if(user_id[j].length()!=banned_id[i].length()) {
    				can=false;
    			}
        		else {
        			while(idx<banned_id[i].length()) {
            			//글자수 다르면 false
            			
            			if(banned_id[i].charAt(idx)=='*') {
            				idx++;
            				continue;
            			}
            			if(banned_id[i].charAt(idx)!=user_id[j].charAt(idx)) {
            				can=false;
            				break;
            			}
            			idx++;
            		}
        		}
        		
        		if(can) {
        			li[i].add(j+1);
        		}
        	}
        }
        HashSet<String> getSize = new HashSet<String>();
        for(int i=0;i<banned_id.length;i++) {
        	Collections.sort(li[i]);
        	StringBuilder s = new StringBuilder();
        	for(int j=0;j<li[i].size();j++) {
        		s.append(li[i].get(j));
        	}
        	String stemp= new String(s);
        	System.out.println(stemp);
        	getSize.add(stemp);
        }
        answer=getSize.size();
       
      
        return answer;
    }
}