#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int n;
vector<pair<int, int>> vec[10001];


int main() {
	cin >> n;
	for (int i = 1; i < n; i++) { //2번째줄부터 n번째줄까지
		int a, b, c; scanf("%d %d %d", &a, &b, &c);
		vec[a].push_back(pair<int, int>(b, c));
		vec[b].push_back(pair<int, int>(a, c));
	}




	return 0;
}