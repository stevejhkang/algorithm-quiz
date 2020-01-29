package bruteforce;

import java.util.Arrays;

public class Babygin {
//	static int[] arr = {2,3,5,7,7,7};
	static int[] arr = {6,6,7,7,6,7};
	static int[] output= new int[6];
	static int[] visit=new int[6];
	static boolean ans= false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(nextSame(new int[] {6, 6, 6,7,7,7},2,0));
//		System.out.println(nextIncrease(new int[] {4, 5, 6,7,7,7},2,0));
		
		permutation(0, 6);
		System.out.println(ans);

		
	}
	static void permutation(int r, int n) {
		
		if(r==n) {
			System.out.println(Arrays.toString(output));
			if(isBabyGin(output)) {
				ans=true;
			}
		}
		for(int i=0;i<arr.length;i++) {
			if(visit[i]==0) {
				visit[i]=1;
				output[r]=arr[i];
				permutation(r+1, n);
				output[r]=0;
				visit[i]=0;
			}
		}
		return;
	}
	static boolean isBabyGin(int[] output ) {
		if(output[0]+1==output[1]&&output[1]+1==output[2]) {
			if(output[3]==output[4]&&output[4]==output[5]) {
				return true;
			}
			else if(output[3]+1==output[4]&&output[4]+1==output[5])
				return true;
			else 
				return false;
		}
		else if(output[0]==output[1]&&output[1]==output[2]) {
			if(output[3]==output[4]&&output[4]==output[5]) {
				return true;
			}
			else 
				return false;
		}
		else {
			return false;
		}
	}
	
}
