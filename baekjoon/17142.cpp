#include<iostream>
#include<vector>
#include<algorithm>
#include<cstring>
#include<map>
#include<queue>
using namespace std;

int visit[51][51];
int dy[] = { -1, 1, 0, 0 };
int dx[] = { 0,0,-1,1 };
int answer = 987654321; //모든 순서에서 찾은 최솟값을 갱신하기 위한 변수

int main() {
	int n, m; cin >> n >> m;
	vector<vector<int> > v(n, vector<int>(n)); 
	vector<pair<int, int> > virus;
	int nonVi = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int a; cin >> a;
			v[i][j] = a;
			if (a == 2) {
				virus.push_back(make_pair(i, j));//바이러스 위치를 저장
			}
			else if (a == 0) { 
				//////////////////////////////////////////////////////////////
				//바이러스가 아닌 빈칸을 세준다.
				nonVi++;
			}
		}
	}
	int viSize = int(virus.size());
	vector<int> com(viSize);
	for (int i = viSize-1; i >=0; i--) {
		if (i >= viSize - m) {
			com[i] = 1;
		}
		else com[i] = 0;
	}
	if (nonVi == 0) {
		cout << 0 << "\n";
		return 0;
	}
	do {
		//케이스 시작
		//초기화////////////////////////////////////////////////////////////
		//무엇을 초기화 해줘야 하는가???////////////////////////////////////
		int numberNon = nonVi; //빈칸개수 초기화
		queue<pair<int, int> > q; //활성화된 바이러스를 넣는곳
		memset(visit, 0, sizeof(visit));//visit은 케이스마다 초기화
		vector<vector<int> > Vcopy = v; //Vcopy 초기화

		//조합에 따라 활성화될 바이러스를 넣어준다
		for (int i = 0; i < com.size(); i++) {
			if (com[i] == 1) {//활성화된 바이러스 인경우
				//해당지역을 0으로 만들고 방문표시하고 큐에 넣어준다.
				int y = virus[i].first; int x = virus[i].second;
				Vcopy[y][x] = 0; visit[y][x] = 1;
				q.push(make_pair(y, x));
			}
		}
		//퍼뜨리기 시작
		while (!q.empty()) {
			int endFlag = 0;
			int y = q.front().first; int x = q.front().second; q.pop();
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i]; int nx = x + dx[i];
				if (ny < 0 || ny >= n || nx < 0 || nx >= n)
					continue;
				if (!visit[ny][nx]&&Vcopy[ny][nx] == 0) {
					//빈칸이면 방문처리 후 시간을 넣어준다.
					Vcopy[ny][nx] = Vcopy[y][x] + 1;
					visit[ny][nx] = 1;
					numberNon--; //빈칸개수 빼준다.
					//큐에 넣는다.
					q.push(make_pair(ny, nx));
					if (numberNon == 0) { 
						//빈칸이 0이 되는순간 Vcopy가 마지막 시간이된다. 
						//기존 값과 비교한다. 그리고 반복문 끝낸다.
						endFlag = 1;
						if (answer > Vcopy[ny][nx]) {
							answer = Vcopy[ny][nx];
						}
						break;
					}
				}
				else if (!visit[ny][nx] && Vcopy[ny][nx] == 2) {
					//비활성 바이러스이면 방문처리하고 개수는 빼주면 안됨
					Vcopy[ny][nx] = Vcopy[y][x]+1;
					visit[ny][nx] = 1;
					q.push(make_pair(ny, nx));
				}
			}
			if (endFlag)
				break;
		}
	} while (next_permutation(com.begin(), com.end()));

	if (answer == 987654321) { //다 확인해도 답이 0이 되는 경우가 없으면 그대로
		cout << -1 << "\n";
	}
	else
		cout << answer << "\n";

	return 0;
}
