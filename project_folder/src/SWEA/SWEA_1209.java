package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.junit.experimental.max.MaxCore;

import jdk.internal.util.xml.impl.Input;

public class SWEA_1209 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1;tc<=10;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
			String t= stringTokenizer.nextToken();
			int [][] input = new int[100][100];
			for(int i=0;i<100;i++) {
				stringTokenizer = new StringTokenizer(bReader.readLine());
				for(int j=0;j<100;j++) {
					input[i][j]=Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			int sero_max=Integer.MIN_VALUE; int garo_max=Integer.MIN_VALUE;
			int cross_max=Integer.MIN_VALUE;
			for(int i=0;i<100;i++) {
				int temp=0;
				for(int j=0;j<100;j++) {
					temp+=input[i][j];
				}
				if(garo_max<temp)
					garo_max=temp;
			}
			for(int i=0;i<100;i++) {
				int temp=0;
				for(int j=0;j<100;j++) {
					temp+=input[j][i];
				}
				if(sero_max<temp)
					sero_max=temp;
			}
			int first_cross=0; int second_cross=0;
			for(int i=0;i<100;i++) {
				 first_cross+=input[i][i];
				 second_cross+=input[i][99-i];
			}
			cross_max=Math.max(first_cross, second_cross);
			int total_max= Math.max(sero_max, Math.max(garo_max, cross_max));
			StringBuilder sb= new StringBuilder();
			sb.append("#"+tc+" ").append(total_max);
			System.out.println(sb);
		}
	}

}
