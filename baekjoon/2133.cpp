#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int main() {

	int n;  scanf("%d", &n);
	int dp[31] = { 0, };
	int result[31] = { 0, };

	result[0] = 1; dp[1] = 0; result[1] = 0; dp[2] = 3; result[2] = 3;
	for (int i = 3; i <= 30; i++)
	{
		if (i % 2 == 0) dp[i] = 2;
		else result[i] = 0;
	}

	for (int i = 4; i <= n; i+=2) {
		for (int j = 2; j <= i; j+=2) {
			result[i] += dp[j] * result[i - j]; 
			//result(n)�� n���� �� ���� ���� �� �ִ� �ִ� ���� ��
			//dp(n)�� 2*4=n 4*2=n���� ����°� �ƴ϶� �װ��� �����ϰ� n�ٷθ� ���� �� �ִ� ������
			//6�̸� dp[2]*result[4] + dp[4]*result[2] + dp[6]*result[0]��
		}
	}

	printf("%d\n", result[n]);
	return 0;
}