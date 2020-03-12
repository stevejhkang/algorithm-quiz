package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 12. 오전 11:00:51
 * @category 
* @problem_description 이름의 길이가 짧을 수록 앞에 같은 길이면 사전순,
* 같은 이름이면 하나만 남겨두리고 하여 이름 명부 만들기
* @solving_description 
*/

public class SWEA_7701 {
	private static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bufferedReader.readLine());
		StringBuffer sb = new StringBuffer();
		
		for(int tc=1;tc<=T;tc++) {
			n = Integer.parseInt(bufferedReader.readLine());
			TreeSet<String> name_list = new TreeSet<>(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					if(o1.length()==o2.length()) {
						return o1.compareTo(o2);
					}
					else if (o1.length()>o2.length()){
						return 1;
					}
					else return -1;
				}
				
			});
			for(int i=0;i<n;i++) {
				String s = bufferedReader.readLine();
				name_list.add(s);
			}
			sb.append("#"+tc+"\n");
			for (Iterator iterator = name_list.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				sb.append(string+"\n");
			}
		}//for tc
		System.out.println(sb);
	}//main
	
	static class name{
		String s;

		public name(String s) {
			super();
			this.s = s;
		}
		
	}
}
