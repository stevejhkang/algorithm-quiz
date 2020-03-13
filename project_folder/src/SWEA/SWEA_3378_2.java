package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3378_2 {
	private static int[][] m;
	private static int[][] dap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int p = Integer.parseInt(stringTokenizer.nextToken());
			int q = Integer.parseInt(stringTokenizer.nextToken());

			m = new int[p][4];
			for (int i = 0; i < p; i++) {
				String line = bufferedReader.readLine();// 한줄을 입력받아서
				// 앞 부분에 나온.의 개수
				int index = 0;
				while (line.charAt(index) == '.') {
					index++;
				}
				m[i][0] = index;
				// 괄호의 개수는 누적처리
				if (i > 0) {
					m[i][1] = m[i - 1][1]; // 소괄호
					m[i][2] = m[i - 1][2];
					m[i][3] = m[i - 1][3];
				}
				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(':
						m[i][1]++;
						break;
					case ')':
						m[i][1]--;
						break;
					case '{':
						m[i][2]++;
						break;
					case '}':
						m[i][2]--;
						break;
					case '[':
						m[i][3]++;
						break;
					case ']':
						m[i][3]--;
						break;
					}

				}

			} // 마스터 스타일리쉬 코드 분석

			dap = new int[q][4];
			for (int i = 0; i < q; i++) {
				String line = bufferedReader.readLine();// 한줄을 입력받아서
				// 앞 부분에 나온.의 개수
				int index = 0;

				// 괄호의 개수는 누적처리
				if (i > 0) {
					dap[i][1] = dap[i - 1][1]; // 소괄호
					dap[i][2] = dap[i - 1][2];
					dap[i][3] = dap[i - 1][3];
				}
				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(':
						dap[i][1]++;
						break;
					case ')':
						dap[i][1]--;
						break;
					case '{':
						dap[i][2]++;
						break;
					case '}':
						dap[i][2]--;
						break;
					case '[':
						dap[i][3]++;
						break;
					case ']':
						dap[i][3]--;
						break;
					}

				}

			} // 내코드 분석

			// dap[i][0]: 초기값 -2 .의 개수를 몇개
			for (int i = 0; i < q; i++) {
				dap[i][0] = -2;
			}
			// 중복순열
			for(int R=1;R<=20;R++) {
				for(int C=1;C<=20;C++) {
					for(int S=1;S<=20;S++) {
						if(check(R,C,S)) { //마스터 코드에서 해가 되는가?
							cal(R,C,S);
						}
					}
				}
			}//for R
			System.out.print("#"+tc+" 0");
			for(int i=1;i<dap.length;i++) {
				System.out.print(" "+dap[i][0]);
			}
			System.out.println();
		}//for TC
	}

	private static void cal(int R, int C, int S) {
		// TODO Auto-generated method stub
		for(int i=1;i<dap.length;i++) {
			int x = dap[i-1][1]*R +dap[i-1][2]*C+dap[i-1][3]*S;
			if(dap[i][0]==-2) {
				dap[i][0]=x;
			}else if(dap[i][0]!=x) { //기존 값이랑 다른 값이 생긴다면 유일한 해가 아니다
				dap[i][0] =-1;
			}
		}
		
	}

	private static boolean check(int R, int C, int S) {
		// TODO Auto-generated method stub
		for(int i=1;i<m.length;i++) {
			if(m[i][0]!=m[i-1][1]*R+m[i-1][2]*C+m[i-1][3]*S) { //한번이라도 다르면
				return false;
			}
		}
		return true;
	}
}
