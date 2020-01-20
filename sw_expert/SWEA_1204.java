import java.util.Scanner;

public class SWEA_1204 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
//		scanner = new Scanner("10, 8, 7, 2, 2, 4, 8, 8, 8, 9, 5, 5, 3");
		
		int n = scanner.nextInt();
		for(int t=1;t<=n;t++) {
			String tcase=scanner.next();
			System.out.print("#"+tcase+" ");
			int[] arr= new int[101];
			for(int i=0;i<1000;i++) {
				int a = scanner.nextInt();
//				System.out.println(a);
				arr[a]+=1;
			}
			int max=Integer.MIN_VALUE;
			int idx=0;
			for (int i = 1; i < arr.length; i++) {
				if(max<=arr[i]) {
					max=arr[i];
					idx=i;
				}
			}
			System.out.println(idx);
		}
		
	}

}

