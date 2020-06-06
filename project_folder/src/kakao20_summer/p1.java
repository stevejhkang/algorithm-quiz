package kakao20_summer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 30. 오후 4:58:50
 * @category 
* @level 3
* @problem_description 
* 휴대폰에서 번호를 누르는데 가운데 라인 번호를 왼손과 오른손 중에서 현재 누르려는 번호와 더 가까운 손가락이 누르는 규칙이 있는 문제로
* 번호 인풋이 주어졌을 때 왼손 오른손 누른 순서를 출력
* 
* 해결방식
* 해당 번호를 키로 위치y,x를 map에 저장한다. 
* 누른 번호가 왼쪽 라인에 있으면 L를 추가하고 현재 위치를 해당 위치로 바꿔주고
* 오른쪽 라인에 있으면 R를 추가하고 현재 오른 손가락 위치를 바꿔주고
* 가운데 라인이면 현재 왼쪽, 오른쪽 손가락 중에 가까운 것을 비교하고 가까운 것의 알파벳을 추가하고 위치를 바꿔주는 식으로 진행
* 
* @solving_description 
*/

import java.util.ArrayList;
import java.util.HashMap;

public class p1 {
	public static void main(String[] args) {
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		
//		int[] numbers= {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
//		String hand = "left";
		
//		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
//		String hand = "right";
		
		System.out.println(new Solution().solution(numbers, hand));
	}
	static class Solution {
	    public String solution(int[] numbers, String hand) {
	        String answer = "";
	        
	        StringBuilder stringBuilder =new StringBuilder();
	        
	        //각 번호에 대한 포지션 번호를 저장해놓는다.
	        HashMap<Integer, int[]> map = new HashMap<>();
	        map.put(1, new int[] {0,0});
	        map.put(2,new int[] {0,1});
	        map.put(3, new int[] {0,2});
	        map.put(4, new int[] {1,0});
	        map.put(5,new int[] {1,1});
	        map.put(6,new int[] {1,2});
	        map.put(7, new int[] {2,0});
	        map.put(8, new int[] {2,1});
	        map.put(9, new int[] {2,2});
	        map.put(0,new int[] {3,1});
	        
	        int[] leftpo = {3,0};
	        int[] rightpo = {3,2};
	        
	        ArrayList<Integer> leftpush =new ArrayList<>();
	        leftpush.add(1); 
	        leftpush.add(4); 
	        leftpush.add(7); 
	        ArrayList<Integer> rightpush =new ArrayList<>();
	        rightpush.add(3);
	        rightpush.add(6);
	        rightpush.add(9);
	        
	        for(int i=0;i<numbers.length;i++) {
	        	int num = numbers[i];
	        	if(leftpush.contains((Integer)num)){
	        		stringBuilder.append("L");
	        		leftpo = map.get(num).clone();
	        	}else if(rightpush.contains((Integer)num)){
	        		stringBuilder.append("R");
	        		rightpo = map.get(num).clone();
	        	}
	        	else{ //거리비교
	        		int y = map.get(num)[0];
	        		int x = map.get(num)[1];
	        		int leftlen = Math.abs(leftpo[0]-y)+Math.abs(leftpo[1]-x);
	        		int rightlen = Math.abs(rightpo[0]-y)+Math.abs(rightpo[1]-x);
	        		
	        		if(leftlen==rightlen) { //거리 같으면 손잡이에 따라 다름
	        			if(hand.equals("left")) { //왼손잡이이면 
	        				stringBuilder.append("L");
	        				leftpo=  map.get(num).clone();
	        				
	        			}
	        			else { //오른손잡이
	        				stringBuilder.append("R");
	        				rightpo=  map.get(num).clone();
	        			}
	        		}
	        		else {
	        			if(leftlen>rightlen) { //오른쪽이 짧으면 오른쪽으로
	        				stringBuilder.append("R");
	        				rightpo=  map.get(num).clone();
	        			}
	        			else {
	        				stringBuilder.append("L");
	        				leftpo=  map.get(num).clone();
	        			}
	        		}
	        	}
	        }
	        
	        return stringBuilder.toString();
	    }
	}
}
