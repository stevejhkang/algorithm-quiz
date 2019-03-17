#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <vector>
using namespace std;

int main() {
	int n,k; scanf("%d %d", &n,&k);
	vector<int> v;
	for (int i = 0; i < n; i++) {
		int a; scanf("%d", &a);
		v.push_back(a);
	}
	sort(v.begin(), v.end());

	printf("%d\n", v[k - 1]);
	return 0;
}