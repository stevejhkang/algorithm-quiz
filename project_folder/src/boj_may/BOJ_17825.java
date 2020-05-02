package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 2. 오후 9:31:20
 * @category 
* @level 5
* @problem_description 
* 10개의 결과를 이미 다 알고있고 말들을 무엇무엇 선택할지에 따라 점수가 달라지는데
* 얻을 수 있는 점수의 최댓값을 구해야 한다.
* 일단 범위가 10개를 4개에 배치하는 것이므로 조합이다. 이 조합으로 일단 짜고 배치를 해보자
* 
* 1. 도착칸으로 이동하면 주사위에 나온 수와 관계없이 이동을 마친다 -> 특정 인덱스 이상으로 가면 그냥 추가점수 없이 마감.
* 2. 해당 칸에 다른 말이 있으면 그말은 고를 수 없다. 해당 경우의 수는 fail.
* 	해당 칸에 다른 말이 있다는건 다른 말리스트 중 하나의 현재 위치가 도착지점이라는 뜻
* 말 옮길때마다 전체 리스트에서 해당 인덱스에 말이 있는지를 체크하면 될듯!
* 3. 해당 말들을 한번씩 움직일 때마다 해당 인덱스에 있는 점수를 획득하게 되고 
* 최종 점수는 그것을 합산하면 된다. 그것의 값을 현재 계산된 최댓값과 비교하면 될듯!
* 
* 
* @solving_description 
* 1. 일단 중복조합으로 하면 4^10이 나오는데 이는 1048576이라서 충분히 완전탐색으로 할 수 있다. 
* 
* 2. 그리고 겹치는 부분을 없애기 위해서 일일이 특정 인덱스를 벗어나면 해당 인덱스로 이동을 시켜주어 중복된 인덱스(위치)를
* 확인할 수 있도록 만들었다.
* 
* 3. 그리고 다른 말이 이미 도착지점에 있으면 해당 조합은 틀린 조합이므로 그냥 리턴시킨다.
* 이것을 확인하기 위해서 현재 말들의 인덱스 정보를 저장하는 배열을 일일이 확인했다. 
* 
* 4. 그리고 실수를 할 수 있는 부분은 게임보드의 점수가 제대로 되었는지를 확인했어야 했다.
* 5. 그리고 해당 인덱스가 되면 제대로 그 지점으로 이동하는지 등도 실수하기 쉬웠다. 그래서 모든 분기 부분을 체크해야 됐다.
* 
* 
* 
*/

public class BOJ_17825 {
	private static int[] map;
	private static List[] mal;
	private static int[] order;
//	private static int[] order= {0,0,0,1,1,2,2,1,2,2};

	private static int[] now_index;
	private static int max_point;
	private static int[] game_board={0,2,4,6,8,10,12,14,16,18,20,22,24,26,
			28,30,32,34,36,38,10,13,16,19,20,22,24,30,28,27,26,25,30,35,40,0};

