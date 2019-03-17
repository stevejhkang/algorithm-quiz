#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> G[20001];
int color[20001]; //1번 빨강, 2번 파랑, 0: 방문 안함

void DFS(int node, int c) {
	color[node] = c; //c색깔로 칠한다.
	for (int i = 0; i < G[node].size(); i++) { //node번과 연결된 모든 노드 방문
		int next = G[node][i]; //next: node와 i번째 연결된 노드 
		if (color[next] == 0) { //연결이 안되어 있으면 다른 번호를 매긴다.
			DFS(next, 3 - c); //다른번호를 매기면서 DFS한다.
			//빨강 다음에는 파랑, 파랑 다음은 빨강이어야 한다.
		}
	}
}

int main() {
	int t; scanf("%d", &t);
	while (t--) {
		int n, m;
		scanf("%d %d", &n, &m); //노드와 엣지 개수 입력
		for (int i = 1; i <= n; i++) {
			G[i].clear(); //그래프 초기화 
			color[i] = 0; //색칠 초기화
		}
		for (int i = 0; i < m; i++) {
			int u, v; //몇번과 몇번 노드가 연결되어 있는지 입력
			scanf("%d %d", &u, &v);
			G[u].push_back(v);
			G[v].push_back(u);
		}
		for (int i = 1; i <= n; i++) { //1번부터 방문을 한다.
			if (color[i] == 0) {  //이미 방문했으면 skip하고 안 한곳만 방문한다.
				DFS(i, 1); //연결 안된거랑 연결된거는 독립이므로 
				//그냥 1번으로 색칠해도 영향력 없다.
			}
		}
		bool isBin = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < G[i].size(); j++) {
				int k = G[i][j];//i와 연결된 모든 것들이 색깔이 달라야한다.
				if (color[i] == color[k]) isBin = false;
			}
		}
		if (isBin) printf("YES\n");
		else printf("NO\n");
	}
	return 0;
}