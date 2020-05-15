package boj_may;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static int row;
    private static int col;
    private static int[][] board;
    private static boolean[][] visited;
    private static Queue<Point> meltingQueue = new LinkedList<>();
    private static int day = 0;
    private static int pre = 0;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        row = scanner.nextInt();
        col = scanner.nextInt();

        board = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        while (true) {
            visited = new boolean[row + 1][col + 1];
            
            // 바깥 공기를 -1로 바꾼다.
            checkOutsideAir();

            // 치즈의 가장자리 구역을 찾고, meltingQueue에 저장
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    if (board[i][j] == 1 && isEdge(i, j)) {
                        bfs(i, j);
                    }
                }
            }

            // 녹을 치즈가 더이상 없는 경우 종료
            if (meltingQueue.isEmpty()) break;

            // 모든 치즈가 녹기 한 시간 전 남은 치즈적 면 == 마지막으로 녹아야할 치즈 면
            pre = meltingQueue.size();

			// meltingQueue에 있는 치즈 녹이기
            while (!meltingQueue.isEmpty()) {
                Point point = meltingQueue.poll();
                board[point.x][point.y] = -1;
            }

            // 날짜++
            day++;
        }

        System.out.println(day + " " + pre);
    }

    private static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (isEdge(point.x, point.y)) {
                board[point.x][point.y] = 2;
                meltingQueue.add(new Point(point.x, point.y));
            }

            for (int i = 0; i < 4; i++) {
                int newX = point.x + dx[i];
                int newY = point.y + dy[i];

                if (isInArea(newX, newY) && board[newX][newY] == 1 && !visited[newX][newY]) {
                    queue.add(new Point(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }
    }

    private static boolean isEdge(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (board[newX][newY] == -1) {
                return true;
            }
        }

        return false;
    }

    private static void checkOutsideAir() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visitedAir = new boolean[row + 1][col + 1];

        queue.add(new Point(1, 1));
        board[1][1] = -1;
        visitedAir[1][1] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = point.x + dx[i];
                int newY = point.y + dy[i];

                if (isInArea(newX, newY) && !visitedAir[newX][newY] && board[newX][newY] <= 0) {
                    board[newX][newY] = -1;
                    visitedAir[newX][newY] = true;
                    queue.add(new Point(newX, newY));
                }
            }
        }
    }

    private static boolean isInArea(int x, int y) {
        return x > 0 && x <= row && y > 0 && y <= col;
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
