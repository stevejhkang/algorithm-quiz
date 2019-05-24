#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <vector>
#include <string>
#include <iostream>
using namespace std;

int n, s, m;//°î ¼ö, ½ÃÀÛº¼·ý, ÃÖ´ëº¼·ý
int v[101];
int dp[1001][101];

int main() {
	scanf("%d %d %d\n", &n, &s, &m);
	for (int i = 0; i < n; i++) {
		scanf("%d", &v[i]);
	}
	dp[s][0] = 1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= m; j++) {
			if (dp[j][i] == 1) { //i¹øÂ°°îÇÏ°í³ª¼­ º¼·ýÀÌ jÀÌ¸é
				if (j - v[i] >= 0)dp[j - v[i]][i + 1] = 1;
				if (j + v[i] <= m)dp[j + v[i]][i + 1] = 1;
			}
		}
	}
	/*for (int j = 0; j <= m; j++) {
	for (int i = 1; i <= n; i++) {
	printf("%d ", dp[j][i]);
	}
	printf("\n");
	}*/
	bool t = false;
	for (int j = m; j >= 0; j--) {
		if (dp[j][n] == 1) {
			printf("%d\n", j);
			t = true;
			break;
		}

	}
	if (t == false) printf("-1\n");
	return 0;
}