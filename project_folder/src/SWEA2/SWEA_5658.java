package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 29. 오후 12:05:34
 * @category 
* @problem_description 각 변에 다음과 같이 16진수 숫자가 적혀있는 보물상자가 있다. 시계방향으로 한칸씩 회전 가능
* 이 자물쇠의 비밀번호는 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진 수로 만든 수이다.
* N개의 숫자가 입력으로 주어졌을 때, 보물 상자의 비밀번호를 출력하는 프로그램을 만들어보자. 
* 크기 순서를 셀 때 같은 수를 중복으로 세지 않도록 주의한다.
* @solving_description 
*/

public class SWEA_5658 {
	private static int n;
	private static int k;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder stringBuilder = new StringBuilder();
		int T = Integer.parseInt(bufferedReader.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			k = Integer.parseInt(stringTokenizer.nextToken());
			
			
			String s = bufferedReader.readLine();
			
			int div= n/4;
			
			TreeSet<Integer> treeset = new TreeSet<Integer>();
			
//			System.out.println();
			int idx= 0;
			for(int k=0;k<div;k++) {
				for(int i=0;i<4;i++) {
					int num=0;
//					System.out.println(s.substring(div*i,div*i+div));
					for(int j=1;j<=div;j++) {
						idx = div*i+j-1;
						if(s.charAt(idx)=='A') {
							num+=10*(Math.pow(16,div-j));
						}
						else if(s.charAt(idx)=='B') {
							num+=11*(Math.pow(16,div-j));
						}
						else if(s.charAt(idx)=='C') {
							num+=12*(Math.pow(16,div-j));
						}
						else if(s.charAt(idx)=='D') {
							num+=13*(Math.pow(16,div-j));
						}
						else if(s.charAt(idx)=='E') {
							num+=14*(Math.pow(16,div-j));
						}
						else if(s.charAt(idx)=='F') {
							num+=15*(Math.pow(16,div-j));
						}
						else {
							num+=(s.charAt(idx)-'0')*(Math.pow(16,div-j));
						}
					}//for j
					treeset.add(num);
//					System.out.println(num);
				}//for i
//				System.out.println();
				//이동시킨다.
				char[] arr = new char[s.length()];
				for(int i=0;i<s.length();i++) {
					if(i==0) {
						arr[i]=s.charAt(s.length()-1);
						continue;
					}
					arr[i] = s.charAt(i-1);
				}
				s= new String(arr); //이동 시킨거 갱신
				
			}//for k
//			System.out.println();
//			System.out.println(treeset.size());
			while(k>1) {
//				System.out.println(treeset.pollLast());
				treeset.pollLast();
				k--;
			}
			
			int ans = treeset.pollLast();
			stringBuilder.append("#"+tc+" "+ans+"\n");
//			System.out.println("#"+tc+" "+ans);
			
		}//for tc
		System.out.println(stringBuilder);
	}
}
