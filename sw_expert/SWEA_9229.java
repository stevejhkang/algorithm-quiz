package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_9229 {
	static int max, m;
	static int[] visit;
	static int[] input;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			//�Էº��� �޴´�.
			st = new StringTokenizer(br.readLine());
			int n =Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			//�ʱ�ȭ
			max = -1;
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			visit = new int[2000001];
			input = new int[n];
			
			//���� �о �迭�� �ֱ�
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < input.length; i++) {
				int a = Integer.parseInt(st.nextToken());
				input[i] = a;
			}
			
			combination(0, 2, 0, 0);
			sb.append(max);
			System.out.println(sb);
		}
	}

	static void combination(int r,
			int n, int sum, int idx) { //������ �߿����� �����Ƿ� ����. �׸��� �ߺ� �ȵ�.
		if (r == n) {
			if (sum <= m && sum > max) { //�ִ��� ���� �ʰ�, ���� ���� max������ ũ�� ����
				max = sum;
			}
			return;
		}
		for (int i = idx; i < input.length; i++) { //
			sum += input[i];
			combination(r + 1, n, sum, i + 1);
			sum -= input[i];
		}
	}

}
