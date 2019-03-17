#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int main() {

	int n, k; scanf("%d %d", &n, &k);
	long dp[201][201] = { 0, };

	for (int i = 0; i <= n; i++) {
		dp[1][i] = 1;
	}
	for (int i = 2; i <= k; i++) {
		for (int j = 0; j <= n; j++) {
			for (int l = 0; l <= n; l++) {
				dp[i][j] += dp[i - 1][l] ;
			}
			dp[i][j] = dp[i][j] % 1000000000;
		}
		
	}
	printf("%d\n", dp[k][n]);
	return 0;
}