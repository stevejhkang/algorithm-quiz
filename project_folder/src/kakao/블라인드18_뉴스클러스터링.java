package kakao;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 5. 오전 11:29:50
 * @category Map을 사용하는 방법과 정규표현식을 알아야 한다. 
* @level 3
* @problem_description 
* 유사한 기사를 묶는 기준을 정하기 위해서 자카드 유사도를 사용한다.
* 자카드 유사도는 집한 간의 유사도를 검사하는 방법
* 자카드 유사도 J(A,B)= 두 집합의 교집합 크기를 두 집합의 합집합의 크기로 나눈 값으로 정의
* A와 B 둘다 공집합이면 유사도는 1이다.
* 
* 자카드 유사도는 원소의 중복을 허용하는 다중집합에서도 확장할 수 있다.
* 교집합은 A와 B중에서 중복 횟수의 최솟값 합집합은 중복횟수가 더큰 값이 된다.
* 
* 문자열 사이의 유사도를 계산하는데 사용할 수 있다. 
* 문자열 France와 French가 주어졌을 때 두 글자씩 끊어서 다중집합을 만들 수 있다.
* {fr,ra,an,nc,ce} 이런식으로!
* 
* 입력:
* 두 문자열이 들어온다. 문자열의 길이 <=1000
* 문자열은 두글자씩 끊어서 다중 집합의 원소로 만든다 . 이때 영문자로 된 글자쌍만 유효
* 기호가 들어간 두글자는 버린다.
* AB와 Ab ab는 같은 원소로 취급한다.
* 출력 
* 유사도 * 65536
*   
* @solving_description
* 
*  1. map iter를 하는 방법을 알아놓자
*  2. 기호를 버리는 방법 = 문자로만 이루어져있는지 확인
*/
public class 블라인드18_뉴스클러스터링 {
	public static void main(String[] args) {
//		System.out.println((double)16384/65536);
		
//		String str1 = "FRANCE";
//		String str2 = "french";
		
//		String str1 = "E=M*C^2";
//		String str2 = "e=m*c^2";
		
//		String str1 = "handshake";
//		String str2 = "shake hands";
		
		String str1 = "aa1+aa2";
		String str2 = "AAAA12";
		System.out.println(new Solution().solution(str1, str2));
	}
	static class Solution {
		public int solution(String str1, String str2) {
			int answer = 0;
			
			str1 = str1.toLowerCase();
			str2 = str2.toLowerCase();
			
			Map<String,Integer> map1 = new HashMap<String, Integer>();
			Map<String,Integer> map2 = new HashMap<String, Integer>();
			
			int len1 = str1.length(); int len2 = str2.length();
			
			String regExp = "^[a-zA-Z]{2}$";
			
			//map에 각각 담아준다.
			for(int i=0;i<=len1-2;i++) {
				String key = str1.substring(i,i+2);
				if(!key.matches(regExp)) { //영어단어가 아니면 넘긴다.
					continue;
				}
				if(map1.containsKey(key)) {
					map1.put(key, map1.get(key)+1);
				}
				else {
					map1.put(key, 1);
				}
			}
			for(int i=0;i<=len2-2;i++) {
				String key = str2.substring(i,i+2);
				if(!key.matches(regExp)) { //영어단어가 아니면 넘긴다.
					continue;
				}
				if(map2.containsKey(key)) {
					map2.put(key, map2.get(key)+1);
				}
				else {
					map2.put(key, 1);
				}
			}
//			System.out.println(map1.size());
//			System.out.println(map2.size());
			if(map1.size()==0&&map2.size()==0) {
				answer=65536;
				return answer;
			}
			
			double cross = 0;
			int union= 0;
			//이제 교집합과 합집합을 구한다. 
			//교집합은 map1을 이용해서 먼저 구하고
			//map2를 이용해서 2의 나머지를 합집합에 넣는다.
			for(String key: map1.keySet()) {
				//둘이 겹치는게 있으면 값을 받아와서 비교하고 교집합에 넣는다.
				int val1 = map1.get(key); //값이고
				
				if(map2.containsKey(key)) { //있으면
					int val2 = map2.get(key);
					//큰 값을 교집합 작은 값은 합집합에 넣어야 한다.
					if(val1<val2) { //큰값을 유니언에 작은 값을 크로스에 넣는다.
						union+=val2;
						cross+=val1;
					}else {
						union+=val1;
						cross+= val2;
					}
				}
				else { //없으면 합집합에만 넣는다.
					union+=val1;
				}
			}
			//그리고 map1에 없는 것만 넣는다.
			for(String key: map2.keySet()) {
				int val2 = map2.get(key);
				if(!map1.containsKey(key)) {
					union +=val2;
				}
			}
//			System.out.println((double)cross/union);
			answer = (int) ((cross/union)*65536); 
			return answer;
		}
	}
}

