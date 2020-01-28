import java.util.HashMap;

//https://programmers.co.kr/learn/courses/30/lessons/42578

class Solution {
    public int solution(String[][] clothes) {
                                                                                    
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        
        for (int i = 0; i < clothes.length; i++) {
			if(hm.containsKey(clothes[i][1]))
				hm.replace(clothes[i][1], hm.get(clothes[i][1])+1);
			else {
				hm.put(clothes[i][1],1);
			}
		}
    
        //+1은 해당 종류의 옷가지를 선택하지 않을 경우를 말한다. 
        //(A종류 옷가지수 +1)*(B종류 옷가지수 +1)*(C종류 옷가지수+1)-1
        //-1을 한 이유는 모든 종류의 옷을 선택하지 않을 경우를 말한다.
        int answer=1;
        for (int value : hm.values()) {
        	answer*=(value+1);
		}
        answer-=1;
        return answer;
    }
}
