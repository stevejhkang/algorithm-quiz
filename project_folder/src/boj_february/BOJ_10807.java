package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_10807 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine());
		int n = Integer.parseInt(stringTokenizer.nextToken());
		
		HashMap<Integer, Integer> hMap = new HashMap<>();
		stringTokenizer = new StringTokenizer(bReader.readLine());
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(stringTokenizer.nextToken());
			if(hMap.containsKey(a)) {
				hMap.replace(a, hMap.get(a)+1);
			}
			else {
				hMap.put(a, 1);
			}
		}
		stringTokenizer = new StringTokenizer(bReader.readLine());
		int a = Integer.parseInt(stringTokenizer.nextToken());
		if(hMap.containsKey(a)) {
			System.out.println(hMap.get(a));
		}
		else
		{
			System.out.println(0);
		}
			
		
		
		
	}

}
