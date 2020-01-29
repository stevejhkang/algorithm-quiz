package algo_basic_day1;

public class Gravity {
	static int[] data = {7,4,2,0,0,6,0,7,0};
	public static void main(String[] args) {
		int max=Integer.MIN_VALUE;
		for (int i = 0; i < data.length; i++) {
			int cnt=0;
			for (int j = i+1; j < data.length; j++) {
				if(data[i]>data[j]) {
					cnt++;
				}
			}
			if(max<cnt) {
				max=cnt;
			}
		}
		System.out.println(max);
	}
}
