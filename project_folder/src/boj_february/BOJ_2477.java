package boj_february;

import java.util.Scanner;

public class BOJ_2477 {

	static int[] dist = new int[6];
	static Scanner scanner =new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println((-2%6));
		int k = scanner.nextInt();
		int max=0; int idx=0;
		for(int i=0;i<6;i++) {
			int a = scanner.nextInt();
			int b= scanner.nextInt();
			dist[i]=b;
		}
		for(int i=0;i<6;i++) {
			if(max<(dist[i]*dist[(i+1)%6])) {
				max=(dist[i]*dist[(i+1)%6]);
				idx=i;
			}
		}
		int empty=dist[(idx+3)%6]*dist[(idx+4)%6];
		System.out.println(k*(max-empty));
	}


}
