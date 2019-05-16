#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <cstring>
using namespace std;

typedef struct {
	int y;
	int x;
}Dir;

Dir move_Dir[4] = {{-1,0},{1,0},{0,1},{0,-1}};
vector<vector<char> > v(22, vector<char>(22,0));
vector<vector<int> > visit(20, vector<int>(20, 0));
int arr[26];
int r, c;
int maxValue = 0;

bool possible(int y, int x) {
	if (y >= r || x >= c || y < 0 || x < 0) return false; //배열 범위 벗어날때
	//if (visit[y][x] == 1) return false; //이미 방문한 경우
	if (arr[v[y][x] - 65] == 1) return false; //알파벳이 중복된 경우
	return true;
}

void dfs(int y, int x, int cnt) {
	//cout << "y: " << y << ", x: " << x ;
	//printf(" %c\n", v[y][x]);
	//maxValue = max(maxValue, cnt);
	arr[v[y][x] - 65] = 1;
	int end = 0;
 	for (int i = 0; i < 4; i++) {
		int nextY = y + move_Dir[i].y; int nextX = x + move_Dir[i].x;
		if (possible(nextY, nextX)) { //가능하면
			// visit[nextY][nextX] = 1;
			dfs(nextY, nextX, cnt+1);
			//cnt--;
			//arr[v[nextY][nextX] - 65] = 0;
			
		}
		else end += 1; //불가능하면 +1
		if (end == 4) {//모두 불가능하면 최댓값 갱신 시도
			if (maxValue < cnt) maxValue = cnt;
		}
	}
	cnt--;
	arr[v[y][x] - 65] = 0; //visit[y][x] = 0;
}

int main() {
	scanf("%d %d\n", &r, &c);
	for (int i = 0; i < r; i++) {
		char s[21]; cin.getline(s, sizeof(s));
		for (int j = 0; j < c; j++) {
			v[i][j] = s[j];
		}
		cin.clear();
	}
	
	arr[v[0][0] - 65] = 1;
	visit[0][0] = 1;
	dfs(0,0,1);
	printf("%d\n", maxValue);

}