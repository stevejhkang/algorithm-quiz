package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1244 {
	private static String string;
	private static boolean[] visit;
	private static char[] arr;
	private static int time;
	private static int[] idx;
	private static int max;
	private static char[] arr_copy;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader =new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(bReader.readLine());
		for(int tc=1;tc<=t;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
			string = stringTokenizer.nextToken();
			arr = string.toCharArray();
			arr_copy=new char[string.length()];
			time = Integer.parseInt(stringTokenizer.nextToken());
			visit= new boolean[string.length()];
			idx = new int[2];
			max= Integer.MIN_VALUE;
			for(int i=1;i<=time;i++) {
				dfs(0, 0, i,arr_copy);
			}
			
			System.out.println("#"+tc+" "+max);
		}
		
	}//main
	static char[] dfs(int k,int index,int t,char[] arr) {
		if(k==2) {
			char temp = arr[idx[0]];
			arr[idx[0]]=arr[idx[1]];
			arr[idx[1]] = temp;
			
			return arr;
		}
		for(int i=index;i<string.length();i++) {
			if(!visit[i]) {
				visit[i]=true;
				idx[k]=i;
				dfs(k+1,i+1,time,arr);
				visit[i]= false; 
			}
		}
		return arr;
	}
	static int charArrayToInteger(char[] array){
	    String arr = new String(array);
	    int number = Integer.parseInt(arr);

	    return number;
	}
}
