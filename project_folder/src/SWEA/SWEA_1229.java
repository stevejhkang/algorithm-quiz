package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1229 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1;tc<=10;tc++) {
			int n = Integer.parseInt(bReader.readLine());
			ArrayList<Integer> ll = new ArrayList<>();
			StringTokenizer stringTokenizer =new StringTokenizer(bReader.readLine());
			for (int i = 0; i < n; i++) {
				int a = Integer.parseInt(stringTokenizer.nextToken());
				ll.add(a);
			}
			int order= Integer.parseInt(bReader.readLine());
			stringTokenizer = new StringTokenizer(bReader.readLine());
			for(int i=0;i<order;i++) {
				String order_type= stringTokenizer.nextToken();
				if(order_type.equals("I")) {
					int idx=Integer.parseInt(stringTokenizer.nextToken());
					int y = Integer.parseInt(stringTokenizer.nextToken());
					ArrayList<Integer> insert= new ArrayList<>();
					for(int j=0;j<y;j++) {
						insert.add(Integer.parseInt(stringTokenizer.nextToken()));
					}
					ll.addAll(idx, insert);
				}
				else {
					int idx= Integer.parseInt(stringTokenizer.nextToken());
					int times= Integer.parseInt(stringTokenizer.nextToken());
					for(int j=0;j<times;j++) {
						ll.remove(idx);
					}
				}
			}
			StringBuilder sBuilder= new StringBuilder();
			sBuilder.append("#"+tc+" ");
			for(int i=0;i<10;i++) {
				sBuilder.append(ll.get(i)+" ");
			}
			System.out.println(sBuilder);
		}
	}
}
