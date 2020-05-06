package kakao;
import java.util.Arrays;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 5. 오후 4:25:48
 * @category 문자열 처리, 정렬
* @level 3
* @problem_description 
* 1. 파일을 이름 순으로 정렬하면 나중에 만들어진 ver10이 ver9보다 먼저 표시되기 때문
* 2. img12 img10 img2 img1일경우 img1 img10 img12 img2 순이 되지만
* 숫자순으로 정렬된경우 img1 img2 img10 img12 순이 훨씬 자연스럽다.
* 
*  단순한 문자 코드 순이 아닌 파일명에 포함된 숫자를 반영한 정렬 기능을 구현하고자 한다.
*  소스 파일 저장소에 저장된 파일명은 100글자 이내로, 
*  영대소문자, 숫자, 공백" ", 마침표".", 빼기"-"부호만으로 이루어져 있다.
*  파일명은 영문자로 시작하며, 숫자를 하나 이상 포함하고 있다.
*  
*  파일명은 크게 HEAD, NUMBER, TAIL 부분으로 구성된다.
*  HEAD: 숫자가 아닌 문자로 최소 한 글자 이상
*  NUMBER: 한 글자에서 최대 다섯글자 사이의 연속된 숫자로 앞쪽에 0이 올 수 있다.
*  즉 0부터 99999 사이의 숫자,
*  TAIL: 그 나머지 부부능로 여기에는 숫자가 다시 나타날 수 도 있으며, 아무것도 없을 수 도 있다.
*  
*  파일명은 우선 해드부분을 기준으로 사전순 정렬, 대소문자 구분 안함
*  대소문자 차이가 같은 경우, 넘버 순으로 정렬
*  해드와 넘버가 같은 경우 입력이 들어온 순서대로 유지
*  
*  files는 1000개 이하의 파일명을 포함하는 문자열 배열
*  각 파일 명은 100글자 이하길이, 
* @solving_description 
* 일단 1000개를 정렬하려면 1000log1000 = 1만이다.
* 그런데 파일명하나하나 탐색해서 하려면 100*1만 100만이 된다.
* 해도 괜찮을 것 같음.. 객체정렬로 100만까지는 해도 될듯
* 
* tail부분이 숫자로 시작하는 경우.....
* 
* 문자열처리
* 처음에는 HEAD부분에 넣다가 나중에 처음 숫자가 나오면 그 때부터 number에 넣기 시작한다.
* 문자열 처리 방법은 
* 문자열의 [A-Za-z]를 넣어서 문자를 판단하고 나머지 예외 기호는 equals(".") 이런식으로 판단한다.
* 숫자는 [0-9]를 이용해서 판단한다. 숫자가 나오기 시작하면 boolean을 true로 만들어 주고 
* 만약 길이가 5이상이거나 새로운 문자가 나오면 그 뒤는 전부 tail에 넣어준다.
* 
* 
*/

public class 블라인드18_파일명정렬 {
	public static void main(String[] args) {
//		String[] files = {"img9d99.png", "img10d10.png", "img013.png", "img014.png","img000111.JPG", "IMG0011.GIF" };
		String[] files = {"img09999999999", "img9998999", "img013", "img014","img00011.", "IMG0011111." };
//		String[] files = {"F015", "F15" };
		
//		String[] files = {"img129999.png", "img109999.png", "img0299999.png", "img02998.png", "IMG019999.GIF", "img299999.JPG"};
//		String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
		System.out.println(Arrays.toString(new Solution().solution(files)));
	}
	static class Solution {
		  public String[] solution(String[] files) {
		      
		      int len = files.length;
		      file[] store = new file[len];
		      
		      String letterReg = "[A-Za-z]" ;
		      String numberReg= "[0-9]";
		      //그냥 그것 영문자이거나 matches를 쓰고 -나 " "띄어쓰기 .이 들어가는 지를 확인하면 될듯
		      
		      for(int i=0;i<len;i++) {
		    	  StringBuilder head = new StringBuilder();
		    	  
		    	  StringBuilder number = new StringBuilder();
		    	  StringBuilder tail = new StringBuilder();
//		    	  int numberIdx = files[i].
		    	  boolean numberStart= false;
		    	  boolean tailStart = false;
		    	  for(int index=0;index<files[i].length();index++) {
		    		  String letter = files[i].substring(index,index+1);
		    		  
		    		  //숫자가 처음나오는 인덱스를 찾는다.
		    		  if(!tailStart&&(letter.equals(" ")||letter.equals(".")||letter.equals("-")||letter.matches(letterReg))) {
		    			  head.append(letter);
//		    			  numberStart=true;
		    			  continue;
		    		  }
		    		  //numberStart&&
		    		  else if(letter.matches(numberReg)&&number.length()<5) { //길이가 99999 안 넘는지 확인
		    			  number.append(letter);
		    			  tailStart= true;
		    			  continue;
		    		  }
		    		  //&&letter.equals(" ")||letter.equals(".")||letter.equals("-")||letter.matches(letterReg)
		    		  else if(tailStart) { //나머지는 다 tail로
		    			  tail.append(files[i].subSequence(index, files[i].length()));
		    			  break;
		    		  }
		    		  
		    	  }//for index
		    	  store[i] = new file(head.toString(),number.toString(),Integer.parseInt(number.toString()) ,tail.toString());
		      }
		      Arrays.sort(store);
		      
		      String[] answer = new String[len];
		      
		      for(int i=0;i<len;i++) {
		    	  System.out.println(store[i]);
		    	  answer[i] = store[i].head+store[i].numberString+((store[i].remain==null)?"":store[i].remain);
		      }
		      
		      return answer;
		  }//solution
		  
		  static class file implements Comparable<file>{
			  String head;
			  String numberString;
			  int number;
			  String remain;
			public file(String head, String numberString, int number, String remain) {
				super();
				this.head = head;
				this.numberString = numberString;
				this.number = number;
				this.remain = remain;
			}
			@Override
			public String toString() {
				return "file [head=" + head + ", numberString=" + numberString + ", remain=" + remain + "]";
			}
			@Override
			public int compareTo(file o) {
				// TODO Auto-generated method stub
				if((this.head.toLowerCase()).compareTo(o.head.toLowerCase())==0) { //head가 같으면 number 순으로
					return Integer.compare(this.number, o.number);
				}
				else {
					return (this.head.toLowerCase()).compareTo(o.head.toLowerCase());
				}
			}
			  
		  }//files
		}
}
