package boj;

import java.util.Scanner;

/**
 * @author junhukang
 * @time 2020. 2. 9. 오후 5:34:51
 * @category 브루트포스 
* @problem_description
* @solving_description 0,0에서 부터 n-1,m-1까지 흑백흑백하면서 다른 수 체크하기
*/
public class BOJ_1018 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		String map[] = new String[n];
		for(int i=0;i<n;i++) {
			map[i]=scanner.next();
		}
		int min= Integer.MAX_VALUE;
		//모든 점에 대해서 해보기
		for(int i=0;i<=n-8;i++) {
			for(int j=0;j<=m-8;j++) { 
				int num=0; int check_B=0; int check_W=0;
				int temp_min=Integer.MAX_VALUE;
				for(int k=0;k<8;k++) {
					if(k%2==0) {
						for(int l=0;l<8;l++) {
							int indexY=i+k; int indexX=j+l;
							//시작이 B일때 
							//2로 안나눠지면서 B이면 ++ 2로 나눠지는데 W이면 ++
							if(num%2!=0&&map[indexY].charAt(indexX)=='B') {
								check_B++;
							}
							else if(num%2==0&&map[indexY].charAt(indexX)=='W') {
								check_B++;
							}
							//시작이 W일때 
							//2로 안나눠지면서 W이면 ++ 2로 나눠지는데 B이면 ++
							if(num%2!=0&&map[indexY].charAt(indexX)=='W') {
								check_W++;
							}
							else if(num%2==0&&map[indexY].charAt(indexX)=='B') {
								check_W++;
							}
							num++;
						}
					}
					else {
						for(int l=7;l>=0;l--) {
							int indexY=i+k; int indexX=j+l;
							//시작이 B일때 
							//2로 안나눠지면서 B이면 ++ 2로 나눠지는데 W이면 ++
							if(num%2!=0&&map[indexY].charAt(indexX)=='B') {
								check_B++;
							}
							else if(num%2==0&&map[indexY].charAt(indexX)=='W') {
								check_B++;
							}
							//시작이 W일때 
							//2로 안나눠지면서 W이면 ++ 2로 나눠지는데 B이면 ++
							if(num%2!=0&&map[indexY].charAt(indexX)=='W') {
								check_W++;
							}
							else if(num%2==0&&map[indexY].charAt(indexX)=='B') {
								check_W++;
							}
							num++;
						}
					}
					
				}
				temp_min=(check_B<check_W?check_B:check_W);
				if(min>temp_min)
					min=temp_min;
			}
		}
		System.out.println(min);
	}
}
