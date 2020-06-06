package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 9. 오전 1:22:46
 * @category 
* @problem_description 모든 층에서 양쪽 모두 창문을 열었을 때 모두 거리 2이상의 공간이 확보될때 조망권이 확보된다.
* 조망권이 확보된 세대의 수를 반환하는 프로그램 작성.
* 가로길이 1000, 최대 높이 255  255000 20*4 80만이니까 가능 262MB 1024.1키로바이트 = 1mb
* @solving_description 
* 터질 것 같지만 계산해보면 1mb밖에 안쓴다. 완전탐색 가능
*/

public class SWEA_1206_View {
	private static int[][] map;
	private static int[] height;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder=  new StringBuilder();
		for(int tc =1;tc<=10;tc++) {
			int len  = Integer.parseInt(bufferedReader.readLine());
			
			map = new int[256][len];
			height = new int[len];
			String str;
			
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			for(int i=0;i<len;i++) {
				height[i] = Integer.parseInt(stringTokenizer.nextToken());
				for(int j=1;j<=height[i];j++) {
					map[j][i]=1;
				}
			} //입력
			
			cnt =0;
			for(int i=0;i<len;i++) {
				for(int j=1;j<=height[i];j++) {
					//1일때
					if(map[j][i]==1) {
						//좌 우가 둘다 0이어야 한다.
						//좌
						int y = j; int x = i;
						int move=1;
						boolean cansee = true;
						
						while(move<=2) {
							int leftX=x-move; int rightX = x+move;
							if(map[y][leftX]==1) {
								cansee=false;
								break;
							}
							if(map[y][rightX]==1) {
								cansee=false;
								break;
							}
							move++;
						}//while
						if(cansee) { //조망 확보되면 카운트 해준다.
							cnt++;
						}
					}//map[j][i]==1
				}
			}//for i len
			stringBuilder.append("#"+tc+" "+cnt+"\n");
		}
		System.out.println(stringBuilder);
	}
}

