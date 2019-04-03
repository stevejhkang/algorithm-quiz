#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int n;
vector<pair<int, int>> vec[100001];
int visited[100001];
int end_point;
int ans;

void dfs(int point, int distance) {
	if (visited[point] == 1) return;
	visited[point] = 1;
	if (ans < distance) {
		ans = distance;
		end_point = point;
	}
	for (int i = 0; i < vec[point].size(); i++) {
		dfs(vec[point][i].first, distance + vec[point][i].second);
	}
}

int main() {
	cin >> n;
	for (int i = 1; i <= n; i++) {
		int a, b, val;
		scanf("%d", &a);
		while (1) {
			scanf("%d", &b);
			if (b == -1) break;
			scanf("%d", &val);
			vec[a].push_back(pair<int, int>(b, val));
		}
	}

	dfs(1, 0);
	ans = 0; memset(visited, 0, sizeof(visited));
	dfs(end_point, 0);

	printf("%d\n", ans);
	return 0;
}