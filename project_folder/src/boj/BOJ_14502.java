package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author junhukang
 * @time 2020. 2. 2. 오후 3:05:11
 * @category BFS
* @problem_description
* @solving_description 
*/

public class BOJ_14502 {
	static int[][] map; //map저장 
	static int dy[]= {-1,0,1,0}; static int dx[]= {0,1,0,-1};
	static ArrayList<Dot> street_list; //wall 후보
	static ArrayList<Dot> wall_setting;
	static int[][] map_copy;
	static int n,m;
	static ArrayList<Dot> gem_list; 
	static int empty;
	static int visit[][];
	static int min;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n= scanner.nextInt(); m = scanner.nextInt();
		map=new int[n][m]; //초기 지도 저장
		gem_list= new ArrayList<>(); //2인거 저장
		street_list=new ArrayList<Dot>(); //1을 3개 세우기위해 0인 목록을  전체 저장
		wall_setting=new ArrayList<>();
		
		empty=-3;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				int a = scanner.nextInt();
				map[i][j]=a;
				if(a==2) {//세균이면 큐에 추가
					gem_list.add(new Dot(i,j));
				}
				else if(a==0) {
					empty++;
					street_list.add(new Dot(i, j)); //후보군에 추가
				}
			}
		}

		min=Integer.MAX_VALUE; //전체 
		//여기에 벽을 세우는 로직이 나와야됨
		setWall(0, 0);
		
		System.out.println(min);
	}
	static void setWall(int r,int idx) {
		if(r==3) { //3개가 되면 바이러스를 퍼뜨린다.
			visit=new int[n][m];
			map_copy=new int[n][m];
			//map 복사해주고 
			for(int i=0;i<n;i++) { 
				for(int j=0;j<m;j++) {
					map_copy[i][j]=map[i][j];
					visit[i][j]=map[i][j];
				}
			}
			
			//벽 표시해주고
			for(int i=0;i<wall_setting.size();i++) {
				map_copy[wall_setting.get(i).y][wall_setting.get(i).x]=1;
			}
//			for(int i=0;i<n;i++) {
//				for(int j=0;j<m;j++) {
//					System.out.print(map_copy[i][j]+" ");
//				}
//				System.out.println("");
//			}
//			System.out.println("");
			spreadVirus();
			return;
		}
		for(int i=idx;i<street_list.size();i++) { 
			wall_setting.add(street_list.get(i)); 
			setWall(r+1, i+1);
			wall_setting.remove(r);
		}
		return;
	}
	static void spreadVirus() {
		Stack<Dot> stk = new Stack<>();
		int temp_empty=empty;
//		System.out.println(temp_empty);
		//초기 바이러스를 넣어준다.
		for(int i=0;i<gem_list.size();i++) {
			stk.push(gem_list.get(i));
		}
		//바이러스 다퍼질때까지 
		while(!stk.isEmpty()) {
			int y= stk.peek().y; int x = stk.peek().x; stk.pop();
			for(int i=0;i<4;i++) {
				int ny= y+dy[i]; int nx=x+dx[i]; 
				//범위 확인
				if(ny<0||ny>=m||nx<0||nx>=n) { //범위벗어나면 continue;
					continue;
				}
				if(visit[ny][nx]==0&&map_copy[ny][nx]==0) {
					visit[ny][nx]=2; map_copy[ny][nx]=2;
					stk.push(new Dot(ny,nx));
					temp_empty--;
				}
			}
		}
//		for (int i = 0; i < map_copy.length; i++) {
//			for (int j = 0; j < map_copy[i].length; j++) {
//				System.out.print(map_copy[i][j]+" ");
//			}
//			System.out.println("");
//			
//		}
//		System.out.println("");
		if(min>temp_empty) {
			min=temp_empty;
			
		}
	}
}

class Dot {
	int y;
	int x;
	public Dot(int y, int x) {
		this.y = y;
		this.x=x;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.y+" : "+this.x;
	}
}