package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 21. 오후 3:56:50
 * @category 
* @problem_description  
* 0. 받았던던 것부터 차례대로 격자에 맞춰서 붙이려고 한다.
* 1. 스티커를 회전시키지 않고 모눈종이에서 떼어낸다.
* 2. 다른 스티커와 겹치거나 노트북을 벗어나지 않으면서 스티커를 붙일 수 있는 위치를 찾는다. 위쪽부터 스티커를
* 3. 선택한 위치에 스티커를 
* @solving_description 
*/

public class p1 {
	private static int n;
	private static int m;
	private static int k;
	private static int[][][] shape;
	private static ArrayList<rc> rc_list;
	private static int[][] map;
	private static ArrayList<dot> al;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());
		
		shape = new int[k][][];
		rc_list= new ArrayList<rc>();
		
		int r,c;
		for(int tc=0;tc<k;tc++) {
			stringTokenizer=new StringTokenizer(bufferedReader.readLine());
			r = Integer.parseInt(stringTokenizer.nextToken());
			c = Integer.parseInt(stringTokenizer.nextToken());
			rc_list.add(new rc(r, c));
			for(int i=0;i<r;i++) {
				stringTokenizer= new StringTokenizer(bufferedReader.readLine());
				for(int j=0;j<c;j++) {
					int a= Integer.parseInt(stringTokenizer.nextToken());
					shape[tc][i][j]= a;
				}
			}
		}
		
		map = new int[n][m];
		
		for(int tc=0;tc<k;tc++) { //k개를 전부 맞춰본다 
			//전부 맞추는데 중간에 범위(i+r,j+c) 벗어나면 break;
			int row = rc_list.get(tc).row; int col = rc_list.get(tc).col;
			cancover: for(int turn=0;turn<3;turn++) {
				//turn함수 실행
				for(int i=0;i+row<=n;i++) {
					for(int j=0;j+col<=m;j++) {
						al = new ArrayList<dot>();
						boolean flag=true;
						outer: for(int q=0;q<row;q++) {
							for(int w=0;w<col;w++) {
								int y= i+q; int x = j+w;
								if(map[y][x]==0&&shape[tc][y][x]==1) {
									al.add(new dot(y, x));
								}
								else if(map[y][x]==1&&shape[tc][y][x]==1) {
									//안되므로 false
									flag= false;
									break outer;
								}
							}
						}
						//덮을 수 있으면 덮는다.
						if(flag) {
							for(int i1=0;i1<al.size();i1++) {
								int y = al.get(i1).y; int x = al.get(i1).x;
								map[y][x]=1;
								break cancover;
							}
						}
					}
				}//for i
			}
		} //for tc
		cnt = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		
	}//main
	static class rc{
		int row,col;

		public rc(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		
	}
	static class dot{
		int y, x;

		public dot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
