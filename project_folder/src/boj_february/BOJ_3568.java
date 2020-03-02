package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3568 {
	public static void main(String[] args) throws IOException {
		//*,&,[] 
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine(),", ;");
		
		String first = stringTokenizer.nextToken();
		StringBuilder stringBuilder =new StringBuilder();
		while(stringTokenizer.hasMoreTokens()) {
			stringBuilder = new StringBuilder();
			stringBuilder.append(first);
			
			String temp= stringTokenizer.nextToken();
			
			int index = (temp.indexOf("[")==-1?Integer.MAX_VALUE:temp.indexOf("["));
			index=Math.min(index, (temp.indexOf("&")==-1?Integer.MAX_VALUE:temp.indexOf("&")));
			index=Math.min(index,(temp.indexOf("*")==-1?Integer.MAX_VALUE:temp.indexOf("*")));
			
//			System.out.println(index);
			
			if(index!=Integer.MAX_VALUE) {
				String name = temp.substring(0,index);
				String type = (new StringBuffer(temp.substring(index)).reverse().toString());
				type= type.replace(']', '+');
				type = type.replace('[', ']');
				type = type.replace('+', '[');
				stringBuilder.append(type+" "+name+";");
			}
			else {
				stringBuilder.append(" "+temp+";");
			}
			System.out.println(stringBuilder);
		}
	}
}
