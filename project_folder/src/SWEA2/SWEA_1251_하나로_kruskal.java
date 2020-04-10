package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 9. 오후 12:21:59
 * @category 
* @problem_description 
* 황금 부담금 = 황금 부담금 세율*거리제곱(L^2)
* 최소로 지불하여 N개의 모든 섬을 연결할 수 있는 시스템 설계
* 섬 개수 1000, 좌표는 100만 부담세율 double로 계산값도 double로 처리해야한다.
* @solving_description 
*/

public class SWEA_1251_하나로_kruskal {
	private static int n;
	private static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int tc =1;tc<=T;tc++) {
			n = Integer.parseInt(bufferedReader.readLine());
			//객체배열을 만들고 두선 간의 길이의 제곱을 저장해두어야 한다.
			long[][] lensquare = new long[n*(n-1)/2][3];
			int[][] land = new int[n][2];
			
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int i=0;i<n;i++) {
				land[i][0] = Integer.parseInt(stringTokenizer.nextToken()); 
			}
			
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int i=0;i<n;i++) {
				land[i][1] =Integer.parseInt(stringTokenizer.nextToken());
			}
			
			//이중포문 돌아서 두점(i,j)간 거리 계산
			int idx=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<i;j++) {
					//거리 제곱 은 y차제곱 + x차제곱
	
					lensquare[idx][0]= i;
					lensquare[idx][1]= j; 
					lensquare[idx++][2]= (land[i][0]-land[j][0])*(land[i][0]-land[j][0])+
							(land[i][1]-land[j][1])*(land[i][1]-land[j][1]);
				}
			}
			
			//정렬
			Arrays.sort(lensquare, new Comparator<long[]>() {
				@Override
				public int compare(long[] o1, long[] o2) {
					// TODO Auto-generated method stub
					return Long.compare(o1[2], o2[2]);
				}
			});
			
			double E = Double.parseDouble(bufferedReader.readLine());
//			System.out.println(lensquare.toString());
			
			parents= new int[n];
			makeSet();
			
			long result=0;
			
			for(int i=0;i<n*(n-1)/2;i++) {
//				if(lensquare[i][0]==lensquare[i][1] )continue;
				
				int start = (int) lensquare[i][0]; 
				int end= (int) lensquare[i][1]; 
				long lensq= lensquare[i][2];
				
				int parentStart = findSet(start);
				int parentEnd = findSet(end);
				if(parentEnd==parentStart) continue;
				
				union(start,end);
				result+= lensq;
			}
//			result=Math.round(E*result);
			stringBuilder.append("#"+tc+" "+Math.round(E*result)+"\n"); 
		}//for tc
		System.out.println(stringBuilder);
	}//main
	static void makeSet() {
		for(int i=0;i<n;i++) {
			parents[i] = i;
		}
	}
	static int findSet(int v) {
		if(parents[v]==v) {
			return v;
		}
		return parents[v]= findSet(parents[v]);
	}
	static void union(int a, int b) {
		int pa= findSet(a);
		int pb = findSet(b);
		
		if(pa!=pb) {
			parents[pa] = pb;
		}
	}
}
