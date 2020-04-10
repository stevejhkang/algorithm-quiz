package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13567 {
	private static int M;
	private static int N;
	private static int[][] map;
	private static int y;
	private static int x;
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { -1, 0, 1, 0 };
	private static int dir;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		M= Integer.parseInt(stringTokenizer.nextToken()); // 정사각형
		N = Integer.parseInt(stringTokenizer.nextToken()); // 명령횟수
		
		map = new int[M+1][M+1];
		y=0;  x=0; dir = 2; //동에서 시작
		
		for(int i=0;i<N;i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			String s = stringTokenizer.nextToken();
			int num = Integer.parseInt(stringTokenizer.nextToken());
			
			if(s.equals("MOVE")) {
				y = y+dy[dir]*num; x = x+dx[dir]*num;
//				System.out.println();
//				System.out.println("y,x: "+y+","+x);
				//범위 벗어나면 그냥 -1 
				if(y<0||y>M||x<0|x>M) {
					System.out.println(-1);
					return;
				}
			}
			else { //0이 왼쪽 1이 오른쪽
				if(num==0) {
					dir=(dir+1+4)%4;
				}
				else if(num==1) {
					dir=(dir-1+4)%4;
				}
			}
		}
		System.out.println(x+" "+y);
	}
}
