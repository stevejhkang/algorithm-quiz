#include<iostream>
#include<vector>
using namespace std;

int main() {
	int n; cin >> n;
	vector<int> cost(n + 1);
	vector<int> dp(n + 1);
	for (int i = 1; i <= n; i++) cin >> cost[i];
	for (int i = 1; i <= n; i++) {
		dp[i] = cost[i];
		for (int j = 1; j <= i / 2; j++) {
			if (dp[i] > dp[j] + dp[i - j])
				dp[i] = dp[j] + dp[i - j];
		}
	}
	cout << dp[n] << '\n';
	return 0;
}
