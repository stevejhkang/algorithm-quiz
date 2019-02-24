#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;
//1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 세개 연속은 불가.
//2. 마지막 도착 계단은 반드시 밟아야 한다.
int main() {

	int n; scanf("%d", &n);

	int arr[301];
	for (int i = 1; i <= n; i++) {
		scanf("%d", &arr[i]);
	}
	int dp[301];

	for (int i = 0; i <= n; i++) {
		if (i == 0)dp[i] = 0;
		else if (i == 1)dp[i] = arr[i];
		else if (i == 2)dp[i] = arr[i] + arr[i - 1];
		else dp[i] = max(dp[i - 2], arr[i - 1] + dp[i - 3]) + arr[i];
	}
	printf("%d\n", dp[n]);
	//입력의 첫째 줄에 계단의 개수가 주어진다.

	//둘째 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다.
	//계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10, 000이하의 자연수이다.
	//dp(i) = max(dp(i - 2), arr(i - 1) + dp(i - 3)) + arr(i)
	return 0;
}