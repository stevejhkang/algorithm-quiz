#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
using namespace std;
int n, m;
int arr[1001][1001];
int visit[1001];
int cnt;

void BFS(int start) {
	if (visit[start] != 0) { //방문 했었으면 그냥 빠져나온다.
		return;
	}
	visit[start] = 1; //방문 표시
	for (int i = 1; i <= n; i++) {
		if (arr[start][i] == 1) BFS(i); //연결되어 있으면 들어간다.
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
			cnt++;  //여기에서 BFS가 몇번 호출 되었는지가 연결요소의 개수와 같다.
			BFS(i); //연결되어 있으면 쭉 BFS로 탐색하여 visit을 1로 만들어 준다.
		}
	}
	printf("%d\n", cnt);
	
	return 0;
}