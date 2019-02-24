#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int main() {

	int N;
	scanf("%d", &N);
	int wine[10001];
	int Dp[10001]; //n번째 잔을 먹었을 때 최대 값
	
	for (int i = 1; i <= N; i++)
		scanf("%d", &wine[i]);

	for (int i = 1; i < 3 && i <= N; i++) {
		if (i == 1)
			Dp[i] = wine[i]; //첫 번째 잔 마실때가 최대
		else
			Dp[i] = wine[i] + wine[i - 1]; //첫 번째 두 번째 모두 마실때가 최대
	}

	for (int i = 3; i <= N; i++) { 
		//세 번째부터는 i-1,i-2,i-3번째 잔을 먹었을 때를 최대를 계산해주고 이를 이용해 i번째를 판단할 수 있도록 한다.
		int result = 0;
		result = max(Dp[i - 1], wine[i] + Dp[i - 2]);
		result = max(result, wine[i] + wine[i - 1] + Dp[i - 3]);
		//i번째 와인을 먹지 않는 경우 
		//i번째 와인을 먹고 i-1을 마시지 않고 i-2를 마신경우
		//i번째 와인을 먹고 i-1을 마신 경우 i-3의 dp를 더해주어야 한다.
		Dp[i] = result;
	}

	printf("%d\n", Dp[N]);

	return 0;
}


