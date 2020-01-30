package boj;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntPredicate;
import java.util.PriorityQueue;
import java.util.Scanner;

//DNA를 전부 숫자로 바꿔주고, 열마다 숫자 등장횟수를 체크하여 max값을 갱신시켜주고 max값의 인덱스에 따라 DNA배열을 구성한다.
//max값의 인덱스를 result에 저장하여
//for문을 한번 더 돌려서 반복해주면서 result와 다르면 횟수를 더해서 개수를 구한다.

public class BOJ_1969 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		int n =scanner.nextInt(); int m = scanner.nextInt();
		String [] strings= new String[n];
		int ans=0;
		int[][] input = new int[n][m];
		for(int  i=0;i<n;i++) {
			strings[i]=scanner.next();
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for(int j=0;j<m;j++) {
				if(strings[i].charAt(j)=='A') {
					input[i][j]=0;
				}
				else if(strings[i].charAt(j)=='C') {
					input[i][j]=1;
				}
				else if(strings[i].charAt(j)=='G') {
					input[i][j]=2;
				}
				else if(strings[i].charAt(j)=='T') {
					input[i][j]=3;
				}
			}
		}
		int idx=0;
		int result[] = new int[m];
		for(int i=0;i<m;i++) {
			int max=0; idx=0;
			int check[] = new int[4];
			for(int j=0;j<n;j++) {
				int now=input[j][i];
				check[now]++;
				if(max<check[now]) {
					max=check[now];
					idx=now;
				}
				else if(max==check[now]&&idx>now) {
					idx=now;
				}
			}
			if(idx==0) {
				stringBuilder.append('A');
				result[i]=idx;
			}
			else if(idx==1) {
				stringBuilder.append('C');
				result[i]=idx;
			}
			else if(idx==2) {
				stringBuilder.append('G');
				result[i]=idx;
			}
			else if(idx==3) {
				stringBuilder.append('T');
				result[i]=idx;
			}
			
		}
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(result[i]!=input[j][i]) {
					ans++;
				}
			}
		}
		System.out.println(stringBuilder);
		System.out.println(ans);
		
	}

}
