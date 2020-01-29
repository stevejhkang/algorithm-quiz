package bruteforce;
import java.util.Arrays;

public class VisitedRecur {
	private static char[] src= {'A','B','C','D'};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int r=3;
		permutation(r, new char[3], 0, new boolean[src.length]);
	}
	static void permutation(int r,char[] temp, int current, boolean[] visited) {
		if(current==r) {
			System.out.println(Arrays.toString(temp));
			return;
		}
		for(int i=0;i<src.length;i++) {
			if(visited[i]==false) {
				visited[i]=true;
				
				temp[current]=src[i];
				permutation(r, temp, current+1, visited);
				visited[i]=false;
			}
		}
	}
}
