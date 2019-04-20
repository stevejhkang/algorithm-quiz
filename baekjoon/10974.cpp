#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int a[8];

int main() {
	int n;
	scanf("%d", &n);
	for (int i = 1; i <= n; i++) {
		a[i-1] = i;
	}
	for (int i = 0; i < n; i++) {
		printf("%d ", a[i]);
	}
	printf("\n");
	while (1) {
		int i = n - 1;
		while (i > 0 && a[i - 1] > a[i]) {
			i--;
		}
		if (i == 0) break;
		int idx = 0;
		int min = 10;
		int j = n - 1;
		while (j > i - 1) {
			if (a[i - 1]<a[j]&&min>a[j]) {
				min = a[j];
				idx = j;
			}
			j--;
		}
		int tmp = a[i - 1];
		a[i - 1] = min;
		a[idx] = tmp;
		sort(a + i, a + n);
		for (int k = 0; k < n; k++) {
			printf("%d ", a[k]);
		}
		printf("\n");
		
	}

	return 0;
}
