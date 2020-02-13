package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SWEA_1258 {
	private static int n;
	private static int[][] input;
	private static int[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int t=  Integer.parseInt(br.readLine());
		for(int tc=1;tc<=t;tc++) {
			n = Integer.parseInt(br.readLine());
			input= new int[n][n];
			visit= new int[n][n];
			for (int i = 0; i < n; i++) {
				StringTokenizer stringTokenizer =new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					input[i][j]=Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			ArrayList<matrix> matrix_list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				for(int j=0;j<n;j++) {
					if(visit[i][j]==0&&input[i][j]!=0) {//탐색 시작
						visit[i][j]=1;
//						System.out.println(i+", "+j);
						int r =1; int c=1;
						//r부터 탐색시작
						int y=i; 
						int x=j;
						while(true) {
							int ny =y+r;
							//범위 벗어나면
							if(ny<0||ny>=n) {
								
								break;
							}
							if(input[ny][x]!=0) {
								visit[ny][x]=1;
								r++;
								continue;
							}
							else {
								break;
							}
						}
						while(true) {
							int nx = x+c;
							if(nx<0||nx>=n) {
								
								break;
							}
							if(input[y][nx]!=0) {
								c++;
								visit[y][nx]=1;
								continue;
							}
							else {
								break;
							}
						}
						//r,c완성
						matrix temp = new matrix(r, c);
						matrix_list.add(temp);
						for(int q=i;q<i+r;q++) {
							for(int w=j;w<j+c;w++) {
								visit[q][w]=1; 
							}
						}
//						for(int q=0;q<n;q++) {
//							for(int w=0;w<n;w++) {
//								System.out.print(visit[q][w]+" "); 
//							}
//							System.out.println("");
//						}
					}//if(input[i][j]!=0)
					
				}//for j
			} //fori
			Collections.sort(matrix_list, new Comparator<matrix>() {

				@Override
				public int compare(matrix o1, matrix o2) {
					if(o1.r*o1.c>o2.r*o2.c) {
						return 1;
					}
					else if(o1.r*o1.c<o2.r*o2.c){
						return -1;
					}
					else {
						
						if(o1.r>o2.r) {
							return 1;
						}
						else {
							return -1;
						}
					}
					
				}
			});
			StringBuilder sBuilder =new StringBuilder();
			sBuilder.append("#"+tc+" ");
			sBuilder.append(matrix_list.size()+" ");
			for(int i=0;i<matrix_list.size();i++) {
				sBuilder.append(matrix_list.get(i)+" ");
			}
			System.out.println(sBuilder);
		}// for tc
	}//main
	static class matrix{
		int r; int c;

		public matrix(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return r+" "+c;
		}
		
	}
	
}
