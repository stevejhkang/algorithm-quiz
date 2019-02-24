#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
using namespace std;

int dp[1001][10];

int main() {
	int n;
	scanf("%d", &n);
	int sum = 0;
	for (int i = 0; i < 10; i++) {
		dp[1][i] = 1;
		
	}
	
	for (int i = 2; i <=n; i++) {
		for (int j = 0; j < 10; j++) {
			//dp[j][i]=dp[j-1][i보다 작은 것들 전부 더한다.]
			for (int k = 0; k <= j; k++) {
				dp[i][j] += dp[i - 1][k];
			}
			if (i == n) sum += dp[n][j];
		}
	}
	if (n == 1) printf("%d\n", 10);
	else printf("%d\n", sum%10007);
	return 0;
}