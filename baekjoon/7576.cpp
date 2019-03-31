#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <queue> //BFS
using namespace std;

int m, n; //가로, 세로
int moving[4][2] = { {-1,0},{1,0},{0,1},{0,-1} }; //이동
int map[1001][1001]; 
int day, cnt; //날짜 계산, 전부 1 계산
int max1, min1;
queue<pair<int, int> > q;

bool IsInside(int y, int x) { //범위 계산
	return (0 <= y && y < n) && (0 <= x && x < m);
}

int main() {
	scanf("%d %d", &m, &n);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == 1) {
				cnt++;
				q.push(pair<int, int>(i, j));
			}
		}
	}
	if (cnt == n * m) { //모두 1이면
		printf("0\n");
		return 0;
	}

	while (!q.empty()) {
		int b = q.front().first; int a = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nextY = b + moving[i][0]; int nextX = a + moving[i][1];
			if (IsInside(nextY, nextX) && map[nextY][nextX] == 0) {
				map[nextY][nextX] = map[b][a] + 1;
				q.push(pair<int, int>(nextY, nextX));
			}
		}
	}
	int mx = -100;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (!map[i][j]) return !printf("-1\n");;
			mx = max(mx, map[i][j]);
		}
	}

	printf("%d\n", mx - 1);
	return 0;
}