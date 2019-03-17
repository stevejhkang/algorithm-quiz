#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
using namespace std;
int Graph[1001][1001];
int DFSvisit[1001];
int BFSvisit[1001];
queue<int> q;
void DFS(int v, int N) { //v�������� n���� �湮���� ���
	int i;
	DFSvisit[v] = 1;//ù �湮
	printf("%d ", v);
	for (i = 1; i <= N; i++) {
		if (Graph[v][i] == 1 && DFSvisit[i] == 0) { //i�� v�� ����Ǿ� �ְ� �湮�� ���� �ʾ�����
			DFS(i, N); //�� ���� Ž���Ѵ�.
		}
	}
	return;
}
void BFS(int v, int N) {
	int Pop, i;
	printf("%d ", v); //ù�湮
	q.push(v);//bfs�� ���� queue
	BFSvisit[v] = 1; //vŽ�� ���� ǥ��

	while (!q.empty()) {
		Pop = q.front();//���Ҹ� ���� �� pop�� ����
		q.pop();
		for (i = 1; i <= N; i++) {
			if (Graph[Pop][i] == 1 && BFSvisit[i] == 0) { //pop�� ���� i�� ����Ǿ� �ְ� Ž������ �ʾ�����
				printf("%d ", i); //����Ѵ�.
				q.push(i);
				//i���� ť�� �߰��Ѵ�. �׷��� i���� �� ���� �켱���� Ž�������� ������ �ش�.
				BFSvisit[i] = 1;
			}
		}
	}
	return;
}
int main() {
	int n, m, Start; //������ ���� N, ������ ���� M, Ž�� ���� ����Start
	int i, x, y;
	scanf("%d%d%d", &n, &m, &Start);
	for (i = 0; i<m; i++) { //���� ��尣�� ������ �迭�� ����.
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
