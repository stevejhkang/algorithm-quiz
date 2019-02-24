#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int N;
int arr[1000001];
#define min(X,Y) ((X) < (Y) ? (X) : (Y))  
#define max(X,Y) ((X) > (Y) ? (X) : (Y))


int DP(int n)
{
	for (int i = 2; i <= n; i++)
	{
		arr[i] = arr[i - 1] + 1;
		if (i % 2 == 0) {
			arr[i] = min(arr[i], arr[i / 2] + 1);
		}
		if (i % 3 == 0)
		{
			arr[i] = min(arr[i], arr[i / 3] + 1);
		}
	}
	return arr[n];
}
int main()
{
	arr[1] = 0;
	scanf("%d", &N);
	printf("%d\n", DP(N));
	return 0;
}