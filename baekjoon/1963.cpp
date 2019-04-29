#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <vector>
#include <string>
#include <cstring>
#include <iostream>
using namespace std;

int a[10001];
int min_change = 987654321;
string befo, aft;
int visit[10001];

void bfs(int time) {
	queue<pair<string,int>> q;
	q.push(make_pair(befo,time));

	while (!q.empty()) {
		string cur = q.front().first; int move = q.front().second;
		q.pop();
		//cout<<move<<" " << cur << " " << aft <<  "\n";
		if (cur == aft && min_change > move) {
			//만약 현재 위치가 최종 위치이고 이전 값보다 작으면 갱신하고 종료
			//printf("last!\n");
			min_change = move;
			return;
		}
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i <= 9; i++) {// 이동하려는 수가 소수이고 방문을 안 했었으면
				if (j == 0&&i==0) continue;
				string next_num = cur;
				if ((int)next_num[j] - '0' == i) continue; //현재이면 취소
				next_num[j] = i + '0'; //자리값 변경
				int next_int = stoi(next_num);
				if (a[next_int] != 0 && visit[next_int] == 0 && min_change >= move + 1) {
					visit[next_int] = 1;
					q.push(make_pair(next_num, move + 1));
				}
				//소수이고 방문 안했고 최소변화값보다 작으면 큐에 넣어준다.
			}
		}

	}
}

int main() {
	for (int i = 2; i < 10000; i++) {
		a[i] = i;
	}

	for (int i = 2; i < 10000; i++) {
		if (a[i] == 0) continue;
		for (int j = i + i; j < 10000; j += i) {
			a[j] = 0;
		}
	}
	/*for (int i = 1000; i < 10000; i++) {
		printf("%4d ", a[i]);
		if (i % 20 == 0) printf("\n");
	}*/
	int n; scanf("%d", &n);
	while (n--) {
		cin >> befo >> aft;
		bfs(0);
		if (min_change == 987654321) {
			printf("0\n");
			return 0;
		}
		printf("%d\n", min_change);
		min_change = 987654321;
		memset(visit, 0, sizeof(visit));
	}
	/*
	for (int i = 1000; i < 10000; i++) {
		printf("%4d ", a[i]);
		if (i % 20 == 0) printf("\n");
	}*/
	return 0;
}
