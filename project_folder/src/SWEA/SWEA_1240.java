package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1240 {
//	static String[] num = {"0001101","0011001","0010011","0111101","0100011","0110001","0101111","0111011","0110111","0001011"};
//	static int[] num = {0001101,0011001,0010011,0111101,0100011,0110001,0101111,0111011,
//			0110111,0001011};
	static int[] num = {1101,11001,10011,111101,100011,110001,101111,111011,110111,1011};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader  = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bReader.readLine());
		StringBuilder sBuilder =new StringBuilder();
		for(int tc=1;tc<=t;tc++) {
			StringTokenizer stringTokenizer= new StringTokenizer(bReader.readLine());
			int n = Integer.parseInt(stringTokenizer.nextToken());
			int m = Integer.parseInt(stringTokenizer.nextToken());
			String password= "";
			outer: for(int i=0;i<n;i++) {
				stringTokenizer = new StringTokenizer(bReader.readLine());
				String string = stringTokenizer.nextToken();
				int end=0; int start=0;
				for(int j=0;j<m;j++) {
					if(string.charAt(j)=='1') {
						end = string.lastIndexOf('1')+1;
						start = end-56;
						password = string.substring(start, end);
						while(i<n-1) {
							bReader.readLine();
							i++;
						}
						break outer;
					}
					
				}
			}//outer
//			System.out.println(password);
			//7자씩 끊어서 숫자로 만들어준다.
//			System.out.println("");
			int odd=0; int even=0;
			int index=0;
			for(int i=0;i<55;i+=7) {
				String oneString = password.substring(i,i+7);
				int a = Integer.parseInt(oneString);
//				System.out.println(oneString);
				//홀수 합구하기
				for(int j=0;j<=9;j++) {
//					if(i==49) {
//						if(oneString.equals(num[j])) {
//						
//						}
//					}
//					System.out.println(a);
					if(a==num[j]) {
						if(index%2==0) { //홀수
//							System.out.print(j+" ");
							odd+=j;
						}
						else {
//							System.out.print(j+" ");
							even+=j;
						}
						break;
					}
					
				}//for j
				
				index++;
			}
//			System.out.println();
			int result =(odd*3)+even;
//			System.out.println(result);
			if(result%10==0) {
				sBuilder.append("#"+tc+" "+(odd+even)+"\n");
			}
			else{
				sBuilder.append("#"+tc+" "+0+"\n");
			}
		}//tc
		System.out.println(sBuilder);
	}
}
