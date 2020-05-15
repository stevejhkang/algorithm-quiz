package hyundai_IT_and_E;

import java.util.Arrays;

public class p2 {
	public static void main(String[] args) {
		int N = 3;
//		int[][] bus_stop = {{1,2}};
		int[][] bus_stop= {{1,2},{3,3}};
		int[][] ans = new Solution().solution(N, bus_stop);
		
		for(int i=0;i<ans.length;i++) {
			System.out.println(Arrays.toString(ans[i]));
		}
	}
	static class Solution{
		public int[][] solution(int N, int[][] bus_stop){
			int[][] answer = new int[N][N];
			
			int stop_len = bus_stop.length;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					int dist= Integer.MAX_VALUE;
					for(int k=0;k<stop_len;k++) { //모든 정류장과의 거리를 측정하고 최솟값 갱신
						int y_index = bus_stop[k][0]-1;
						int x_index= bus_stop[k][1]-1;
						
						int temp_dist = Math.abs(y_index-i)+Math.abs(x_index-j);
						
						dist = Math.min(dist, temp_dist);
						if(dist==0) {
							break;
						}
					}
					answer[i][j] = dist;
				}
			}
			
			return answer;
		}
		static class dot{
			int y,x;

			public dot(int y, int x) {
				super();
				this.y = y;
				this.x = x;
			}
			
		}
	}
}
