#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<int> vec[100001]; //������踦 ��Ÿ�� ����
queue<int> q; //�湮 ������ ������ ť
int arr[100001]; 
//��Ʈ�� ������ �迭, �湮���ε� ��Ÿ�� �� ����
int n;

int main() {
	cin >> n;
	int a, b;

	for (int i = 0; i < n - 1; i++) {
		scanf("%d %d", &a, &b);
		vec[a].push_back(b);
		vec[b].push_back(a);
	}
	q.push(1); arr[1] = 1;
	while (!q.empty()) {
		int num = q.front(); q.pop();
		for (int i = 0; i < vec[num].size(); i++) {
			if (arr[vec[num][i]] != 0) continue; //�̹� �湮 �����Ƿ� �н�
			if (arr[vec[num][i]] == 0) q.push(vec[num][i]);
			//1�� ����� �ڽĵ��� ��Ʈ�� �ǹǷ� �� ������� ť�� �־��ش�.
			arr[vec[num][i]] = num; //��Ʈ�� �������ش�.

		}
	}
	for (int i = 2; i <= n; i++) {
		printf("%d\n", arr[i]);
		//2���� ������� ���
	}

	return 0;
}