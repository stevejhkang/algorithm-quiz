package bit;

public class Bitmask {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=10;
		System.out.println(Integer.toBinaryString(i));
		System.out.printf("%d --> %s \n",i<<1,Integer.toBinaryString(i<<1));
		System.out.printf("%d --> %s \n",i>>1, Integer.toBinaryString(i>>1));
		
		System.out.println(i&(1<<0));
	}

}
