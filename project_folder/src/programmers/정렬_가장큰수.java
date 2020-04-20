package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class 정렬_가장큰수 {
	public static void main(String[] args) {
		Solution s = new Solution();
//		int[] arr = {6,10,2};
		int[] arr = {3, 30, 34, 5, 9};
		s.solution(arr);
	}
	static class Solution {
	    public String solution(int[] numbers) {
	        String answer = "";
	        
	        String[] arr= new String [numbers.length];
	        for(int i=0;i<numbers.length;i++) {
	        	arr[i] = (new Integer(numbers[i])).toString();
	        }
	        //1000이하니까 한자리씩 비교하자
	        Arrays.sort(arr,new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					if(o1.length()==o2.length()) {//자리수 같으면 숫자가 큰거
						for(int i=0;i<o2.length();i++) {
							if(o1.charAt(i)==o2.charAt(i)) continue;
							return (o1.substring(i, i+1).compareTo(o2.substring(i,i+1)))*-1;
						}
					}
					else { //다르면 앞자리부터 비교
						int len = Math.min(o1.length(), o2.length());
						int i=0;
						for(;i<len;i++) {
							if(o1.charAt(i)==o2.charAt(i)) continue;
							return (o1.substring(i, i+1).compareTo(o2.substring(i,i+1)))*-1;
						}
						if(len==i) { //다음 원소보다 빠르면
							
							if(o1.length()<o2.length()) {
								for(;i<o2.length();i++) {
									if(o1.charAt(len-1)==o2.charAt(i)) continue;
									return (o2.substring(len-1, len).compareTo(o1.substring(i,i+1)))*-1;
								}
							}
							else {
								for(;i<o1.length();i++) {
									if(o2.charAt(len-1)==o1.charAt(i)) continue;
									return (o1.substring(len-1, len).compareTo(o2.substring(i,i+1)))*-1;
								}
							}
						}
					}
					return 0;
				}
			});
	        
	        System.out.println(Arrays.toString(arr));
	        
	        return answer;
	    }
	}
}
