#define _CRT_SECURE_NO_WARNINGS
#include <algorithm>
#include <cstdio>
#include <vector>
#include <cmath>
using namespace std;

vector<int> a;

int main() {
	int n; scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		int num;
		scanf("%d", &num);
		a.push_back(num);
	}

	sort(a.begin(), a.end());
	int ans = 0;
	do {
		int result = 0;
		for (int i = 1; i < a.size(); i++) {
			result += abs(a[i - 1] - a[i]);
		}
		ans = max(ans, result);
	} while (next_permutation(a.begin(), a.end()));
	
	printf("%d\n", ans);

	return 0;
}