package algo_basic_day1;

public class SimpleArray {
	public static void arrTest1() {
		int[] num= new int[4];  //배열 객체가 있는 주소를 num에 저장.\
		String[] strings = {"Hello", "Java","World"};
		
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}
		for(String str: strings) {
			System.out.println(str);
		}
	}
}

