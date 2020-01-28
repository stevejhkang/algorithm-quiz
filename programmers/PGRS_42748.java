import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
    	int[] answer = new int[commands.length];
    	
        for(int i=0;i<commands.length;i++) {
        	int[] sub_arr=Arrays.copyOfRange(array, commands[i][0]-1,commands[i][1]);
        	Arrays.sort(sub_arr);
        	answer[i]=sub_arr[commands[i][2]-1];
        }
        return answer;
    }
}
