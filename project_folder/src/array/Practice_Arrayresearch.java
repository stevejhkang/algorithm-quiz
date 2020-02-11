package array;

public class Practice_Arrayresearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = {{1,2,3,4,5},{6,7,8,9,0,3},{7,5,4,3,1}}; //3*5
		int x = arr.length; int y = arr[0].length;
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println("");
		}
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				System.out.print(arr[j][i]+" ");
			}
			System.out.println("");
		}
		for (int i = 0; i < arr.length; i++) {
			int rowlen = arr[i].length;
			for (int j = 0; j < rowlen; j++) {
				if(i%2==0) {
					
					System.out.print(arr[i][j]+" ");
				}
				else {
					System.out.print(arr[i][rowlen-1-j]+" ");
//				for (int j = rowlen-1; j >=0 ; j--) {
//					System.out.print(arr[i][j]+" ");
//				}
				}
			}
			System.out.println("");
		}
	}

}
