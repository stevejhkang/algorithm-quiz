package SWEA;

import java.util.Scanner;
import java.util.Stack;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import sun.tools.jar.resources.jar;

public class SWEA_1218 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		for(int tc=1;tc<=10;tc++) {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("#"+tc+" ");
			int n = scanner.nextInt();
			String string = scanner.next();
			Stack<String> stack =new Stack<>();
			String open="({[<"; 
			String close=")}]>";
			
			for(int i=0;i<n;i++) {
				boolean result=false;
				String charc= string.substring(i,i+1);
				
				if(open.indexOf(charc)!=-1) {//-1 이 아니면 있는거
					stack.push(charc);
//					System.out.println(stack.peek());
				}
				else { //여는게 없으면 닫는거
					if(stack.isEmpty()) { //비어있으면 0
						sBuilder.append(0);
//						System.out.println(sBuilder);
						break;
					}
					if(open.indexOf(stack.peek())!=-1) { //맨위에 것이 여는거면 
						String prev = stack.pop();
						//open.contains(
						if(!stack.isEmpty()) {
							if(charc.contains(")")&&prev.equals("(")) {
								result =true;
							}
							else if(charc.contains("}")&&prev.equals("{")) {
								result =true;
							}else if(charc.contains("]")&&prev.equals("[")) {
								result =true;
							}else if(charc.contains(">")&&prev.equals("<")) {
								result =true;
							}
							if(!result) {
								sBuilder.append(0);
								break;
							}
						}
						
					}
				}
			}
			if(stack.empty()) {
				sBuilder.append(1);
			}
			
			System.out.println(sBuilder);
			
		}
	}

}
