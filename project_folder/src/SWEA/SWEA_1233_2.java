package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1233_2 {
	static node[] node_list;
	static int tc;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bReader =new BufferedReader(new InputStreamReader(System.in));
		for( tc=1;tc<=10;tc++) {
			int n = Integer.parseInt(bReader.readLine());

			 node_list= new node[n+1];
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(bReader.readLine());
				int idx = Integer.parseInt(st.nextToken());
				node temp_node=getNode(idx);
				Character s = st.nextToken().charAt(0);
				while(st.hasMoreElements()) {
				
					int l_index=Integer.parseInt(st.nextToken());
					node node_left=getNode(l_index);
					int r_index=Integer.parseInt(st.nextToken());
					node node_right = getNode(r_index);
					temp_node.l=node_left;
					temp_node.r=node_right;
				}
			}//for i
			if(search(1,0)==2)
				System.out.println("#"+tc+" "+1);
			else {
				System.out.println("#"+tc+" "+0);
			}
		}
	}
	private static int search(int i,int count) {
		// TODO Auto-generated method stub
		//사칙연산이 나왔을때 아래 숫자가 있어야됨 또 사칙연산이면 또 내려가야돼
		//두개의 숫자가 나올 때까지
		node now = node_list[i];
		if(now==null) {
			return 0;
		}
		
		int cnt=0;
		if(Character.isDigit(now.l.value)) {//왼쪽이 숫자이고
			cnt++;
//			search(now.l.idx, count+1);
		}
		else if(!Character.isDigit(now.l.value)) {//숫자가 아니면 초기화
			cnt+=search(now.l.idx, 0);
		}
		if(Character.isDigit(now.r.value)) {//오른쪽 숫자이고
			cnt++;
//			search(now.r.idx, count+1);
		}
		else if(!Character.isDigit(now.r.value)) {//숫자가 아니면 초기화
			cnt+=search(now.r.idx, 0);
		}
		if(cnt==2) {
			return 1;
		}
		return 0;
			
		
	}
	static node getNode(int idx) {
		if(node_list[idx]==null) {
			node temp= new node(idx,'a', null, null);
			node_list[idx]=temp;
			return temp;
		}
		else {
			return node_list[idx];
		}
	}
	static class node{
		int idx;
		Character value;
		node l;
		node r;
		public node(int idx, Character value, node l, node r) {
			super();
			this.idx = idx;
			this.value = value;
			this.l = l;
			this.r = r;
		}
		
		
	}
}
