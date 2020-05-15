package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 13. 오후 12:14:41
 * @category 
* @level 3
* @problem_description 최대한 만족도 높은 여행 계획을 세우려고한다.
* 1. 첫날 공항에 도착한 후 부터 M일 후 다시 공항에 돌아올때까지 얻은 만족도와 경로를 구해야한다.
* 2. 방문할 수 있는 지점은 공항 호텔, 관광포인트를 모두 합해 N개지점
* 3. 각 관광포인트를 즐기기 위해 소요되는 시간과 얻을 수 있는 만족도가 정해져있으며, 한번 갔단 관광포인트 다시 방문x
* 4. 각 지점들은 서간에 모두 연결된 길이 있으며 이동에 소요되는 시간이 정해져있다. 다른 길로 우회 안함
* 5. 하루에 이동+놀이에 소요되는 시간이 9시간을 넘으면 안되고 그 전에 반드시 호텔에 입실해야한다. 호텔 중복가능, 다른호텔도 가능
* 6. M일째 날에도 9시간 사용할 수 있으며 그전에 도착해야한다.
* 
* 3<=N<=35 지점 개수
* 1<=M<=5 휴가기간
* 공항은 한 곳만 존재
* 각 지점간 이동 소요 시간, 노는 시간은 분단위로 주어지며 10<= <=240
* 각 관광 포인트에서 얻는 만족도 1<=<=10
* @solving_description 
*/

public class SWEA_1798_범준이의제주도여행계획 {
	private static int n;
	private static int m;
	//각 정점을 연결할 그래프
	static int[][] graph;
	//관리할 지점들
	static Point airport;;
	static List<Point> hotels;
	static List<Point> tourSpots;
	
	//최대 만족도
	static int maxSatis;
	static List<Integer> maxSatisPath;
	
	//탐색에 사용할 임시 경로
	static Stack<Integer> tempPath;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		bufferedReader = new BufferedReader(new StringReader(src));
		StringBuilder output = new StringBuilder();
		int T = Integer.parseInt(bufferedReader.readLine());
		StringTokenizer stringTokenizer =null;
		for(int tc=1;tc<=T;tc++) {
			//n, m 
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			m= Integer.parseInt(stringTokenizer.nextToken());
			
			graph = new int[n+1][n+1];
			for(int r = 1;r<n;r++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for(int c=r+1;c<graph.length;c++) {
					graph[r][c]= graph[c][r] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
//			for(int []row : graph) {
//				System.out.println(Arrays.toString(row));
//			}
			//2. 지점 정보 가져오기
			tourSpots = new ArrayList<>();
			hotels = new ArrayList<>();
			
			for(int i=0;i<n;i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				String type = stringTokenizer.nextToken();
				if(type.equals("H")) {
					hotels.add(new Point(type, i));
				}else if(type.equals("A")) {
					airport = new Point(type, i);
				}else {
					int playTime = Integer.parseInt(stringTokenizer.nextToken());
					int satis = Integer.parseInt(stringTokenizer.nextToken());
					tourSpots.add(new Point(type, i,playTime,satis));
				}
			}//for i
//			System.out.println(airport);
//			System.out.println(hotels);
//			System.out.println(tourSpots);
			
			//3. 관광지에 가장 가까운 호텔 정보 설정해주기 - graph
			for(Point p : tourSpots) {
				int min= Integer.MAX_VALUE;
				for(Point h: hotels) {
					if(graph[p.idx][h.idx]<min) {
						min = graph[p.idx][h.idx];
						p.nearH= h;
					}
				}
			}//for tourspots
//			for(Point p: tourSpots) {
//				System.out.println(p+": "+p.nearH);
//			}
			
			//4. 여행준비
			maxSatis = Integer.MIN_VALUE;
			tempPath = new Stack<>();
			
			solve(0,airport,0,0,new boolean[n+1]);
			
			//5. 결과 출력
			output.append("#").append(tc+" ");
			if(maxSatis==Integer.MAX_VALUE) {
				output.append(0);
			}else {
				output.append(maxSatis+" ");
				for(int i: maxSatisPath) {
					output.append(i+" ");
				}
			}
			output.append("\n");
			
		}
		System.out.println(output);
	}//main
	//day, 시작 포인트, 
	static void solve(int day, Point start, int satis, int time, boolean[] visited) {
		//종료조건?
		if(day==m) {
			if(satis > maxSatis) {
				maxSatis=satis;
				//최대 만족도에서의 경로 전달
				maxSatisPath = new ArrayList<Integer>(tempPath);
			}
			return;
		}
		//일반 탐색.
		boolean canGoNext = false;
		for(Point point: tourSpots) {
			//갈 수 있는 지점들을 찾아서 계속 돌아다닌다.
			if(!visited[point.idx]) {
				//미방문이라면.. 그 지점을 갈 수 있는지 시간을 계산해볼 필요가 있다.
				//기존시간에서 그 지점까지 가는데까지 가는 데 걸린 시간
				int tempTime = time+graph[start.idx][point.idx] +point.playTime;
				if(day==m-1) { //마지막날이라면
					tempTime+= graph[point.idx][airport.idx];
				}else {
					tempTime += graph[point.idx][point.nearH.idx];
				}
				if(tempTime>540) {
					continue;
				}
				canGoNext = true;
				visited[point.idx]=true;
				tempPath.push(point.idx);
				solve(day, point,satis+point.satis,time+graph[start.idx][point.idx] +point.playTime,visited);
				tempPath.pop();
				visited[point.idx]=false;
			}
		}
		//관광지로 못가면 날자에 따라서 공항/호텔로 이동
		if(!canGoNext) {
			if(day==m-1) { //마지막날이라면 공항으로
				tempPath.push(airport.idx);
				solve(day+1,airport,satis, 0,visited);
				tempPath.pop();
			}else { //호텔로
				for(Point hotel: hotels) {
					if(time+graph[start.idx][hotel.idx]<=540) {
						tempPath.push(hotel.idx);
						solve(day+1, hotel, satis, 0, visited);
						tempPath.pop();
					}
				}
			}
		}
	}
	static class Point{
		String type;
		int idx,playTime, satis;
		Point nearH;
		public Point(String type, int idx, int playTime, int satis) {
			super();
			this.type = type;
			this.idx = idx;
			this.playTime = playTime;
			this.satis = satis;
			
		}
		public Point(String type, int idx) {
			this(type,idx,0,0);
		}
		@Override
		public String toString() {
			return "Point [type=" + type + ", idx=" + idx + ", playTime=" + playTime + ", satis=" + satis + "]";
		}
		
		
	}
	static String src ="1\r\n" + 
			"10 3\r\n" + 
			"60 90 100 110 240 40 30 60 30\r\n" + 
			"60 120 140 200 120 100 60 60\r\n" + 
			"90 110 170 100 100 30 90\r\n" + 
			"50 110 40 80 90 90\r\n" + 
			"70 30 50 90 90\r\n" + 
			"100 140 180 120\r\n" + 
			"40 90 40\r\n" + 
			"100 20\r\n" + 
			"70\r\n" + 
			"A\r\n" + 
			"P 240 6\r\n" + 
			"P 120 4\r\n" + 
			"P 240 5\r\n" + 
			"P 60 4\r\n" + 
			"P 200 6\r\n" + 
			"P 180 1\r\n" + 
			"P 180 1\r\n" + 
			"H\r\n" + 
			"H";
}
