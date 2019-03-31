#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <iostream>
#include <algorithm>
using namespace std;

int map[25][25]; //지도
int dangi_size[323]; //단지들의 사이즈를 출력하기 위한 배열.
//최대 단지 수는 n*n/2+1
int cnt; // 단지 개수 
int n; //지도 크기 결정
int d[4][2] = { { 1,0 },{ -1,0 },{ 0,1 },{ 0,-1 } }; //이동방법

bool isInside(int a, int b) {//칸을 안 넘어 가는지 체크
	return (a >= 0 && a < n) && (b >= 0 && b < n);
}

void BFS(int a, int b, int mark) { //좌표와 단지 번호
	queue <pair<int, int>> q;
	q.push(pair<int, int>(a, b)); //좌표를 넣고
	map[a][b] = mark; 
	dangi_size[map[a][b] - 2]++;
	//단지 번호 마킹. 기존에는 전부 1이었는데 조회하면서 단지번호 입력
	//나중에 같은 단지 개수를 측정하는 데 사용

	while (!q.empty()) {
		pair<int, int> p; //좌표를 pair를 이용해 저장
		int y = p.first = q.front().first;
		int x = p.second = q.front().second;
		q.pop(); //원소 하나 꺼내는 과정

		for (int i = 0; i < 4; i++) {
			if (isInside(y + d[i][0], x + d[i][1]) && map[y + d[i][0]][x + d[i][1]] == 1) {
				//범위를 벗어나지 않고, 다음 곳이 집이 있으면 방문한다.
				BFS(y + d[i][0], x + d[i][1], mark);
			}
		}
	}
}
int main() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%1d", &map[i][j]);
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == 1) {
				BFS(i, j, cnt + 2); 
				//기존에 1이 있기 때문에 2로 입력해준다.
				cnt++;
			}
		}
	}
	sort(dangi_size, dangi_size + cnt);
	printf("%d\n", cnt);
	for (int i = 0; i < cnt; i++) {
		printf("%d\n", dangi_size[i]);
	}
	return 0;
}