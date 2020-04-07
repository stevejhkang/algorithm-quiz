package SWEA;

public class 최대공약수구하기 {
	public static void main(String[] args) {
		System.out.println(getGCD(190, 150));
	}
	static int getGCD(int big, int small) {
		int tmp =0;
		while(big!=0) {
			if(big<small) {
				tmp=big;
				big=small;
				small=tmp;
			}
			big=big%small;
		}
		return small;
	}
}
