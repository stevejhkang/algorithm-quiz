#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;
//1. ����� �� ���� �� ��ܾ� �Ǵ� �� ��ܾ� ���� �� �ִ�. ���� ������ �Ұ�.
//2. ������ ���� ����� �ݵ�� ��ƾ� �Ѵ�.
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
	//�Է��� ù° �ٿ� ����� ������ �־�����.

	//��° �ٺ��� �� �ٿ� �ϳ��� ���� �Ʒ��� ���� ��ܺ��� ������� �� ��ܿ� ���� �ִ� ������ �־�����.
	//����� ������ 300������ �ڿ����̰�, ��ܿ� ���� �ִ� ������ 10, 000������ �ڿ����̴�.
	//dp(i) = max(dp(i - 2), arr(i - 1) + dp(i - 3)) + arr(i)
	return 0;
}