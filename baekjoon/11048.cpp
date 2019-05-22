#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <stack>
#include <cstring>
using namespace std;

int n, m;
vector<vector<int> > v(1001,vector<int>(1001,0));
int dp[1001][1001][3];

//���������� ���Ƿ� dp�� ������ ��찡 �����Ƿ� �̰��� 3�߹迭���ٰ� �����ؼ� ���� �ִ��� ���Ž�Ų��.
int main() { //[y][x]
	scanf("%d %d\n", &n, &m);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> v[i][j];
		}
	}
	dp[0][0][0]=dp[0][0][1]=dp[0][0][2]=v[0][0];
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (i == 0) //ù��° ��
				dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = dp[i][j - 1][0] + v[i][j];
			else if (j == 0) //ù��° ��
				dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = dp[i - 1][j][0] + v[i][j];
			else {
				dp[i][j][0] = max(dp[i - 1][j][0], max(dp[i - 1][j][1], dp[i - 1][j][2]))+v[i][j];// ������ �Ʒ���
				dp[i][j][1] = max(dp[i][j - 1][0], max(dp[i][j - 1][1], dp[i][j - 1][2]))+v[i][j];// ���ʿ��� ����������
				dp[i][j][2] = max(dp[i - 1][j - 1][0], max(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]))+v[i][j]; //�밢�� ������ �Ʒ���
			}
		}
	}
	printf("%d\n", max(dp[n - 1][m - 1][0], max(dp[n - 1][m - 1][1], dp[n - 1][m - 1][2])));

	return 0;
}