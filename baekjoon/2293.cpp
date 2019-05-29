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

//동전이나 가방채우기 같은 건 이전 것들로 만들었던 경우를 이용해 현재 것으로 만들 수 있는 구성을 만들어낸다.
//즉 작은 단위부터 구하고 점점더 큰 것에 대한 경우를 추가해주는 방식으로 진행한다.
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