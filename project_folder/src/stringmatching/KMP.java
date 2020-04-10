package stringmatching;

public class KMP {
	//접두사와 접미사 개념을 활용한다.
	//파이테이블: 각 길이별로 접두사 == 접미사의 "최대길이"가 저장된 배열
	public static void main(String[] args) {
		
	}
	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j=0;
		for(int i=1;i<pattern.length();i++) {
			//맞는 경우
			if(pattern.charAt(i)==pattern.charAt(j)) {
				//i 길이 문자열의 공통 길이는 j의 위치 +1
				pi[i] = ++j;
			}
			//안 맞는 경우
			else {
				//맞는 날이 올때까지 하나 전칸에서의 공통부분 위치로 이동
				while(j>0&&pattern.charAt(i)!=pattern.charAt(j)) {
					j=pi[j-1];
				}
			}
		}
		return pi;
	}//getPi
	static void KMP(String origin, String pattern) {
		int[] pi = getPi(pattern);
		int j =0;
		for(int i=0;i<origin.length();i++) {
			//맞는 경우
			if(origin.charAt(i)==pattern.charAt(j)) {
				if(j==pattern.length()-1) {
					System.out.println("ㅇㅋ"+(i-pattern.length()+1));
					j=pi[j];
				}
				//맞았지만 패턴이 끝나지 않았다면 j를 하나 증가
				else {
					j++;
				}
			}
			
			//안 맞는 경우
			else {
				while(j>0 && origin.charAt(i)!=pattern.charAt(j)) {
					j=pi[j-1];
				}
			}
		}
	}
}
