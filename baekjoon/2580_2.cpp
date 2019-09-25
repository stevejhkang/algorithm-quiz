#include<iostream>
#include<vector>
#include<algorithm>
#include<cmath>
using namespace std;

//백트래킹: 행, 열, 작은 사각형 별로 확인함.
//되는거 하나 넣어주고, 
//그다음에 안되는게 있으면 더 나가지 않고 다시 빼준다.

bool c1[9][10]; //행확인
bool c2[9][10]; //열확인
bool c3[9][10]; //작은 사각형 확인
bool hello;
vector<int> v;

void go(int z) {
	if (z == 81) { //완성되면
		for (int i = 0; i < 81; i++) {
			if (i % 9 == 0) cout << "\n";
			cout << v[i] << " ";
		}
		exit(0);
	}
	int x = z / 9; int y = z % 9;
	if (v[z] != 0) {
		go(z + 1);
	}
	else {
		for (int i = 1; i <= 9; i++) {
			if (c1[x][i] == false && c2[y][i] == false && c3[x / 3 * 3 + y / 3][i] == false) {
				c1[x][i] = c2[y][i] = c3[x / 3 * 3 + y / 3][i] = true;
				v[z] = i;
				go(z + 1);
				v[z] = 0;
				c1[x][i] = c2[y][i] = c3[x / 3 * 3 + y / 3][i] = false;
			}
		}
	}

}

int main() {
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			int a;
			cin >>a;
			v.push_back(a);
			if (a != 0) {
				c1[i][a] = true;
				c2[j][a] = true;
				c3[(i / 3) * 3 + j / 3][a] = true;
			}
		}
	}
	go(0);
	return 0;
}
