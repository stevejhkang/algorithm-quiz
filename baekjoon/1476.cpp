#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int a[7981][4];

int main() {
	int e, s, m;
	scanf("%d %d %d", &e, &s, &m);

	for (int i = 1; i <= 7980; i++) {
		if (i % 15 == 0) a[i][0] = 15;
		else a[i][0] = i%15; //e
		if (i % 28 == 0) a[i][1] = 28;
		else a[i][1] = i%28; //s
		if (i % 19 == 0) a[i][2] = 19;
		else a[i][2] = i % 19; //m
		a[i][3] = i; //year
	}

	for (int i = 1; i <= 7980; i++) {
		if (a[i][0] == e && a[i][1] == s && a[i][2] == m)
			printf("%d\n", i);
	}
	
	return 0;
}