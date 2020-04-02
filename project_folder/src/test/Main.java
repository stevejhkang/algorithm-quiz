package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 26. 오후 7:20:56
 * @category 
* @problem_description 두 노드의 번호를 받아서 첫번재 노드의 자손이면서 두번째 노드의 자손이 아닌 노드들의 개수를 계산하는 프로그램을 작성하다
* 트리에서 노드의 자손이란 노드의 자식, 그 자식의 자식, 그 자식의 자식의 자식 등을 모두 포함하는 용어
* 두번째 노드는 첫번째 노드의 자손임이 보장된다.
* 
*   노드개수 n 질문 개수 m이 주어진다.  다음줄에는 n개의 노드 번호가 주어진다. 
*   m개의 질문 두번째 노드 번호는 첫번째 노드의 자손임이 보장된다. 
* @solving_description 
*/

public class Main {
	private static int n;
	private static int m;
	static Node[] node_list;
	private static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer =new StringTokenizer(bufferedReader.readLine());
		
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		node_list = new Node[n+1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for(int i=1;i<=n;i++) {
			int parent= Integer.parseInt(stringTokenizer.nextToken());
			if(parent==0) continue;
			Node temp = getNode(parent);
			if(temp.l==0) {
				temp.l=i;
			}
			else {
				temp.r=i;
			}
		}
		int sum=0;
		for(int i=0;i<m;i++) {
			cnt=0;
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			go(start,end);
//			System.out.println(cnt);
			sum+=cnt;
		}
		System.out.println(sum);
		
	}
	private static void go(int now, int end) {
		// TODO Auto-generated method stub
		if(node_list[now]==null) return;
		int left = node_list[now].l;
		int right = node_list[now].r;
		
		if(left!=0) {
			cnt++;
			if(left!=end) {
				go(node_list[now].l, end);
			}
		}
		if(right!=0) {
			cnt++;
			if(right!=end) {
				go(node_list[now].r,end);
			}
		}
	}
	static Node getNode(int v) {
		if(node_list[v]==null) {
			Node temp = new Node(v, 0, 0);
			node_list[v]=temp;
			return node_list[v];
		}
		else 
			return node_list[v];
	}


}
class Node{
	int idx;
	int l;
	int r;
	public Node(int idx, int l, int r) {
		super();
		this.idx = idx;
		this.l = l;
		this.r = r;
	}
	
	
}
