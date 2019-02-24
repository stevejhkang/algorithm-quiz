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
	int dp[1001] = { 0, };
	dp[1] = 1;
	for (int i = 2; i <= n; i++) {
		for (int j = i - 1; j >= 1; j--) {
			if (arr[j] > arr[i]) { //arr[j]�� ���� ������ ���� ���� ã�µ�
				if (dp[j] > dp[i]) dp[i] = dp[j]; //�� �߿��� dp�� ���� ū ���� ã���־�� �Ѵ�.
			}
		}
		dp[i] = dp[i] + 1;//���������� ���� �� �߰����ش�.
	}
	printf("%d\n", *max_element(dp + 1, dp + n + 1));

	return 0;
}