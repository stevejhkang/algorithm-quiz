#include<iostream>
#include<vector>
using namespace std;


int main() {
	int t; cin >> t;
	vector<vector<long long>> dp(100001, vector<long long>(3));
	
	dp[1][0] = 1; dp[1][1] = 0; dp[1][2] = 0;
	dp[2][0] = 0; dp[2][1] = 1; dp[2][2] = 0;
	dp[3][0] = 1; dp[3][1] = 1; dp[3][2] = 1;
	for (int i = 4; i <= 100000; i++) {
		dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % 1000000009;
		dp[i][1] = (dp[i - 2][0] + dp[i - 2][2]) % 1000000009;
		dp[i][2] = (dp[i - 3][0] + dp[i - 3][1]) % 1000000009;
	}
	
	while (t--) {
		int n; cin >> n;
		if (n == 1) {
			cout << 1 << '\n';
			continue;
		}
		else if (n == 2) {
			cout << 1 << '\n';
			continue;
		}
		else if (n == 3) {
			cout << 3 << '\n';
			continue;
		}
		else {
			long long result = (dp[n][0] + dp[n][1] + dp[n][2]) % 1000000009;
			cout << result << '\n';
		}
		
	}

	return 0;
}
