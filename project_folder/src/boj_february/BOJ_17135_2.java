package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135_2 {
	static StringTokenizer stringTokenizer;
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };
	static int n, m, d, ans = 0;
	static boolean[] visit; // 궁수 중복없이 선택하기 위한 배열
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		stringTokenizer = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		d = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n + 1][m];
		visit = new boolean[m];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		recursion(0, 0);
		System.out.println(ans);

	}

	static void recursion(int idx, int k) {
		if (k == 3) {
			int[][] map_copy = new int[n + 1][m]; //
			for (int i = 0; i < n + 1; i++) {
				for (int j = 0; j < m; j++) {
					map_copy[i][j] = map[i][j];
				}
			}
			bfs(map_copy);
			return;
		}
		for (int i = idx; i < m; i++) {
			if (!visit[i]) {
				visit[i] = true;
				recursion(i, k + 1);
				visit[i] = false;
			}

		}
	}// recursion
	public static void bfs(int[][] map_copy) {
        int cnt = 0;
        for (int i = n; i > 0; i--) {   //궁수의 포지션을 한칸씩 앞으로 이동
            int archerIdx = 0;
            Queue<Pair> q = new LinkedList<>();
            //궁수를 표시하는 과정
            for (int j = 0; j < m; j++) {
                //map_copy에서 궁수 위치는 2
                if (visit[j]) { //궁수가 있으면
                    map_copy[i][j] = 2;
                    q.add(new Pair(i - 1, j, 1, archerIdx++));
                } else {    //궁수 위치가 아니면 0
                	map_copy[i][j] = 0;
                }
            }
 
            boolean[] isEnemyFind = new boolean[3];     //각 궁수가 몬스터를 찾았는지 찾았으면 스킵해주기 위해서
            boolean[][][] visited = new boolean[n][m][3];   //각 궁수가 해당 좌표를 방문했는지
            boolean[][] isAlreadyFindLocation = new boolean[n][m];  //이미 다른 궁수가 찾은 목표물인지
            ArrayList<Pair> alist = new ArrayList<>(); //잡은 적을 추가
 
            while (!q.isEmpty()) {
                Pair p = q.poll();
                if (isEnemyFind[p.idx]) continue; //해당 궁수가 이미 잡았으면 더이상 잡지 않고 계속 넘어간다.
                if (map_copy[p.x][p.y] == 1) {
                    isEnemyFind[p.idx] = true;
                    if (!isAlreadyFindLocation[p.x][p.y]) { //이미 잡은 곳이 아니면
                        isAlreadyFindLocation[p.x][p.y] = true; //true로 처리
                        alist.add(p); //넣는다.
                        cnt++; //카운트하고
                    }
                    continue; //한 궁수가 이미 잡았으므로 다음 궁수로
                }
                if (!isEnemyFind[p.idx]) { //찾지 못했으면
                	//해당 궁수가 방문했다고하고
                    visited[p.x][p.y][p.idx] = true;
                    //탐색한다. 그리고 거리도 +1처리해주고
                    for (int j = 0; j < 3; j++) {
                        int nx = p.x + dx[j];
                        int ny = p.y + dy[j];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny][p.idx] && p.depth < d) {
                            q.add(new Pair(nx, ny, p.depth + 1, p.idx));
                        }
                    }
                }
            }
            for (int j = 0; j < alist.size(); j++) {
            	//그리고 마지막에 0으로 만들어 준다.
                Pair p = alist.get(j);
                map_copy[p.x][p.y] = 0;
            }
        }
        ans = Math.max(ans, cnt);
    }//bfs
	
	public static class Pair {
		int x, y, depth, idx;

		public Pair(int x, int y, int depth, int idx) {
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.idx = idx;
		}
	}//Pair
}
