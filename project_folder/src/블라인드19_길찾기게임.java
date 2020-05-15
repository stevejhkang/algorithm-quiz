import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 8. 오후 8:16:25
 * @category 그래프찾기 
* @level 4
* @problem_description 
* 두 팀으로 나누고, 각 팀이 같은 곳을 다른 순서로 방문하도록 해서 먼저 순횔르 마친 팀이 승리
* 방문할 곳의 2차원 좌표 값을 구하고 각 장소를 이진트리의 노드가 되도록 구성한 후, 순회 방법을 힌트로 주어 각 팀이 스스로 경로를 찾도록
* 할 계획이다.
* 트리 노드 구성 규칙
* 1. 트리를 구성하는 모든 x,y 좌표 값은 정수이다.
* 2. 모든 노드는 서로 다른 x값을 가진다.
* 3. 같은 레벨에 있는 노드는 같은 y좌표를 가진다.
* 4. 자신 노드의 y값을 항상 부모 노드보다 작다.
* 5. 임의의 노드 V의 왼쪽 서브 트리에 있는 모든 노드의 x값은 v의 x값보다 작다.
* 6. 임의의 노드 V의 오른쪽 서브 트리에 있는 모든 노드의 x값은 V의 x값보다 크다.
* 
* 
* 노드들의 좌표가 담긴 배열 nodeinfo가 매개변수로 주어질 때, 
* 노드들로 구성된 이진트리를 전위 순회, 후위 순회한 결과를 2차원배열에 순서대로 담아 return하도록 하자
* @solving_description 
* 내가 몰랐던 점!: 링크드리스트를 이용해서 트리를 구성하는 방법을 몰랐음...
* 
*/


public class 블라인드19_길찾기게임 {
	public static void main(String[] args) {
		int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
		
		System.out.println(Arrays.deepToString(new Solution().solution(nodeinfo)));
	}
	
}
class Solution {
	
    private static int idx;
    
	public int[][] solution(int[][] nodeinfo) {
    	int[][] answer = new int[2][nodeinfo.length];
    	if(nodeinfo==null || nodeinfo.length==0) return answer;
    	
        int[][] nodeinfos = new int[nodeinfo.length][3];
        for(int i=0;i<nodeinfo.length;i++) {
        	nodeinfos[i][0] = nodeinfo[i][0]; //x
        	nodeinfos[i][1]= nodeinfo[i][1]; //y
        	nodeinfos[i][2]= i+1; //index
        }
        
        //정렬한다.
        Arrays.sort(nodeinfos, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				int first_standard = Integer.compare(o1[1], o2[1])*-1;
				if(first_standard==0) { //y가 같을 경우
					return Integer.compare(o1[0], o2[0]); //x오름차순
				}
				else {
					return first_standard; //y 내림차순
				}
			}
		});
        
        Node root = null,cur=null,prev=null; //이런 식으로 초기화 할 수 있다.
        int[] preOrder = new int[nodeinfo.length], postOrder = new int[nodeinfo.length];
        answer[0] = preOrder;
        answer[1] = postOrder;
        
        root = new Node(nodeinfos[0][0], nodeinfos[0][1], nodeinfos[0][2]);	
        
        for(int i=1;i<nodeinfos.length;i++) {
        	cur = root; //루트에서부터 계속 탐색해서 넣을 곳을 찾는다.
        	Node node = new Node(nodeinfos[i][0], nodeinfos[i][1], nodeinfos[i][2]); //넣을 node
        	
        	while(true) {
        		if(node.x<cur.x) { //넣으려는 노드 x값이 현재 탐색 노드의 x보다 작으면 현재를 왼쪽으로 옮긴다.
        			prev= cur;
        			cur = cur.left;
        			if(cur==null&&node.x<prev.x) { //그런데 현재 노드가 비어있으면 이전노드의 왼쪽에 넣어준다.
        				prev.left = node;
//        				cur= node;
        				break;
        			}
        		} else if(node.x>cur.x) { //넣으려는 노드 x값이 현재 탐색 노드의 x보다 크면 현재를 오른쪽으로 옮겨 다시 탐색
        			prev= cur;
        			cur = cur.right;
        			if(cur==null&&node.x>prev.x) { //그런데 옮긴 노드가 null이라면 이전노드의 오른쪽에 노드를 넣는다.
        				prev.right= node;
//        				cur= node;
        				break;
        			}
        		}
        	}
        }
        idx=0;
        preOrderfunc(root,preOrder);
        idx = 0;
        postOrderfunc(root,postOrder);
        return answer;
    }//solution
    
    static void preOrderfunc(Node cur, int[] pre) {
    	if(cur==null )return;
    	pre[idx++] = cur.idx;
    	preOrderfunc(cur.left, pre);
    	preOrderfunc(cur.right,pre);
    }
    static void postOrderfunc(Node cur, int[] post) {
    	if(cur==null )return;
    	postOrderfunc(cur.left, post);
    	postOrderfunc(cur.right,post);
    	post[idx++] = cur.idx;
    }
    
    static class Node{
    	int x; 
    	int y;
    	int idx;
    	Node left;
    	Node right;
    	
		public Node(int x, int y, int idx) {
			super();
			this.x = x;
			this.y = y;
			this.idx = idx;
			this.left = null;
			this.right = null;
		}
    	
    	
    }
  
}//Solution