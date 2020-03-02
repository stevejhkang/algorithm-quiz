package boj_february;

import java.awt.Frame;
import java.awt.dnd.DragGestureEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author junhukang
 * version1 DFS 터지는 버전 : DFS는 터진다.
 * 결국 상하좌우 점프1,2,3,4 이동하는 전부를 계산해야한다.
 */

public class BOJ_1600_teacher {
	
	private static int W;
	private static int H;
	private static int[][] m;
	private static int minMoveCnt;
	private static int[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int K=Integer.parseInt(bReader.readLine()); //말처럼 이동할 수 있는 횟수, 0<=K<=30
		
		StringTokenizer stringTokenizer =new StringTokenizer(bReader.readLine()," ");
		W= Integer.parseInt(stringTokenizer.nextToken()); //가로 //줄 통째복사는 ctrl+alt+아래방향키
		H= Integer.parseInt(stringTokenizer.nextToken()); //세로
		m=new int[H][W];
		for(int i=0;i<H;i++) {
			String str = bReader.readLine();
			for(int j =0,index=0;j<W;j++,index+=2) {
				m[i][j]=str.charAt(index)-'0'; //0:평지 1:장애물
			}
		}
		minMoveCnt = Integer.MAX_VALUE;
		visited = new int[H][W];
		visited[0][0]=1; //줄 옮기기 맨앞칸에 커서 두고 alt로 위아래
		//DFS는 방문 배열
		dfs(0,0,K,0);
		//minMoveCnt 출력
		System.out.println(minMoveCnt==Integer.MAX_VALUE?-1:minMoveCnt);
	}//main
	static int [] dr = {-1,-2,-2,-1,1,2,2,1,-1,1,0,0}; //말 점프, 상하좌우
	static int [] dc = {-2,-1,1,2,2,1,-1,-2,0,0,-1,1}; //말 점프, 상하좌우
	/** r,c좌표, K: 말처럼 이동할 수 있는 남은 횟수, moveCnt: 현재까지 이동 횟수*/
	public static void dfs(int r, int c, int K, int moveCnt) {
		// TODO Auto-generated method stub
		if(r==H-1&&c==W-1) {//종료파트, 우축하단 도착 
			if(minMoveCnt>moveCnt) {
				minMoveCnt = moveCnt;
			}
			return;
		}
		if(minMoveCnt<=moveCnt) return;
		//재귀파트
		//말처럼 이동
		if(K>0) {
			for(int i=0;i<8;i++) {
				int nr= r+dr[i]; 
				int nc=c+dc[i]; //많이쓰이는 값은 저장해두고, 최대한 가까이 저장해둔다.
				//배열 범위 체크, 방문했는지, 갈 수 있는 길인지 체크
				if(0<=nr && nr<H&&0<=nc&&nc<W
						&&visited[nr][nc]==0&&m[nr][nc]==0) {
					visited[nr][nc]=1;
					dfs(nr, nc, K-1, moveCnt+1);
					visited[nr][nc]=0; 
				}
			}
		}
		//상하좌우
		for(int i=8;i<dr.length;i++) {
			int nr= r+dr[i]; 
			int nc=c+dc[i]; //많이쓰이는 값은 저장해두고, 최대한 가까이 저장해둔다.
			//배열 범위 체크, 방문했는지, 갈 수 있는 길인지 체크
			if(0<=nr && nr<H&&0<=nc&&nc<W
					&&visited[nr][nc]==0&&m[nr][nc]==0) {
				visited[nr][nc]=1;
				dfs(nr, nc, K, moveCnt+1);
				visited[nr][nc]=0; 
			}
		}
		
	}//dfs
}
