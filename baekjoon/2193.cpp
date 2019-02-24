#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
using namespace std;

int depth;

int main() {

	scanf("%d", &depth);
	
	long long count[91];
	count[0] = 0; count[1] = 1;
	for (int i = 2; i <= depth; i++) {
		count[i] = count[i - 1] + count[i - 2];
	}
	printf("lld\n", count[depth]);

	return 0;
}