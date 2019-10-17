#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

vector<pair<int, int> > v(17);
int sum = 0;
int answer = 0;
int n;

void dfs(int day) {
	
	for (int i = day; i <= n; i++) {
		int ti = v[i].first; int pi = v[i].second;
		if (i + ti <= n) {
			sum += pi;
			dfs(i + ti);
			sum -= pi;
		}
		if (i + ti > n) {
			if (i + ti == n + 1) {
				if (answer < sum + pi) {
					answer = sum + pi;
					//이게 현재까지 값보다 큰거라고 해도
					//다음 i값에 의한 값이 더 클 수 있으므로 
					//return해서 종료 안된다.
				}
			}
			if (answer < sum) {
				answer = sum;
				//다음 i값에 의한 값이 더 클 수 있으므로 
				//return해서 종료하면 안된다.
			}
		}
	}
}

int main() {
	cin >> n;
	
	for (int i = 1; i <= n; i++) {
		int t, p; cin >> t >> p;
		v[i].first = t; v[i].second = p;
	}
	for (int i = 1; i <= n; i++) {
		if (i+v[i].first <= n) {
			sum += v[i].second;
			dfs(i + v[i].first);
			sum -= v[i].second;
		}
		else if (i + v[i].first == n + 1) {
			if (answer < sum + v[i].second) {
				answer = sum + v[i].second;
			}
		}
	}
	cout << answer << "\n";
	

	return 0;
}
