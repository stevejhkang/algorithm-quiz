package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779 {
	static int n, m,k; //세로 가로 회전연산
	static int[][] map;
	static ArrayList<spin> spin_List;
	static spin[] choice; //순열을 만들기 위한 인풋배열
	static int min;
	static int[] visit; //순서를 위한 배열
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n+1][m+1];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		choice= new spin[k];
		visit = new int[k];
		
		for(int n=0;n<k;n++) {
			stringTokenizer = new StringTokenizer(bReader.readLine());
			int r = Integer.parseInt(stringTokenizer.nextToken());
			int c = Integer.parseInt(stringTokenizer.nextToken());
			int s = Integer.parseInt(stringTokenizer.nextToken());
			choice[n]=new spin(r, c, s);
		}
		
		spin_List = new ArrayList<>(); //여러 순서를 만들어낸 배열 
		
		
		min = Integer.MAX_VALUE;
		
		permu(0);
		
		System.out.println(min);
	}
	static void permu(int now) {
		if(now==k) {
			
//			System.out.println(spin_List.toString());
			int[][] map_copy = new int[n+1][m+1];
			for(int i=0;i<map_copy.length;i++) {
				map_copy[i]=map[i].clone();
			}
			
			for(int l=0;l<k;l++) {
				spin spin_set = spin_List.get(l);
				int r= spin_set.r; int c=spin_set.c; int s=spin_set.s;
			
				for(int i=1;i<=s;i++) {
					int temp=map_copy[r-i][c-i];
					for(int j=r-i;j<r+i;j++) { //맨왼쪽 위로
						map_copy[j][c-i]=map_copy[j+1][c-i];
					}
					for(int j=c-i;j<c+i;j++) { //맨아래 왼쪽으로
						map_copy[r+i][j]=map_copy[r+i][j+1];
					}
					for(int j=r+i;j>r-i;j--) { //맨오른쪽 아래로
						map_copy[j][c+i]=map_copy[j-1][c+i];
					}
					for(int j=c+i;j>c-i;j--) { //맨위쪽 오른쪽으로
						map_copy[r-i][j]=map_copy[r-i][j-1];
						if(j==c-i+1)
							map_copy[r-i][j]=temp;
					}
				}
//				for (int i = 1; i <= n; i++) {
//					for(int j=1;j<=m;j++) {
//						System.out.print(map_copy[i][j]+" ");
//					}
//					System.out.println("");
//				}
//				System.out.println("");
			}
			for (int i = 1; i <= n; i++) {
				int row_sum=0;
				for(int j=1;j<=m;j++) {
					row_sum+=map_copy[i][j];
				}
//				System.out.println(row_sum);
				if(min>row_sum) {
					min=row_sum;
				}
			}
			return;
		}
		for(int i=0;i<k;i++) {
			if(visit[i]==0) {
				visit[i]=1;
				spin_List.add(choice[i]);
				permu(now+1);
				spin_List.remove(now);
				visit[i]=0;
				
			}
		}
		
	}
	static class spin{
		int r; int c; int s;

		public spin(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}

		@Override
		public String toString() {
			return "spin [r=" + r + ", c=" + c + ", s=" + s + "]";
		}
		
	}
}
