#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
using namespace std;
int n, m;
int arr[1001][1001];
int visit[1001];
int cnt;

void BFS(int start) {
	if (visit[start] != 0) { //�湮 �߾����� �׳� �������´�.
		return;
	}
	visit[start] = 1; //�湮 ǥ��
	for (int i = 1; i <= n; i++) {
		if (arr[start][i] == 1) BFS(i); //����Ǿ� ������ ����.
	}
}
int main() {
	//freopen("Text.txt", "r", stdin);
	scanf("%d %d", &n, &m);
	int x=0; int y=0;
	for (int i = 0; i < m; i++) {
		scanf("%d %d", &x, &y);
		arr[x][y] = 1; arr[y][x] = 1;
	}
	for (int i = 1; i <= n; i++) {
		if (visit[i] == 0) {
			cnt++;  //���⿡�� BFS�� ��� ȣ�� �Ǿ������� �������� ������ ����.
			BFS(i); //����Ǿ� ������ �� BFS�� Ž���Ͽ� visit�� 1�� ����� �ش�.
		}
	}
	printf("%d\n", cnt);
	
	return 0;
}