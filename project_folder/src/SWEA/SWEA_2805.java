package SWEA;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;



public class SWEA_2805 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int T= scanner.nextInt();
		for(int tc=1;tc<=T;tc++) {
			
			int n =scanner.nextInt();
			int [][] input = new int[n][n];
			for(int i=0;i<n;i++) {
				String s = scanner.next();
				for(int j=0;j<n;j++) {
					input[i][j]=Character.getNumericValue(s.charAt(j));
				}
			}
//			for(int i=0;i<n;i++) {
////				stringTokenizer = new StringTokenizer(bReader.readLine());
//				for(int j=0;j<n;j++) {
////					input[i][j]=Integer.parseInt(stringTokenizer.nextToken());
//					System.out.print(input[i][j]+" ");
//				}
//				System.out.println("");
//			}
			
			int mid= n/2;
			int sum=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					int dist = Math.abs(mid-i)+Math.abs(mid-j);
					if(dist<=mid) {
						sum+=input[i][j];
					}
				}
			}
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("#"+tc+" ").append(sum);
			System.out.println(sBuilder);
		}
	}

}
