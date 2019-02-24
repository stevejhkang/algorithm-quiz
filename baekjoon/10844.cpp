#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
//10844.쉬운계단수: 수의 길이가 N인 계단 수가 몇 개 있는 지를 알아내기

int N;
long Dp[101][10] = {0};
int sum = 0;
#define mod 1000000000


int main()
{
	scanf("%d", &N);
	for (int i = 0; i < 10; i++)
	{
		Dp[1][i] = 1;
	}
	for (int i = 2; i <= N; i++)
	{
		Dp[i][0] = Dp[i - 1][1] % mod;
		for (int j = 1; j < 10; j++)
		{
			if (j == 9) Dp[i][9] = Dp[i - 1][8] % mod;
			else Dp[i][j] = (Dp[i - 1][j - 1] + Dp[i - 1][j + 1]) % mod;
		}
	}
	for (int i = 1; i < 10; i++)
		sum = (sum + Dp[N][i]) % mod;
	printf("%d\n", sum%mod);
	return 0;
}


