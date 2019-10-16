#include<iostream>
#include<queue>
#include<algorithm>
#include<vector>
#include<map>
#include<cstring>
using namespace std;

int visit[8][8];
int dy[] = { 0,0,1,-1 };
int dx[] = { 1,-1,0,0 };

int main() {
	int n, m; cin >> n >> m; //세로n, 가로m
	vector<vector<int>> v(n, vector<int>(m));
	vector<pair<int, int>> virus;
	vector<pair<int, int>> road;
	int road_number = -3;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int a; cin >> a;
			v[i][j] = a;
			if (a == 2) {
				virus.push_back(make_pair(i, j)); //바이러스이면 따로 위치 저장
			}
			else if (a == 0) {
				road.push_back(make_pair(i, j)); //길이면 따로 저장
				road_number++;
			}	
		}
	}
	//조합을 사용해서 모든 길 중에서 3개에 벽을 설치하고 바이러스를 퍼뜨려보고
	//바이러스가 퍼지지 않은 빈칸의 개수를 확인해본다.
	vector<int> combi(int(road.size())); //길의 개수만큼 만들어주고 3개의 1과 나머지 0으로 채워넣는다.
	for (int i = 0; i < road.size(); i++) {
		if (i >= int(road.size()) - 3) {
			combi[i] = 1;
		}
		else combi[i] = 0;
	}
	/*do {
		for (int i = 0; i < combi.size(); i++) {
			cout << combi[i] << " ";
		}cout << "\n";
	} while (next_permutation(combi.begin(), combi.end()));*/

	
	int ans = -1;
	do {
		memset(visit, 0, sizeof(visit));
		int copy_number = road_number; //길의 개수 복사
		vector<vector<int>> copy = v; //지도 복사
		//copy.clear(); copy.assign(v.begin(), v.end());
		queue<pair<int, int>> q;
		//바이러스를 큐에 넣어준다.
		for (int i = 0; i < virus.size(); i++) {
			q.push(virus[i]); visit[virus[i].first][virus[i].second] = 1;
		}
		//빈 길에 벽을 설치한다.
		for (int i = 0; i < combi.size(); i++) {
			if (combi[i] == 1) { //벽을 설치할 길이면 벽이면 설치해준다. 0->1로 바꿔준다.
				copy[road[i].first][road[i].second]=1;
			}
		}
		while (!q.empty()) {
			if (ans>copy_number||copy_number == 0) { //다 퍼져버리면 그냥 그만둔다.
				break;
			}
			
			int y = q.front().first; int x = q.front().second; q.pop();
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i]; int nx = x + dx[i];
				//범위 벗어나면 포문 끝
				if (0 > ny || ny >= n || 0 > nx || nx >= m) continue;
				if (visit[ny][nx] == 0 && copy[ny][nx] != 1) {//만약 방문하지 않았고 벽이 아니라면
					if (copy[ny][nx] == 0) {//길이면 바이러스에 감염된 길이므로 
						copy_number--;
					}
					visit[ny][nx] = 1; copy[ny][nx] = 2; //방문처리해주고 바이러스로 만들어준다.
					//그리고 바이러스 큐에 넣어준다.
					q.push(make_pair(ny, nx));
					
				}
			}
		}
		//cout << copy_number << endl;
		/*for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cout << copy[i][j] << " ";
			}
			cout << "\n";
		}*/
		if (copy_number > ans) {
			ans = copy_number; //만약 길이 현재 정답보다 크면 최댓값 갱신
		}
	} while (next_permutation(combi.begin(),combi.end()));
	cout << ans << "\n";

	return 0;
}
