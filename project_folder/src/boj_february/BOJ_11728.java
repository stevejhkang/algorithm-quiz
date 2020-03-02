package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11728 {
	private static int n;
	private static int m;
	private static int[] n_arr;
	private static int[] m_arr;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		
		n_arr = new int[n];
		m_arr = new int[m];
		
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for(int i=0;i<n;i++) {
			n_arr[i]=Integer.parseInt(stringTokenizer.nextToken());
		}
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for(int i=0;i<m;i++) {
			m_arr[i]=Integer.parseInt(stringTokenizer.nextToken());
		}
		Arrays.sort(n_arr); Arrays.sort(m_arr);
		int n_index=0;
		int m_index=0;
		int result_index=0;
		StringBuilder sBuilder = new StringBuilder();
		while(result_index<n+m) {
			if(n_arr[n_index]<m_arr[m_index]) {
				sBuilder.append(n_arr[n_index++]+" ");
				result_index++;
			}
			else if(n_arr[n_index]>=m_arr[m_index]) {
				sBuilder.append(m_arr[m_index++]+" ");
				result_index++;
			}
			//만약 m이  끝난경우
			if(m_index==m) {
				while(n_index<n) {
					sBuilder.append(n_arr[n_index++]+" ");
					result_index++;
				}
			}
			//n이 끝난경우
			else if(n_index==n){
				while(m_index<m) {
					sBuilder.append(m_arr[m_index++]+" ");
					result_index++;
				}
			}
		}
		
		System.out.println(sBuilder);
		
	}
}
