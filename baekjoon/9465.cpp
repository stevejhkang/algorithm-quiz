#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

char point[2][100001];
int maxp[2][100001];

int main() {

	int n; scanf("%d", &n);
	while (n--) {
		int m; scanf("%d", &m);
		char point[2][100001] = { 0, };
		for (int j = 0; j < 2; j++) {
			for (int i = 1; i <= m; i++) {
				scanf("%d", &point[j][i]);
			}
		}

		for (int i = 1; i <= m; i++) {
			if (i == 1) {
				maxp[0][i] = point[0][i];
				maxp[1][i] = point[1][i];
			}
			maxp[0][i] = max(maxp[1][i - 1], maxp[1][i - 2]) + point[0][i];
			maxp[1][i] = max(maxp[0][i - 1], maxp[0][i - 2]) + point[1][i];
		}
		printf("%d\n", max(maxp[0][m], maxp[1][m]));
	}
	return 0;
}
