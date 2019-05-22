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
//dp[i][j]는 i,j좌표로 올 수 있는 경우의 수를 저장해 놓는다.
//일반 배열을 사용해서 실험한 결과 28KB가 더 사용되었다.

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
			if (dp[i][j] != 0) {//dp가 0이 아니면 이전 지점에서 이동이 가능하다는 뜻.
				if(possible(i + v[i][j],j))
					dp[i + v[i][j]][j]+=dp[i][j]; 
				//i,j에서 i+v[i][j],j로 이동이 가능하므로 i,j로 이동할 수 있는 경우의 수를 더해준다.
				if(possible(i,j+v[i][j]))
					dp[i][j+v[i][j]]+=dp[i][j];
				//i,j에서 i,j+v[i][j]로 이동이 가능하므로 i,j로 이동할 수 있는 경우의 수를 더해준다.
			}
		}
	}
	//printf("%lld\n", dp[n - 1][n - 1]);
	cout << dp[n - 1][n - 1] << "\n";
	return 0;
}