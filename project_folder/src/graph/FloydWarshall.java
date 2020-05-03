package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FloydWarshall {
	private static int n;
	private static int m;
	private static int[][] map;
	private static int[][] D;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		m = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer =null;
		
		map = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}
		for(int i=0;i<m;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int weight =Integer.parseInt(stringTokenizer.nextToken());
			
			if(map[from][to]>weight) { //작은 값이 있으면 갱신한다.
				map[from][to]= weight;
			}
		}
		
		//모든 노드에서 다른 노드까지 가는 경로의 가중치의 최소합을 구하는 알고리즘 
		//D배열을 선언 및 초기화
//		D = new int[n+1][n+1];
//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<=n;j++) {
//				if(map[i][j]!=0) {
//					D[i][j] = map[i][j];
//				}
//				else {
//					D[i][j] = Integer.MAX_VALUE;
//				}
//			}
//		}//for i
		//10000000
		//987654321
		
		for(int k=1;k<=n;k++) {
			for(int from =1; from<=n;from++) {
				for(int to = 1;to<=n;to++) {
					
					if(map[from][k]==Integer.MAX_VALUE||map[k][to]==Integer.MAX_VALUE||from==to)//연결이 안되어 있으면 넘어간다.
						continue;
					if(map[from][to]>map[from][k]+map[k][to]) {//k를 거쳐서 가는 게 더 짧으면 갱신해준다.
						map[from][to] = map[from][k]+map[k][to];
					}
				}
			}
		}//for k
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				int a = map[i][j];
				if(a==Integer.MAX_VALUE) {
					System.out.print(0+" ");
				}
				else {
					System.out.print(map[i][j]+" ");
				}
			}
			System.out.println();
		}
	}
}
