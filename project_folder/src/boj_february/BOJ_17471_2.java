package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17471_2 {
	private static int n;
	private static int[] popul;
	private static int[][] link;
	private static int[] team_divide;
	private static int min;
	private static ArrayList<Integer> team1;
	private static ArrayList<Integer> team2;
	private static int sum1;
	private static int sum2;
	private static int[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bufferedReader.readLine());
		popul = new int[n+1];
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		//인구 수 저장
		for(int i=1;i<=n;i++) {
			popul[i]=Integer.parseInt(stringTokenizer.nextToken());
		}
		//연결상태 표시 인접행렬 표시
		link = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			int m = Integer.parseInt(stringTokenizer.nextToken());
			for(int j=1;j<=m;j++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				link[j][a]=1;
			}
		}
		//최솟값 설정
		team_divide = new int[n+1];
		min = Integer.MAX_VALUE;
		team1 = new ArrayList<Integer>();
		sum1 = 0;
		team2 = new ArrayList<Integer>();
		sum2=0;
		
		for(int i=n/2;i>=1;i--) {
			//0부터 시작해서 i개를 선택한다.
			com(0,i,0);
		}
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
	}
	static void com(int k, int r,int idx) {
		if(k==r) {
			//나머지는 team2 로 할당
//			System.out.println("");
//			for(int i=0;i<team1.size();i++) {
//				System.out.print(team1.get(i)+" ");
//			}
//			System.out.println("");
			for(int i=1;i<=n;i++) {
				if(team_divide[i]==0) {
					team_divide[i]=2;
					team2.add(i);
					sum2+=popul[i];
//					System.out.print(i+" ");
				}
			}
			if(min<Math.abs(sum1-sum2)) {
				team2.clear(); sum2=0;
				return;
			}
//			System.out.println("");
			//다 팀이 할당되면 각 팀에 대해 dfs를 시작한다.
			visit = new int[n+1];
			for(int i=1;i<=n;i++) {
				if(team_divide[i]==2) {
					visit[i]=2;
				}
			}
			Stack<Integer> stack1 = new Stack<>();
			stack1.push(team1.get(0));
			while(!stack1.isEmpty()) {
				int now = stack1.pop();
				for(int i=1;i<=n;i++) {
					if(link[now][i]==1&&visit[i]==0) {//연결되어 있고 같은 그룹이면
						visit[i]=1; //1로 처리해준다.
						stack1.push(i);
					}
				}
			}
			for(int i=1;i<=n;i++) {
				if(team_divide[i]!=visit[i]) {
					team2.clear();
					sum2=0;
					team_divide = new int[n+1];
					for(int j=1;j<=n;j++) {
						if(team_divide[j]==2) {
							team_divide[j]=0;
						}
					}
					return;
				}
			}
			visit = new int[n+1];
			for(int i=1;i<=n;i++) {
				if(team_divide[i]==1) {
					visit[i]=1;
				}
			}
			Stack<Integer> stack2 = new Stack<>();
			stack1.push(team2.get(0));
			while(!stack2.isEmpty()) {
				int now = stack2.pop();
				for(int i=1;i<=n;i++) {
					if(link[now][i]==1&&visit[i]==0) {//연결되어 있고 같은 그룹일때만 
						visit[i]=2; //1로 처리해준다.
						stack2.push(i);
					}
				}
			}
			for(int i=1;i<=n;i++) {
				if(team_divide[i]!=visit[i]) {
					team2.clear();
					sum2=0;
					for(int j=1;j<=n;j++) {
						if(team_divide[j]==2) {
							team_divide[j]=0;
						}
					}
					return;
				}
			}
			int sub=Math.abs(sum1-sum2);
			if(min>sub) {
				min=sub;
			}
			//team2 초기화
			team2.clear();
			sum2=0;
			for(int j=1;j<=n;j++) {
				if(team_divide[j]==2) {
					team_divide[j]=0;
				}
			}
			return;
		}
		for(int i=idx;i<=n;i++) {
			//team 1번을 설정하고 리스트에 추가
			team_divide[i]=1;
			team1.add(i);
			sum1+=popul[i];
			com(k+1, r, i+1);
			team1.remove(k);
			team_divide[i]=0;
			sum1-=popul[i];
		}
	}// com
	
}
