package boj_may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {
    private static int r;
    private static int c;
    private static int[][] map;
    private static boolean[][] visit;
    private static Queue<dot> static_queue = new LinkedList<>();
    private static int time = 0;
    private static int before = 0;
    private static int[] dy = {0, 1, 0, -1};
    private static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
        	stringTokenizer= new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= c; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        while (true) {
            visit = new boolean[r + 1][c + 1];
            
            init();

            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++) {
                    if (map[i][j] == 1 && Edge(i, j)) {
                    	 Queue<dot> queue = new LinkedList<>();

                         queue.add(new dot(i, j));
                         visit[i][j] = true;

                         while (!queue.isEmpty()) {
                             dot point = queue.poll();

                             if (Edge(point.y, point.x)) {
                                 map[point.y][point.x] = 2;
                                 static_queue.add(new dot(point.y, point.x));
                             }

                             for (int dir = 0; dir < 4; dir++) {
                             	int ny = point.y + dy[dir];
                                 int nx = point.x + dx[dir];
                                 
                                 if(ny<=0||ny>r&&nx<=0||nx>c) continue;
                                 
                                 if (map[ny][nx] == 1 && !visit[ny][nx]) {
                                     queue.add(new dot(ny, nx));
                                     visit[ny][nx] = true;
                                 }
                             }
                         }
                    }
                }
            }

            if (static_queue.isEmpty()) break;

            before = static_queue.size();
            while (!static_queue.isEmpty()) {
                dot point = static_queue.poll();
                map[point.y][point.x] = -1;
            }

            time++;
        }

        System.out.println(time + "\n" + before);
    }

    private static boolean Edge(int y, int x) {
        for (int dir = 0; dir < 4; dir++) {
        	int ny = y + dy[dir];
            int nx = x + dx[dir];

//            if(ny<=0||ny>r||nx<=0||nx>c) return false;
            
            if (map[ny][nx] == -1) {
                return true;
            }
        }

        return false;
    }

    private static void init() {
        Queue<dot> queue = new LinkedList<>();
        boolean[][] visited = new boolean[r + 1][c + 1];

        queue.add(new dot(1, 1));
        map[1][1] = -1;
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            dot point = queue.poll();

            for (int i = 0; i < 4; i++) {
            	int ny = point.y + dy[i];
                int nx = point.x + dx[i];

                if(ny<=0||ny>r||nx<=0||nx>c) continue;
                
//                System.out.println(ny+","+nx);
                if (!visited[ny][nx]
                		&& map[ny][nx] <= 0) {
                    map[ny][nx] = -1;
                    visited[ny][nx] = true;
                    queue.add(new dot(ny, nx));
                }
            }
        }
    }

    static class dot {
        private int y;
        private int x;

        public dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}