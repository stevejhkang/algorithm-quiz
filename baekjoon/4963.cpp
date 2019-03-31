#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstring>
#include <queue>
#include <algorithm>
using namespace std;

int w, h;//너비, 높이
int map[51][51];
int moving[8][2] = { {-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1} };
int cnt; //섬의 개수

bool IsInside(int y, int x) {
	return (0 <= y && y < h) && (0 <= x && x < w);
}
void BFS(int y, int x) {
	queue<pair<int, int>>q;
	q.push(pair<int, int>(y, x)); //BFS를 위해 큐에 넣어준다.
	map[y][x] = 2; //방문 표시로 2를 넣어준다.
	while (!q.empty()) {
		int b = q.front().first; //y값 받아온다.
		int a = q.front().second; //x값 받아온다.
		q.pop();
		for (int i = 0; i < 8; i++) {
			int nextY = b + moving[i][0];    
			int nextX = a + moving[i][1];
			if (IsInside(nextY, nextX) && map[nextY][nextX] == 1) {
			//범위 안에 있고 방문한 적이 없는 방문가능 지역이면 방문
				BFS(nextY, nextX);
			}
				
		}
	}

}

int main() {
	while (1) { 
		scanf("%d %d", &w, &h);
		if (w == 0 && h == 0) return 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				scanf("%d", &map[i][j]);
			}
		}
		//BFS
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 1)
				{
					BFS(i, j);
					cnt++;
				}
			}
		}
		//섬의 개수 출력
		printf("%d\n", cnt);
		cnt = 0;
		for (int i = 0; i < h; i++) {
			memset(map[i],0,sizeof(int)*w);
		}
		//섬개수, 배열초기화
	}
	return 0;
}