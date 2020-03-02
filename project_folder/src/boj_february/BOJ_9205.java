package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 25. 오전 10:25:08
 * @category 
* @problem_description	//20개 한박스 50미터당 한병씩 50나누면 1미터당 한병씩이다.
		//가는 길에 맥주를 사야될 수도 있다. 
		//박스에 있는 맥주 20병을 넘을 수 없다,
		//집, 페스티벌 좌표 주어짐
		//페스티벌에 무사히 도착할 수 있는지 구하라
* @solving_description 
*/
public class BOJ_9205 {
	
	private static int n;
	private static int startY;
	private static int startX;
	private static int endY;
	private static int endX;
	private static int beer;
	private static boolean flag;
	private static boolean[] visit;
	private static ArrayList<dot> convi;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bufferedReader.readLine());

		for(int tc = 0;tc<t;tc++) {
			n = Integer.parseInt(bufferedReader.readLine());
			
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			startY = Integer.parseInt(stringTokenizer.nextToken());
			startX =Integer.parseInt(stringTokenizer.nextToken());
			
			convi = new ArrayList<dot>();
			for(int i=0;i<n;i++) {
				stringTokenizer  = new StringTokenizer(bufferedReader.readLine());
				int y = Integer.parseInt(stringTokenizer.nextToken());
				int x = Integer.parseInt(stringTokenizer.nextToken());
				convi.add(new dot(y, x));
			}
			
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			endY = Integer.parseInt(stringTokenizer.nextToken());
			endX = Integer.parseInt(stringTokenizer.nextToken());
			
			beer = 20*50;
			flag = false;
			visit = new boolean[n];
			dfs(startY,startX);
			if(flag) {
				System.out.println("happy");
			}
			else {
				System.out.println("sad");
			}
		}//tc
		
	}//main
	
	static void dfs(int y, int x) {
		if(flag) return;
		if(Math.abs(endY-y)+Math.abs(endX-x)<=1000) {
			flag= true;
			return;
		}
		for(int i=0;i<convi.size();i++) {
			if(flag) return;
			dot temp = convi.get(i);
			int ny = temp.y; int nx = temp.x;
			
			if(!visit[i]&&(Math.abs(ny-y)+Math.abs(nx-x)<=1000)) {
				visit[i] = true;
				dfs(ny,nx);
//				visit[i] = false; //이미 i를 거치고 간 경우를 전부 탐색햇으나 안되었으므로 
				//false 시키지 않게 처리해서 가지치기를 해준다.
			}
		}
	}
	
	static class dot{
		int y,x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}//dot
	
}
