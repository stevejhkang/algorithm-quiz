package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5607 {
	private static int n;
	private static int r;
	private static long[] facto;
	private static int mod;
	private static long[] power;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer;
		
		for(int tc=1;tc<=T;tc++) {
			stringTokenizer= new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			r = Integer.parseInt(stringTokenizer.nextToken());
			facto = new long[n+1];
			mod = 1234567891;
			power = new long[n+1];
			facto[0]=1;  power[0]=1;
			for(int i=1;i<=n;i++) {
				facto[i] = (facto[i-1]*i)%mod;
				power[i] = pow(facto[i], mod-2);

			}
		
			
			long ans = (long) ( ( ( (facto[n]*power[r])%mod) * power[n-r] ) %mod);
					//(((fact[N] * pow[K])%key) * pow[N-K])%key;
//			long ans2 = 3;
//			ans2 = (long) ((n_fac*(long)Math.pow(nmr_fac*r_fac, 1234567891-2))%1234567891);
			
			
			System.out.println("#"+tc+" "+ans);
		}
	}//main
//	static long factorial(int n) {
//		if(facto[n]!=0) {
//			return facto[n];
//		}
//		facto[n]=1;
//		for(int i=n;i>=1;i--) {
//			facto[n]*=i;
//		}
//		return facto[n];
//	}
	
	static long pow(long N, long k) {
	      long result = 1;
	      while(k > 0) {
	         if(k%2 == 1) {
	            result *= N;
	            result %= mod;
	         }
	         N *= N;
	         N %= mod;
	         k /= 2;
	      }
	      return result;
	      
	   }

	
}
