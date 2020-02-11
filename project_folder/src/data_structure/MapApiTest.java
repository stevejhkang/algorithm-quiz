package data_structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;



public class MapApiTest {
	public static void main(String[] args) {
		useMap(new HashMap<Integer, String>());
		useMap(new LinkedHashMap<>());
		useMap(new TreeMap<>());
	}
	static void useMap(Map<Integer,String> map) {
		int [] keys = {1,-1,0,2,-2,-2};	
		for(int key:keys) {
			map.put(key, Integer.toString(key));
		}
		//hashmap-순서가 없다.
		System.out.println(map.getClass().getName()+" : "+map);
		
		
		//수정 
		//put - 처음 들어오면 insert, 동일한 키일 경우 새로운 데이터로 수정
		map.put(-3, "200"); 
		map.replace(-5, "200"); //이건 없으면 안넣고 있으면 수정
		
		System.out.println(map.getClass().getName()+" : "+map);
		
		//탐색1: 키만 가져와서  value조회하기
		Set<Integer> keySet = map.keySet();
		for(Integer key: keySet) {
			System.out.println(key+" : "+map.get(key));
		}
		
		//탐색2: key:value의 조합인 Entry 활용
		Set<Entry<Integer,String>> entrySet = map.entrySet();
		for(Entry<Integer, String> entry: entrySet) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		//탐색3 = value 중점
		Collection<String> values = map.values();
		System.out.println(values);
		System.out.println(map.containsKey(1)+" : "+map.containsValue("100"));
		
		if(map instanceof TreeMap) {
			TreeMap<Integer, String> tMap = (TreeMap<Integer, String>)map;
			System.out.println(tMap.headMap(0)); //0보다 앞에 있는 원소들 반환
			System.out.println(tMap.tailMap(0)); //0보다 같거나 뒤에 있는
			System.out.println(tMap.subMap(-1, 1)); //-1<=x<1인 원소들 반환
		}
	}
}

