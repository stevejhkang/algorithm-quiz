package p2;

public class p2 {
	public static void main(String[] args) {
		int x=-1;
		int y = 2;
		int r = 2;
		int d=60;
		int[][] target= {{0,1},{-1,1},{1,0},{-2,2}};
		
		
		
		System.out.println(new Solution().solution(x, y, r, d, target));
	}
}
class Solution{
	int solution(int x, int y, int r, int d, int[][] target) {
		int answer = 0;
		
		
		for(int i=0;i<target.length;i++) {
			//원점과의 거리가 r이랑 비교
			int tx = target[i][0];
			int ty= target[i][1];
			int xsquare = (x-tx)*(x-tx);
			int ysquare= (y-ty)*(y-ty);
			if(xsquare+ysquare<=r*r) {//거리가 작을때만 각도 계산한다
				double angle = calcAngle(x,y,tx,ty);
				if(angle<=d) {
					answer++;
				}
			}
		}
		
		
		return answer;
	}
	static double calcAngle(int x1, int y1, int x2, int y2) {
		if(x2>x1) {
			return (Math.atan2((x2-x1),(y1-y2))*180/Math.PI);
		}
		else if(x2<x1) {
			return 360-(Math.atan2((x1-x2),(y1-y2))*180/Math.PI);
		}
		return Math.atan2(0, 0);
	}
}