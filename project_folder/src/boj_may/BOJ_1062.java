package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class BOJ_1062 {
	private static int n;
	private static int k;
	static Set[] words; //모든 단어들의 셋을 저장하는 집합
	static Set<Character> learn; //배운 단어를 저장하는 집합
	private static int max; //맥스값을 저장
	private static Set<Character> antic; //기본적으로 배워야하는 앞뒤 단어
	private static int count;
	private static ArrayList<Character> list;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());
		
		if(k<=4) {
			System.out.println(0);
			return;
		}
		//여기서 모든 문자열을 set으로 만들어준 후에 containsAll을 확인해본다.
		words = new Set[n];
		for(int i=0;i<n;i++) {
			words[i]= new HashSet<Character>();
		}
		for(int i=0;i<n;i++) {
			String s = bufferedReader.readLine();
			for(int j=0;j<s.length();j++) {
				char c = s.charAt(j);
				words[i].add(c);
			}
		}
		String alpha = "bdefghjklmopqrsuvwxyz";
		list = new ArrayList<Character>();
		for(int i=0;i<alpha.length();i++) {
			list.add(alpha.charAt(i));
		}
		learn = new HashSet<Character>();
		learn.add('a');
		learn.add('n');
		learn.add('t');
		learn.add('i');
		learn.add('c');
		
		max = Integer.MIN_VALUE;

		dfs(0);
		System.out.println(max);
		return;
		
	}
	static void dfs(int index) { 
		//21개중에서 k개를 선택한 후에 전부 해본다.
		if(learn.size()==k) {
			int cnt=0;
			for(int i=0;i<n;i++) {
				if(learn.containsAll(words[i])) {
					cnt++;
				}
			}
			max=Math.max(max, cnt);
			return;
		}
		for(int i=index;i<list.size();i++) {
			char temp = list.get(i);
			learn.add(temp);
			dfs(i+1);
			learn.remove(temp);
		}
	}
}
