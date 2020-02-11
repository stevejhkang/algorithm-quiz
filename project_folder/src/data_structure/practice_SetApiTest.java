package data_structure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class practice_SetApiTest {
	public static void main(String[] args) {
		useSet(new HashSet<>()); //순서 보장 안됨, 중복 없음
		useSet(new LinkedHashSet<>()); //순서 보장됨, 중복없음
		useSet(new TreeSet<>()); //알파벳 순(우선순위)으로 보장됨, 중복없음
	}
	static void useSet(Set<String>set) {
		set.add("Hello");
		set.add("Hello");
		//중복데이터가 허용되지 않음, 객체의 equals, hashcode를 비교한다.
		set.addAll(Arrays.asList("My","Dreams","True","Come"));
		System.out.println(set.getClass().getName()+" : "+set);
		//set의 정보 조회
		for(String str: set) {
			System.out.println("for : "+str);
		}
		//collection의 하위 클래스이므로...
		//size, isEmpty, add, contains,... 
		
		//수정 set은 인덱스가 없어서 콕찝어서 수정이 불가능하다.
		
		//삭제
		set.remove("Hello");
		System.out.println("삭제 후: "+set);
		
		//TreeSet의 기능 살펴보기
		if(set instanceof TreeSet) {
			TreeSet<String> test= (TreeSet<String>)set;
			//TreeSet은 검색과 관련된 기능 제공
			System.out.println("Test의 앞에 요소들: "+test.headSet("True"));
			System.out.println("Test의 뒤의 요소들: "+test.tailSet("True"));
			
			System.out.println("Dinner <= X < Test인 요소들? "+test.subSet("Dinner", "Test"));
			
		}
		
	}
}
