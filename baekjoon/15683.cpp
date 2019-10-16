#include<iostream>
#include<queue>
#include<algorithm>
#include<vector>
#include<map>
#include<cstring>
using namespace std;

typedef struct {
	int y, x;
	int kind;
}cctv;

vector<vector<int> > v(9,vector<int>(9));
vector<cctv> cv;
int n, m; //세로 가로
int ccsize;
int answer = 987654321;

//벽이 있을때까지 계속 -1처리해준다.
void move(int num,int y, int x) {
	if (num == 0) {//동 x에서부터 끝까지 -1처리
		for (int i = x; i < m; i++) {
			if (i == x) {
				continue;
			}
			if (v[y][i] == 6)
				break;
			else
				v[y][i] = -1;
		}
	}
	else if (num == 2) {//서
		for (int i = x; i >= 0; i--) {
			if (i == x)
				continue;
			if (v[y][i] == 6)
				break;
			else
				v[y][i] = -1;
		}
	}
	else if (num == 1) {//남
		for (int i = y; i < n; i++) {
			if (i == y)
				continue;
			if (v[i][x] == 6)
				break;
			else
				v[i][x] = -1;
		}
	}
	else if (num == 3) {//북
		for (int i = y; i >= 0; i--) {
			if (i == y)
				continue;
			if (v[i][x] == 6)
				break;
			else 
				v[i][x] = -1;
		}
	}
}

void dfs(int num) {
	if (num == ccsize) {
		//개수를 확인
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (v[i][j] == 0) {
					sum++;
				}
			}
		}
		if (answer > sum) {
			answer = sum;
			/*for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					cout << v[i][j] << " ";
				}
				cout << endl;
			}*/
		}
			
		return;
	}
	vector<vector<int> > Vcopy = v;

	//동0남1서2북3
	int type = cv[num].kind; int y = cv[num].y; int x = cv[num].x;
	if (type == 1) {//항방향
		for (int i = 0; i < 4; i++) {
			move(i, y, x);
			dfs(num + 1);
			v = Vcopy; //원래대로 복구시켜준다.
		}
	}
	else if (type == 2) { //180도
		for (int i = 0; i < 2; i++) {
			move(i, y, x);
			move(i + 2, y, x);
			dfs(num + 1);
			v = Vcopy;
		}
	}
	else if (type == 3) { //90도
		for (int i = 0; i < 4; i++) {
			move(i, y, x);
			move((i + 1)%4, y, x);
			dfs(num + 1);
			v = Vcopy;
		}
	}
	else if (type == 4) { //세방향
		for (int i = 0; i < 4; i++) {
			move(i, y, x);
			move((i + 1) % 4, y, x);
			move((i + 2) % 4, y, x);
			dfs(num + 1);
			v = Vcopy;
		}
	}
	else if (type == 5) { //네방향
		move(0, y, x);
		move(1, y, x);
		move(2, y, x);
		move(3, y, x);
		dfs(num + 1);
		v = Vcopy;
	}
}

int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int a; cin >> a;
			v[i][j] = a;
			if (a >= 1 && a <= 5) {//cctv이면 벡터에 담는다.
				cctv temp; temp.kind = a;
				temp.y = i; temp.x = j;
				cv.push_back(temp);
			}
		}
	}
	ccsize = int(cv.size());
	dfs(0);
	cout << answer << "\n";
	
	return 0;
}
