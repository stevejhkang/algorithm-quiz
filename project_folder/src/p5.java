import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class p5 {
	public static void main(String[] args) {
		Solution s = new Solution();
		String[][] dataSource= {{"doc1", "t1", "t2", "t3"},
				{"doc2", "t0", "t2", "t3"},
				{"doc3", "t1", "t6", "t7"},
				{"doc4", "t1", "t2", "t4"},
				{"doc5", "t6", "t100", "t8"}};
		String[] tags= {"t1", "t2", "t3"};
		String[] result =s.solution( dataSource, tags);
		System.out.println(Arrays.toString(result));
	}
}
class Solution {
    public String[] solution(String[][] dataSource, String[] tags) {
       
        //테그에 대한 도큐먼트 hashmap을 만들어서 
        //태그들이 들어간 총횔수를 바로 계산에서 treemap에 넣는다.
        PriorityQueue<doc> pq = new PriorityQueue<>();
        Set<String> set_tag = new HashSet<String>(Arrays.asList(tags));
        for(int i=0;i<dataSource.length;i++) {
        	int cnt=0;
        	String doc_name = dataSource[i][0];
        	for(int j=1;j<dataSource[i].length;j++) {
        		if(set_tag.contains(dataSource[i][j])) {
        			cnt++;
        		}
        	}
        	if(cnt!=0) {
        		
        		pq.offer(new doc(doc_name,cnt));
        	}
        }
        String[] answer= {};
        if(pq.size()>=10) {
        	answer = new String[10];
        	int idx=0;
        	while(idx<10) {
        		doc now =pq.poll();
        		if(now.num==0) {
        			break;
        		}
              	answer[idx]=now.doc_name;
              	idx++;
        	}
        	
        }
        else {
//        	System.out.println(pq.size());
        	answer = new String[pq.size()];
        	int idx=0;
        	int size=pq.size();
        	while(idx<size) {
        		doc now =pq.poll();
        		if(now.num==0) {
        			break;
        		}
              	answer[idx]=now.doc_name;
              	idx++;
        	}
        	 
        }
       
        return answer;
    }
    static class doc implements Comparable<doc>{
    	String doc_name;
    	int num;
		@Override
		public int compareTo(doc o) {
			// TODO Auto-generated method stub
			
			if(this.num==o.num) {
				return this.doc_name.compareTo(o.doc_name);
			}
			else {
				if(this.num<o.num) {
					return 1;
				}
				else  {
					return -1;
				}
			}
			
		}
		public doc(String doc_name, int num) {
			super();
			this.doc_name = doc_name;
			this.num = num;
		}
		
    	
    }
}