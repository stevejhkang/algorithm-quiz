package boj;

import java.io.*;
import java.util.*;
 
public class BOJ_17136_3 {
    public static StringTokenizer stk;
    public static StringBuilder sb = new StringBuilder();
    public static int[] paper = {0, 5, 5, 5, 5, 5};
    public static int[][] map = new int[10][10];
    public static int min = Integer.MAX_VALUE, one_cnt = 0;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
            	int a =  Integer.parseInt(stk.nextToken());
                if(a==1) {
                	map[i][j] =a;
                	one_cnt += map[i][j];    //1의 개수 세기
                }
            }
        }
        //r = 해당 row, cnt = 사용한 색종이수, total = 제거한 1의 개수
        Backtracking(0, 0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
 
    public static void Backtracking(int r, int cnt, int total) {
        if (min <= cnt) return;      //현재 갱신된 최솟값보다 많이 사용하면 리턴
        if (total == one_cnt) {      //1을 다 채운 경우
            min = Math.min(min, cnt);
            return;
        }
        for (int i = r; i < 10; i++) {     //r부터 시작
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 1) {
                    boolean flag = false;  //큰 색종이로 덮을 수 있으면 이후 색종이는 확인해 보지 않아도 된다
                    for (int k = 5; k >= 1; k--) {
                        if ((i + k) <= 10 && (j + k) <= 10 && paper[k] > 0) { //범위와 종이개수 체크
                            if (!flag) {
                                flag = check(i, j, k); //k*k 색종이를 덮을 수 있으면 check = true
                            }
                            if (flag) {
                                setVisited(i, j, k);
                                paper[k]--;
                                Backtracking(i, cnt + 1, total + k * k);
                                paper[k]++;
                                setVisited(i, j, k);
                            }
                        }
                    }
                    if (!flag) return;          //색종이를 못덮는 경우
                }
                if (map[i][j] == 1) return;     //덮고나서도 해당좌표를 못지우는경우
            }
        }
    }
 
    //size만큼의 색종이를 사용할 수 있는지 확인
    public static boolean check(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }
 
    //방문한 점을 XOR 연산 XOR은 0일때 1과 연산하면 1이되고 1일때 1과 연산하면 0이된다.
    public static void setVisited(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                map[i][j] = map[i][j] ^ 1;
            }
        }
    }
}
