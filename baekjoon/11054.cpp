#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int main() {

	int n; scanf("%d", &n);
	int arr[1001];
	for (int i = 1; i <= n; i++) {
		scanf("%d", &arr[i]);
	}
	int dp[2][1001] = { 0, };
	int sum[1001];
	dp[0][1] = 1; dp[1][n] = 1;
	for (int i = 2; i <= n; i++) {
		for (int j = i-1; j >= 1; j--) {
			if(arr[j]<arr[i]) //현재 값보다 작은 값이고
				if (dp[0][j] > dp[0][i]) dp[0][i] = dp[0][j]; //dp가 현재 값보다 크다면
		}
		dp[0][i]++;
	}
	for (int i = n-1; i >= 1; i--) { //거꾸로 가
		for (int k=i+1; k <= n; k++) {
			if (arr[i] > arr[k])
				if (dp[1][i] < dp[1][k]) dp[1][i] = dp[1][k];
		}
		dp[1][i]++;
		sum[i] = dp[0][i] + dp[1][i]; 
	}
	for (int i = 1; i <= n; i++) {
		sum[i] = dp[0][i] + dp[1][i];
	}

	printf("%d\n", *max_element(sum + 1, sum + n + 1)-1);

	return 0;
}
//1 2 4 3 7 6 8 4 2 3 1