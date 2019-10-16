#include<iostream>
#include<queue>
#include<vector>
#include<algorithm>
#include<string>
using namespace std;
string map[101];
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };
int main() {
	int n, m; cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> map[i];
	}
	vector<vector<int>> visit(n, vector<int>(m));
	queue<pair<int, int>> q;
	visit[0][0] = 1;
	q.push(make_pair(0, 0));
	while (!q.empty()) {
		int y = q.front().first; int x = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i]; int nx = x + dx[i];
			if (0 <= ny && ny < n && 0 <= nx && nx < m) {
				if (map[ny][nx]=='1' && visit[ny][nx]==0) {
					visit[ny][nx] = visit[y][x] + 1;
					q.push(make_pair(ny, nx));
				}
			}
		}
	}
	/*for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << visit[i][j] << ' ';
		}
		cout << '\n';
	}*/
	cout << visit[n - 1][m - 1] << '\n';
	return 0;
}
