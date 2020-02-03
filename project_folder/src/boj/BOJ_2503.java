package boj;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author junhukang
 * @time 2020. 2. 2. 오후 2:59:54
 * @category 완전탐색
* @problem_description 영수가 생각한 세자리 숫자를 맞추는 게임으로, 
* 민혁이가 후보를 말하면 영수가 자기가 생각한 숫자의 수와 자리도 같은 수의 개수(스트라이크)
* 자리는 다르지만 생각한 숫자 개수(ball)을 말해주면서 가능한 숫자 후보 수를 출력하는 문제
* @solving_description 123~987까지 숫자 종류가 중복되지 않은 수들을 가지고 완전탐색을 진행해서 
* 민혁이가 말한 숫자와 스트라이크와 볼이 다른 숫자를 제외하면서 최종적으로 남은 숫자들의 수를 출력
*/

public class BOJ_2503 {
	static ArrayList<String> arr;
	static int[] visit = new int[10];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		int n = scanner.nextInt();
		arr = new ArrayList<>();
		recursion(0, 3, "");
		for(int i=0;i<n;i++) {
			String num= scanner.next(); int st= scanner.nextInt(); int ball=scanner.nextInt();
			for (int k=0;k<arr.size();k++) {
				int st_check=0; int ball_check=0;
				for(int j=0;j<3;j++) {
					if(arr.get(k).charAt(j)==num.charAt(j)) {//st
						st_check++;
					}
					if(arr.get(k).contains(num.substring(j,j+1))) {//ball or st
						ball_check++;
					}
				}
				ball_check-=st_check;
				if(ball_check!=ball|| st_check!=st) {//다르면 지워버린다.
					arr.remove(k);
					k--;
				}	
			}
		}
		System.out.println(arr.size());
	}
	static void recursion(int r, int m,String s) {
		if(r==m) {
			arr.add(s);
			return;
		}
		for(int i=1;i<=9;i++) {
			if(visit[i]==0) {
				visit[i]=1;
				recursion(r+1,m,s+i);
				visit[i]=0;
			}
		}
	}

}
