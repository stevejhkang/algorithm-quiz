package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author junhukang
 * version2 BFS : 
 * 결국 상하좌우 점프1,2,3,4 이동하는 전부를 계산해야한다.
 * 일일이 이동해서 이동횟수가 많아도 결국 경우의 수 중 일부이므로 이동횟수로 가지치기는 힘들다.
 * 그렇다면? -> k가 몇개 남은 채로 거기로 이동했는지를 저장한다.
 * 가지치기(위치가 같고 남은 점프횟수가 같으면 같은 상태)를 어떻게 할지, 어떻게 저장할지(3차원 배열)를 고민하는 문제
 * 
 */

public class BOJ_1600_teacher2 {
	
	private static int W;
	private static int H;
	private static int[][] m;
	private static int minMoveCnt;
	private static boolean[][][] visited;
	
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
		visited = new boolean[H][W][K+1]; //방문배열, 남은 이동횟수배열 칸을 추가한다.
		visited[0][0][K]=true; //줄 옮기기 맨앞칸에 커서 두고 alt로 위아래
		//BFS //무엇을 넣을 것인가? 스택에 넣을거 그대로 넣자 {r,c,K,moveCnt}
		Queue<int[]> queue = new LinkedList();
		//배열 생성 방법 4가지
		//1. int[] a ={1,1,2,2,};
		//2. int[] a = new int[4];
		//3. int[] a = new int[] {1,1,2,2}; //익명배열 쓸 때 사용
		//4. 
		queue.offer(new int[]{0,0,K,0});
		while(!queue.isEmpty()) {
			int[] arr = queue.poll();
			int r = arr[0];
			int c = arr[1];
			int kk = arr[2];
			int moveCnt = arr[3]; //배열 참조보다 지역변수로 저장하면 좀더 빠르다.
			
			if(r==H-1&&c==W-1) {//우측하단 도착 //BFS는 각 단계별로 
				if(minMoveCnt>moveCnt) {
					minMoveCnt=moveCnt;
				}
				break; //단계별로 가는 것이기 때문에 답을 찾았으면 그게 최솟값이다.
			}
			for(int i=8;i<dr.length;i++) {//상하좌우
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(0<=nr&&nr<H&&0<=nc&&nc<W
						&&!visited[nr][nc][kk]&&m[nr][nc]==0) {
					visited[nr][nc][kk]=true;
					queue.offer(new int[] {nr,nc,kk,moveCnt+1});
				}
			}
			if(kk==0) continue;// 말처럼 이동할 수 있는 남은 횟수가 없으면 그냥 넘어가라.
			//말처럼 이동
			for(int i=0;i<8;i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(0<=nr&&nr<H&&0<=nc&&nc<W
						&&!visited[nr][nc][kk-1]&&m[nr][nc]==0) {
					visited[nr][nc][kk-1]=true;
					queue.offer(new int[] {nr,nc,kk-1,moveCnt+1});
				}
			}
		}//while
		
		
		//minMoveCnt 출력
		System.out.println(minMoveCnt==Integer.MAX_VALUE?-1:minMoveCnt);
	}//main
	static int [] dr = {-1,-2,-2,-1,1,2,2,1,-1,1,0,0}; //말 점프, 상하좌우
	static int [] dc = {-2,-1,1,2,2,1,-1,-2,0,0,-1,1}; //말 점프, 상하좌우
	/** r,c좌표, K: 말처럼 이동할 수 있는 남은 횟수, moveCnt: 현재까지 이동 횟수*/
	
}
