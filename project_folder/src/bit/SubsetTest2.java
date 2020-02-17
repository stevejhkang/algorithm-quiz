package bit;

import java.util.ArrayList;
import java.util.List;

public class SubsetTest2 {
	public static void main(String[] args) {
		int n =6;
		for(int i=1;i<(1<<n)-1;i++) {
			List<Integer> g1 = new ArrayList<>();
			List<Integer> g2 =new ArrayList<>();
			for(int j=0;j<n;j++) {
				if((i&1<<j)>0) {
					g1.add(j); //그룹의 부분집합
				}
				else {
					g2.add(j); //그룹의 여집합
				}
			}
//			System.out.println(g1+" : "+ g2);
			
//			System.out.println(g1+":"+g2);
//			System.out.println("");
//			//그룹별 탐색 후 최소 인구 차이 구하기
//			int result1 = bfs(g1);
//			if(result1>0) {
//				int result2= bfs(g2);
//				if(result2>0) {
//					min=Math.min(min, Math.abs(result2-result1));
//				}
//			}
			
		}//for i
	}
}
