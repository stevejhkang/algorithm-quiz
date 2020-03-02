package boj_february;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_11866 {
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		ArrayList<Integer> li = new ArrayList<>();
		
		for(int i=1;i<=n;i++) {
			li.add(i);
		}
		int idx=0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<");
		while(!li.isEmpty()) {
			if(li.size()==1) {
				sBuilder.append(li.get(0)+">");
				System.out.println(sBuilder);
				return;
			}
			idx=(idx+k-1)%li.size();
			sBuilder.append(li.remove(idx)+", ");
		}
	}
	
}
