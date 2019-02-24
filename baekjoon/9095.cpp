#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include<cstdio>
using namespace std;

int function(int n) {

	if (n == 1) return 1;
	else if (n == 2) return 2;
	else if (n == 3) return 4;
	else return function(n - 1) + function(n - 2) + function(n - 3);
}

int main() {
	int n;
	scanf("%d", &n);

	while (n--) {
		int m;
		scanf("%d", &m);
		printf("%d\n", function(m));
	}


	return 0;
}