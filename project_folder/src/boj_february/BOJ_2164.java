package boj_february;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_2164 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Deque<Integer> deque = new LinkedList<>();
		int n = scanner.nextInt();
		for(int i=1;i<=n;i++) {
			deque.offer(i);
		}
		while(deque.size()>1) {//1이 될때 빠져나옴
			//빼고
			deque.pop();
			//다시 뺀 것을 맨 아래로
			deque.add(deque.pop());
		}
		System.out.println(deque.peek());
	}
}
