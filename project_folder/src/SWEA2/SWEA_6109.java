package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 29. 오후 4:11:01
 * @category 
* @problem_description 
* 1. 한 방향을 정해서 두 타일에 적힌 숫자가 같다면 두 타일은 합쳐져 새로운 하나의 타일이 되고 그 타일의 숫자는 합쳐진 숫자들의 합이 된다.
* 2. 한 타임에 합쳐진 타일은 숫자가 같은 타일이 밀려와도 합쳐져서는 안된다.
* 3. 만약 같은 숫자가 적힌 타일이 세개 이상 있으면 벽쪽에 가까운 타일들이 합쳐진다.
* 3의 예시: 224222 왼쪽으로 밀면 444200
* 타일이 주어지고 방향이 주어질 때 타일을 모두 이동시키고 나면 격자가 어떻게 변할 지 계산하는 프로그램
* @solving_description 
*/

public class SWEA_6109 {
	private static int n;
	private static String direction;
	private static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException  {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		for(int tc =1;tc<=T;tc++) {
			StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			direction = stringTokenizer.nextToken();
			map = new int[n+2][n+2]; //1,1~n,n까진데 0,0 n+1,n+1은 0 삽입용
			
			for(int i=1;i<=n;i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for(int j=1;j<=n;j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			
			if(direction.equals("up")) { //i==0
				for(int j=1;j<=n;j++) {
					int i=1;
					while(i<n) { //i==n일땐 합쳐질 수 없으므로 n-1까지만
						if(map[i][j]==0) { //0인경우
							int idx = i;
							boolean can=false;
							for(;idx<=n;idx++) {
								if(map[idx][j]!=0) {
									can = true;
								}
							}
							if(can) {
								move(direction, i, j);
							}
							else {
								i++;
							}
							
						}
						else if(map[i+1][j]==0) { //다음이 i+1이라면 
							//i+1부터 n까지 0이 아닌 수가 있는지 확인한다
							//있으면 해주고 없으면 옮기지말고 i++해주고 끝낸다.
							int idx = i+1;
							boolean can=false;
							for(;idx<=n;idx++) {
								if(map[idx][j]!=0) {
									can = true;
								}
							}
							if(can) {
								move(direction, i+1, j);
							}
							else {
								i++;
							}
						}
						else if(map[i][j]!=0&&map[i][j]==map[i+1][j]) { //같은 경우
							map[i][j]+=map[i][j];
							//i+1부터 위로 쭉 땡긴다.
							move(direction, i+1, j);
							//합쳐졌으므로 아랫것을 확인한다.
							i++;
						}
						else {
							i++;
						}
					}//while
				}
			}
			else if(direction.equals("down")) { //i==n
				for(int j=1;j<=n;j++) {
					int i=n;
					while(i>1) { //i==1일땐 합쳐질 수 없으므로 1까지만
						if(map[i][j]==0) { //0인경우
							int idx = i;
							boolean can=false;
							for(;idx>=1;idx--) {
								if(map[idx][j]!=0) {
									can = true;
								}
							}
							if(can) {
								move(direction, i, j);
							}
							else {
								i--;
							}
							
						}
						else if(map[i-1][j]==0) { //다음이 i+1이라면 
							//i+1부터 n까지 0이 아닌 수가 있는지 확인한다
							//있으면 해주고 없으면 옮기지말고 i++해주고 끝낸다.
							int idx = i-1;
							boolean can=false;
							for(;idx>=1;idx--) {
								if(map[idx][j]!=0) {
									can = true;
								}
							}
							if(can) {
								move(direction, i-1, j);
							}
							else {
								i--;
							}
						}
						else if(map[i][j]!=0&&map[i][j]==map[i-1][j]) { //같은 경우
							map[i][j]+=map[i][j];
							//i+1부터 위로 쭉 땡긴다.
							move(direction, i-1, j);
							//합쳐졌으므로 아랫것을 확인한다.
							i--;
						}
						else {
							i--;
						}
					}
				}
			}
			else if(direction.equals("left")) { //i==0
				for(int i=1;i<=n;i++) {
					int j=1;
					while(j<n) { //i==n일땐 합쳐질 수 없으므로 n-1까지만
						if(map[i][j]==0) { //0인경우
							int idx = j;
							boolean can=false;
							for(;idx<=n;idx++) {
								if(map[i][idx]!=0) {
									can = true;
								}
							}
							if(can) {
								move(direction, i, j);
							}
							else {
								j++;
							}
							
						}
						else if(map[i][j+1]==0) { //다음이 i+1이라면 
							//i+1부터 n까지 0이 아닌 수가 있는지 확인한다
							//있으면 해주고 없으면 옮기지말고 i++해주고 끝낸다.
							int idx = j+1;
							boolean can=false;
							for(;idx<=n;idx++) {
								if(map[i][idx]!=0) {
									can = true;
								}
							}
							if(can) {
								move(direction, i, j+1);
							}
							else {
								j++;
							}
						}
						else if(map[i][j]!=0&&map[i][j]==map[i][j+1]) { //같은 경우
							map[i][j]+=map[i][j];
							//i+1부터 위로 쭉 땡긴다.
							move(direction, i, j+1);
							//합쳐졌으므로 아랫것을 확인한다.
							j++;
						}
						else {
							j++;
						}
					}//while
				}
			}
			else if(direction.equals("right")) { //i==n
				for(int i=1;i<=n;i++) {
					int j=n;
					while(j>1) { //i==1일땐 합쳐질 수 없으므로 1까지만
						if(map[i][j]==0) { //0인경우
							int idx = j;
							boolean can=false;
							for(;idx>=1;idx--) {
								if(map[i][idx]!=0) {
									can = true;
								}
							}
							if(can) {
								move(direction, i, j);
							}
							else {
								j--;
							}
							
						}
						else if(map[i][j-1]==0) { //다음이 i+1이라면 
							//i+1부터 n까지 0이 아닌 수가 있는지 확인한다
							//있으면 해주고 없으면 옮기지말고 i++해주고 끝낸다.
							int idx = j-1;
							boolean can=false;
							for(;idx>=1;idx--) {
								if(map[i][idx]!=0) {
									can = true;
								}
							}
							if(can) {
								move(direction, i, j-1);
							}
							else {
								j--;
							}
						}
						else if(map[i][j]!=0&&map[i][j]==map[i][j-1]) { //같은 경우
							map[i][j]+=map[i][j];
							//i+1부터 위로 쭉 땡긴다.
							move(direction, i, j-1);
							//합쳐졌으므로 아랫것을 확인한다.
							j--;
						}
						else {
							j--;
						}
					}
				}
			}
			System.out.println("#"+tc);
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
		}//for tc
	}//main
	//쭉 땡기는 처리
	static void move(String direction,int i, int j) {
		if(direction.equals("up")) {
			//i부터 n까지 쭉 올린다.
			for(;i<=n;i++) {
				map[i][j] = map[i+1][j];
			}
		}
		else if(direction.equals("down")) {
			//i부터 1까지 쭉 내린다.
			for(; i>=1;i--) {
				map[i][j] = map[i-1][j];
			}
		}
		else if(direction.equals("left")) {
			//j부터 n까지 쭉 왼쪽으로 옮긴다.
			for(;j<=n;j++) {
				map[i][j]= map[i][j+1];
			}
		}
		else {
			//j부터 1까지 쭉 오른쪽으로 이동시킨다.
			for(; j>=1;j--) {
				map[i][j]= map[i][j-1];
			}
		}
	}
}
