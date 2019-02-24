#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int main() {

	int n; scanf("%d", &n);

	int arr[100001];
	for (int i = 1; i <= n; i++) {
		scanf("%d", &arr[i]);
	}

	int dp[100001];
	for (int i = 1; i <= n; i++) {
		if (i == 1) dp[i] = arr[i];
		//연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합
		//10 -4 3 1 5 6 -35 12 21 -1
		else if (dp[i - 1] > 0) dp[i] = dp[i - 1] + arr[i];
		else dp[i] = arr[i];
	}
	int max = -1000000;
	for (int i = 1; i <= n; i++) {
		if (max < dp[i])
			max = dp[i];
	}
	printf("%d\n", max);
	return 0;
}
