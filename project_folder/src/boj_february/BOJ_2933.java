package boj_february;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 2. 27. 오전 11:04:31
 * @category 
* @problem_description R*C 각 칸은 비거나 미네랄, 네방향인접 미네랄은 클러스터. 
* 창영은 동굴의 왼쪽에 서있고 상근은 오른쪽 서로 막대기를 던짐, 던질 높이 정한다
* 막대가 날아가다가 미네랄을 만나면 그 칸 미네랄은 모두 파괴, 막대는 멈춤.
* 
* x미네랄 .빈칸
* @solving_description 쏠때마다 BFS/DFS를 해서 바닥에 닿는지를 체크해야한다.
* 바닥에 하나라도 닿으면 분리된게 아님. 그러나 쭉 돌아도 바닥에 닿는게 없으면 분리된 것이므로
* 중력에 의해 떨어진다. 언제까지 떨어지는 지 어떻게 판단하지? 클러스터 맨 아래부분
*/

public class BOJ_2933 {
	public static void main(String[] args) {
		
	}
}
