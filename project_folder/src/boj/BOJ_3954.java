package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3954 {
	private static int m;
	private static int c;
	private static int i;
	private static int[] memory;
	private static int idx;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bufferedReader.readLine());
		for(int tc=1;tc<=t;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			m= Integer.parseInt(stringTokenizer.nextToken()); //배열의 크기
			c= Integer.parseInt(stringTokenizer.nextToken()); //프로그램 코드 길이
			i= Integer.parseInt(stringTokenizer.nextToken()); //입력의 크기
			memory= new int[m]; //수 범위는 0~255 넘어가면 terminate
			
			String code= bufferedReader.readLine(); //프로그램 코드
			String input = bufferedReader.readLine(); //입력
			
			idx=0; //idx가 -1되면 m-1로 넘어가고 m이 되면 0이된다.
			int input_idx=0;//인풋의 인덱스
			
			//'['와 ']'개수를 유지하면서'['나 ']'가 나오면 길이를 비교해서 중간이라면 0보다 크거나 같고 마지막에는 0이어야 함.
			int open=0; int end=0;
			//출력은 무시
			
			//코드 한자한자 만들기
			for(int i=0;i<code.length();i++) {
				char command = code.charAt(i);
				if(command=='+') { //현재 포인터에 있는 값 +1
					memory[idx]++;
					if(memory[idx]>=256) {
						System.out.println("Terminates");
						break;
					}
				}
				else if(command=='-') {//현재 포인터에 있는 값 -1
					memory[idx]--;
					if(memory[idx]<0) {
						System.out.println("Terminates");
						break;
					}
				}
				else if(command==',') {//문자하나 읽고 포인터가 가리키는 곳에 저장.
					if(input_idx==input.length()) {
						memory[idx]=255;
						continue;
					}
					memory[idx]=input.charAt(input_idx);
				}
				else if(command=='[') { //close가 어디에서 나오는지를 어떻게 알 것인가???
					if(idx==0) {
						
					}
				}
			}
//			for(int i=0;i<memory.length;i++) {
//				System.out.println(memory[i]);
//			}
			//근데 최근 []의 위치를 저장할 필요가 있다.... 근데 이건 나중에 해도 될듯..
			
			//사이클이 있기 때문에 이걸 전부 일일이 이동시키면서 확인하면 시간 터질듯
			
		}//for tc
	}//main
}
