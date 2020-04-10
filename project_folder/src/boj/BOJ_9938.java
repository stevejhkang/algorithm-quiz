package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_9938 {
    static int N, L, A[], parent[];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer= new StringTokenizer(bufferedReader.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(stringTokenizer.nextToken());
        L = Integer.parseInt(stringTokenizer.nextToken());
        A = new int[L + 1];
        parent = new int[L + 1];
        for (int i = 0; i <=L; i++) parent[i] = i;
        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = find(Integer.parseInt(stringTokenizer.nextToken()));
            int v = find(Integer.parseInt(stringTokenizer.nextToken()));
            if (A[u] == 0 || A[v] == 0) {
                if (A[u] == 0 && A[v] == 0) {
                    if (u > v) {
                        int temp = v;
                        v = u;
                        u = temp;
                    }
                    A[v] = i;
                } else if (A[u] == 0) {
                    A[u] = i;
                } else if (A[v] == 0) {
                    A[v] = i;
                }
                parent[v] = u;
                sb.append("LADICA").append("\n");

            } else {
                sb.append("SMECE").append("\n");
            }


        }
        System.out.println(sb);

    }

    public static int find(int idx) {
        if (idx == parent[idx]) return idx;
        else return parent[idx] = find(parent[idx]);
    }
}
