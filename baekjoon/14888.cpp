#include<iostream>
#include<queue>
#include<algorithm>
#include<vector>
#include<map>
#include<cstring>
using namespace std;

vector<int> v;
vector<int> sign;
int max_result = -1000000000;
int min_result = 1000000000;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
	int n; cin >> n;
	for (int i = 0; i < n; i++) {
		int a; cin >> a;
		v.push_back(a);
	}
	int p, mi, mul, di;
	cin >> p >> mi >> mul >> di;
	int ssize = p + mi + mul + di;
	for (int i = 0; i < p; i++) {
		sign.push_back(0);
	}
	for (int i = 0; i < mi; i++) {
		sign.push_back(1);
	}
	for (int i = 0; i < mul; i++) {
		sign.push_back(2);
	}
	for (int i = 0; i < di; i++) {
		sign.push_back(3);
	}
	
	//reverse(sign.begin(), sign.end());
	//cout << "size" << sign.size() << endl;
	do {
		//cout << endl;
		int result = v[0];
		for (int i = 0; i < n-1; i++) {
			//cout << sign[i] << " ";
			if (sign[i] == 0) {
				result += v[i + 1];
			}
			else if (sign[i] == 1) {
				result -= v[i + 1];
			}
			else if (sign[i] == 2) {
				result *= v[i + 1];
			}
			else if (sign[i] == 3) {
				result /= v[i + 1];
			}
		}
		if (result < min_result) {//최솟값 갱신
			min_result = result;
		}
		if (result > max_result) {
			max_result = result;
		}
		//cout << endl;
	} while (next_permutation(sign.begin(), sign.end()));
	cout << max_result << "\n";
	cout << min_result << "\n";
	return 0;
}
