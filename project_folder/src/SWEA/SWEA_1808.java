package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 5. 오전 10:45:43
 * @category 
* @problem_description 몇개의 숫자버튼과 곱하기 버튼 계산버튼만
* @solving_description 
*/
public class SWEA_1808 {
	private static int[] number;
	private static int target;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		 
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			number= new int[10];
			
			for(int i=0;i<10;i++) {
				number[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			
			target= Integer.parseInt(bufferedReader.readLine());
			
			//완전탐색인듯.... 근데 몇번누를지를 어떻게알지...
			//곱하기이면 일단 무조건 증가하는 것이다.
			//그래서 그게 몇자리인지를 파악하고 그이상으로 하면 안될듯
			//10개 숫자 중에서 1인것만 연속으로 치던지 아니면 곱하기를 누르던지 이런식으로 근데 
			//곱하기는 연속으로 누르면 안 될듯.
			
			min = Integer.MAX_VALUE;
			dfs("",false,0);
			System.out.println((min==Integer.MAX_VALUE?-1:min));
		}
	}//main
	
	static void dfs(String s, boolean use_mul,int cnt) {
		//처음 0이 아닐때만 정수로 만들어 준다.
		int num =-1;
		if(s.length()!=0) {
			num = Integer.parseInt(s);
		}
		if(num!=-1&&num==target) { //숫자가 같으면 끝낸다.	
			System.out.println("min"+num);
			min = Math.min(min, cnt);
			return;
		}
		for(int i=-1;i<10;i++) { //10개 숫자를 붙이거나 아니면 곱하기를 해준다.
			if(i==-1) { //마지막이고 이전에 곱하기를 사용하지 않았으면
				if(!use_mul&&num!=-1)
					dfs(s,true,cnt+1);
				continue;
			}
			if(number[i]==1&&!use_mul) {//곱하기가 아니면 그냥 이어준다.
				Integer in = new Integer(i);
				int temp = Integer.parseInt(s+in.toString());
				System.out.println("temp1: "+temp);
				if(temp>target) { //넘어가면 그냥 리턴
					return;
				}
				dfs(s+in.toString(),false,cnt+1);
			}
			else if(number[i]==1&&use_mul) { //이전이 곱하기였으면 다음 수와 곱해준다.
				int temp =Integer.parseInt(s);
				temp*=i;
				System.out.println("temp2: "+temp);
				if(temp>target) { //넘어가면 그냥 리턴
					return;
				}
				Integer in = new Integer(temp);
				String result= in.toString();
				dfs(result, false,cnt+1);
			}
			
		}
	}
}
