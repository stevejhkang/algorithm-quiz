package boj_february;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_11502 {
	static int[] result;
	static boolean done;
	static ArrayList<Integer> arrayList = new ArrayList<>();
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		//1000까지의 소수를 구하자.
		arrayList.add(2);
		arrayList.add(3);
		
		for(int i=5;i<=1000;i+=2) {
			boolean flag= false;
			for(int j=2;j<=Math.sqrt(i);j++) {
				if(i%j==0) {//소수가 아니면
					flag=true;
					break;
				}
			}
			if(flag==false) { //소수이면
				arrayList.add(i);
			}
		}
//		for(int i=0;i<arrayList.size();i++) {
//			System.out.print(arrayList.get(i)+" ");
//		}
//		System.out.println("");
		for(int i=1;i<=t;i++) {
			result = new int[3];
			int r = scanner.nextInt();
			done=false;
			recursion(0,r,0);
			if(done==false) {
				System.out.println(0);
			}
		}
	}
	static void recursion(int k, int r,int idx) {
		if(done) {
			return;
		}
		if(k==3) {
			int sum=0;
			for(int i=0;i<3;i++) {
				sum+=result[i];
			}
			if(sum==r) {
				for(int i=0;i<3;i++) {
					System.out.print(result[i]+" ");
				}
				System.out.println("");
				done=true;
			}
			return;
		}
		for(int i= idx; i<arrayList.size();i++) {
			int a = arrayList.get(i);
			if(a>r) {
				return;
			}
//			System.out.println(k);
			result[k]=a;
			recursion(k+1, r, i);
		}
	}
}
