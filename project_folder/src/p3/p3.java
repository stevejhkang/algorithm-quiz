package p3;

import java.util.Arrays;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 23. 오후 2:31:19
 * @category \
* @level 3
* @problem_description 
* 		//1,2,3,4모양
		//가로 또는 세로로 같은 모양의 사탕을 3개이상 연결하면 깨진다.
		//깨진개수만큼 점수를 얻을 수 있다.
		//임의의 사탕 한개를 깨뜨려 사탕을 깨트린다.
		//단 한번만 사용가능
		//깨지면 빈공간이 생겨 위 사탕이 빈공간을 채우게 된다.
		//사탕이 내려운 후 가로 혹은 세로 방향으로 같은 모양이
		//3개 이상 연결되면 해당 사탕이 모두 깨집니다.
		//3애 이상이면 한꺼번에 깨진다.
		//사탕이 모두 깨지면 깨진 자리는 빈공간이 되므로
		//한칸식 내려옵니다.
		//내려온 후 3개이상이면 또 깨진다
		//이런게 끝날때 까지 반복
		
		//세로 가로 길이는 3이상 10이하
		//처음부터 같은 경우는 없다.
* 오른쪽 아래를 탐색하면서 연속으로 3개가 되는것을 탐색해야한다.
* 3개이상이 있으면 해당 점들을 visit처리해주고
* 모든 점에서 visit처리가 끝냈을 때 해당 점들을 하나씩 터뜨려주어야 한다.
* 모든 점에서 터뜨려야 되므로 배열을 복사가 필요하다.
* 

* @solving_description 
*/
public class p3 {
	public static void main(String[] args) {
		int[][] board  = {{1,1,3,3},{4,1,3,4},{1,2,1,1},{2,1,3,2}};
		
		System.out.println(new Solution().solution(board));
	}
}
class Solution {

	static private boolean visit[][];
	static private int[][] copy_boards;
	static int remove_cnt;
	public int solution(int[][] board) {
		int n = board.length;
		int answer = 0;
		for(int o=1;o<=n;o++) {
			for(int p=1;p<=n;p++) {
				copy_boards = new int[n+1][n+1];
				
				remove_cnt=0;
				for(int i=1;i<=n;i++) {
					 for(int j=1;j<=n;j++) {
						 copy_boards[i][j] = board[i-1][j-1];
					 }
				}
				remove_block(o, p,true);
//				visit[3][3] = true;
//				remove_block(3, 3);
				
//				for(int i=0;i<=n;i++) {
//					System.out.println(Arrays.toString(copy_boards[i]));
//				}
				
				//하나를 부수고 이제 while문을 돌면서 계속 깨지는지 확인한다.
				while(true) {
					visit = new boolean[n+1][n+1];
					boolean is_change=false;
					for(int i=1;i<=n;i++) {
						for(int j=1;j<=n;j++) {
							
							int num = copy_boards[i][j];
							//빈칸이 아니면
							if(num==0) continue;
							
							//오른쪽
							int idx = j;
							int cnt=0;
							while(idx<=n) {
								if(copy_boards[i][idx]==num) {//같은 경우를 세준다.
									cnt++;
								}
								else {
									//다르면 break;
									break;
								}
								idx++;
							}
							if(cnt>=3) { //3이상이면 visit처리 해준다.
								is_change=true;
								int temp_idx= 0;
								while(temp_idx<cnt) {
									visit[i][j+temp_idx]=true;
									temp_idx++;
								}
							}
							
							
							//아래쪽
							idx = i;
							cnt=0;
							while(idx<=n) {
								if(copy_boards[idx][j]==num) {//같은 경우를 세준다.
									cnt++;
								}
								else {
									//다르면 break;
									break;
								}
								idx++;
							}
							if(cnt>=3) { //3이상이면 visit처리 해준다.
								is_change=true;
								int temp_idx= 0;
								while(temp_idx<cnt) {
									visit[i+temp_idx][j]=true;
									temp_idx++;
								}
							}
						}
					}
					if(!is_change) {
						break;
					}
					for(int i=1;i<=n;i++) {
						for(int j=1;j<=n;j++) {
							if(visit[i][j]) {
								remove_block(i,j,false);
							}
						}
					}
				}
				if(answer<remove_cnt) {
					answer=remove_cnt;
				}
			}//for p
		}//for o
		
		
//		System.out.println(remove_cnt);
		return answer;
	}//solution
	static void remove_block(int i,int j, boolean init) {
		if(init||visit[i][j]) { //col별로 row 1씩 내려줘야한다.
			remove_cnt++;
			for(int k = i;k>=1;k--) {
				copy_boards[k][j] = copy_boards[k-1][j];
			}
		}
	}
}