package kakao;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 7. 오후 11:00:06
 * @category 완전탐색
 * @level 3
 * @problem_description 일정 단위로 자르면서 그 뒤에 부분도 같은게 있는 지를 확인해야한다. 최대 자를 수 있는 단위는
 *                      문자열 길이의 절반 부터 시작한다.
 * @solving_description 어떻게 하면 다음 시작 인덱스를 잡아줄까 고민...
 * 현재 탐색하는 것이 target과 다르면 target을 현재 탐색으로 바꿔주고 다음을 탐색하게 만든다.
 */

public class 블라인드20_문자열압축 {
	public static void main(String[] args) {
//		String s = 	"aabbaccc";
//		String s = 	"ababcdcdababcdcd";
//		String s =	"abcabcdede";
		String s = "abcabcabcabcdededededede";
//		String s = 	"xababcdcdababcdcd";
		System.out.println(new Solution().solution(s));
	}

	static class Solution {
		public int solution(String s) {
			int answer = Integer.MAX_VALUE;

			for (int len = 1; len < s.length(); ++len) {
				String compressed = "";
				String target = "";
				String current = "";
				int cnt = 1;

				// 첫 번쨰 단위 지정
				for (int i = 0; i < len; ++i) {
					target += s.charAt(i);
				}

				// 단위만큼 잘랐을 때 각 부분의 시작 인덱스
				for (int i = len; i < s.length(); i += len) {
					current = "";
					for (int j = i; j < i + len; ++j) {
						if (j >= s.length())
							break;
						current += s.charAt(j);
					}

					if (target.equals(current)) {
						cnt++;
					} else {
						if (cnt > 1) {
							compressed += cnt + target;
						} else {
							compressed += target;
						}
						cnt = 1;
						target = current;
					}
				}

				if (cnt > 1) {
					compressed += cnt + target;
				} else {
					compressed += target;
				}

				int length = compressed.length();
				answer = answer > length ? length : answer;
			}

			if (answer == Integer.MAX_VALUE)
				answer = 1;

			return answer;
		}
	}
}
//class Solution {
//    public int solution(String s) {
//    	int len= s.length();
//        int answer = len;
//        
//        //2에서부터 잘라보면서 되는지 확인한다
//        for(int cut=1;cut<len;cut++) {
//        	StringBuilder stringBuilder = new StringBuilder();
//        	String target= s.substring(0, cut); //첫 시작을 잡아주고
//        	
//        	int count=1;
//        	for(int i=cut;i<s.length();i+=cut) {
//        		String comp="";
//        		//len부터 끝까지 확인해본다.
//        		if(i+cut<=s.length()) {
//        			comp= s.substring(i,i+cut);
//        			if(target.equals(comp)) { //같을땐 카운트+1해주고 뒤로 계속 이어간다.
//        				count++;
//        			}else { //다를땐 target을 comp로 바꿔주고 뒤를 탐색한다.
//        				if(count>1) { //1이상일 때 압축할 수 있다.
//        					stringBuilder.append(count+target);
//        				}else {//1일땐 그냥 붙이고 
//        					stringBuilder.append(target);
//        				}
//        				//target을 바꾸어 주고 count=1로 바꾼다.
//    					target=comp;
//    					count=1; 
//        			}
//        		}
//        		//범위를 벗어나면
//        		else {
//        			if(count>1) {
//    	        		stringBuilder.append(count+target);
//    	        	}else {
//    	        		stringBuilder.append(target);
//    	        	}
//        			target=s.substring(i,len);
//        		}
//        	}
//        	if(count>1) {
//        		stringBuilder.append(count+target);
//        	}else {
//        		stringBuilder.append(target);
//        	}
//        	//크기비교
////        	System.out.println(stringBuilder.toString());
//        	int result = stringBuilder.toString().length();
//        	answer=Math.min(result, answer);
//        }//for cut
//        
// 
//        return answer;
//    }
//}