#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <iostream>
#include <algorithm>
using namespace std;

int map[25][25]; //����
int dangi_size[323]; //�������� ����� ����ϱ� ���� �迭.
//�ִ� ���� ���� n*n/2+1
int cnt; // ���� ���� 
int n; //���� ũ�� ����
int d[4][2] = { { 1,0 },{ -1,0 },{ 0,1 },{ 0,-1 } }; //�̵����

bool isInside(int a, int b) {//ĭ�� �� �Ѿ� ������ üũ
	return (a >= 0 && a < n) && (b >= 0 && b < n);
}

void BFS(int a, int b, int mark) { //��ǥ�� ���� ��ȣ
	queue <pair<int, int>> q;
	q.push(pair<int, int>(a, b)); //��ǥ�� �ְ�
	map[a][b] = mark; 
	dangi_size[map[a][b] - 2]++;
	//���� ��ȣ ��ŷ. �������� ���� 1�̾��µ� ��ȸ�ϸ鼭 ������ȣ �Է�
	//���߿� ���� ���� ������ �����ϴ� �� ���

	while (!q.empty()) {
		pair<int, int> p; //��ǥ�� pair�� �̿��� ����
		int y = p.first = q.front().first;
		int x = p.second = q.front().second;
		q.pop(); //���� �ϳ� ������ ����

		for (int i = 0; i < 4; i++) {
			if (isInside(y + d[i][0], x + d[i][1]) && map[y + d[i][0]][x + d[i][1]] == 1) {
				//������ ����� �ʰ�, ���� ���� ���� ������ �湮�Ѵ�.
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
				//������ 1�� �ֱ� ������ 2�� �Է����ش�.
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