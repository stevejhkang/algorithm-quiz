#define _CRT_SECURE_NO_WARNINGS
//#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <cstring>
using namespace std;

vector<vector<int> > v(9, vector<int>(9, 0));
vector<pair<int, int> > zero;

bool check(int y, int x,int num){
	for (int i = 0; i < 9;i++) { //세로
		if (v[i][x] == num) return false;
	}
	for (int j = 0; j < 9;j++) { //가로
		if (v[y][j] == num) return false;
	}
	for (int i = 0; i < 3; i++) { //영역
		for (int j = 0; j < 3; j++) {
			if (v[3 * (y / 3) + i][3 * (x / 3) + j] == num) return false;
		}
	}
	return true;
}

void dfs(int idx){
	if (idx == zero.size()){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cout << v[i][j] << " ";
				//printf("%d ", v[i][j]);
			}
			//printf("\n");
			cout << "\n";
		}
		exit(0);
	}
	int y = zero[idx].first; int x = zero[idx].second;
	for (int i = 1; i <= 9; i++) {
		if (check(y, x, i)) {
			v[y][x] = i; //가능하다면 그 숫자를 전체 배열에 넣고 dfs를 한다. 
			dfs(idx + 1);
			//**중요부분: 순서가 중요하다.** 
			//dfs도중 발견을 못하면 이 숫자로는 완성이 안된다는 것을 의미하므로 초기화를 시켜준다.
			v[y][x] = 0; 
		}
	}
}

int main() {
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			cin >> v[i][j];
			if (v[i][j] == 0) zero.push_back({ i,j }); //**중요부분: 0인 것만 저장해둔다.**
		}
	}
	int y = zero[0].first; int x = zero[0].second;
	for (int i = 1; i <= 9; i++) {
		if (check(y, x, i)) {
			v[y][x] = i;
			dfs(1);
			v[y][x] = 0;
		}
			
	}
	return 0;
}