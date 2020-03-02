package boj_february;

import java.util.Scanner;

/**
 * @author junhukang
 * @time 2020. 2. 9. 오후 4:40:17
 * @category 
* @problem_description n번째 손님에게 배정되어야 하는 방 번호
* @solving_description 
*/
public class BOJ_10250 {
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		int t = scanner.nextInt();
		for(int i=0;i<t;i++) {
			int h = scanner.nextInt();
			int w = scanner.nextInt();
			int n = scanner.nextInt();
			StringBuilder ans = new StringBuilder();
			
			int floor = n%h;
			if(floor==0) {
				ans.append(h);
			}
			else {
				ans.append(floor);
			}
			int room_num=((n-1)/h)+1;
			if(room_num<10) {
				ans.append("0"+room_num);
			}
			else {
				ans.append(room_num);
			}
			System.out.println(ans);
		}
	}
}
