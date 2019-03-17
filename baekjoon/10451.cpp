#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;
vector<int> v[1001];
int visit[1001];
int t, n;
void DFS(int node) {
	if (visit[node] == 1) return;
	visit[node] = 1;
	int next = v[node][0];
	DFS(next);
}
int main() {
	scanf("%d", &t);
	while (t--) {
		int cnt = 0;
		scanf("%d",&n);
		for (int i = 1; i <= n; i++) {
			int m; scanf("%d", &m);
			v[i].push_back(m);
		}
		for (int i = 1; i <= n; i++) {
			if (visit[i] == 1) continue;
			DFS(i);
			cnt++;
		}
		printf("%d\n", cnt);
		for (int i = 1; i <= n; i++) {
			v[i].clear();
			visit[i] = 0;
		}
	}


	return 0;
}