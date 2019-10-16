#include<iostream>
#include<vector>
#include<queue>
#include<tuple>
#include<algorithm>
#include<cstring>
#include<map>
using namespace std;
map<int,int> a;

int d[1001][1001];
//이모티콘의 개수 s와 클립보드의 이모티콘 개수 c가 중요하다. 
int main() {
	int n; cin >> n;
	
	memset(d, -1, sizeof(d));
	queue<pair<int, int>> q;
	//memset(visit, -1, visit.size());
	q.push(make_pair(1, 0));
	d[1][0] = 0;
	while (!q.empty()) {
		int s = q.front().first; int c = q.front().second;
		//tie(s, c) = q.front(); 
		q.pop();
		if (d[s][s] == -1) {
			d[s][s] = d[s][c] + 1;
			q.push(make_pair(s, s));
		}
		if (s+c<=n&&d[s + c][c]==-1) {
			d[s + c][c] = d[s][c]+1;
			q.push(make_pair(s + c, s));
		}
		if (s - 1 >= 0 && d[s - 1][c] == -1) {
			d[s - 1][c] = d[s][c] + 1;
			q.push(make_pair(s - 1, c));
		}
	}
	int ans = -1;
	for (int i = 0; i <=n; i++) {
		if (d[n][i] != -1) {
			if (ans == -1 || ans > d[n][i]) {
				ans = visit[n][i];
			}
		}
	}
	cout << ans << '\n';
	return 0;
}
