package dfs_and_bfs;

import java.util.ArrayList;
import java.util.List;

import sun.misc.ThreadGroupUtils;

public class graph6 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int v = 7;
		String data ="1 2 2 1 3 4 1 6 1 1 7 3 6 4 6 6 5 1 5 4 2 7 5 4";
		List<Point>[] graph = new List[v+1];
		
		//graph초기화
		for(int i=1;i<graph.length;i++) {
			graph[i] = new ArrayList<>();
		}
		
		String[] splited = data.split(" ");
		for(int i=0;i<splited.length;i+=3) {
			int a=Integer.parseInt(splited[i]);
			int b=Integer.parseInt(splited[i+1]);
			int w = Integer.parseInt(splited[i+2]);
			graph[a].add(new Point(b, w));
		}
		
		
		for(List<Point> list: graph) {
			System.out.println(list);
		}
	}
	//정점과 가중치 정보를 저장할 사용자 정의 클래스
	static class Point{
		int vId;
		int weight;
		
		public Point(int vId, int weight) {
			super();
			this.vId = vId;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "[vId=" + vId + ", weight=" + weight + "]";
		}
		
	}
	static class pair{
		int y; int x;

		public pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "[y=" + y + ", x=" + x + "]";
		}
		
	}
}

