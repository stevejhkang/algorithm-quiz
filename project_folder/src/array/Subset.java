package array;

import java.util.Arrays;

public class Subset {
	static int[] nums= {1,2};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int th =0;
		int[] temp = new int [nums.length];
		for (int i = 0; i < 2; i++) {
			temp[0]=i;
			for (int j = 0; j < 2; j++) {
				temp[1]=j;
				System.out.println(++th+": "+Arrays.toString(temp));
			}
		}
	}
}
