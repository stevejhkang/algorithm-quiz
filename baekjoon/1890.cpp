#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <stack>
#include <cstring>
using namespace std;


//vector<vector<long> >v(101, vector<long>(101, 0));
long v[101][101];
int n;
//vector<vector<long> >dp(101, vector<long>(101, 0)); 
long dp[101][101];
//dp[i][j]�� i,j��ǥ�� �� �� �ִ� ����� ���� ������ ���´�.
//�Ϲ� �迭�� ����ؼ� ������ ��� 28KB�� �� ���Ǿ���.

bool possible(int y, int x) {
	return (y < n) && (x < n);
}

int main() {
	scanf("%d\n", &n);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> v[i][j];
		}
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (i == 0 && j == 0) {
				dp[v[0][0]][0]++;
				dp[0][v[0][0]]++;
			}
			if (i == n - 1 && j == n - 1) break;
			if (dp[i][j] != 0) {//dp�� 0�� �ƴϸ� ���� �������� �̵��� �����ϴٴ� ��.
				if(possible(i + v[i][j],j))
					dp[i + v[i][j]][j]+=dp[i][j]; 
				//i,j���� i+v[i][j],j�� �̵��� �����ϹǷ� i,j�� �̵��� �� �ִ� ����� ���� �����ش�.
				if(possible(i,j+v[i][j]))
					dp[i][j+v[i][j]]+=dp[i][j];
				//i,j���� i,j+v[i][j]�� �̵��� �����ϹǷ� i,j�� �̵��� �� �ִ� ����� ���� �����ش�.
			}
		}
	}
	//printf("%lld\n", dp[n - 1][n - 1]);
	cout << dp[n - 1][n - 1] << "\n";
	return 0;
}