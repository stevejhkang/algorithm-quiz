#define _CRT_SECURE_NO_WANRINGS
#include <cstdio>
#include <vector>
using namespace std;

vector<int> v(10001);
int main() {

	int n; scanf("%d", &n);
	int m = 0; 
	for (int i = 0; i < n; i++) {
		scanf("%d", &m);
		v[m]++;
	}
	for (int i = 1; i <= v.size(); i++) {
		if (v[i] > 0) {
			for (int j = 0; j < v[i]; j++) {
				printf("%d\n", i);
			}
		}
	}

	return 0;
}