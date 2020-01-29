package algo_basic_day1;

public class SplitTest {
	public static void split1() {
		String src = "10,030,042";
		String src2="10.030.042";
		String[] splited= src.split(",");
		String[] splited2=src2.split("\\.");
		int sum=0;
		for(int i=0;i<splited2.length;i++) {
			sum+=Integer.parseInt(splited2[i]);
		
		}
		
		System.out.println(sum);
	}
	
	public static void split2() {
		String src ="이름:홍길동,Java:100,HTML:80,Script:85";
		String[] splited = src.split(",");
		
		int sum=0; 
		String name="";
		for(int i=0;i<splited.length;i++) {
			if(i==0) {
				name=splited[i].split(":")[1];
				System.out.println(name);
				continue;
			}
			sum+= Integer.parseInt(splited[i].split(":")[1]);
		}
//		System.out.println(sum);
		System.out.printf("%s: %d%n",name,sum);
	}
	
	public static void main(String[] args) {
		split2();
	}
	
}
