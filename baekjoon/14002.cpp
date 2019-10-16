#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int n; cin >> n;
	vector<int> cost(n + 1);
	vector<int> dp(n + 1);
	vector<int> indexList(n+1);
	for (int i = 1; i <= n; i++) {
		cin >> cost[i];
		dp[i] = 1;
	}
	int initnum = 0;
	//int index = 0;
	for (int i = 1; i <= n; i++) {
		int index = 0;
		initnum = dp[i];
		for (int j = i; j >= 1; j--) {
			if (cost[i] > cost[j] && dp[i] < initnum + dp[j]) {
				dp[i] = initnum + dp[j];
				index = j;
			}
		}
		//cout << index << endl;
		indexList[i] = index;
	}
	int maxIndex = 0;
	int maxValue = 0;
	for (int i = n; i >=0; i--) {
		if (maxValue < dp[i]) {
			maxValue = dp[i];
			maxIndex = i;
		}
	}
	int loop = maxIndex;
	cout << dp[maxIndex] << '\n';
	vector<int> arr;
	while (loop != 0) {
		arr.push_back(cost[loop]);
		loop = indexList[loop];
	}
	for (int i = arr.size() - 1; i >= 0; i--) {
		cout << arr[i] << " ";
	}
	cout << '\n';
	return 0;
}
