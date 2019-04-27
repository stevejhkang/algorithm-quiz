#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>
using namespace std;

vector<pair<int, int>> w[11];
int min_val = 987654321;
int n;
int visit[11];

void dfs(int start, int next, int sum, int cnt) {
	if (cnt == n && start == next) {
		//모든 도시를 들리고 이번 들리는 곳이 시작 포인트일때
		if (min_val > sum) min_val = sum;
		//경로비용이 최소 경로값보다 작으면 업데이트
		return;
	}
	for (int i = 0; i < w[next].size(); i++) {
		if (!visit[next] && w[next][i].second > 0) {
			//방문하지 않았고 연결비용이 0이 아니면
			visit[next] = true; // 현재를 방문했다 표시하고
			sum += w[next][i].second; 
			//현재 값과 다음 연결비용을 더해주고
			if (sum <= min_val) { 
				//더해준 값이 현재까지 구한 최솟값보다 작을 경우에만 dfs를 진행하게 해준다.
				dfs(start, w[next][i].first, sum, cnt + 1);
			}
			//dfs가 끝났을때는 이미 최솟값이 갱신 됐으므로 다시 초기화 해준다.
			//dfs의 주목할 점인듯... 이미 다 끝나고 실행되니까
			visit[next] = false;
			sum -= w[next][i].second;
		}
	}
}

int main() {
	scanf("%d", &n); //사이즈 입력

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int distance;
			scanf("%d", &distance); //거리 입력
			if (distance != 0) w[i].push_back(make_pair(j, distance));
			//거리가 0이 아닐때만 거리와 연결점을 백터에 저장
		}
	}
	for (int i = 0; i < n; i++) {
		dfs(i, i, 0, 0);
	}
	printf("%d\n", min_val);
	return 0;
}