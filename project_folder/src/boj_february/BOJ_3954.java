package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 19. 오후 2:55:19
 * @category 
 * @problem_description 무한루프이면 무한루프인 부분의 괄호 위치를 출력한다.
 * @solving_description  
 */
public class BOJ_3954 {
	private static int m;
	private static int c;
	private static int i;
	private static int[] memory;
	private static int idx;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bufferedReader.readLine());
		
		//test case만큼 
		for(int tc=1;tc<=t;tc++) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			m= Integer.parseInt(stringTokenizer.nextToken()); //배열의 크기(메모리)
			c= Integer.parseInt(stringTokenizer.nextToken()); //프로그램 코드 길이
			i= Integer.parseInt(stringTokenizer.nextToken()); //입력의 크기
			memory= new int[m]; //수 범위는 0~255 넘어가면 terminate
			boolean flag =true;
			String code= bufferedReader.readLine(); //프로그램 코드
//			System.out.println(code);
			String input = bufferedReader.readLine(); //입력
			
			idx=0; //idx가 -1되면 m-1로 넘어가고 m이 되면 0이된다. 앞뒤로 이어져 있음
			int input_idx=0;//인풋의 인덱스
			
			//'['와 ']'개수를 유지하면서'['나 ']'가 나오면 길이를 비교해서 중간이라면 0보다 크거나 같고 마지막에는 0이어야 함.
			int open=0; int close=0;
			int cnt_open=0; int cnt_close=0;
			//출력은 무시
			int command_run=0;
			//코드 한자한자 분석하기 
			for(int i=0;i<code.length();i++) {
				char command = code.charAt(i);
				command_run++;
				if(command=='+') { //현재 포인터에 있는 값을 +1시켜준다 범위 넘어가면 종료
					memory[idx]++;
					if(memory[idx]>=256) { //넘어가면 에러내고 종료된다.
						System.out.println("Terminates");
						flag = false;
						break;
					}
				}
				else if(command=='-') {//현재 포인터에 있는 값 -1시켜준다.
					memory[idx]--;
					if(memory[idx]<0) { //넘어가면 에러내고 종료한다.
						System.out.println("Terminates");
						flag = false;
						break;
					}
				}
				else if(command==',') {//문자하나 읽고 포인터가 가리키는 곳에 저장.
					if(input_idx==input.length()-1) { //입력의 마지막이면 무조건 255입력한다.
						memory[idx]=255;
						continue;
					}
					memory[idx]=input.charAt(input_idx++); //입력받고 입력 index++시켜준다.
				}
				else if(command=='[') { //close가 어디에서 나오는지를 어떻게 알 것인가???
					open =i;
					cnt_open++;
					//idx가 가리키는 숫자가 0이면 ]를 만날때까지 
					if(memory[idx]==0) {
//						int idx_copy= idx;
						while(true) {
							if(code.charAt(i)!=']') {
								close=i;
								break;
							}
							command_run++;
							i++; 
						}
					}
				}
				else if(command==']') {
					close= i;
					//idx가 가리키는 숫자가 0이 아니면 [를 만날때까지 이동시킨다.
					if(memory[idx]!=0) {
						while(true) {
							if(cnt_open==0&&code.charAt(i)=='[') {
								open=i;
								cnt_open--;
								break;
							}
							if(code.charAt(i)=='[') {
								cnt_open--;
							}
							command_run++;
							i--;
							
						}
					}
				}
				else if(command=='<') {
					//인덱스를 -1감소 -1이 될때 반대로(length-1) 옮겨준다.
					idx--;
					if(idx==-1) {
						idx=code.length()-1;
					}
					
				}
				else if(command=='>') {
					//배열 인덱스 +1증가 길이값이될때 반대로 옮겨준다.
					idx++;
					if(idx==code.length()) {
						idx=0;
					}
					
				}
				if(command_run>= 50000000) {
					System.out.println("Loops "+open+" "+close);
					
					flag = false;
					break;
				}
			}
//			System.out.println(flag);
			if(flag)
				System.out.println("Terminates");
//			for(int i=0;i<memory.length;i++) {
//				System.out.println(memory[i]);
//			}
			//근데 최근 []의 위치를 저장할 필요가 있다.... 근데 이건 나중에 해도 될듯..
			
			//사이클이 있기 때문에 이걸 전부 일일이 이동시키면서 확인하면 시간 터질듯
			// 50,000,000
			
		}//for tc
	}//main
}
