package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayVsLinked {
	public static void main(String[] args) {
		addSequencial(new LinkedList<>());
		addSequencial(new ArrayList<Integer>());
		addNonSequencial(new LinkedList<>());
		addNonSequencial(new ArrayList<Integer>());
	}
	//절차적으로 어레이리스트
	static void addSequencial(List<Integer> list) {
		long start= System.currentTimeMillis();
		for(int i=0;i<1000000*3;i++) {
			list.add(i);
		}
		long end =System.currentTimeMillis();
		System.out.println(list.getClass().getName()+" : "+(end-start));
		
	}
	//비순차적은 링크드리스트
	static void addNonSequencial(List<Integer> list) {
		long start= System.currentTimeMillis();
		for(int i=0;i<100000;i++) {
			list.add(0,i);
		}
		long end =System.currentTimeMillis();
		System.out.println(list.getClass().getName()+" : "+(end-start));
		
	}
}
