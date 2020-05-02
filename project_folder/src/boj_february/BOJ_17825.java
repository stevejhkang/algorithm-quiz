package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 16. 오후 6:22:00
 * @category 중복조합+지저분한 시뮬레이션
* @problem_description 말 4개로 윷놀이판을 겹치지 않게 10개의 주사위 결과를 중복조합하여 
* 점수를 최대로 출력하는 문제. 핵심은 다른 말이 놓인 칸엔 이동할 수 없다는 점
* @solving_description 중복조합 즉, 4개의 말에게 중복을 허락하여 주사위 결과를 할당하고
* 순서에 맞춰서 말을 옮기면서 다른 말이 놓였을 때 그냥 그 케이스를 넘기고 다음 케이스로 다시 확인하는
* 방식으로 구현. 중복되는 부분을 공통된 배열 번호로 할당시켜서 나중에 같은지 비교를 시켜준다.
* 40, 25 30 35 40
*/
public class BOJ_17825 {

	private static int[] input;
	private static List[] order;
	private static int[][] point;
	private static int max;
	private static int[] dice;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer= new StringTokenizer(bufferedReader.readLine());
		
		input= new int[10];
		for(int i=0;i<10;i++) {
			input[i]=Integer.parseInt(stringTokenizer.nextToken());
		}
		order = new List[4];
		for(int i=0;i<4;i++) {
			order[i]= new ArrayList<Integer>();
		}
		point=new int[4][51];
		for(int i=1;i<21;i++) {
			point[0][i]=point[0][i-1]+2;
		}
//		for(int i=0;i<21;i++) {
//			System.out.print(point[0][i]+" ");
//		}
	
		//21끝
		
		point[1][0]=10;
		point[1][1]=13;
		point[1][2]=16;
		point[1][3]=19;
		//13끝
		
		point[2][0] =30;
		point[2][1] =28;
		point[2][2] =27;
		point[2][3] =26;
		
		point[3][0]=20;
		point[3][1]=22;
		point[3][2]=24;
		point[3][3]=25;
		point[3][4]=30;
		point[3][5]=35;
		point[3][6]=40;
		//23끝
		
//		System.out.println("");
//		for(int i=5;i<30;i++) {
//			System.out.print(point[1][i]+" ");
//		}
//		System.out.println("");
//		for(int i=10;i<30;i++) {
//			System.out.print(point[2][i]+" ");
//		}
//		System.out.println("");
//		for(int i=15;i<30;i++) {
//			System.out.print(point[3][i]+" ");
//		}
		max=Integer.MIN_VALUE;
		dice = new int[10];
		recursion(0);
		System.out.println(max);
		
	}
	//0~9까지의 주사위 결과들을 0~3 말들에게 배포한다.
	static void recursion(int r) {
		if(r==10) {
			//0번부터 점수 체크를 시작한다.
			int point = calcPoint();
			
			if(max<point) {
				max=point;
//				System.out.println("");
//				System.out.println(max);
//				for(int i=0;i<10;i++) {
//					System.out.print(dice[i]+" ");
//				}
//				System.out.println("");
//				for(int i=0;i<4;i++) {
//					System.out.println(order[i].toString());
//				}
			}
			return;
		}
		for(int i=0;i<4;i++) {
			order[i].add(input[r]);
			dice[r]=i;
			recursion(r+1);
			//맨 뒤에 것을 삭제한다.
			order[i].remove(order[i].size()-1);
		}
	}//recursion
	
	static int calcPoint() {
		int result=0;
		int next =0;
		int move[] = new int[4]; //현재위치
		int r[] = new int[4]; //현재 로우
		int index[] = new int[4]; //현재 주사위 순서
		//dice에 나온 순서대로 주사위를 던져야된다.
		for(int i=0;i<10;i++) {
			next= dice[i]; //이번 움직일 말 숫자.
			
			move[next]+=  ((Integer) order[next].get(index[next])).intValue();//다음 이동
			
			if(r[next]==0) {
				//5일때 r을 1로
				if(move[next]==5) {
					r[next]=1;
					move[next]=0;
				}
				else if(move[next]==10) {
					r[next]=3;
					move[next]=0;
				}
				else if(move[next]==15) {
					r[next]=2;
					move[next]=0;
				}
				//40에 도착하면 40인 3,6으로 이동시켜준다. 통일
				else if (move[next]==20) {
					r[next]=3;
					move[next]=6;
				}
				//도착점 이상으로 가면
				else if(move[next]>20) {
					r[next]=3;
					move[next]=7;
				}
			}
			else if(r[next]==1) {
				if(move[next]>3) {
					r[next]=3;
					move[next]--;
					if(move[next]>6) {
						move[next]=7;
					}
				}
			}
			else if(r[next]==2) {
				if(move[next]>3) {
					r[next]=3;
					move[next]--;
					if(move[next]>6) {
						move[next]=7;
					}
				}
			}
			else if(r[next]==3) {
				if(move[next]>6) {
					move[next]=7;
				}
			}
			
			for(int j=0;j<4;j++) {
				if(j==next) continue; //현재랑 같으면
				if(r[j]==3&&move[j]==7) continue; //도착점이면
				if(r[j]==0&&move[j]==0) continue; //시작점이면
				
				if(r[next]==r[j]&&move[next]==move[j]) {
					return -1;
				}
			}
			result+=point[r[next]][move[next]];
			//이동시킨다.
			index[next]++;
		}
//		if(result==161) {
//			for(int i=0;i<4;i++) {
//				for(int j=0;j<order[i].size();j++) {
//					System.out.print(order[i].get(j)+" ");
//				}
//				System.out.println();
//			}
//		}
		return result;
	}
}
