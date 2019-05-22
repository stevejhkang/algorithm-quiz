#include <string>
#include <vector>
#include <iostream>
#include <stack>
using namespace std;

typedef struct {
	int y;
	int x;
} moval;
moval moveDir[2] = { {0,1},{1,0} };

//������ ���� ���: [m-1][n-1]���� ���� ��츦 dfs�� �ϸ� �� ��?
//�ִ� ����̹Ƿ� �Ʒ��� ������
int solution(int m, int n, vector<vector<int>> puddles) { //�Ű������� �־���
	int answer = 0;
	//cout << puddles[0][0]<<endl;
	//cout << puddles[0][1] << endl;
	stack <pair<int, int> > stk;
	stk.push({ 1,1 });

	while (!stk.empty()) {
		int y = stk.top().second; int x = stk.top().first; //first�� x, second�� y
		stk.pop();
		if (y == n && x == m) { //���̸� �߰�
			answer += 1;
			answer = answer % 1000000007;
			continue;
		}
		for (int i = 0; i <= 1; i++) {
			bool cann = true;
			int nextY = y + moveDir[i].y; int nextX = x + moveDir[i].x;
			for (int j = 0; j < puddles.size(); j++) {
				if (nextY == puddles[j][1] && nextX == puddles[j][0]) {
					cann = false;
					break;
				}
			}
			if (cann&&nextY <= n && nextX <= m)
				stk.push({ nextX,nextY });
		}
	}
	return answer % 1000000007;
}