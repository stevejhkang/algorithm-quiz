package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;



public class JG_1809_2 {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		
//		BufferedReader bReader =new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());	
//		int n = Integer.parseInt(stringTokenizer.nextToken());
		int n = scanner.nextInt();
		
		StringBuilder sBuilder = new StringBuilder();
		Stack<wall> stack = new Stack<>();
		
//		stringTokenizer = new StringTokenizer(bReader.readLine());
		for(int i=1;i<=n;i++) {
//			int newHeight =Integer.parseInt(stringTokenizer.nextToken());
			int newHeight =scanner.nextInt();
			while(!stack.isEmpty()) {
				int height_temp=stack.peek().height;
				if(height_temp<=newHeight) {
					stack.pop();
				}else {
					
					break;
				}
			}
			if(stack.isEmpty()) {
				sBuilder.append(0+" ");
			}else {
				sBuilder.append(stack.peek().idx+" ");
			}
			stack.push(new wall(newHeight, i));
		}
		System.out.println(sBuilder);
	}

}
class wall{
	int height;
	int idx;
	public wall(int height, int idx) {
		super();
		this.height = height;
		this.idx = idx;
	}
	
}