#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

//fn=n/i * i값 + f(n%i) 중 최대 
//1에서부터 2, 3, 4, 5를 완성시켜야 n을 만들 수 있다.
int arr[1001];

int main() {
	int n;
	scanf("%d", &n);
	long long card[1001] = { 0 };
	for (int i = 1; i <= n; i++) {
		scanf("%d", &arr[i]);
	}
	card[1] = arr[1];
	card[2] = max(2 * arr[1], arr[2]);
	for (int i = 3; i <= n; i++) {
		for (int j = 1; j <= i; j++) {
			card[i] = max(card[i], card[i - j] + arr[j]);
			//printf("현재 card[%d]값은 %d\n", i, card[i]);
		}
	}
	printf("%d\n", card[n]);
	return 0;
}