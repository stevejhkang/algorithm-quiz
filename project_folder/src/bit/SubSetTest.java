package bit;

import java.util.ArrayList;
import java.util.List;

public class SubSetTest {
	static char[] chars = {'A','B','C','D'};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//부분 집합을 찾아라
		for(int i=0;i<(1<<chars.length);i++) {
			System.out.println(Integer.toBinaryString(i));
			List<Character> subset = new ArrayList<Character>();
			for (int j = 0; j < chars.length; j++) {
				if((i&1<<j)>0) {//이러면 포함된 원소
					subset.add(chars[j]);
				}
			}
			System.out.println(subset);
		}
		
	}

}
