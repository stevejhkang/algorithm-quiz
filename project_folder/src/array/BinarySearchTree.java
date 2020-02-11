package array;

public class BinarySearchTree {
	//root node
	static Node root;
	public static void main(String[] args) {
		addNode(5);
		addNode(3);
		System.out.println(searchNode(1));
		addNode(1);
		addNode(4);
		addNode(2);
		System.out.println(searchNode(2));
	}
	static boolean deleteNode(int v) {
		Node target = searchNode(v);
		if(target==null) {
			return false;
		}
		else {
			//자식이 없는 경우
			if(target.l==null&&target.r==null) {
				if(target.p.l==target) { //부모의 왼쪽이 나라면
					target.p.l=null;
				}
				else {
					target.p.r=null;
				}
			}
			//자식이 하나만 있는 경우
			else if(target.l==null||target.r==null){
				Node child = target.l==null? target.r: target.l;
				if(target.p.l==target) {
					target.p.l=child;
				}
				else {
					target.p.r=child;
				}
			}
			//자식이 둘인 경우
			else {
				//1. 왼쪽 트리에서 가장 큰놈을 찾는다. 왼쪽트리에서 가장 오른쪽(오른쪽으로 쭉 갔을때 나오는거=right가 null)
				Node max =target.l;
				while(max.r!=null) {
					max= max.r;
					
				}
				//대상 노드를 지워버리고
				deleteNode(max.v);
				//2. 삭제 대상 노드의 값을 최대 노드의 값으로 변경
				target.v=max.v;
			}
		}
		return true;
	}
	//이진 검색트리에 v를 추가하고 성공 여부를 반환하시오.
	static boolean addNode(int v) {
		//새 노드를 추가하려면 검색에 실패해야한다.
		//검색은 루트부터 시작한다.
		if(root==null) {
			root=new Node(v);
			return true;
		}
		Node current = root;
		while(true) {
			if(current.v==v) { //이미 트리에 있으므로 실패
				return false;
			}
			else if(current.v>v) {
				//현재 노드의 값보다 새로 들어온 녀석이 작으면? 왼쪽으로 다시 찾아봐야 한다.
				if(current.l==null) {
					Node newNode= new Node(v);
					current.l= newNode ; 
					newNode.p = current;//삭제 시 부모를 쉽게 찾기 위해 부모 추가
					return true;
				}else {
					//비어있지 않다면 왼쪽 노드를 이용해서 다시 탐색
					current = current.l;
				}
			}else { //오른쪽으로
				if(current.r ==null) {
					Node newNode = new Node(v);
					current.r = newNode;
					newNode.p = current;
					return true;
				}
				else {
					current=current.r;
				}
			}
			
		}
	}//addNode
	static Node searchNode(int v) {
		if(root==null) {
			return null;
		}
		Node current =root;
		while(true) {
			if(current==null) {
				return null;
			}
			if(current.v==v) {
				return current;
			} 
			else if(current.v<v) {
				current=current.r;
			}
			else {
				current=current.l;
			}
		}
	}
	static class Node{
		int v;
		Node l,r,p;
		public Node(int v) {
			super();
			this.v = v;
		}
		@Override
		public String toString() {
			return "Node [v=" + v + "]";
		}
		
		
	}
}
