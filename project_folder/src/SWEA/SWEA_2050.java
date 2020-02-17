package SWEA;

import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_2050 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> arrayList = new ArrayList<>();
//		scanner= new Scanner("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String string = scanner.next();
		StringBuilder sBuilder = new StringBuilder();
	
		for(int i=0;i<string.length();i++) {
			sBuilder.append(string.charAt(i)-'A'+1).append(" ");
		}
		System.out.println(sBuilder);		
	}

}
