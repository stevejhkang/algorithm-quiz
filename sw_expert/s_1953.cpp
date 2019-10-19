#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<queue>
#include<algorithm>
#include<vector>
#include<map>
#include<cstring>
#include<list>
#include<set>
#include<cstdio>
using namespace std;

int dy[] = {-1,0,1,0}; //상우하좌
int dx[] = {0,1,0,-1};
int t;
int v[51][51];
int visit[51][51];


int main() {
	//freopen("sample_input.txt", "r", stdin);
	cin >> t;
	vector<int>answers;
	while (t--) {
		fill(v[0], v[0] + (51 * 51), 0);
		fill(visit[0], visit[0] + (51 * 51), 0);
		int n, m, r, c, l; //세로, 가로, 출발좌표y,x ,시간
		//출발좌표 기준은 0,0부터
		cin >> n >> m >> r >> c >> l;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int a; cin >> a;
				v[i][j] = a;
			}
		}
		int time = 1;
		int poss = 1; //가능한 개수 측정
		queue<pair<int, int> > q;
		q.push(make_pair(r, c));
		visit[r][c] = 1;
		while (!q.empty()) {
			int y = q.front().first; int x = q.front().second;
			q.pop();
			int kind = v[y][x];
			if (kind == 1) { // 상우하좌 모두 +4
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i]; int nx = x + dx[i];
					if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
						continue;
					}
					
					if (visit[ny][nx] == 0) { //방문하지 않았고 방향도 일치
						int nd = v[ny][nx];
						if(i==0&&(nd==1|| nd==2|| nd==5|| nd==6)){ //상
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
						if (i == 1 && (nd == 1 || nd == 3 || nd == 6 || nd == 7)) { //우
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
						if (i == 2 && (nd == 1 || nd == 2 || nd == 4 || nd == 7)) { //하
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
						if (i == 3 && (nd == 1 || nd == 3 || nd == 4 || nd == 5)) { //좌
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
					}
				}
			}
			else if (kind == 2) { //상하
				for (int i = 0; i <= 2; i += 2) {
					int ny = y + dy[i]; int nx = x + dx[i];
					if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
						continue;
					}
					if (visit[ny][nx] == 0) {
						int nd = v[ny][nx];
						if (i == 0 && (nd == 1 || nd == 2 || nd == 5 || nd == 6)) { //상
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
						if (i == 2 && (nd == 1 || nd == 2 || nd == 4 || nd == 7)) { //하
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
					}
				}
			}
			else if (kind == 3) { //좌우
				for (int i = 1; i <= 3; i += 2) {
					int ny = y + dy[i]; int nx = x + dx[i];
					if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
						continue;
					}
					if (visit[ny][nx]== 0) {
						int nd = v[ny][nx];
						if (i == 1 && (nd == 1 || nd == 3 || nd == 6 || nd == 7)) { //우
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
						if (i == 3 && (nd == 1 || nd == 3 || nd == 4 || nd == 5)) { //좌
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
					}
				}
			}
			else if (kind == 4) { //상우
				for (int i = 0; i <= 1; i++) {
					int ny = y + dy[i]; int nx = x + dx[i];
					if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
						continue;
					}
					if (visit[ny][nx] == 0) {
						int nd = v[ny][nx];
						if (i == 0 && (nd == 1 || nd == 2 || nd == 5 || nd == 6)) { //상
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
						if (i == 1 && (nd == 1 || nd == 3 || nd == 6 || nd == 7)) { //우
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
					}
				}
			}
			else if (kind == 5) { //우하
				for (int i = 1; i <= 2; i++) {
					int ny = y + dy[i]; int nx = x + dx[i];
					if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
						continue;
					}
					if (visit[ny][nx] == 0) {
						int nd = v[ny][nx];
						if (i == 1 && (nd == 1 || nd == 3 || nd == 6 || nd == 7)) { //우
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
						if (i == 2 && (nd == 1 || nd == 2 || nd == 4 || nd == 7)) { //하
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
					}
				}
			}
			else if (kind == 6) {//좌하
				for (int i = 2; i <= 3; i++) {
					int ny = y + dy[i]; int nx = x + dx[i];
					if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
						continue;
					}
					if (visit[ny][nx]== 0) {
						int nd = v[ny][nx];
						if (i == 2 && (nd == 1 || nd == 2 || nd == 4 || nd == 7)) { //하
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
						if (i == 3 && (nd == 1 || nd == 3 || nd == 4 || nd == 5)) { //좌
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
					}
				}
			}
			else if (kind == 7) {//좌상
				for (int i = 0; i <= 3; i += 3) {
					int ny = y + dy[i]; int nx = x + dx[i];
					if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
						continue;
					}
					if (visit[ny][nx] == 0) {
						int nd = v[ny][nx];
						if (i == 3 && (nd == 1 || nd == 3 || nd == 4 || nd == 5)) { //좌
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
						if (i == 0 && (nd == 1 || nd == 2 || nd == 5 || nd == 6)) { //상
							q.push(make_pair(ny, nx));
							visit[ny][nx] = visit[y][x] + 1;
							poss++;
						}
					}
				}
			}
		}
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				//cout << visit[i][j] << " ";
				if (visit[i][j] <= l&&visit[i][j]!=0) {
					result++;
				}
			}
			//cout << endl;
		}
		//cout << endl;
		answers.push_back(result);
	}
	for (int i = 0; i < answers.size(); i++) {
		cout << "#" << i + 1 << " " << answers[i] << endl;
	}
	return 0;
}
