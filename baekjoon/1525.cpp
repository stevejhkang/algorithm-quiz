#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <queue>
#include <map>
#include <string>
using namespace std;

int start = 0; //ó�� ���� ����� ���� int
const int GOAL = 123456789;

map<int, int> visited; //<key,value>�� Ÿ���̰� map�� set�� ���������� �ߺ��� ����.

typedef struct{
	int y, x;
}dir;

dir direction[4] = { {1,0},{-1,0},{0,1},{0,-1} };

int main() {
	
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			int num;
			scanf("%d", &num);
			if (num == 0)
				num = 9;
			start = start * 10 + num; //���� �迭�� ����� ���� �Է¹��� ������ ù�ڸ� ���� �־��ش�.
		}
	}
	queue<int> q; 
	q.push(start);//ó�� ���� �־��ش�.
	visited[start] = 0;

	while (!q.empty()) {
		int cur = q.front();
		string str = to_string(cur);
		//���� ���� string���� �ٲ��ش�. �� ������ int �ٷ궧�� string
		q.pop();

		if (cur == GOAL) //�ϼ��Ǹ� break;
			break;
		int blank = str.find('9'); //����ִ� ĭ�� ã�´�.
		int y = blank / 3; //y�� ��ǥ�� ���̴�.
		int x = blank % 3; //x�� ��ǥ�� �������̴�.

		for (int i = 0; i < 4; i++) {
			int nextY = y + direction[i].y;
			int nextX = x + direction[i].x;

			if (0 <= nextY && 0 <= nextX && nextY < 3 && nextX < 3) {
				string temp = str;
				swap(temp[y * 3 + x], temp[nextY * 3 + nextX]);
				//������ �̵��ϴ� ���̶� �Ȱ��� 1���� �迭�� string�󿡼� ��ġ�� �����ϰ� �����.

				int next = stoi(temp); //next���� int�� �ٲ��ش�.
				if (!visited.count(next)) { //next ���Ұ� ������
					//map���� nextŰ ���� ������ ���Ұ� �ִ��� Ȯ���ϱ� ���� count�� ����Ѵ�.
					visited[next] = visited[cur] + 1;
					q.push(next);
				}
			}
		}
	}
	if (!visited.count(GOAL))
		printf("-1\n");
	else
		printf("%d\n", visited[GOAL]);
	return 0;
}