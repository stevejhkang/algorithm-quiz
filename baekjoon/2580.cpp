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
	for (int i = 0; i < 9;i++) { //����
		if (v[i][x] == num) return false;
	}
	for (int j = 0; j < 9;j++) { //����
		if (v[y][j] == num) return false;
	}
	for (int i = 0; i < 3; i++) { //����
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
			v[y][x] = i; //�����ϴٸ� �� ���ڸ� ��ü �迭�� �ְ� dfs�� �Ѵ�. 
			dfs(idx + 1);
			//**�߿�κ�: ������ �߿��ϴ�.** 
			//dfs���� �߰��� ���ϸ� �� ���ڷδ� �ϼ��� �ȵȴٴ� ���� �ǹ��ϹǷ� �ʱ�ȭ�� �����ش�.
			v[y][x] = 0; 
		}
	}
}

int main() {
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			cin >> v[i][j];
			if (v[i][j] == 0) zero.push_back({ i,j }); //**�߿�κ�: 0�� �͸� �����صд�.**
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