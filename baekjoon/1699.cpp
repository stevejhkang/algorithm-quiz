#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int main() {
	//(1 ¡Â N ¡Â 100,000)
	int n; scanf("%d", &n);
	int dp[100001];
	
	for (int i = 0; i <= n; i++) dp[i] = i;
	for (int i = 2; i <= n; i++)
		for (int j = 2; j*j <= i; j++)
			dp[i] = min(dp[i], dp[i - j * j] + 1);
	printf("%d\n", dp[n]);

	return 0;
}