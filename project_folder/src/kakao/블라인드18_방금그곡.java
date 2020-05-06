package kakao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 6. 오후 11:23:13
 * @category 문자열 파싱, 시간 환산, 완전탐색(2억), 객체정렬
* @level 3
* @problem_description 
* 궁금한 점
* 1. #처리를 어떻게 할 것인가?
* 뒤에 마지막에 #이 붙는지를 확인하면 되겠네! 나머지는 같아야 되니
* -> 문제해결할때 특정 조건을 아예 바꿔버리는 경우가 많은 것 같다.
* 여기서도 G#은 아예 하나의 문자이기 때문에 아예 g로 바꿔서 시작을 했다. 이렇게하면
* 굳이 #에 주목할게 아니라 G#하나로 보는게 맞기때문에 다른 문자로 치환하면 다루기가 더 편해질 것이다.
* 
* 2. 조건이 여러개일때 재생된 시간이 제일 긴 음악의 제목을 반환. 객체 소트
* 
* 3. 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
* -> 검색결과에 일치하는 음악들을 객체(제목, 재생시간)을 담아서 정렬시킨다.
* 
* @solving_description 
*/

public class 블라인드18_방금그곡 {
	public static void main(String[] args) {
//		String m ="ABCDEFG#";
//		String[] musicinfos = {"12:00,12:14,HELLO,ABCDEFG#", "13:00,13:05,WORLD,ABCDEF"};
		
		String m = 	"CC#BCC#BCC#BCC#B";
		String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
//		
//		String m = 	"ABCD";
//		String[] musicinfos = {"12:00,12:04,HELLO,ABCD#", "13:00,13:05,WORLD,ABCD#EF"};
//		String[] musicinfos = {
//	            "04:00,04:02,ZERO,B#CC", "15:00,15:02,FIRST,B#CC", "04:04,04:06,SECOND,B#CC", "04:08,04:10,THIRD,B#CC"
//	    };
//
//	    String m = "CC";
		System.out.println(new Solution().solution(m, musicinfos));
	}
	static class Solution {
		public String solution(String m, String[] musicinfos) {
			String answer = "";

			ArrayList<song> list  = new ArrayList<>();
			//재생시간을 먼저 계산하고 그만큼의 String을 만들어줘서 검색을 해야한다.
//			System.out.println(musicinfos.length);
			m = m.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("A#", "a").replace("G#", "g");
			for(String musicinfo: musicinfos) {
				StringTokenizer stringTokenizer =new StringTokenizer(musicinfo,",");
				String start = stringTokenizer.nextToken();
				int start_hour = Integer.parseInt(start.substring(0,2));
				int start_min = Integer.parseInt(start.substring(3,5));
				String end = stringTokenizer.nextToken();
				int end_hour =Integer.parseInt(end.substring(0,2));
				int end_min = Integer.parseInt(end.substring(3,5));
				int play_min = (end_hour-start_hour)*60 + end_min - start_min+1;
				String title = stringTokenizer.nextToken();
				String melody = stringTokenizer.nextToken().replace("C#", "c").replace("D#", "d")
						.replace("F#", "f").replace("A#", "a").replace("G#", "g");
				
//				System.out.println(m);
//				System.out.println(melody);
				
				//몫만큼 통째로 붙여넣어주고 나머지만큼 일부분을 붙여넣어준다.
				int quotient = play_min/melody.length();
				int remain = play_min%melody.length();
//				System.out.println(quotient);
//				System.out.println(remain);
				StringBuilder stringBuilder = new StringBuilder();
				for(int i=0;i<quotient;i++) {
					stringBuilder.append(melody);
				}
				stringBuilder.append(melody.substring(0,remain));
				
//				System.out.println(stringBuilder.toString());
				
				String whole_melody= stringBuilder.toString();
				
				if(whole_melody.contains(m)) {
					list.add(new song(title, play_min));
				}
				
			}//for
			if(list.size()==0) {
				return "(None)";
			}
			else {
				Collections.sort(list);
			}
			
			return list.get(0).title;
		}//solution
		static class song implements Comparable<song>{
			String title;
			int len;
			public song(String title, int len) {
				super();
				this.title = title;
				this.len = len;
			}
			@Override
			public int compareTo(song o) {
				// TODO Auto-generated method stub
				return Integer.compare(this.len, o.len)*-1;
			}
			
			
		}
	}
}

