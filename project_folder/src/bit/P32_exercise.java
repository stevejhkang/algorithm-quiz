package bit;

import java.util.ArrayList;

public class P32_exercise {
	public static void main(String[] args) {
		String input = "00000010001101";
		ArrayList<Integer> arrayList = new ArrayList<>();
//		for(int i=0;i<input.length();i++) {
//			int index=1;
//			int a=0;
//			while(index<=7) {
//				if(input.charAt(i)=='1') {
//					a+=Math.pow(2, 7-index);
//				}
//				if(index==7) {
//					arrayList.add(a);
//					a=0;
//				}
//				index++;
//			}
//		}
//		for(int i=0;i<arrayList.size();i++) {
//			if(i==arrayList.size()-1) {
//				System.out.println(arrayList.get(i));
//				break;
//			}
//			System.out.print(arrayList.get(i)+", ");
//			
//		}
		for(int j=0;j<input.length();j+=7) {
			arrayList.add(calca(input, j, j+7));
		}
		System.out.println(arrayList);
	}
	public static int calca(String data, int from, int to) {
		int sum =0;
		for(int i=from; i<to;i++) {
			if(data.charAt(i)=='1') {
				sum+=1<<(6-i%7);
			}
		}
		return sum;
	}
}
