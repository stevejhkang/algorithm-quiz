import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 6. 6. 오전 12:03:38
 * @category 시뮬레이션
* @level 3
* @problem_description
*  09:00부터 n회 t분 간격으로 역에 도착하며, 하나의 셔틀에는 최대 m명의 승객이 탈 수 있다.
*  셔틀은 도착한 순간에 대기열에 선 크루까지 포함해서 바로 출발
*  콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시간 중 제일 늦은 시각을 구하여라
*  -> 콘은 게을러서 같은 시각에 도착한 크루 중 대기열 젤 뒤에 선다
*  * 모든 크루는 23:59에 집에 돌아간다.
* @solving_description
* 제일 늦게 도착하려면 
* 1. 일단 제일 마지막 버스의 출발 시간보다 늦게 도착해서는 안된다.
* 2. 버스 횟수 * 수용 인원 = total 보다 크루가 많을 때는 total번째에 타야된다
* 3. 해당 버스시간대에 태울 수 있는 사람들을 태우면서 현재 타임테이블에 남은 인구보다
* 남은 버스 횟수 * 수용인원이 작으면 그 전에 넣어야 한다.   
*/

public class 블라인드18_셔틀버스 {
	public static void main(String[] args) {
		int n =1;
		int t=1;
		int m = 5;
		String[] timetable= {"08:00", "08:01", "08:02", "08:03"}; //크루가 대기열의 도착한 시간
		

		System.out.println(new Solution().solution(n, t, m, timetable));
	}
}
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        Queue<Integer> remain_crews = new LinkedList<>();
        for(int i=0;i<timetable.length;i++) {
        	StringTokenizer stringTokenizer = new StringTokenizer(timetable[i],":");
        	int hour = Integer.parseInt(stringTokenizer.nextToken());
        	int min= Integer.parseInt(stringTokenizer.nextToken());
        	
        	int time = hour*60+min;
        	//분으로 환산해서 넣는다.
        	remain_crews.add(time);
        }
        
        System.out.println(remain_crews);
        int bus_time=9*60; //분으로 처리한다.
        int max_time= 23*60+59;
        int answer_time=0;
        
        for(int i=n;i>0;i--) {
        	//남은 수용인원
        	int remain_seats = i*m;
        	//현재 남은 버스 횟수 * 수용인원보다 timetable인원이 많으면 무조건 현재 타임 테이블에 앞에 있는 사람보다
            //빠른 시간에 넣어야한다.
        	
        	//현재 수용할 수 인원 다 태운다.
        	int seat= m;
        	while(seat>0) {
        		int crew_time=remain_crews.peek();
        		if(bus_time>=crew_time) { //태울 수 있고 시간이 작거나 같으면 뺀다.
        			remain_crews.poll();
        		}
        		seat--;
        	}
        	bus_time=bus_time+t; //다음 버스시간 대입
        }
        
     
        
        //남으면 버스 하나씩 오면 그 버스 시간보다 작은 사람들을 태워서 보낸다.
        
        
        return answer;
    }
}