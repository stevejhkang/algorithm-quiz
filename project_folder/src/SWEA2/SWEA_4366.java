package SWEA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 1. 오후 4:18:07
 * @category 
* @level 3
* @problem_description 2진수와 3진수를 기억하는데 둘다 1자리씩 틀리다.
* 이것을 바탕으로 10진수 올바른 값을 추측하라 
* @solving_description 
*/

public class SWEA_4366 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		
		StringBuilder stringBuilder =new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			String binary = bufferedReader.readLine();
			String tritri =bufferedReader.readLine();
			
			out:for(int i=0;i<binary.length();i++) {
				
				StringBuilder bin = new StringBuilder(binary);
				String binary_after=null;
				
				//i번째 인덱스를 반대로 바꿔준다.
				if(binary.charAt(i)=='0') { 
					binary_after=bin.replace(i, i+1, "1").toString();
				}else if(binary.charAt(i)=='1') {
					binary_after=bin.replace(i, i+1, "0").toString();
				}
				
				for(int j=0;j<tritri.length();j++) {
					
//					System.out.println(tri);
					String tri_after = null;
					
					
					for(int k=0;k<3;k++) {
						StringBuilder tri = new StringBuilder(tritri);
//						System.out.println(tri);
						Integer num = new Integer(k);
						if((tri.charAt(j)-'0')==k) continue; //같으면 k로 바꿔줄 필요 없이 넘어가고
						tri_after= tri.replace(j, j+1, num.toString()).toString();
						
						
//						System.out.println(binary_after+", "+tri_after);
						//이제 binary_after와 tri_after를 가지고 변환했을 때 값이 같은지를 확인한다.
						int bin_decimal =0; int tri_decimal=0;
						
						tri_decimal= Integer.parseInt(tri_after,3);
						bin_decimal = Integer.parseInt(binary_after,2);
						
						
//						for(int x=0;x<tri_after.length();x++) {
//							tri_decimal+=(new Integer(tri_after.substring(x,x+1)))*Math.pow(3,tri_after.length()-x-1);
//						}
//						for(int y = 0; y<binary_after.length();y++) {
//							bin_decimal+=(new Integer(binary_after.substring(y,y+1)))*Math.pow(2,binary_after.length()-y-1);
//						}
						if(tri_decimal==bin_decimal) {
							stringBuilder.append("#"+tc+" "+tri_decimal+"\n");
							break out;
						}
					}
					
					
				}
			}//for out
			
		}//for tc
		System.out.println(stringBuilder);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	}
}
