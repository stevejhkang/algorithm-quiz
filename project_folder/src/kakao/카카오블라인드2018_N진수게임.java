package kakao;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 5. 오후 2:29:17
 * @category 진법계산, 문자열 처리, 완전탐색 
* @level 2
* @problem_description 차례대로 숫자를 하나씩 차례대로 말한다.
* 1. 숫자를 0부터 시작해서 차례대로 말한다. 
* 2. 10이상의 숫자부터는 한자리씩 끊어서 말한다. 
* 0, 1, 2, 3, 4, ---- 9, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4
* 
*  코딩 동아리 일원은 이진수로 이 게임을 한다.
*  0 1 10 11 100 101 110 111 1000 1001
*  0, 1, 1, 0, 1, 1 ,1,0,0, 1,0,1, 1,1,0, 1,1,1, 1,0,0,0
*  
*  이진법에서 16진법까지 모든 진법으로 게임을 진행해보려한다.
*  진법에 따라 자신이 말해야할 숫자를 미리 출력해주는 프로그램을 만들자
*  
*  2<=n<=16 : 진법
*  0<t<=1000: 미리 구할 숫자의 갯수 
*  2<=m<=100: 게임에 참가하는 인원
*  1<=p<=m: 튜브의 순서
*  튜브의 순서 1000번 횟수를 받아와야 하므로 전체 순서 * 턴수인 m*t가 된다 10만번 연산 - 완전탐색가능
*  
*  
*  출력
*  튜브가 말해야하는 숫자 t개를 공백없이 차례대로 나타낸 문자열
* @solving_description 
* 
* 1. 일단 숫자를 끊임없이 해당 진법으로 바뀐 문자열로 바꾸고 그것을 한자리씩 센다.
* 2. 그리고 해당 순서가 turn%m == p-1일 경우에 스트링빌더에 추가한다.
* 
*/

public class 카카오블라인드2018_N진수게임 {
	public static void main(String[] args) {
		int n = 2;
		int t = 4;
		int m = 2;
		int p =1;
		System.out.println(new Solution().solution(n, t, m, p));
	}
	static class Solution {
		  public String solution(int n, int t, int m, int p) {
//		      String answer = "";
		      
		      int index =0;
		      Integer num=0;
		      StringBuilder stringBuilder = new StringBuilder();
		      while(index<t*m) {
		    	  
		    	  String transformed_num = num.toString(num, n);
		    	  //만약 직접 구한다면 일일히 구해서 문자열로 만든다음 앞에서부터 하나씩 해줘야 될듯...
//		    	  System.out.println(transformed_num+" ");
		    	  for(int i=0;i<transformed_num.length();i++) {
		    		 
		    		  if(index>=t*m) {
		    			  break;
		    		  }
		    		  if(index%m==p-1) {
		    			  stringBuilder.append(transformed_num.substring(i, i+1).toUpperCase());
		    		  }
		    		  index++;
		    	  }
//		    	  System.out.println(transformed_num);
		    	  num++;
		      }
		      String answer = stringBuilder.toString();
		      return answer;
		  }
		} 

}
