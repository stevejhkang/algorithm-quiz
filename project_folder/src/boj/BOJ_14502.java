package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * @author junhukang
 * @time 2020. 2. 3. 오후 10:09:13
 * @category BFS + DFS
* @problem_description 3개의 벽을 설치해서 안전지역이 최대가 되도록 만들기
* @solving_description safe_zone의 위치를 저장해서 재귀로 3개를 선택한 후에 map에 setting한 후 바이러스를 bfs로 퍼뜨린다. 그리고 safe_zone의 크기를 비교한다.
* 중요한건 테케마다 초기화를 잘 시켜주어야 한다.
*/
public class BOJ_14502 {
	static int map[][] ;
	static int dy[]= {1,0,-1,0}; static int dx[]= {0,-1,0,1};
	static int n,m,safe_zone; //세로 가로 안전 영역 크기
	static ArrayList<position> virus = new ArrayList<>();
	static ArrayList<position> safe_list = new ArrayList<>();
	static ArrayList<position> set_wall = new ArrayList<>();
	static int max;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt(); m = scanner.nextInt();
		map=new int[n][m];
		
		safe_zone=-3;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				int a = scanner.nextInt();
				map[i][j]=a;
				if(a==2) {
					virus.add(new position(i, j));
				}
				else if(a==0) {
					safe_zone++; //safe_zone 개수 체크 
					safe_list.add(new position(i, j));
				}
			}
		}
		max=Integer.MIN_VALUE;
		setwall(0, 0);
		System.out.println(max);
	}
	static void setwall(int r,int idx) {
		if(r==3) { //3개가 세워지면 바이러스를 퍼뜨린다.
			spread();
			return;
		}
		for(int i=idx;i<safe_list.size();i++) { //안전한 구역 중에서 벽을 놓을 3개를 선택한다.
			position temp = safe_list.get(i);
			set_wall.add(temp);
			setwall(r+1,idx+1);
			set_wall.remove(r);
		}
	}
	static void spread() {
		//테케마다 초기화 시켜주어야한다.
		int[][] map_copy = new int [n][m];
		int[][] visit= new int [n][m];
		int safe_zone_copy=safe_zone;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map_copy[i][j]=map[i][j];
				visit[i][j]=map[i][j];;
			}
		}
		for(int i=0;i<set_wall.size();i++) {
			map_copy[set_wall.get(i).y][set_wall.get(i).x]=1;
		}
		//바이러스를 큐에 담아준다.
		Queue<position> queue = new LinkedList<position>();
		for(int i=0;i<virus.size();i++) {
			queue.add(virus.get(i));
		}
		while(!queue.isEmpty()) {
			int y= queue.peek().y; int x =queue.poll().x;
			for(int i=0;i<4;i++) {
				int ny= y+dy[i]; int nx=x+dx[i];
				if(ny<0||ny>=n||nx<0||nx>=m)
					continue;
				if(map_copy[ny][nx]==0&&visit[ny][nx]==0) {
					map_copy[ny][nx]=2; visit[ny][nx]=2;
					safe_zone_copy--;
					queue.add(new position(ny, nx));
				}
			}
		}
		
		if(max<safe_zone_copy) {
			max=safe_zone_copy;
		}
	}
	
}
class position {
	int y; int x;

	public position(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "[y=" + y + ", x=" + x + "]";
	}
	
}
