package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 19. 오후 6:08:08
 * @category 
* @problem_description 초기 미생물 s마리를 t로 만들어야한다.
* 1. 먹이주어 a만큼 늘린다.
* 2. 배양액을 주어 b배 만큼 늘린다.
* 정확히 t마리로 만들어야 한다. 
* 최대 빨리 갈려면 몇일 후에 갈 수 있는가
* @solving_description 
*/

public class SWEA_7194 {
	private static long s;
	private static long t;
	private static long a;
	private static long b;
	private static HashMap<Long, Long> map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		
		StringBuilder stringBuilder = new StringBuilder();
		StringTokenizer stringTokenizer;
		for(int tc=1;tc<=T;tc++) {
			stringTokenizer= new StringTokenizer(bufferedReader.readLine());
			s = Integer.parseInt(stringTokenizer.nextToken());
			t = Integer.parseInt(stringTokenizer.nextToken());
			a = Integer.parseInt(stringTokenizer.nextToken());
			b = Integer.parseInt(stringTokenizer.nextToken());
			
			stringBuilder.append("#"+tc+" ");
			map = new HashMap<Long, Long>();
			map.put(t, (long) 0);
			if(b==1) {
				stringBuilder.append(t-s+"\n");
				dfs(t-a,1);
			}
			else {
				if(t%b==0) {
					dfs(t/b,1);
				}
				dfs(t-a,1);
			}
			if(map.containsKey(s)) {
				stringBuilder.append(map.get(s)+"\n");
//				System.out.println(map.get(t));
			}
			else {
				stringBuilder.append(-1+"\n");
//				System.out.println(-1);
			}
		}//tc
		System.out.println(stringBuilder);
	}//main
	static void dfs(long next,long day) {
		//이미 있는 경우 크기 비교 후 갱신
		if(map.containsKey(next)) {
			long saved_day = map.get(next);
			if(saved_day>day) { //새로 들어간게 작으면 갱신
				map.put(next, day);
			}
			else { //크거나 같으면 그냥 리턴시켜준다.
				return;
			}
		}
		else {
			//없으면 넣어준다.
			map.put(next, day);
		}
		if(next<=0) {
			//같거나 크면 더이상 커질 필요없으므로 리턴
			return;
		}
		if(next%b==0) {
			dfs(next/b,day+1);
		}
		dfs(next-a,day+1);
	}//dfs
}
