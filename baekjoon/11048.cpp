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

//세방향으로 오므로 dp가 세가지 경우가 있으므로 이것을 3중배열에다가 저장해서 다음 최댓값을 갱신시킨다.
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
			if (i == 0) //첫번째 행
				dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = dp[i][j - 1][0] + v[i][j];
			else if (j == 0) //첫번째 열
				dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = dp[i - 1][j][0] + v[i][j];
			else {
				dp[i][j][0] = max(dp[i - 1][j][0], max(dp[i - 1][j][1], dp[i - 1][j][2]))+v[i][j];// 위에서 아래로
				dp[i][j][1] = max(dp[i][j - 1][0], max(dp[i][j - 1][1], dp[i][j - 1][2]))+v[i][j];// 왼쪽에서 오른쪽으로
				dp[i][j][2] = max(dp[i - 1][j - 1][0], max(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]))+v[i][j]; //대각선 위에서 아래로
			}
		}
	}
	printf("%d\n", max(dp[n - 1][m - 1][0], max(dp[n - 1][m - 1][1], dp[n - 1][m - 1][2])));

	return 0;
}