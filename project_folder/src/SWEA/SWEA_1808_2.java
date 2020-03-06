package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class SWEA_1808_2 {
	private static int min;
	static int X; static boolean[] btn;
	static int[] memo; static int size;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine().trim());
		for(int tc=1;tc<=T;tc++) {
			min = Integer.MAX_VALUE;
			StringTokenizer stringTokenizer;
			btn = new boolean[10];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim()," ");
			int num;
			for(int i=0;i<10;i++) {
				num=Integer.parseInt(stringTokenizer.nextToken());
				if(num==1) {
					btn[i] = true;
				}
			}
			X = Integer.parseInt(bufferedReader.readLine().trim());
			//min값 초기화 Integer.MAX_VALUE
			min = Integer.MAX_VALUE;
			size = (int)Math.sqrt(X);
			//약수만 저장하므로 제곱근까지만 선언
			memo = new int[size];
			//처리 dfs: 약수로 처리를 해야하므로 어떤 값이 들어올 것인가?
			find(X,0);
			
			//btn 1이면 true로 설정
			
			
			System.out.println("#"+tc+" "+(min==Integer.MAX_VALUE?-1:min));
		}
	
	}//main
	private static int find(int x, int cnt) {
		// TODO Auto-generated method stub
		if(x<size&&memo[x]!=0) { //이미 계산되어 있다면
			return memo[x];
		}
		//base 케이스 
		//x값이 주어진 모든 수로 누를 수 있는지 검사-> x의 길이를 리턴
		if(isMake(x+"")) {
			if(cnt==0) { //0이면 추가적으로 누른게 없으므로 이게 최솟값이다.
				min = len(x)+1; //계산 버튼을 위해 1을 더한다.
			}
			//아니면 해당 길이와 이동한 횟수를 더해준다.
			return len(x)+1;
		}
		//처리
		//result값을 -1로 초기화 
		int result = -1;
		//2~제곱근까지 반복(i) 약수를 구하기 위해 
		for(int i=2,end=(int)Math.sqrt(x)+1;i<end;i++) {
			//i는 x의 약수이고 i를 우리가 사용할 수 있는 수로 누를 수 있는지 검사
			if(x%i==0&&isMake(i+"")) {
				//가능하다면 i의 길이를 구하고
				int len1 = len(i)+1; //여기서 +1은 곱하기버튼
				
				// 몫이 x의 약수, 모든 수로 누를 수 있는지 검사 -> 재귀호출
				int len2 = find(x/i,cnt+1);
				//몫이 -1이 아니면 -> x의 약수, 모든 수로 누를 수 있다는 뜻
				if(len2>-1) {
					//i의 길이와 몫의 길이를 + *=
					result = len1+len2;
					if(result<min&&x==X) {
						//결과가 min비교해서 갱신
						min = result;
					}
				}
			}
		
		}
		return result;
		
		//출력
	}
	private static int len(int x) {
		// TODO Auto-generated method stub
		//숫자의 길이는 로그로 처리할 수 있으므로
		return (int)Math.log10(x)+1 ;
	}
	private static boolean isMake(String string) {
		// TODO Auto-generated method stub
		for (char c : string.toCharArray()) {
			if(!btn[c-'0']) { //하나라도 true가 아니라면  char숫자에서 -'0'하면 인덱스가 나온다.
				return false;
			}
		}
		return true;
	}
	
	
	
}
