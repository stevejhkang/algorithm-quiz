#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<int> vec[100001]; //연결관계를 나타낼 백터
queue<int> q; //방문 순서를 정해줄 큐
int arr[100001]; 
//루트를 저장할 배열, 방문여부도 나타낼 수 있음
int n;

int main() {
	cin >> n;
	int a, b;

	for (int i = 0; i < n - 1; i++) {
		scanf("%d %d", &a, &b);
		vec[a].push_back(b);
		vec[b].push_back(a);
	}
	q.push(1); arr[1] = 1;
	while (!q.empty()) {
		int num = q.front(); q.pop();
		for (int i = 0; i < vec[num].size(); i++) {
			if (arr[vec[num][i]] != 0) continue; //이미 방문 했으므로 패스
			if (arr[vec[num][i]] == 0) q.push(vec[num][i]);
			//1과 연결된 자식들이 루트가 되므로 그 순서대로 큐에 넣어준다.
			arr[vec[num][i]] = num; //루트를 저장해준다.

		}
	}
	for (int i = 2; i <= n; i++) {
		printf("%d\n", arr[i]);
		//2부터 순서대로 출력
	}

	return 0;
}