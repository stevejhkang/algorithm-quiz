package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 17. 오전 11:00:26
 * @category dfs
 * @problem_description
 * @solving_description 
 * 핵심은 1인 좌표를 리스트에 넣어주는게 핵심인듯
 * 무엇을 넘겨줄지, 무엇을 수정하고 다시 복구 할지를 고민해야되는 문제  종이 숫자를 변경하고, map을 변경하고 dfs를 넘겨준다.
 * 그리고 나서 종이 숫자와 map을 다시 바꿔준다.
 * 종료조건: 모든 1을 살펴보았다. idx가 list의 크기와 같다.
 * 가지고 다닐 값: 1위치를 가지고 있는 list, 색종이 몇장 사용한지 cnt
 * 복귀 시켜야 할 값: 변경한 map, 사용한 색종이 다시 ++
 * 갱신. 재귀: 색종이를 붙일 수 있으면 색종이를 몇장 사용했는지 갱신해서 재귀
 * 가지치기: 최솟값을 구하는 경우 중간에 현재 가장 작은 값보다 cnt가 커지면 더이상 볼 필요없다.
 */

public class BOJ_17136 {
 
    static class pair {
        int r;
        int c;
 
        public pair(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }
 
    private static int min_paper;
    private static int[][] map;
 
    public static void main(String[] args) throws Exception {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        List<pair> list = new ArrayList<pair>();
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < 10; j++) {
                int number = Integer.parseInt(st.nextToken());
                map[i][j] = number;
                if (number == 1) { //1인 좌표들을 리스트에 순서대로 넣어준다.
                    list.add(new pair(i, j));
                }
            }
        }
        min_paper = Integer.MAX_VALUE;
        dfs(list, 0, 0); // list , idx , paper_cnt 1좌표 리스트, 1의 인덱스, 사용한 종이
        if(min_paper == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min_paper);
    } // end of main
 
    private static int[] paper = { 0, 5, 5, 5, 5, 5 };
 
    private static void dfs(List<pair> list, int idx, int paper_cnt) {
        // 가지치기
        if(min_paper < paper_cnt)
            return;
        // 종료조건
        if (idx == list.size()) {
            if (min_paper > paper_cnt)
                min_paper = paper_cnt;
            return;
        }
        pair p = list.get(idx);
        if (map[p.r][p.c] == 0) { //이미 0이 되었을 때
            dfs(list, idx + 1, paper_cnt); // paper_cnt 갱신없이 재귀
        }
        for (int i = 5; i >= 1; i--) {
            // check에는 붙어야 하는 색종이 위치 , 어떤 크기의 색종이?
            if (check(p, i)) { //i번째 색종이를 붙일 수 있으면
               
                // 색종이 붙여
                for (int j = p.r; j < p.r + i; j++) {
                    for (int k = p.c; k < p.c + i; k++) {
                        map[j][k] = 0;
                    }
                }
                // 색종이 사용
                paper[i]--;
                // 갱신해서 재귀
                dfs(list, idx + 1, paper_cnt + 1);
                paper[i]++;
                for (int j = p.r; j < p.r + i; j++) {
                    for (int k = p.c; k < p.c + i; k++) {
                        map[j][k] = 1;
                    }
                }
            }
        }
    }
 
    private static boolean check(pair p, int paper_number) {
        // 해당 색종이 없으면 못 붙임
        if(paper[paper_number] <= 0) {
            return false;
        }
        int r = p.r;
        int c = p.c;
        boolean flag = false;
        here: for (int i = r; i < r + paper_number; i++) {
            for (int j = c; j < c + paper_number; j++) {
                if (i >= 0 && i < 10 && j >= 0 && j < 10) {
                    // 맨 끝부분이고 거기까지 1이면 색종이 붙일 수 있음
                    if (i == r + paper_number - 1 && j == c + paper_number - 1 && map[i][j] == 1) {
                        flag = true;
                        break here;
                    } else if (map[i][j] != 1) { // 중간에 1이 아니면 그 색종이 붙일 수 없음
                        flag = false;
                        break here;
                    }
                }
            }
        }
        return flag;
    }
} // end of class
