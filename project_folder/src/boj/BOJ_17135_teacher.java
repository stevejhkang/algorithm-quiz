package boj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * @author 조용준
 * @since 2020. 2. 14.
 * @see https://www.acmicpc.net/problem/17135
 * @mem 31616
 * @time 440
 * @caution #combination, #priorityqueue
 * 조합을 이용해 궁수를 배치한 후 시뮬로 적군 이동. 
 * 동일 거리일 경우 맨 왼쪽의 적이 죽고, 즉시 죽는것이 아니라 모든 공격이 끝나면 죽는다.
 */
public class BOJ_17135_teacher {
	private static int R, C, D;
	private static int [][] map;
	private static int MAX = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		scanner = new Scanner(src);
		R = scanner.nextInt();
		C = scanner.nextInt();
		D = scanner.nextInt();
		
		map = new int[R][C];
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				map[r][c]=scanner.nextInt();
			}
		}
		// 입력 확인
		/*for(int [] row: map) {
			System.out.println(Arrays.toString(row));
		}*/
		// 궁수를 어디다 배치하지?
		combination(0, new int[3], 0, 0);
		
		System.out.println(MAX);
	}
	/**
	 * 조합된 궁수의 위치를 이용해서 공격한다.
	 * @param positions
	 */
	public static void attack(int [] positions) {
		// 적군을 모아보자.!!
		List<Enemy> enemies = new ArrayList<>();
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c]==1) {
					enemies.add(new Enemy(r, c));
				}
			}
		}
		//System.out.println("적군 정보 확인: " + enemies);
		
		// 죽은 병사의 수
		int deadMan = 0;
		while(true) {// loop 한번이 한 턴
			// 궁수가 쏜다. - 죽일 대상 선정
			for(int i=0; i<positions.length; i++) {
				int archer = positions[i];
				PriorityQueue<Enemy> targetedEnemies = new PriorityQueue<>();
				
				for(int e=0; e<enemies.size(); e++) {
					Enemy enemy = enemies.get(e);
					// 거리는?
					enemy.d = Math.abs(archer - enemy.c) + Math.abs(enemy.r - R);
					// 아직 죽지 않았고.. 거리내에 있다면 사망 가능!!
					if(enemy.d <=D) {
						targetedEnemies.offer(enemy);
					}
				}
				// pq가 비어있지 않다면 맨 처음 녀석을 사망 표시
				if(!targetedEnemies.isEmpty()) {
					targetedEnemies.poll().isTargeted = true;
				}
			}// 궁수의 공격/포인팅이 종료
			
			// 사망자 정리 --> 남은 병사들이 이동
			for(int e=0; e<enemies.size(); e++) {
				Enemy enemy = enemies.get(e);
				if(enemy.isTargeted) {
					enemies.remove(e--);// 삭제하면서 인덱스 조절 조심하세요..
					deadMan++;
				}else if(enemy.r==R-1){	// 맨 끝에까지 도달한 적은 죽은건 아님
					enemies.remove(e--);
				}else {
					enemy.r++;
				}
			}
			// 모든 병사가 사망했다면 - 게임 오버
			if(enemies.size()==0) {
				break;
			}
		}// while
		MAX = Integer.max(MAX, deadMan);
	}
	/**
	 * 궁수를 배치할 수 있는 조합을 구해보자
	 * @param r
	 * @param temp
	 * @param ti
	 * @param si
	 */
	public static void combination(int r, int [] temp, int ti, int si) {
		if(r==3) {
			//System.out.println(Arrays.toString(temp));
			attack(temp);
			return;
		}
		for(int i=si; i<C; i++) {
			temp[ti] = i;
			combination(r+1, temp, ti+1, i+1);
		}
	}
	
	static class Enemy implements Comparable<Enemy>{
		Integer r, c, d;// d: distance: 궁수와의 거리
		boolean isTargeted;// 표적이 되었는지
		public Enemy(Integer r, Integer c) {
			super();
			this.r = r;
			this.c = c;
		}
		// 기본적으로는 거리기준, 거리가 같다면 왼쪽기준
		public int compareTo(Enemy o) {
			if(this.d.equals(o.d)) {
				return this.c.compareTo(o.c);
			}else {
				return this.d.compareTo(o.d);
			}
		}
		
		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + "]";
		}
	}
	
	private static String src = "5 5 2\r\n" + 
			"0 0 0 0 0\r\n" + 
			"0 0 0 0 0\r\n" + 
			"0 0 0 0 0\r\n" + 
			"1 1 1 1 1\r\n" + 
			"0 0 0 0 0";
}
