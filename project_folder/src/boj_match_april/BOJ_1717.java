package boj_match_april;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 26. 오전 1:05:52
 * @category Disjoint-set
* @problem_description 첫째 줄에 n, m 이 주어진다.
* m은 연산의 개수, 0일때는 a가 포함된 집합과 b가 포함된 집합을 합친다. 1일때는 a와 b가 같은 집합에 포함되어 있는지를 확인하는
* 연산이다. 
*  
* @solving_description 
*/

public class BOJ_1717 {
	private static int n;
	private static int m;
	private static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		//먼저 부모를 자기자신으로 저장
		parent= new int[n+1];
		for(int i=1;i<=n;i++) {
			parent[i]=i;
		}
		
		for(int i=0;i<m;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int order= Integer.parseInt(stringTokenizer.nextToken());
			int a = Integer.parseInt(stringTokenizer.nextToken());
			int b = Integer.parseInt(stringTokenizer.nextToken());
			if(order==0) { //합친다.
				union(a, b);
			}
			else { //둘이 같은 집합인지 확인한다.
				int root_a= find(a);
				int root_b= find(b);
				if(root_a==root_b) {
					System.out.println("YES");
				}
				else {
					System.out.println("NO");
				}
			}
		}
	}//main
	
	static int find(int a) {
		 if(parent[a]==a) {
			 return a;
		 }
		 int p = find(parent[a]);
		 parent[a]=p;
		 return p;
	}
	
	static void union(int a, int b) {
		int root_a = find(a);
		int root_b = find(b);
		if(root_a!=root_b) { //다르면 합친다.
			parent[root_a]=b;
		}
	}
}
