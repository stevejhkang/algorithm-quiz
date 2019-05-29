#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <stack>
#include <cstring>
using namespace std;

int cost[101];
int dp[10001];
int n, k;

//�����̳� ����ä��� ���� �� ���� �͵�� ������� ��츦 �̿��� ���� ������ ���� �� �ִ� ������ ������.
//�� ���� �������� ���ϰ� ������ ū �Ϳ� ���� ��츦 �߰����ִ� ������� �����Ѵ�.
int main() {
	scanf("%d %d\n", &n, &k);
	for (int i = 1; i <= n; i++)
		scanf("%d", cost + i);

	dp[0] = 1;

	for (int i = 1; i <= n; i++) {
		for (int j = cost[i]; j <= k; j++) {
			dp[j] += dp[j - cost[i]];
		}
	}
	printf("%d\n", dp[k]);

	return 0;
}