package graph;

import java.util.Arrays;
import java.util.Scanner;

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
		
		//MAX값으로 초기화 해준다.
		int[] D = new int[V]; //시작점에서 idx까지 가는데 걸리는 가중치합
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
					//맥스값은 연결되지 않았음을 의미
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
				//최소이면 D[index]+adj[index][j]<D[j] 인덱스까지 간 거리 + index에서 j까지 갈때의 weight의 합이
				//기존 저장된 시작점에서 j까지 가는 거리보다 작으면 갱신시켜준다.
				//프림과 다른 점은 경로의합.
				if(!check[j] && adj[index][j]!=0&&D[index]+adj[index][j]<D[j]) {
					D[j] = D[index]+adj[index][j];
					//갱심했으면 decrease pq한다.
				}
			}
			//처리된놈으로 체크
			check[index]=true;
		}
		System.out.println(Arrays.toString(D));
	}
}
