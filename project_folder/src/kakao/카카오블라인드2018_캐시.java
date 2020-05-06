package kakao;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 5. 오후 12:21:31
 * @category LRU + 링크드리스트 활용방법
* @level 3
* @problem_description 
* 도시이름을 검색하면 해당 도시와 관련된 맛집 게시물들을 디비에서 읽어보여주는 서비스
* 디비에서 게시물을 가져오는 부분의 실행시간이 너무 오래 걸린다
* 디비 캐시를 적용해서 성능 개선을 시도하려 한다. 캐시 크기를 얼마로  하면 효율적일지 계산
* 
* DB캐시를 적용할 때 캐시 크기에 따른 시간 측정 프로그램 작성
* 
* 캐시 크기, 도시 이름 배열 입력
* 0<=캐시사이즈<=30 도시수는 <=10만
* 대소문자 구분하지 않는다. 도시이름은 최대 20자
* 
* 캐시 교체 알고리즘은 LRU - 최근 사용한 것이 들어가는
* cache hit일경우 실행시간 1
* 미스일 경우 실행시간 5
* 
* @solving_description 
* 
* 입력된 도시이름 배열을 순서대로 처리할 때 총 실행시간
* 
* 캐시사이즈가 0인것을 체크하지 못했음!!!!!!!!!!!!!!!!!!!!!
* 항상 범위 체크를 하자;;;;
* 
* LRU는 최근 사용한 것을 캐시에 저장하는 방법인데 특이한 점이 이미 있는 것이 hit되면 가장 먼저들어왔던 것을 지워주고 
* 새로 넣어주어야 한다는 점.
* 앞뒤로 지워주는 거니까 링크드 리스트를 사용하면 좋을 것이라는 점.
*/

public class 카카오블라인드2018_캐시 {
	public static void main(String[] args) {
		int cacheSize= 5;
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		String[] cities = {"N","S","N","A","N","Y","N","G"};
		System.out.println(new Solution().solution(cacheSize, cities));
	}
	static class Solution {
		  public int solution(int cacheSize, String[] cities) {
		      int answer = 0;
		      if(cacheSize==0) {
		    	  return 5*cities.length;
		      }
		      LinkedList<String> cache = new LinkedList<String>();
		      
		      for(int i=0;i<cities.length;i++) {
		    	  String city = cities[i].toLowerCase(); //대소문자를 구분하지 않으므로 처음부터 소문자로 만들어 준다.
		    	  if(cache.size()==0) { //캐시가 비어있으면 그냥 +5하고 도시를 추가해준다.
		    		  answer+=5;
		    		  cache.add(city);
		    		  continue;
		    	  }
		    	  boolean find=false;
		    	  for(int index=0;index<cache.size();index++) {
		    		  if(city.equals(cache.get(index))) {//캐시가 있으면 시간은 +1
		    			 answer+=1;
		    			 find=true;
		    			 break; //캐시를 빠져나온다.
		    		  }
		    	  }//캐시 탐색
		    	  
		    	  if(!find) { //못찾은 경우
	    			  answer+=5;
	    		  }
		    	  
	    		  //사이즈가 cacheSize이상이면 처음 것을 삭제하고 
				  //마지막에 새로추가하고
		    	  //같아졌을때만 지우고
		    	  //이하일때는 찾으면 찾은것만 지우고 안 찾은건 
		    	 
				  if(cache.size()==cacheSize) { 
					  if(find) {
						  cache.removeFirstOccurrence(city); //가장 먼저 담긴 중복도시를 지워주어야 한다.
						  cache.add(city);
					  }
					  else { 
						  cache.remove();
						  cache.add(city);
					  }
				  }
				  //아니면 추가만 한다.
				  else {
					  cache.add(city);
//					  if(find) {
//						  cache.removeFirstOccurrence(city);
//						  cache.add(city);
//					  }
//					  else {
//						  
//						  
//					  }
				  }
		      }
		      
//		      for(int i=0;i<cache.size();i++) {
//		    	  System.out.print(cache.get(i)+" ");
//		      }
//		      System.out.println();
		      return answer;
		  }
		}
}
