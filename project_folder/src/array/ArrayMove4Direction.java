package array;

import java.util.Random;
import java.util.Scanner;

import javax.print.attribute.standard.NumberUpSupported;

public class ArrayMove4Direction {
	static int[] dx= {0,0,-1,1};
	static int[] dy= {-1,1,0,0};
	static int[] dx1= {-1,-1,1,1};
	static int[] dy2= {-1,1,-1,1};
	static int[][] arr;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner("1 2 3\r\n" + 
				"4 5 6\r\n" + 
				"7 8 9");
		Random random = new Random();
		int num=0;
		
		while(num!=1) {
			num= random.nextInt(100);
			System.out.println(num);
		}
		arr = new int [3][3];
		for (int i = 0; i < 3; i++) {
			for(int j=0;j<3;j++) {
				arr[i][j]=scanner.nextInt();
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				int sum=0;
				for (int d = 0; d < 4; d++) {
					int nx=j+dx[d]; int ny=i+dy[d];
					if(nx<0 || nx>=arr.length || ny<0 || ny>=arr.length) //갈 수 있는지 확인해보고 안되면 continue;
						continue;
					if(isIn(i, j)) {
						int a = arr[i][j]-arr[ny][nx];
						sum+=Math.abs(a); //갈 수 있을때만 계산
					}
				}
				System.out.printf("%d, %d의 값의 합은 %d이다.\n",j,i,sum);
			}
		}
	}
	public static boolean isIn(int r, int c) {
		return 0<=r && 0<=c && r<arr.length && c<arr[0].length;
	}
}
