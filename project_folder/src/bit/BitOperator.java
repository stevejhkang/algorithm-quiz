package bit;

public class BitOperator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1=10;
		System.out.println(num1+": "+Integer.toBinaryString(num1));
		System.out.println((num1<<1)+": "+Integer.toBinaryString((num1<<1)));
		
		//2. bit masking 연산
		int status =0; // 32bit, 각 bit는 0/1 --> 포함 여부 표현
		int student1 =1;
		
		//2-1 포함 여부는 &연산을 사용한다!
		System.out.println("student1 포함? "+(status & student1));
		
		//2-2 포함 시키기는 | 연산을 사용한다!
		status|=student1;
		System.out.println("student1 포함? "+(status & student1));
		
		System.out.println("현재 상태: "+Integer.toBinaryString(status));
		int student2 = 0b100; //0b는 2진수를 표현할때 사용한다. 100(2진수)=>4(10진수)
		System.out.println(status&student2);
		status|=student2;
		System.out.println("현재 상태: "+Integer.toBinaryString(status));
		
		//홀수 짝수 확인 -> 첫번째 비트가 1이면 무조건 홀수이므로
		System.out.println((status&1)==0? "짝수":"홀수");
		status+=1;
		System.out.println((status&1)==0? "짝수":"홀수");
	}

}
