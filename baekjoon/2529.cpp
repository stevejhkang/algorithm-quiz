#include<iostream>
#include<vector>
#include<algorithm>
#include<cmath>
using namespace std;

bool valid(char order,int a, int b) {
	if (order == '<'&&a > b)
		return false;
	else if (order == '>'&&a < b)
		return false;
	return true;
}

int main() {
	int k; cin >> k;
	string oper = "";
	for (int i = 0; i < k; i++) {
		char letter; cin >> letter;
		oper += letter;
	}
	vector<int> maxv(k + 1);
	vector<int> minv(k + 1);
	for (int i = 0; i < k + 1; i++) { 
		//무조건 큰거/작은거 k+1개 뽑고 알맞게 정렬만 하면 된다.
		minv[i] += i;
		maxv[i] += 9-i;
	}
	while (1) {
		bool check = false;
		for (int i = 0; i < oper.size(); i++) {
			if (!valid(oper[i], maxv[i], maxv[i + 1])) {
				check = false;
				break;
			}
			else
				check = true;
		}
		if (check == false)
			prev_permutation(maxv.begin(), maxv.end());
		else
			break;
	}
	while (1) {
		bool check = false;
		for (int i = 0; i < oper.size(); i++) {
			if (!valid(oper[i], minv[i], minv[i + 1])) {
				check = false;
				break;
			}
			else
				check = true;
		}
		if (check == false)
			next_permutation(minv.begin(), minv.end());
		else
			break;
	}
	for (int i = 0; i < maxv.size(); i++)
		cout << maxv[i];
	cout << "\n";
	for (int i = 0; i < minv.size(); i++)
		cout << minv[i];
	cout << "\n";

	return 0;
}
