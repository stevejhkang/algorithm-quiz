#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <stack>
#include <cstring>
using namespace std;

int t, k;
int arr[501];
int minV = 987654321;
int dp[501][501];
int pSum[501];
//dp[i][j]: i��°���� j��° ������ ��ġ�� �� �ʿ��� �ּҺ��
//�츮�� ���ϰ����ϴ� ��. dp[1][4]
/*
������ ����� ��
1) null (40) (30 30 50)
2)     null (40) {(30) (30 50)}
3)     null (40) {(30 30) (50)}
4) null (40 30) (30 50)
5) null (40 30 30) (50)
6)     null {(40) (30 30)} (50)
7)     null {(40 30) (30)} (50)
��ȭ��: dp[i][j]=min(dp[i][k]+dp[k+1][j])+sum[i][j]
*/

int main() {
	scanf("%d\n", &t);
	while (t--)
	{
		int n;
		scanf("%d", &k);

		for (int i = 1; i <= k; i++)
		{
			scanf("%d", &arr[i]);
			pSum[i] = pSum[i - 1] + arr[i]; //�κ����� ���ϱ� ���� pSum
		}

		for (int i = 2; i <= n; i++)
		{
			for (int j = i - 1; j > 0; j--)
			{
				dp[j][i] = 987654321;
				for (int k = j; k <= i; k++)
					//2-1 3-2,1 4-3,2,1 
					dp[j][i] = min(dp[j][i], dp[j][k] + dp[k + 1][i]);

				dp[j][i] += pSum[i] - pSum[j - 1];
			}
		}
		printf("%d\n", dp[1][n]);
	}


	return 0;
}