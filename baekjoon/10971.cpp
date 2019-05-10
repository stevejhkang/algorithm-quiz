#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>
using namespace std;

vector<pair<int, int>> w[11];
int min_val = 987654321;
int n;
int visit[11];

void dfs(int start, int next, int sum, int cnt) {
	if (cnt == n && start == next) {
		//��� ���ø� �鸮�� �̹� �鸮�� ���� ���� ����Ʈ�϶�
		if (min_val > sum) min_val = sum;
		//��κ���� �ּ� ��ΰ����� ������ ������Ʈ
		return;
	}
	for (int i = 0; i < w[next].size(); i++) {
		if (!visit[next] && w[next][i].second > 0) {
			//�湮���� �ʾҰ� �������� 0�� �ƴϸ�
			visit[next] = true; // ���縦 �湮�ߴ� ǥ���ϰ�
			sum += w[next][i].second; 
			//���� ���� ���� �������� �����ְ�
			if (sum <= min_val) { 
				//������ ���� ������� ���� �ּڰ����� ���� ��쿡�� dfs�� �����ϰ� ���ش�.
				dfs(start, w[next][i].first, sum, cnt + 1);
			}
			//dfs�� ���������� �̹� �ּڰ��� ���� �����Ƿ� �ٽ� �ʱ�ȭ ���ش�.
			//dfs�� �ָ��� ���ε�... �̹� �� ������ ����Ǵϱ�
			visit[next] = false;
			sum -= w[next][i].second;
		}
	}
}

int main() {
	scanf("%d", &n); //������ �Է�

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int distance;
			scanf("%d", &distance); //�Ÿ� �Է�
			if (distance != 0) w[i].push_back(make_pair(j, distance));
			//�Ÿ��� 0�� �ƴҶ��� �Ÿ��� �������� ���Ϳ� ����
		}
	}
	for (int i = 0; i < n; i++) {
		dfs(i, i, 0, 0);
	}
	printf("%d\n", min_val);
	return 0;
}