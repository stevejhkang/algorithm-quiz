package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 7. 오후 12:33:50
 * @category 그래프 DFS와 조합문제
 * @problem_description
 * @solving_description  전체 그래프에서 team1들이 이어졌는지를 확인하기 위해선 team2들을 visit을 true처리해준 후 돌리는게 관건. 
 * 그래프 이어짐을 ArrayList로 만들 줄 아는 것도 중요한 내용
 */

public class BOJ_17471 {
	private static int n;
	private static int[] popul;
	private static List[] li;
	static boolean visit[];
	private static ArrayList<Integer> team1;
	private static ArrayList<Integer> team2;
	private static int sum1;
	private static int sum2;
	private static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n= Integer.parseInt(stringTokenizer.nextToken());
		
		//인구 수를 작성
		popul =  new int[n+1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for(int i=1;i<=n;i++) {
			popul[i]=Integer.parseInt(stringTokenizer.nextToken());
		}
		
		//연결상태를 저장 i번에 연결된 마을 인덱스
		li = new List[n+1];
		for(int i=1;i<=n;i++) {
			li[i]= new ArrayList<Integer>();
		}
		
		//
		for(int i=1;i<=n;i++) {
			stringTokenizer= new StringTokenizer(bufferedReader.readLine());
			int k = Integer.parseInt(stringTokenizer.nextToken());
			for(int j=0;j<k;j++) {
				int dot = Integer.parseInt(stringTokenizer.nextToken());
				li[i].add(dot);
			}
		}
		visit = new boolean[n+1];
		//선택
		team1= new ArrayList<Integer>();
		team2 = new ArrayList<Integer>();
		sum1=0;
		sum2=0;
		min=Integer.MAX_VALUE;
		
		for(int i=n/2;i>=1;i--) {
			recursion(0, i);
		}
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
		
		
	}
	static void recursion(int k, int r) {
		if(k==r) {
			for(int i=1;i<=n;i++) {
				if(!visit[i]) {
					sum2+=popul[i];
					team2.add(i);
				}
			}
			if(min<Math.abs(sum1-sum2)) {
				team2.clear(); sum2=0;
				return;
			}
			if(dfs()) {
				min=Math.abs(sum1-sum2);
			}
			
			team2.clear(); sum2=0;
			return;
		}
		for(int i=1;i<=n;i++) {
			if(!visit[i]) {
				visit[i]=true;
				sum1+=popul[i];
				team1.add(i);
				recursion(k+1, r);
				team1.remove(k);
				sum1-=popul[i];
				visit[i]= false; 
			}
		}
	}//recursion
	
	static boolean dfs() {
		boolean visit2[]=new boolean[n+1];
		boolean flag1= true; 
		boolean flag2=true;
		
		for(int i=0;i<team2.size();i++) {
			visit2[team2.get(i)]=true;
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(team1.get(0));
		while(!stack.isEmpty()) {
			int now = stack.pop();
			if(visit2[now]) continue;
			visit2[now]=true;
			for(int i=0;i<li[now].size();i++) {
				int next= (int) li[now].get(i);
				if(!visit2[next]) {
					stack.push(next);
				}
			}
		}
		
		//team1을 다 방문했는지 확인한다.
		for(int i=0;i<team1.size();i++) {
			if(!visit2[team1.get(i)])
				flag1=false;
		}
		
		//team2방문한 것을 다 초기화
		for(int i=0;i<team2.size();i++) {
			visit2[team2.get(i)]=false;
		}
		//team1다 방문했다고 초기화
		for(int i=0;i<team1.size();i++) {
			visit2[team1.get(i)]=true;
		}
		
		stack.clear();
		stack = new Stack<>();
//		System.out.println(team2.size());
		stack.push(team2.get(0));
		while(!stack.isEmpty()) {
			int now = stack.pop();
			if(visit2[now]) continue;
			visit2[now]=true;
			for(int i=0;i<li[now].size();i++) {
				int next= (int) li[now].get(i);
				if(!visit2[next]) {
					stack.push(next);
				}
			}
		}
		for(int i=0;i<team2.size();i++) {
			if(!visit2[team2.get(i)])
				flag2=false;
		}
		return flag1&&flag2;
	}//dfs
}//last

