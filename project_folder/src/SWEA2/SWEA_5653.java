package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 29. 오전 9:52:27
 * @category 교수님 풀이
 * @problem_description 생명력 X인 줄기세포, 처음은 비활성 X시간이 지나는 순간 활성상태됨. 활성 상태가
 *           되면 X시간 동안 살아 있을 수 있으며 X시간 지나면 죽는다. 죽은 상태로 셀 차지 활성화 된 줄기 세포 첫 1시간동안
 *           상하좌우 번식을 한다. 번식된 줄기세포는 비활성 상태이다. 이미 있는 경우 번식하지 않는다. 두개가 동시에 번식하려는
 *           경우 생명력수치가 높은 줄기 세포가 차지한다.
 * 
 * @solving_description 0은 아직 퍼지지 않은거, -1은 이미 활성화 된 후 죽은 경우 전체 그리드에서 1~10인 수를
 *                      구하면 된다.
 *                      
 *  ** 내가 어려워 했던 것이 생명력 순으로 넣어주기 위해서 어떻게 처리해야될까 문제 
 *  -> 처음 넣을때 생명력 별로 리스트를 만들어서 넣고 큐에 생명력 높은 것부터 넣는다.
 *  ? 그러면 다음 큐에서 먼저 들어갈 일이 없나?
 *  
 *  ** 활성화 대기시간을 +1해줘서 생명력과 똑같을 때 퍼뜨려야 하나? 아니면 다음 턴에서 퍼뜨려야 하나.
 */
public class SWEA_5653 {

	   static int[] dx = {-1,1,0,0};
	   static int[] dy = {0,0,-1,1};
	   static int[][] board;
	   static ArrayList<ArrayList<Cell>> list;
	   static int N,M,K;
	   static int size = 400;
	   
	   static int check(int[][] board) { //살아있는 세포의 개수를 체크하는 함수
	      int cnt=0;
	      for(int i=0;i<N+K;i++) {
	         for(int j=0;j<M+K;j++) {
	         if(board[i][j] !=0 && board[i][j] !=-1) //2,3만 체크한다.
	            cnt++;
	         }
	      }
	      return cnt;
	   }//check
	   
	   static void solve() {
		   
	      Queue<Cell> q= new LinkedList<>();
	      
	      for(int x =9; x>=0;x--) { //생명력이 높은게 먼저 들어가야 같은 시간에 들어간 낮은 생명력과 비교를 할 필요가 없다.
	         for(int s=0;s<list.get(x).size();s++) {
	            q.offer(list.get(x).get(s)); //해당 세포를 큐에 넣는다.
	         }
	      }
	      
	      while(!q.isEmpty()) {
	         Cell tmp = q.poll();
	         
	         if(tmp.life == 0 && tmp.status) { //활성화된 것이고 살아있는 시간이 0이되면 
	            board[tmp.i][tmp.j] = -1; //죽음 처리한다.
	            continue;
	         }
	         
	         if(tmp.time ==0) continue; //시간이 0이되면 더이상 진행하면 안되므로
	         
	         if(tmp.life !=0) { //아직 활성화 되기 전까지 시간이 남아있거나 죽기까지 시간이 남으면 시간-1 대기시간 및 살아있는 시간-1 해준다.
	            q.offer(new Cell(tmp.i, tmp.j, tmp.x, tmp.life-1, tmp.time-1, tmp.status));
	            continue;
	         }
	         //이 아래는 tmp.life==0이므로 해당 활성화 시킨다. 
	         //그리고 살아있는 시간을 다시 생명력 수치만큼 부여하고 큐에 넣는다. 그리고 1시간을 바로 소비하므로
	         // tmp.x-1과 시간-1를 해준다.
	         q.offer(new Cell(tmp.i, tmp.j, tmp.x, tmp.x-1, tmp.time-1, true)); 
	         
	         //활성화되고 첫 1시간 동안 번식을 하므로 동시에 번식까지 처리를 한다.
	         for(int dir=0;dir<4;dir++) {
	            int nx = tmp.i + dx[dir];
	            int ny = tmp.j + dy[dir];
	            if(board[nx][ny] ==0) { //방문한적이 없으면
	            board[nx][ny] = tmp.x; //생명력 넣어준다.
	            q.offer(new Cell(nx,ny,tmp.x,tmp.x,tmp.time-1,false)); //시간도 -1 해준다.
	            }
	         }
	      }
	      
	   }//solve

	   static class Cell{
	      int i;
	      int j;
	      int x; //생명력
	      int life; //대기시간 -> 살아있는 시간: 이 두값은 공존할 수 없을므로 같이 사용한다.
	      int time; //현재 시간
	      boolean status; //활성화 여부(false: 비활성화, true: 활성화)
	      
	      public Cell(int i, int j, int x, int life, int time, boolean status) {
	         super();
	         this.i = i;
	         this.j = j;
	         this.x = x; //생명력
	         this.life = life; //대기시간
	         this.time = time; //배양시간
	         this.status = status; //활성화 여부
	      }
	      
	   }
	   public static void main(String[] args) throws IOException {

	      
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringTokenizer st = new StringTokenizer(br.readLine());
	      StringBuilder sb = new StringBuilder();
	      
	      int tc =Integer.parseInt(st.nextToken());
	      
	      for(int t=1;t<=tc;t++) {
	         sb.append("#" + t + " ");
	         st = new StringTokenizer(br.readLine());
	         N = Integer.parseInt(st.nextToken()); //세로
	         M = Integer.parseInt(st.nextToken()); //가로
	         K = Integer.parseInt(st.nextToken()); //시간
	         
	         //생명력이 최소 1인 것이 제일 빨리 퍼져나가는데 그 제일 빨리 퍼져나가는 속도가 2시간당 1셀이므로 
	         //양옆으로 K/2이므로 K만 증가시킨다. 
	         board = new int[N+K][M+K]; 
	         
	         //생명력(1~10)별로 저장을 한다.
	         list = new ArrayList<ArrayList<Cell>>(); 
	         for(int i=0;i<10;i++)
	            list.add(new ArrayList<>());
	   
	         for(int i=(K/2);i<N +(K/2);i++) {
	            st = new StringTokenizer(br.readLine());
	            for(int j=(K/2);j<M+(K/2) ;j++) {
	               int tmp = Integer.parseInt(st.nextToken());
	               board[i][j] = tmp;
	               if(board[i][j] !=0) {
	                  int idx = board[i][j] -1; //생명력이 8이면 리스트에는 인덱스가 7인 곳에 들어간다.
	                  list.get(idx).add(new Cell(i,j,board[i][j],board[i][j],K,false));
	               }
	            }
	         }
	         
	         solve();
	         
	         int ans = check(board); //k시간 후에 살아있는 세포의 개수를 구한다.
	         
	         sb.append(ans).append("\n");
	         
	      }
	      
	      System.out.println(sb);
	      
	   }
	}