package dfs_and_bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class mychu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String string ="1 1 2 1 3 2 4 1 5 3";
//		person person_list[] = new person [6];
//		for(int i=1;i<=5;i++) {
//			person_list[i].num=1;
//		}
		int person[]=new int[6];
		int chu=20;
		Queue<person> queue = new LinkedList<person>();
//		StringTokenizer stringTokenizer =new StringTokenizer(string);
		int sum=20;
		queue.offer(new person(1, 1));
		int idx=1;
		int last_person=0;
		Map<Integer, Integer> map = new HashMap<>();
		outer: while(sum>=0) {
//			System.out.println("현재 큐 상태: "+queue);
			person tempPerson = queue.poll();
			
			
			if(sum-tempPerson.num<=0) {
				System.out.printf("%d번 사람이 마지막 캔디 %d개를 가져감\n",tempPerson.id,sum);
//				last_person=tempPerson.id;
//				System.out.println(tempPerson.id);
				if(map.get(tempPerson.id)==null) {
					map.put(tempPerson.id, tempPerson.num);
				}
				else {
					map.put(tempPerson.id, tempPerson.num+map.get(tempPerson.id));
				}
				break outer;
			}
			else {
				sum-=tempPerson.num;
				System.out.printf("%d번이 %d개의 캔디 가져감. 남은개수: %d\n",tempPerson.id,tempPerson.num,sum);
				if(map.get(tempPerson.id)==null) {
					map.put(tempPerson.id, tempPerson.num);
				}
				else {
					map.put(tempPerson.id, tempPerson.num+map.get(tempPerson.id));
				}
			}
			idx++;
			tempPerson.num++;
			queue.offer(tempPerson);
			queue.offer(new person(idx, 1));
			
			System.out.println("큐사람수: "+queue.size());
			System.out.println("");
		}
		for(int i=1;i<map.size();i++) {
			System.out.println("idx: "+i+", num: "+map.get(i));
		}
		
		
	}

}
class person{
	int id;
	int num;
	public person(int id, int num) {
		super();
		this.id = id;
		this.num = num;
	}
	@Override
	public String toString() {
		return "[person"+ id + ", num=" + num + "]";
	}
	
}