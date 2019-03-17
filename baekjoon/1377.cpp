#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int n; pair<int, int> p[500001];

int main() {

	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &p[i].first); p[i].second = i;
	}
	sort(p, p + n);
	int max=0;
	for (int i = 0; i < n; i++) {
		if ((p[i].second - i) > max) {
			max = (p[i].second - i);
		}
	}
	printf("%d\n", max+1);
	return 0;
}