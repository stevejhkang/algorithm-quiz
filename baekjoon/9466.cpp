#include <iostream>
#include <cstring>
#include <vector>
using namespace std;

int vertex[100001];
bool isVisited[100001], isFinished[100001];
int t, n, flag, cnt;

void dfs(int curr_v) {
	isVisited[curr_v] = true;
	int next_v = vertex[curr_v];
	if (!isVisited[next_v]) {
		dfs(next_v);
	}
	else {
		if (!isFinished[next_v]) {
			for (int x = next_v; curr_v != x; x = vertex[x]) {
				cnt++;
			}
			cnt++;
		}
	}
	isFinished[curr_v] = true;
}

int main(int argc, const char * argv[]) {
	cin >> t;
	for (int tc = 0; tc<t; tc++) {
		cin >> n;

		cnt = 0;
		memset(vertex, 0, sizeof(vertex));
		memset(isVisited, 0, sizeof(isVisited));
		memset(isFinished, 0, sizeof(isFinished));

		for (int i = 1; i <= n; i++) {
			cin >> vertex[i];
		}

		for (int i = 1; i <= n; i++) {
			if (!isVisited[i]) {
				dfs(i);
			}
		}

		cout << n - cnt << "\n";
	}
	return 0;
}


