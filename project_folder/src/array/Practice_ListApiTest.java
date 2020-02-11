package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice_ListApiTest {
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) {
		addTest();
		selectTest();
		updateTest();
		removeTest();
	}
	public static void addTest() {
		list.add(100);
		list.add(200);
		list.add(0,10);
		list.add(1,200);
		System.out.println(list);
	}
	public static void selectTest() {
		System.out.println(list.contains(100));
		System.out.println(list.indexOf(100));
		System.out.println(list.isEmpty());
		
		Object[] arr = list.toArray();
		Integer[] arr2 =(Integer[]) list.toArray();
		
		System.out.println("hello"+Arrays.toString(arr2));
		
//		for(int i=0;i<list.size();i++) {
//			System.out.println(i+" : "+list.get(i));
//		}
//		for(int data: list) {
//			System.out.println(data);
//		}
	}
	
	public static void updateTest() {
		list.set(0, -1);
		System.out.println(list);
		
	}
	
	public static void removeTest() {
		list.addAll(Arrays.asList(1,2,3,4,5,6,7));
		Integer num =1;
		int i = num; 
		
		System.out.println("삭제 전"+list);
		list.remove(1);
		System.out.println("삭제 후"+list);
		
		
	}
	
}
