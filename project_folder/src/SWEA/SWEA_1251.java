package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 11. 오후 1:21:02
 * @category @problem_description 모든 섬들을 해저터널로 연결해야한다. 해저터널건설에 환경 부담금이 있다.
 *           환경부담금: 환경 부담 세율(E) * 해저터널 길이의 제곱(L^2)만큼 지불 (x1-x2)^2+(y1-y2)^2 환경
 *           부담금을 최소로 지불하며, N개의 모든 섬을 연결할 수 있는 교통시스템 설계하라 소수 첫째자리에서 반올림하여 정수 형태로
 * @solving_description 섬의 개수 N 두번째 줄에는 섬의 x좌표들이 세번째 줄에는 섬의 y좌표가 주어지고 마지막으로 해저터널
 *                      건설의 환경 부담 세율 실수 E가 주어진다. 풀이 섬의 최대 개수는 1000개... 1000개를
 *                      DFS할 순 없을듯... 모든 섬을 연결하는 DFS돌리면서 순열로 해야할 듯? BFS는 간선의
 *                      가중치가 0이나 1일때 다 같을 때만 BFS를 사용할 수 있다. 싸이클이 발생하지 않고
 */

public class SWEA_1251 {
	private static int n;
	static long[][] islands;
	private static double E;
	private static long[][] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer,stringTokenizer2;
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(bufferedReader.readLine());
			
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			stringTokenizer2 = new StringTokenizer(bufferedReader.readLine());
			
			islands = new long[n][2];
			//섬의 좌표들을 저장한다.
			for(int i=0;i<n;i++) {
				islands[i] = new long[] {Long.parseLong(stringTokenizer.nextToken()),
						Long.parseLong(stringTokenizer2.nextToken())};
			}
			
			E = Double.parseDouble(bufferedReader.readLine());
			graph = new long[n][n];
			
			long [] from,to;
			
			//섬간의 거리를 구하기 위해 한섬(from)과 다른 섬(to)의 좌표를 저장한다.
			for(int r=0;r<n;r++) {
				from = islands[r]; 
				for(int c=r+1;c<n;c++) {
					to = islands[c];
					//거리의 제곱의 합을 저장한다.
					graph[c][r]=graph[r][c] = (from[0]-to[0])*(from[0]-to[0])+ (from[1]-to[1])*(from[1]-to[1]);
				}
			}
			//prim을 구한 후에 E를 구하고
			double cost = prim(0)*E;
			//cost를 첫째자리에서 반올림해준다.
			System.out.println("#"+tc+" "+Math.round(cost));
		}//tc
	}// main

	static long prim(int start) {
		//MST에 들어가지 않은 녀석들 중에서 가장 비용이 작은게 위로 올라오게 하기 위한
		//우선순위 큐
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		Edge[] dynamicGraph = new Edge[n]; //모든 정점들을 관리
		
		
		for(int i =0; i<dynamicGraph.length;i++) {
			//처음 시작할때 전부 코스트를 무한대로 처리해주고
			dynamicGraph[i] = new Edge(i, Long.MAX_VALUE);
			//첫 시작점만 코스트를 0으로 만들어 준다.
			if(i==start) {
				dynamicGraph[i].cost=0;
			}
			//그리고 나서 우선순위 큐에 넣어준다.
			pq.add(dynamicGraph[i]);
		}
		
		//간선 비용의 합을 저장하는 변수
		long cost=0;
		while(!pq.isEmpty()) {
			//현재에서 최소인 것을 뽑아준다.
			Edge front = pq.poll(); 
			cost+=front.cost; //더해준다.
			
			//그렇게 front를 연결시켜주고 front와 연결된 아직 MST에 추가되지 않은 정점의 가중치를 갱신해준다.
			for(int i=0;i<dynamicGraph.length;i++) {
				Edge child = dynamicGraph[i];
				//pq에 포함되어 있으면 비MST(아직 연결되지 않은 정점)
				if(pq.contains(child)) { //contains로 확인하고
					long tempCost= graph[front.idx][child.idx]; //코스트 계싼하고
					if(tempCost<child.cost) { //지금 저장되어 있는 것보다 작으면
						child.cost = tempCost; //갱신한후
						pq.remove(child); //삭제하고
						pq.offer(child); //다시 넣어준다.
					}
				}
			}
		}//while 
		
		return cost;
	}

	static class Edge implements Comparable<Edge>{
		int idx; long cost;
		
		@Override
		public String toString() {
			return "Edge [idx=" + idx + ", cost=" + cost + "]";
		}
		
		public Edge(int idx, long cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			//o.cost - Long.MIN_VALUE 빼는 과정에서 범위를 벗어날 수 있다.
			//return this.cost-o.cost>0? 1:-1;
			return Long.compare(this.cost,o.cost);
		}
	}
}
