package array;

public class Practice_ChangeArr {
	static int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if(i<j) {
					int temp = arr[i][j];
					arr[i][j]=arr[j][i];
					arr[j][i]=temp;
					
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]+" ");
				
			}
			System.out.println("");
		}
	}

}
