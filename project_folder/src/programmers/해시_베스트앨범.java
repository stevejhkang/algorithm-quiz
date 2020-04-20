package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 4. 19. 오후 11:16:31
 * @category 
* @problem_description 장르별로 가장 많이 재생된 노래 두개 씩 모아 베스트 앨범 출시하려고 한다.
* 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같다.
* 1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.  제일 많이 재생된 장르먼저?
* 2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.  그 안에서도 많이 재생된 노래 먼저
* 3. 장르 내에서 재생 횟수가 같은 노래 중에서 고유 번호가 낮은 노래를 먼저 수록합니다. 고유번호가 낮은 노래 먼저   
* @solving_description 
* 
*/

public class 해시_베스트앨범 {
	public static void main(String[] args) {
		String[] genres= {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		Solution s = new Solution();
		System.out.println(Arrays.toString(s.solution(genres, plays)));
	}
}
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer=null;
        
        //장르별로 Hash map을 만든다.
        TreeMap<String,Integer> treemap = new TreeMap<>();
        HashMap<String, PriorityQueue<song>> hashmap = new HashMap<>();
        for(int i=0;i<genres.length;i++) {
        	if(!treemap.containsKey(genres[i])) {
        		treemap.put(genres[i], plays[i]);
        		PriorityQueue<song> pq = new PriorityQueue<>();
        		pq.add(new song(i, plays[i]));
        		hashmap.put(genres[i],pq);
        	}
        	else {
        		treemap.replace(genres[i], treemap.get(genres[i])+plays[i]);
        		PriorityQueue<song> pq = hashmap.get(genres[i]);
        		pq.add(new song(i, plays[i]));
        		hashmap.put(genres[i], pq);
        	}
        }
        //이미 정렬이 되어 있으므로
        PriorityQueue<genre> pq2 = new PriorityQueue<>();
        for(Map.Entry<String, Integer> entry : treemap.entrySet()) {
        	String next = entry.getKey();
        	Integer value = entry.getValue();
        	pq2.add(new genre(next, value));
        }
        ArrayList<Integer> indexList  = new ArrayList<Integer>();
       
        while(!pq2.isEmpty()) {
        	genre now_genre = pq2.poll();
        	String next = now_genre.genre;
          	int size = 2;
        	PriorityQueue<song> pq = hashmap.get(next);
        	while(!pq.isEmpty()&&size>0) {
        		song now = pq.poll();
        		int index = now.index;
        		int value = now.value;
        		indexList.add(index);
        		size--;
        	}
        }
        
        
        answer = new int[indexList.size()];
        for(int i=0;i<indexList.size();i++) {
        	answer[i] = indexList.get(i);
        }
        
        return answer;
    }
    static class song implements Comparable<song>{
    	int index, value;

		public song(int index, int value) {
			super();
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(song o) {
			// TODO Auto-generated method stub
			return (new Integer(this.value)).compareTo(new Integer(o.value))*-1;
		}
    	
    }
    static class genre implements Comparable<genre>{
    	String genre;
    	int sum;
		public genre(String genre, int sum) {
			super();
			this.genre = genre;
			this.sum = sum;
		}
		@Override
		public int compareTo(genre o) {
			// TODO Auto-generated method stub
			return (new Integer(this.sum).compareTo(new Integer(o.sum)))*-1;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((genre == null) ? 0 : genre.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			genre other = (genre) obj;
			if (genre == null) {
				if (other.genre != null)
					return false;
			} else if (!genre.equals(other.genre))
				return false;
			return true;
		}
    	
    	
    }
    
}