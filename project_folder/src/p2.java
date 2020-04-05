
/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 5. 오전 10:18:41
 * @category 
* @problem_description 
* 두 응시자가 제출한 답안지에 대해서, 부정행위 가능성 지수 계산
* 의심문항 = 같은 선택지를 골랐으나 오답인 문항
* 부정행위 가능성 지수 = 총 의심문항의 수 + (가장 긴 연속된 의심문항의 수)^2  : 가장 긴 연속된이라는게 연속으로 몇번 같았는지를 의미하는 것 같음
* 응시자수는 200명 이하 문항수는 100개 이하
* 이중포문 돌려도 4백만이라서 완탐가능
* @solving_description 
*/

public class p2 {
	public static void main(String[] args) {
		Solution s = new Solution();
		
//		String answer_sheet="4132315142";
//		String[] sheets= {"3241523133","4121314445","3243523133","4433325251","2412313253"};
//		
//		String answer_sheet="53241";
//		String[] sheets= {"53241", "42133", "53241", "14354"};
		
		String answer_sheet="24551";
		String[] sheets= {"24553", "24553", "24553", "24553"};
		
		System.out.println(s.solution( answer_sheet, sheets));
	}
	static class Solution {
	    public int solution(String answer_sheet, String[] sheets) {
	        int answer = -1;
	        
	        int max_conti=0;
	        int max_point = 0;
	        int max_sum=0;
	        int conti=0;
	        for(int i=0;i<sheets.length;i++) {
	        	for(int j=0;j<sheets.length;j++) {
	        		if(i==j) continue;
	        		int sum=0;
	        		for(int index=0;index<answer_sheet.length();index++) {
	        			//서로 답이 같고 오답인 문항
	        			if(sheets[i].charAt(index)==sheets[j].charAt(index)&&
	        					sheets[i].charAt(index)!=answer_sheet.charAt(index)) {
	        				sum++;
	        				conti++;
	        			}
	        			else { //다르면 0으로 만들어준다.
	        				max_conti=Math.max(conti, max_conti);	
	        				conti=0;
	        			}
	        		}// for index 전부 끝났을 때 제일 그중에서 제일 연속된 의심문항의 수를 갱신한다./
	        		max_sum=Math.max(max_sum,sum);
	        		max_conti=Math.max(conti, max_conti);
	        		int point = max_sum+max_conti*max_conti;
	        		max_point = Math.max(point, max_point);
	        	}
	        	
	        }
	        
	        return max_point;
	    }
	}
}
 