#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <vector>
#include <string>
#include <iostream>
using namespace std;

int n; long long v[101];
long long dp[21][101]; //�� ����
//+�Ǵ� ��� ���� -�Ǵ� ��� ���� �μ��� ������༭ ������ ������ ���༭
//�̰��� ���� �ܰ��� ����� ������ �ǰ� �� ���� ������ �ش�.

int main() {
	scanf("%lld", &n);
	for(int i=0;i<n;i++)
		scanf("%lld", &v[i]);
	dp[v[0]][0] = 1;
	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j <= 20; j++) {
			if (dp[j][i] != 0) {
				long long plus = j + v[i+1]; long long minus = j - v[i+1];
				if (plus <= 20) {
					dp[plus][i + 1]+=dp[j][i];
				}
				if (minus >= 0) {
					dp[minus][i + 1]+=dp[j][i];
				}
			}
		}
	}
	/*for (int j = 0; j <= 20; j++) {
		for (int i = 0; i < n - 1; i++) {
			printf("%lld ", dp[j][i]);
		}
		printf("\n");
	}*/
	printf("%lld\n", dp[v[n - 1]][n - 2]);

	return 0;
}