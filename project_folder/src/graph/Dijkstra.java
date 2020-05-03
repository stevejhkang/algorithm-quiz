package graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 5. 3. 오후 7:55:16
 * @category Dijkstra
* @level 3
* @problem_description 
* 한 정점에서 다른 정점까지의 경로의 가중치의 최소합을 구하는 알고리즘 
* @solving_description 
* 1. 먼저 연결상태(엣지)를 따로 저장하고 
* 2. 시작점에서 각 점들까지의 경로의 가중치 합을 무한대로 초기화 한다.
* 3. 그리고 시작점을 0으로 만들고 
* 4. for을 돌려 방문하지 않았고 현재까지 계산된 가중치 합중 최소의 인덱스와 합을 구한다.
* 5. 그리고 해당 점을 거쳐서 다른 모든 점으로 가는 값과 지금까지 저장된 가중치 합들을 비교해서
* 6. 갱신을 해준다.
* 7. 그리고 해당 점은 방문처리 해준다.
* 8. 이를 모든 점에 대해서 실시한다. 
*/

public class Dijkstra {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		//간선이 많으면 인접행렬 유리하다. 간선 없으면 인접리스트가 유리
		//연결상태 입력 및 초기화
		int[][] adj = new int[V][V];
		for(int i=0;i<E;i++) {
			adj[sc.nextInt()][sc.nextInt()]=sc.nextInt();
		}
		
		//시작점에서 해당 idx까지 가는데 걸리는 가중치 합을 저장하는 배열
		int[] D = new int[V]; 
		Arrays.fill(D,Integer.MAX_VALUE); //MAX_VALUE로 채워준다.
		
		boolean[] check  = new boolean[V];//선택하면 점을 체크해주는 배열
		
		//시작점이 0이면 0으로 만들어준다.
		D[0] = 0;
		for(int i=0;i<V-1;i++) { 
			
			int min = Integer.MAX_VALUE; //가장 작은 값을 기억할 변수
			int index=-1; // 그 위치를 기억할 변수 
			
			for(int j=0;j<V;j++) {
				//아직 처리하지 않았으면서, 가장 짧은 거리라면
				if(!check[j] && min>D[j] ) {  //초기에는 자기자신 빼고 전부 맥스값이므로 시작점 인덱스가 반환됨.
					min=D[j];
					index=j;
				}
			}
			//이미 방문한 노드 제외하고 모드 맥스값이면, 연결이 없는 경우 끝
			if(index==-1)
				break;
			
			//새로운 친구로부터 갈수있는 경로들을 업데이트
			for(int j=0;j<V;j++) {
				//아직 처리하지(선택하지) 않았으면서, !check[j]
				//경로가 존재하고(갈 수 있으면), adj[index][j]!=0 
				// index까지 간 거리 + index에서 j까지 갈때의 weight의 합이 = D[index]+adj[index][j]<D[j]
				//기존 저장된 시작점에서 j까지 가는 경로의 가중치 합=D[j]보다 작으면 갱신시켜준다.
				if(!check[j] && adj[index][j]!=0&&D[index]+adj[index][j]<D[j]) {
					D[j] = D[index]+adj[index][j];
					//갱심했으면 decrease pq한다.
				}
			}
			//최종적으로 방문 체크
			check[index]=true;
		}
		System.out.println(Arrays.toString(D));
	}
}