	public static void main(String[] args) throws IOException {
//		System.out.println(game_board[31]);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		map = new int[10];
		for(int i=0;i<10;i++) {
			map[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		mal = new List[5];
		for(int i=1;i<=4;i++) {
			mal[i] = new ArrayList<Integer>();
		}
		 
		order = new int[10];
		now_index = new int[4];
		max_point = Integer.MIN_VALUE;
		dfs(0);
//		order = {1,2,2,1,1,1,1,1,2,1};
//		game();
		System.out.println(max_point);
	}//main
	
	static void dfs(int r) {
		if(r==10) {
			//이때 게임을 시작한다.
//			System.out.println("--------------------");
//			for(int i=1;i<=4;i++) {
//				
//				System.out.println(mal[i].toString());
//			}
//			for(int i=0;i<10;i++) {
//				System.out.print(order[i]+", ");
//			}
//			System.out.println("-----------");
			game();
			return;
		}
		for(int i=0;i<4;i++) {
//			mal[i].add(map[r]);
			order[r]=i;
			//map[r]
			dfs(r+1);
//			mal[i].remove(mal[i].size()-1);
		
		}
	}//dfs
	static void game() {
		now_index= new int[4];
		int thisGamePoint = 0;
		for(int turn=0;turn<10;turn++) {//순서
			int player_num = order[turn]; //말번호
			int player_preIndex = now_index[player_num]; //현재 위치 받아온다
			int player_nowIndex=player_preIndex;
			//현재 위치가 0~19이하이면 정석 도착인덱스가 5->20,10->24,15->27일때마다 인덱스를 점프시켜주어야 한다.
			if(player_preIndex>=0&&player_preIndex<=19) {
				player_nowIndex+=map[turn]; //다음위치 현재 위치에서 해당 순서의 주사위 결과를 더한다.
				if(player_nowIndex==5) {
					player_nowIndex=20;
				}
				else if(player_nowIndex==10) {
					player_nowIndex=24;
				}
				else if(player_nowIndex==15) {
					player_nowIndex=27;
				}else if(player_nowIndex>=20) {
					int remain = player_nowIndex-20;
					player_nowIndex=34+remain;
				}
			}
			//20~22사이이면 서경로 24이상되면 31로 이동시켜야 한다.
			else if(player_preIndex>=20&&player_preIndex<=23) {
				player_nowIndex+=map[turn]; //다음위치 현재 위치에서 해당 순서의 주사위 결과를 더한다.
				//24이면 31 25이면 32
				
				if(player_nowIndex>=24) {
					int remain = player_nowIndex-24;
					player_nowIndex=31+remain;
				}
			}
			//23~24사이이면 남경로 //27이상되면 31로 점프시켜서 이동시켜야 한다.
			else if(player_preIndex>=24 && player_preIndex<=26) {
				player_nowIndex+=map[turn]; //다음위치 현재 위치에서 해당 순서의 주사위 결과를 더한다.
				if(player_nowIndex>=27) {
					int remain = player_nowIndex-27;
					player_nowIndex=31+remain;
				}
			}
			//25~27사이이면 동경로 여기도 더이상 경로가 변경되지 않음 아래와 합치자
//			else if(player_nowIndex>=25&&player_nowIndex<=27) {
//				
//			}
			//28~31이면 북경로 //여기는 더이상 경로 변환이 없음
			else if(player_preIndex>=27&&player_preIndex<=33) {
				player_nowIndex+=map[turn]; //다음위치 현재 위치에서 해당 순서의 주사위 결과를 더한다.
			}
			//마지막은 34 +1해도 점수 못 얻으므로 인덱스만 35으로 바꾸고 끝낸다.
			else if(player_preIndex==34) {
				now_index[player_num]=35;
				continue;
			}
			//35은 이미 도착이므로 턴 넘긴다.
			else if(player_preIndex==35) {
				continue;
			}
			
			//범위를 벗어나는 지 확인한다. 벗어나면 35로 박는다
			if(player_nowIndex>=36) {
				player_nowIndex=35;
			}
			
			//도착 지점에 다른 말이 있는지 확인한다. 있으면 이 게임은 그냥 무효
			for(int i=0;i<4;i++) {
				if(player_num==i) continue; //현재 플레이어와 같으면 패스한다.
				if(player_nowIndex!=35&&player_nowIndex==now_index[i]) { //말이 이동을 마치는 칸에 다른 말이 있으면 
					return;
				}
				
			}
			//겹치지 않으면 점수를 추가하고 말 인덱스를 옮긴다. 어짜피 35번째 0이므로 더해도 상관없음.
//			System.out.println(turn+"-("+player_num+"): "+player_nowIndex+"-point="+game_board[player_nowIndex]);
			thisGamePoint+=game_board[player_nowIndex];
			now_index[player_num]=player_nowIndex;
//			order = {1,2,2,1,1,1,1,1,2,1};
		}
		
		max_point=Math.max(thisGamePoint,max_point);
	}
}
