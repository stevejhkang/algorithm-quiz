package boj_february;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author junhukang
 * @time 2020. 1. 30. 오후 9:52:34
 * @category
 * @problem_description 같은 수 여러번, 고른 수열은 비내림차순 비내림차순이면 인덱스
 * @solving_description 오름차순은 인덱스를 반환해서 그것보다 같은것부터 큰것만 고를 수 있게한다.
 */
public class BOJ_15666 {
	static int[] input;
	static int[] jari;
	static int n; 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt(); int m= scanner.nextInt();
		input= new int[n];
		jari= new int[m];
		for (int i = 0; i < n; i++) {
			input[i]=scanner.nextInt();
		}
		Arrays.sort(input);
		
		recursion(0, m, 0, "");
	}
	static void recursion(int r, int m, int idx,String s) {
		if(r==m) {
			System.out.println(s);
			return;
		}
		for(int i = idx;i<n;i++) {
			if(jari[r]!=input[i]) {
				jari[r]=input[i];
				recursion(r+1, m, i, s+input[i]+" ");
			}
			
		}
		jari[r]=0;
	}
}
