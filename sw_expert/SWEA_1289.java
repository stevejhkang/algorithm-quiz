import java.io.IOError;
import java.util.Scanner;

public class SWEA_1289 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
//		scanner = new Scanner("2\r\n" + 
//				"0011\r\n" + 
//				"100");
		int n = scanner.nextInt();
		StringBuilder sBuilder = new StringBuilder();
		for(int t=1;t<=n;t++) {
			sBuilder.append("#"+t+" ");
			String string= scanner.next();
			int cnt=0; int flag =0;
			for(int i=0;i<string.length();i++) {
				if(string.charAt(i)=='1' && flag==0) {
					flag=1; cnt++;
				}
				else if(string.charAt(i)=='0'&&flag==1) {
					flag=0; cnt++;
				}
			}
			sBuilder.append(Integer.toString(cnt)+"\n");
		}
		System.out.println(sBuilder);
	}
}

