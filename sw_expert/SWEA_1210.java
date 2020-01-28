package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1210 {
	static int[] dx= {-1,1}; //좌, 우
	static int[] dy= {0,0};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int[][] map = new int[100][102]; //양쪽에 비워둔다. 1부터 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int t=1;t<=10;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tc= Integer.parseInt(st.nextToken());
//			System.out.println("");
			StringBuilder sb= new StringBuilder();
			sb.append("#").append(t).append(" ");
			int y=0; int x=0;
			for(int i=0;i<100;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=1;j<=100;j++) {
					int a= Integer.parseInt(st.nextToken());
					if(a==2) {
						y=i; x=j;
					}
					map[i][j]=a;
				}
			}
			while(y>=0) {
				if(map[y][x+dx[0]]==1) {//좌
					while(map[y][x+dx[0]]==1) {
						x--;
					}
					y--;
				}
				else if(map[y][x+dx[1]]==1) {//우
					while(map[y][x+dx[1]]==1) {
						x++;
					}
					y--;
				}
				else {
					y--;
				}
				if(y==0) {
					sb.append(x-1);
					System.out.println(sb);
				}
			}

		}
		
	}

}
