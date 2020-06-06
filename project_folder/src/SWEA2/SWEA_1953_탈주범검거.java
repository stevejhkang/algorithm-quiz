package SWEA2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
 
public class SWEA_1953_탈주범검거 {
    static int[][] map;
    static int N,M,R,C,L;
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,-1,1};
    
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int tc=1; tc<=T; tc++) {
        	StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            R = Integer.parseInt(stringTokenizer.nextToken());
            C = Integer.parseInt(stringTokenizer.nextToken());
            L = Integer.parseInt(stringTokenizer.nextToken());
            
            map = new int[N][M];
            for(int i=0; i<N; i++) {
            	stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for(int j=0; j<M; j++) {
                    map[i][j]=Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            stringBuilder.append("#"+tc+" "+bfs(new dot(R,C,1))+"\n");
        }
        System.out.println(stringBuilder);
    }
    
    private static int bfs(dot idot) {
        // TODO Auto-generated method stub
        Queue<dot> q= new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[idot.y][idot.x]=true;
        q.add(idot);
        int count=1;
        while(!q.isEmpty()) {
            dot p = q.remove();
            int y= p.y;
            int x = p.x;
            int d= p.d;
            
            if(d==L) continue;
            for(int k=0; k<4; k++) {
                int ny = y+dy[k];
                int nx = x+dx[k]; 
                if(ny<0||nx<0||ny>=N||nx>=M) continue;
                if(visited[ny][nx]) continue;
                if(map[ny][nx]==0) continue;
                if(k==0) {
                    if(map[y][x]==3||map[y][x]==4||map[y][x]==7) continue;
                    if(map[ny][nx]==3||map[ny][nx]==5||map[ny][nx]==6) continue;
                }else if(k==1) {
                    if(map[y][x]==3||map[y][x]==5||map[y][x]==6) continue;
                    if(map[ny][nx]==3||map[ny][nx]==4||map[ny][nx]==7) continue;
                }else if(k==2) {
                    if(map[y][x]==2||map[y][x]==4||map[y][x]==5) continue;
                    if(map[ny][nx]==2||map[ny][nx]==6||map[ny][nx]==7) continue;
                }else if(k==3) {
                    if(map[y][x]==2||map[y][x]==6||map[y][x]==7) continue;
                    if(map[ny][nx]==2||map[ny][nx]==4||map[ny][nx]==5) continue;
                }
                visited[ny][nx]=true;
                q.add(new dot(ny,nx,d+1));
                count++;
            }
        }
        return count;
    }
    
    static class dot{
        int y;
        int x;
        int d;
        dot(int y, int x, int d){
            this.y=y;
            this.x=x;
            this.d=d;
        }
    }
}
 
