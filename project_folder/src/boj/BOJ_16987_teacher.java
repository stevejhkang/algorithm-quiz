package boj;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 
 * @author junhukang
 * 입력방법: Scanner -> BufferedReader가 더 낫다.
 * 쪼개는 방법: String.split() -> StringTokenizer(br.readLine()," ")
 * 쪼개지 않아도 되는 경우: String.charAt();
 * 
 * 출력방법: String-> StringBuffer(멀티쓰레드) -> StringBuilder(싱글쓰레드)
 */
public class BOJ_16987_teacher {

	private static int N;
	private static int[] s;
	private static int[] w;
	private static int maxCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
	
		N= Integer.parseInt(bReader.readLine()); //계란의 수 (1<=N<=8)
		
		s = new int[N];//내구도
		w=new int[N];//무게
		//이차원배열은 이중 참조를 하므로 일차원으로 하는게 낫다.
		
		for(int i=0;i<N;i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine()," ");
			s[i]= Integer.parseInt(stringTokenizer.nextToken()); //내구도<=300
			w[i]=Integer.parseInt(stringTokenizer.nextToken()); //무게<=300
		}
		maxCnt=0; //깨진 계란의 최대값
		dfs(0,0); //
		System.out.println(maxCnt);
		
	}//main
	/** index번째의 계란을 들어서 깨지지 않은 계란을 타격한다 
	 * cnt: 깨진 계란 수를 재귀 파라미터에 들고다니면 나중에 깨진 계란 수를 한꺼번에 계산하는 것보다 계산이 적어진다.*/
	private static void dfs(int index, int cnt) {
		// TODO Auto-generated method stub
		if(cnt>=N-1 || index==N) { //종료파트,  남은 계란이 1~0개이면 끝, 마지막 계란까지 내리쳤으면 끝
			if(maxCnt<cnt) maxCnt=cnt;
			return;
		}
		if(s[index]<=0) {//index 번째 계란이 깨졌으면, 진행할 수 없음
			dfs(index+1, cnt);
			return; //뒷부분 안감
		}
		//재귀파트
		for(int i=0;i<N;i++) {
			if(index==i) continue;//자기계란은 자신을 깰 수 없음.
			if(s[i]<=0)continue; //이미 깨진 계란이면 패스
			//다른 계란을 내리치고 (내구도 감소)
			s[i]-= w[index];
			s[index]-=w[i];
			int tempCnt=0; //현재 작업으로 깨진 계란의 수를 체크
			if(s[i]<=0)tempCnt++;
			if(s[index]<=0) tempCnt++;
			dfs(index+1, cnt+tempCnt);
			//다른 계란을 안깬 상태로 복구(내구도 다시 복구)
			s[i]+= w[index];
			s[index]+=w[i];
			
		}
	}
	
}
