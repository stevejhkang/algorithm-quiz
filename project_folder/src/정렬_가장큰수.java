import java.util.Arrays;
import java.util.Collections;

public class 정렬_가장큰수 {
	public static void main(String[] args) {
		
		String[] numbers = {"3", "30", "34", "5", "9"};
		
		Solution s = new Solution();
		System.out.println(s.solution(numbers));
	}
	
}

class Solution {
    public String solution(String[] numbers) {
        String answer = "";
        
        Arrays.sort(numbers, Collections.reverseOrder());
        
        System.out.println(Arrays.toString(numbers));
        return answer;
    }
}