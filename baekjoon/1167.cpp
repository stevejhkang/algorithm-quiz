#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int n;
vector<pair<int, int>> vec[100001];

int main() {
	cin >> n;
	for (int i = 1; i <= n; i++) {
		int a, b, val;
		scanf("%d", &a);
		while (1) {
			scanf("%d", &b);
			if (b == -1) break;
			scanf("%d", &val);

			vec[i].push_back(pair<int, int>(b, val));
		}
	}



	return 0;
}