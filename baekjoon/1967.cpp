#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include<cstring>
#include <vector>
#include <queue>
using namespace std;

int n, r, endp;
int visited[10001];
vector<pair<int, int>> vec[10001]; //����� ���(first)�� �Ÿ�(second)�� ����.
int ans, end_point; //���� ����, ������ �ش��ϴ� ����

//dfs: stack or ���
//��Ʈ���� ������ �� ���� ��� ���̸� �Ѱ��ָ鼭 ����Ǿ� �ִ� �ſ� ���� 
//ȣ���� ���ش�. �ٵ� ���� �����Ų �ͺ��� ȣ��ǹǷ�
//ȣ��� �͵� ��� ȣ���ϱ� ������ dfs�� �ȴ�.
void dfs(int point, int length) {
	if (visited[point]) return; //�̹� �湮
	visited[point] = 1;
	if (ans < length) {
		ans = length; //���� ���̰� �ִ� ���̺��� ��� ���� ���� �����ϰ�
		end_point = point; //���� �� ���� ���� ����
	}
	for (int i = 0; i < vec[point].size(); i++) {
		dfs(vec[point][i].first, length+vec[point][i].second);
		//���̰��� ����� ���� ���� ���ؼ� �Ѱ��ش�.
	}
}

int main() {
	cin >> n;
	for (int i = 1; i < n; i++) { //2��°�ٺ��� n��°�ٱ���
		int a, b, c; scanf("%d %d %d", &a, &b, &c);
		vec[a].push_back(pair<int, int>(b, c));
		vec[b].push_back(pair<int, int>(a, c));
	}
	dfs(1, 0); //dfs�� ���� ��Ʈ(1)���� ���� �� end_point�� ã�´�.
	ans = 0;
	memset(visited, 0, sizeof(visited));

	dfs(end_point, 0); 
	//end_point���� ���� �� �Ǵٸ� ����Ʈ�� �� ���̸� ã�´�.
	cout << ans << endl;


	return 0;
}