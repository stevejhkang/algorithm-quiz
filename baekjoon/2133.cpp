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
			//result(n)은 n개의 블럭 줄을 만들 수 있는 최대 가지 수
			//dp(n)은 2*4=n 4*2=n으로 만드는게 아니라 그것을 제외하고 n줄로만 만들 수 있는 가지수
			//6이면 dp[2]*result[4] + dp[4]*result[2] + dp[6]*result[0]임
		}
	}

	printf("%d\n", result[n]);
	return 0;
}