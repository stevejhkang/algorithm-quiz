package array;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tree {
	static node[] node_list;
	public static void main(String[] args) {
		int n = 13;
		String string="1 2 1 3 2 4 3 5 3 6 4 7 5 8 5 9 6 10 6 11 7 12 11 13";
		StringTokenizer stringTokenizer =new StringTokenizer(string);
		node_list = new node[13+1];
		while(stringTokenizer.hasMoreElements()) {
			int parent= Integer.parseInt(stringTokenizer.nextToken());
			int child = Integer.parseInt(stringTokenizer.nextToken());
			
			
			node parent_node=getNode(parent);
			node child_node= getNode(child);
			
			if(parent_node.left==null) {
				parent_node.left=child_node;
			}
			else {
				parent_node.right=child_node;
			}
		}
		
		//입력받고
		for(int i=1;i<node_list.length;i++) {
			System.out.println(node_list[i]);
		}
		preOrder(node_list[1]);
		System.out.println("");
		inOrder(node_list[1]);
		System.out.println("");
		postOrder(node_list[1]);
		System.out.println("");
		
	}
	static void preOrder(node parent) {
		if(parent==null)
			return;
		System.out.print(parent.idx+" ");
		preOrder(parent.left);
		preOrder(parent.right);
	}
	static void inOrder(node parent) {
		if(parent==null)
			return;
		inOrder(parent.left);
		System.out.print(parent.idx+" ");
		inOrder(parent.right);
	}
	static void postOrder(node parent) {
		if(parent==null)
			return;
		postOrder(parent.left);
		postOrder(parent.right);
		System.out.print(parent.idx+" ");
	}
	static node getNode(int idx) {
		if(node_list[idx]==null) {
			node_list[idx]=new node(idx, null, null);
			return node_list[idx];
		}
		else return node_list[idx];
	}
	static class node{
		int idx;
		node left;
		node right;
		public node(int idx, node left, node right) {
			super();
			this.idx = idx;
			this.left = left;
			this.right = right;
		}
		@Override
		public String toString() {
			return "[idx=" + idx + ", left=" + (left!=null?left.idx:"") + ", right=" + (right!=null?right.idx:"") + "]";
		}
	}
}
