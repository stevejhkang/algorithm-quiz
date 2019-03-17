#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
using namespace std;
int Graph[1001][1001];
int DFSvisit[1001];
int BFSvisit[1001];
queue<int> q;
void DFS(int v, int N) { //v에서부터 n까지 방문순서 출력
	int i;
	DFSvisit[v] = 1;//첫 방문
	printf("%d ", v);
	for (i = 1; i <= N; i++) {
		if (Graph[v][i] == 1 && DFSvisit[i] == 0) { //i가 v와 연결되어 있고 방문을 하지 않았으면
			DFS(i, N); //더 깊히 탐색한다.
		}
	}
	return;
}
void BFS(int v, int N) {
	int Pop, i;
	printf("%d ", v); //첫방문
	q.push(v);//bfs를 위한 queue
	BFSvisit[v] = 1; //v탐색 여부 표시

	while (!q.empty()) {
		Pop = q.front();//원소를 꺼낸 후 pop에 저장
		q.pop();
		for (i = 1; i <= N; i++) {
			if (Graph[Pop][i] == 1 && BFSvisit[i] == 0) { //pop한 값과 i가 연결되어 있고 탐색하지 않았으면
				printf("%d ", i); //출력한다.
				q.push(i);
				//i값을 큐에 추가한다. 그래서 i숫자 즉 넓이 우선으로 탐색순서를 결정해 준다.
				BFSvisit[i] = 1;
			}
		}
	}
	return;
}
int main() {
	int n, m, Start; //정점의 개수 N, 간선의 개수 M, 탐색 시작 정점Start
	int i, x, y;
	scanf("%d%d%d", &n, &m, &Start);
	for (i = 0; i<m; i++) { //노드와 노드간의 연결을 배열에 저장.
		scanf("%d%d", &x, &y);
		Graph[x][y] = 1;
		Graph[y][x] = 1;
	}
	DFS(Start, n);
	printf("\n");
	BFS(Start, n);
	printf("\n");
	return 0;
}
