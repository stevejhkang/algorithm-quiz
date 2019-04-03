#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include<cstring>
#include <vector>
#include <queue>
using namespace std;

int n, r, endp;
int visited[10001];
vector<pair<int, int>> vec[10001]; //연결된 노드(first)랑 거리(second)를 저장.
int ans, end_point; //지름 길이, 지름에 해당하는 끝점

//dfs: stack or 재귀
//루트에서 끝까지 갈 경우는 계속 길이를 넘겨주면서 연결되어 있는 거에 전부 
//호출을 해준다. 근데 먼저 연결시킨 것부터 호출되므로
//호출된 것도 계속 호출하기 때문에 dfs가 된다.
void dfs(int point, int length) {
	if (visited[point]) return; //이미 방문
	visited[point] = 1;
	if (ans < length) {
		ans = length; //현재 길이가 최대 길이보다 길면 지름 길이 갱신하고
		end_point = point; //제일 긴 점도 갱신 저장
	}
	for (int i = 0; i < vec[point].size(); i++) {
		dfs(vec[point][i].first, length+vec[point][i].second);
		//길이값과 연결된 것의 값을 합해서 넘겨준다.
	}
}

int main() {
	cin >> n;
	for (int i = 1; i < n; i++) { //2번째줄부터 n번째줄까지
		int a, b, c; scanf("%d %d %d", &a, &b, &c);
		vec[a].push_back(pair<int, int>(b, c));
		vec[b].push_back(pair<int, int>(a, c));
	}
	dfs(1, 0); //dfs를 통해 루트(1)에서 제일 먼 end_point를 찾는다.
	ans = 0;
	memset(visited, 0, sizeof(visited));

	dfs(end_point, 0); 
	//end_point에서 제일 먼 또다른 포인트와 그 길이를 찾는다.
	cout << ans << endl;


	return 0;
}