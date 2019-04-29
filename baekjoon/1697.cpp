#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <vector>
using namespace std;

int min_time = 987654321;
int n, k;
int visit[100001];

void bfs(int start,int cnt) {
	queue<pair<int,int>> q;
	q.push(make_pair(start, cnt));

	while (!q.empty()) {
		int cur, time;
		cur = q.front().first; time = q.front().second;
		q.pop();

		//k에 도달하고 이전에 측정된 도달 시간보다 작은 값이면 최신화
		if (cur == k && min_time > time)
			min_time = time;
		
		if (time + 1 <= min_time) { //이미 측정된 최솟값보다 작을 때만 들어간다.
			if (cur * 2 < 100001 && visit[cur*2]>time+1) {
				visit[cur * 2] = time + 1;
				q.push(make_pair(cur * 2, time + 1));
			}
			if (cur - 1 > -1 && visit[cur - 1] > time + 1) {
				visit[cur - 1] = time + 1;
				q.push(make_pair(cur - 1, time + 1));
			}
			if (cur + 1 < 100001 && visit[cur + 1] > time + 1) {
				visit[cur + 1] = time + 1;
				q.push(make_pair(cur + 1, time + 1));
			}
		}
	}
}

int main() {
	fill_n(visit, 100001, 987654321);
	scanf("%d %d", &n, &k);

	bfs(n,0);
	
	printf("%d\n", min_time);
	
	return 0;
}
