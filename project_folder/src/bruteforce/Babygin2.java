package bruteforce;

public class Babygin2 {
	static int[] arr = new int[10];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = {4,4,4,4,3,5};
		for(int i=0;i<input.length;i++) {
			arr[input[i]]++;
		}
		int cnt=2;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>=3) {
				arr[i]-=3;
				cnt--;
				break;
			}
		}
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>=1) {
				if(i+2<arr.length) {
					if(arr[i+1]>=1&&arr[i+2]>=1) {
						cnt--;
						break;
					}
				}
				
			}
		}
		if(cnt==0) {
			System.out.println("is babygin!");
		}else {
			System.out.println("is not babygin!");
		}
	}

}
