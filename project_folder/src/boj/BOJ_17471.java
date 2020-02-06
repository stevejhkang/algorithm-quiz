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

public class BOJ_17471 {
	private static int n;
	private static int[] popul;
	static ArrayList<popul> team1;
	static ArrayList<popul> team2;
	private static boolean[] visit;
	static int[] visit2;
	private static int sum1;
	private static int sum2;
	private static int min;
	private static List<Integer> li[];
	private static boolean flag;


	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());

		popul = new int[n+1]; visit= new boolean[n+1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= n; i++) { //도시 번호 인덱스에 인구수 넣는다.
			popul[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		//연결상태 표시를 위한 리스트
		li = new List[n+1];
		for (int i = 1; i <= n; i++) {
			li[i]=new ArrayList<>();
		}
		//연결상태 표시해준다.
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int link= Integer.parseInt(stringTokenizer.nextToken());
			for(int j=0;j<link;j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				li[i].add(a);
			}
		}
		//팀을 두개로 나눈다.
		team1 = new ArrayList<popul>();
		team2 = new ArrayList<popul>();
		
		sum1=0; sum2=0;
		min=1000;
		flag =true;
		//한쪽만 배정해주면 한쪽은 정해지므로 절반만 해준다.
		//그리고 팀배정해줄때 팀마다 인구수도 종합해준다.
		for(int i=n/2;i>=1;i--) {
			recursion(0, i);
		}
		
		
		
		if(min==1000) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
		
	}
	static void recursion(int k, int r) {
		if(k==r) {
			for (int i = 1; i <= n; i++) {
				if(!visit[i]) {//방문 안했으면 team2에 다 배정
					team2.add(new popul(i, 2));
					sum2+=popul[i];
				}
			}
		
			if(min<Math.abs(sum1-sum2)) { //더 크면 연결확인 안하고 초기화
				team2.clear();
				sum2=0;
				return;
			}
			
//			if(Math.abs(sum1-sum2)==1) {
//				System.out.println("");
//				System.out.println("");
//				System.out.println(team1.toString());
//				System.out.println(team2.toString());
//				
//			}
			
			
			//여기에서 연결되어 있는지 확인
			dfs();
//			System.out.println(Math.abs(sum1-sum2));
			//
			team2.clear();
			sum2=0;
		}
		//1부터 n까지 1팀에 r개만큼 배정한다.
		for(int i=1;i<=n;i++) {
			if(!visit[i]) {
				visit[i]=true;
				team1.add(new popul(i, 2));
				sum1+=popul[i];
				recursion(k+1, r);
				sum1-=popul[i];
				team1.remove(k);
				visit[i]=false;
			}
		}
	}
	
	static void dfs() {
		visit2 = new int[n+1];
		//팀 넘버를 같이 넣어주어야 된다!!!!!!!!!!!!!!!!!!!!!!
		//team1의 한 인덱스를 스택에 넣는다.
		Stack<Integer> stack1 = new Stack<>();
		stack1.push(team1.get(0).idx);
		
		while(!stack1.isEmpty()) {
			int a= stack1.pop();
			if(visit2[a]!=1) continue;
			if(Math.abs(sum1-sum2)==1) {
				System.out.print(a+"-");
			}
			visit2[a]=1;
			for(int i=0;i<li[a].size();i++) {
				if(visit2[li[a].get(i)]==0) {//방문 안했으면 넣어준다.
					stack1.push(li[a].get(i));
				}
			}
		}
		
		System.out.println("");
		//team2의 한 인덱스를 스택에 넣는다.
		Stack<Integer> stack2 = new Stack<>();
		stack2.push(team2.get(0).idx);
		while(!stack2.isEmpty()) {
			int a = stack2.pop();
			
			if(visit2[a]!=2) continue;
			if(Math.abs(sum1-sum2)==1) 
				System.out.print(a+"-");
			visit2[a]=2;
			for(int i=0;i<li[a].size();i++) {
				int child_idx=li[a].get(i);
				if(visit2[child_idx]==0) {//방문 안했으면 넣어준다.
					stack2.push(li[a].get(i));
				}
			}
		}
//		System.out.println("");
		//1번팀이 1번체크 되어 있고
		for(int i=0;i<team1.size();i++) {
			int idx= team1.get(i).idx;
			if(visit2[idx]!=1) {
				flag=false;
				break;
			}
		}
		for(int i=0;i<team2.size();i++) {
			int idx= team2.get(i).idx;
			if(visit2[idx]!=2) {
				flag=false;
				break;
			}
		}
		//2번팀이 2번으로 체크되어 있는지 확인
		if(flag&&min>Math.abs(sum1-sum2)) {
			min=Math.abs(sum1-sum2);
			return;
		}
	}
	static class popul{
		int idx;
		int team;
		public popul(int idx, int team) {
			super();
			this.idx = idx;
			this.team = team;
		}
		@Override
		public String toString() {
			return "[idx=" + idx + ", team=" + team + "]";
		}
		
	}
}

