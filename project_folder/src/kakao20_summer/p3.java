package kakao20_summer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 9. 오후 3:44:06
 * @category 투포인터 알고리즘
* @level 3
* @problem_description 
* 모든 종류의 보석을 적어도 1개이상 포함하는 가장 짧은 구간을 찾아서 구매
* 모든 종류의 보석을 포함하면서 가장 짧은 구간을 구하기 위해 투포인터 알고리즘을 사용. 구간의 시작부분에 대한 인덱스를 저장하는 start
* 가장 짧은 구간의 끝 부분에 해당하는 인덱스인 end를 두어 이 두 개의 인덱스를 옮겨가면서 제일 짧은 구간을 찾습니다.
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
	        
	        //1. 일단 전부 돌면서 셋에다 보석을 넣으면서 보석이 총 몇종류인지 파악한다.
	        HashSet<String> category = new HashSet<>();
	        for(int i=0;i<gems.length;i++) {
	        	category.add(gems[i]);
	        }
	        int all_kind = category.size(); //총 종류 개수
 	        int n = gems.length; //총 길이
	        
	        HashSet<String> set = new HashSet<>(); //2. 현재까지 모은 중복없는 보석의 종류 
	        HashMap<String, Integer> map = new HashMap<>(); //3. 구간내에 보석들이 몇번 등장했는지 저장하는 map
	        
	        int min_length = Integer.MAX_VALUE;
	        //0번째 부터 체크하면서 최소 길이를 파악해간다.
	        
	        while(true) {
	        	//set의 크기가 all_kind보다 작으면 모든 종류의 보석을 모은 것이 아니므로 end++ 하고
	        	//Map과 Set에 추가한다.
	        	
	        	//3. 만약 현재 구간에 모든 보석을 담고 있으면 start++ 옮겨서 더짧게 만들어본다.
	        	if (set.size()>=all_kind){ 
	        		int length = end-start+1;
	        		if(min_length>length) { //그때 현재까지 구한 길이보다 짧으면 제일 짧은 길이 갱신해준다.
	        			min_length=length;
	        			answer[0] = start+1; //그리고 인댁스를 갱신한다.
	        			answer[1]= end;
	        		}
	        		
	        		String key = gems[start]; //start인덱스의 보석 키를 이용해 
	        		map.put(key, map.get(key)-1); //보석 등장을 한번빼고 
	        		if(map.get(key)==0) { //더이상 해당 종류의 보석이 0개가 되면
	        			//set에서도 지운다.
	        			set.remove(key);
	        		}
	        		start++;
	        	}
	        
	        	else if(end==n) { //4. 그렇게 끝까지 가본다.
	        		break;
	        	}
	        	//2. 모든 종류의 보석을 구하지 못했으면 end인덱스를 늘리고 해당 보석을 set에 넣고 등장횟수를 늘려준다.
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
