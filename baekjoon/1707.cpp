#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> G[20001];
int color[20001]; //1�� ����, 2�� �Ķ�, 0: �湮 ����

void DFS(int node, int c) {
	color[node] = c; //c����� ĥ�Ѵ�.
	for (int i = 0; i < G[node].size(); i++) { //node���� ����� ��� ��� �湮
		int next = G[node][i]; //next: node�� i��° ����� ��� 
		if (color[next] == 0) { //������ �ȵǾ� ������ �ٸ� ��ȣ�� �ű��.
			DFS(next, 3 - c); //�ٸ���ȣ�� �ű�鼭 DFS�Ѵ�.
			//���� �������� �Ķ�, �Ķ� ������ �����̾�� �Ѵ�.
		}
	}
}

int main() {
	int t; scanf("%d", &t);
	while (t--) {
		int n, m;
		scanf("%d %d", &n, &m); //���� ���� ���� �Է�
		for (int i = 1; i <= n; i++) {
			G[i].clear(); //�׷��� �ʱ�ȭ 
			color[i] = 0; //��ĥ �ʱ�ȭ
		}
		for (int i = 0; i < m; i++) {
			int u, v; //����� ��� ��尡 ����Ǿ� �ִ��� �Է�
			scanf("%d %d", &u, &v);
			G[u].push_back(v);
			G[v].push_back(u);
		}
		for (int i = 1; i <= n; i++) { //1������ �湮�� �Ѵ�.
			if (color[i] == 0) {  //�̹� �湮������ skip�ϰ� �� �Ѱ��� �湮�Ѵ�.
				DFS(i, 1); //���� �ȵȰŶ� ����ȰŴ� �����̹Ƿ� 
				//�׳� 1������ ��ĥ�ص� ����� ����.
			}
		}
		bool isBin = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < G[i].size(); j++) {
				int k = G[i][j];//i�� ����� ��� �͵��� ������ �޶���Ѵ�.
				if (color[i] == color[k]) isBin = false;
			}
		}
		if (isBin) printf("YES\n");
		else printf("NO\n");
	}
	return 0;
}