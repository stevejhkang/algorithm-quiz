#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int main() {

	int N;
	scanf("%d", &N);
	int wine[10001];
	int Dp[10001]; //n��° ���� �Ծ��� �� �ִ� ��
	
	for (int i = 1; i <= N; i++)
		scanf("%d", &wine[i]);

	for (int i = 1; i < 3 && i <= N; i++) {
		if (i == 1)
			Dp[i] = wine[i]; //ù ��° �� ���Ƕ��� �ִ�
		else
			Dp[i] = wine[i] + wine[i - 1]; //ù ��° �� ��° ��� ���Ƕ��� �ִ�
	}

	for (int i = 3; i <= N; i++) { 
		//�� ��°���ʹ� i-1,i-2,i-3��° ���� �Ծ��� ���� �ִ븦 ������ְ� �̸� �̿��� i��°�� �Ǵ��� �� �ֵ��� �Ѵ�.
		int result = 0;
		result = max(Dp[i - 1], wine[i] + Dp[i - 2]);
		result = max(result, wine[i] + wine[i - 1] + Dp[i - 3]);
		//i��° ������ ���� �ʴ� ��� 
		//i��° ������ �԰� i-1�� ������ �ʰ� i-2�� ���Ű��
		//i��° ������ �԰� i-1�� ���� ��� i-3�� dp�� �����־�� �Ѵ�.
		Dp[i] = result;
	}

	printf("%d\n", Dp[N]);

	return 0;
}


