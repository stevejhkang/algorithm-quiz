package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 4. 오전 10:19:22
 * @category 
* @problem_description 
* 1. 이 성에 있는 방의 개수(덩어리 개수)
* 2. 가장 넒은 방의 넓이 - 크기 이동횟수
* 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
* -> 만나는 벽을 다 부수고 dfs해본다 혹은 BFS로 벽을 부순것과 안 부순걸로
* 그러면 동서남북 부순거랑 안부순거 총 5가지의 상태가 있어야 한다.
* 그렇게 해서 최댓값 찾기
* @solving_description  
*/

 
public class BOJ_2234 {
 
    public static int[] dirX = { 0, -1, 0, 1 };
    public static int[] dirY = { -1, 0, 1, 0 };
    public static int[][] map;
    public static boolean[][] visited;
    public static int N, M, maxSize = Integer.MIN_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    public static void main(String[] args) throws Exception {
 
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visited = new boolean[M][N];
 
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        int roomCnt = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    roomCnt += 1;
                }
            }
        }
        System.out.println(roomCnt);
        System.out.println(maxSize);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                breakWall(i, j);
            }
        }
        System.out.println(maxSize);
    }
 
    public static void breakWall(int startRow, int startCol) {
 
        for (int i = 0; i < 4; i++) {
            if ((map[startRow][startCol] & (1 << i)) != 0) {
                for (int j = 0; j < visited.length; j++)
                    Arrays.fill(visited[j], false);
                map[startRow][startCol] -= (1 << i);
                bfs(startRow, startCol);
                map[startRow][startCol] += (1 << i);
            }
        }
    }
 
    public static void bfs(int startRow, int startCol) {
 
        Queue<dot> q = new LinkedList<dot>();
        q.offer(new dot(startRow, startCol));
        visited[startRow][startCol] = true;
        int roomSize = 0;
 
        while (!q.isEmpty()) {
 
            dot node = q.poll();
            int row = node.row;
            int col = node.col;
            int wall = map[row][col];
            roomSize += 1;
 
            for (int i = 0; i < 4; i++) {
 
                if ((wall & (1 << i)) > 0)
                    continue;
 
                int nr = row + dirX[i];
                int nc = col + dirY[i];
 
                if (isBoundary(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new dot(nr, nc));
                }
            }
        }
        maxSize = Math.max(maxSize, roomSize);
    }
 
    public static boolean isBoundary(int row, int col) {
        return (row >= 0 && row < M) && (col >= 0 && col < N);
    }
    static class dot {
    	 
        int row;
        int col;
     
        public dot(int row, int col) {
            this.row = row;
            this.col = col;
        }
     
    }
}
 
