package kakao20_summer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 9. 오후 3:44:06
 * @category 
* @level 3
* @problem_description 
* 모든 종류의 보석을 적어도 1개이상 포함하는 가장 짧은 구간을 찾아서 구매
* 
* @solving_description 
*/

public class p3 {
	public static void main(String[] args) {
//		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//		String[] gems = {"AA", "AB", "AC", "AA", "AC"};
//		String[] gems = {"XYZ", "XYZ", "XYZ"};
		String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
		System.out.println(Arrays.toString(new Solution().solution(gems)));
	}
	static class Solution {
	    public int[] solution(String[] gems) {
	        int[] answer = new int[2];
	        
	        int start = 0;
	        int end = 0;
	        
	        //전부 돌면서 총 몇종류인지 파악한다.
	        HashSet<String> category = new HashSet<>();
	        for(int i=0;i<gems.length;i++) {
	        	category.add(gems[i]);
	        }
	        int all_kind = category.size(); //총 종류 개수
	        
	        int n = gems.length;
	        
	        HashSet<String> set = new HashSet<>(); //현재 
	        HashMap<String, Integer> map = new HashMap<>();
	        
	        
	        int min_length = Integer.MAX_VALUE;
	        //0번째 부터 체크하면서 길이를 파악해간다.
	        
	        
	        while(true) {
	        	//set의 크기가 all_kind보다 작으면 end++ 하고 
	        	//Map 과 Set에 추가한다.
	        	
	        	if (set.size()>=all_kind){ //만약 set.size()==all_kind가 되면 start++
	        		int length = end-start+1;
	        		if(min_length>length) {
	        			min_length=length;
	        			answer[0] = start+1;
	        			answer[1]= end;
	        		}
	        		String key = gems[start];
	        		map.put(key, map.get(key)-1);
	        		if(map.get(key)==0) { //더이상 해당 종류의 보석이 0개가 되면
	        			//set에서도 지운다.
	        			set.remove(key);
	        		}
	        		start++;
	        	}
	        	//set의 크기랑 같아지면 현재 저장된 length값과
	            //해당 start,end를 업데이트한다. 그리고 start++해본다.
	        	else if(end==n) {
	        		break;
	        	}
	        	else {
	        		
	        		String key = gems[end++];
	        		set.add(key);
	        		if(!map.containsKey(key)) {
	        			map.put(key, 1);
	        		}
	        		else {
	        			map.put(key, map.get(key)+1);
	        		}
	        	}
	        	
	        }
	        
	        return answer;
	    }
	}
}
