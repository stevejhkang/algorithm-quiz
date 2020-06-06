package p1;

public class p1 {
	public static void main(String[] args) {
		String strs[] = {"abcaefg","abcdefg","abcdhfg"};
		System.out.println(new Solution().solution(strs));
	}
}

class Solution{
	public String solution(String[] strs) {
		String answer = "";
		
		// 제일 작은 문자열의 길이를 구한다.
		int shortest_len = Integer.MAX_VALUE;
		for(int i=0;i<strs.length;i++) {
			if(shortest_len>strs[i].length()) {
				shortest_len=strs[i].length();
			}
		}
		StringBuilder sb = new StringBuilder();
		out: for(int i=0;i<shortest_len;i++) {
			char c = strs[0].charAt(i);//첫번째 단어의 i번째 글자 
//			System.out.println(c);
			boolean is_same =true;
			//모든 단어에 대해서 i번째 글자가 일치한지 비교
			for(int j=0;j<strs.length;j++) {
//				System.out.println(strs[j].charAt(i));
				if(c!=strs[j].charAt(i)) { //다른 i번째 글자와 비교한다.
					is_same=false;
					break out;
				}
			}
			if(is_same) {
				sb.append(c);
			}
			else {
				break;
			}
		}//for i
		System.out.println(sb);
		return sb.toString();
	}
}