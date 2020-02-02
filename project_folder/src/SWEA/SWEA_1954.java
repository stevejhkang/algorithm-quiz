package SWEA;

import java.util.Scanner;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class SWEA_1954 {
	
	static int[] dx= {1,0,-1,0};
	static int[] dy= {0,1,0,-1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		int tc = scanner.nextInt();
		for(int t = 1; t<=tc;t++ ) {
			int move=0;
			int n = scanner.nextInt();
			int[][] arr = new int[n][n];
			System.out.println("#"+t);
			int x =-1; int y=0;
			
		
			for(int i=0;i<n*n;i++) {
				int nx= x+dx[move]; int ny=y+dy[move];
//				System.out.println((i+1)+"-nx: "+nx+",ny: "+ny);
				if(nx<0 || nx>=n||ny<0||ny>=n || arr[ny][nx]!=0) {
					move=(move+1)%4;
//					System.out.println(move);
				}
				x = x+dx[move];  y= y+dy[move];
//				System.out.println((i+1)+"-x: "+x+",y: "+y);
//				System.out.println(i);
				arr[y][x]=i+1;
			}
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					System.out.printf(arr[i][j]+" ");
				}
				System.out.println("");
			}
			
		}
	}
	// 우(1,0), 아래(0,1), 좌(-1,0), 위(0,-1), 우
//	00 01 02  
//	10 11 12
//	20 21 22
}
