package boj_february;

import java.util.Scanner;

public class BOJ_11729 {
	static int ans=0;
	static StringBuilder sb =new StringBuilder();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		hanoiTower(num, 1, 2, 3);
		sb.insert(0,ans+"\n");
		System.out.println(sb);
	}
	static void hanoiTower(int num, int from, int by, int to) {
		++ans;
		if(num==1) {
			sb.append(from+" "+to+"\n");
			return;
		}
		//1. num-1���� from���� to�� ���ļ� by�� ��ġ��Ų��.
		hanoiTower(num-1, from, to, by);
		//2. �� �Ʒ� ���� from���� to�� �ű��.
		sb.append(from+" "+to+"\n");
		//3. by�� �ִ� num-1���� from�� ���ļ� to�� �ű��.
		hanoiTower(num-1,by,from,to);
		
	}
}
