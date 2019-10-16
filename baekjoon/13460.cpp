#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
using namespace std;
int LIMIT = 10; //최대 10번 이동가능하므로 
//같은 방향으로 연속해서 두번이상 이동하는 건 의미가 없다.
//한 방향으로 이동한 다음, 반대방향으로 바로 이동하는 것도 의미가 없다.
int dx[] = { 0,0,1,-1 };  //우,좌,하,상
int dy[] = { 1,-1,0,0 };

vector<int> gen(int k) { //k라는 20자리 정수의 비트를 2개씩 쪼개서 4방향, 10개의 움직임으로 리턴하기위한 함수
	vector<int> a(LIMIT);
	for (int i = 0; i < LIMIT; i++) { 
		
		a[i] = (k & 3); //이것은 k%4와 같은 의미이다.
		k >>= 2; //이것은 k=k/4와 같은 의미이다.
	}
	return a;
}
pair<bool, bool> simulate(vector<string>& a, int k, int &x, int &y) {
	if (a[x][y] == '.') return make_pair(false, false);
	int n = a.size();
	int m = a[0].size();
	bool moved = false;
	while (1) {
		int nx = x + dx[k];
		int ny = y + dy[k];
		if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
			return make_pair(moved, false);
		}
		if (a[nx][ny] == '#') {//벽이다
			return make_pair(moved, false);
		}
		else if (a[nx][ny] == 'R' || a[nx][ny] == 'B') { //서로 다른 구슬은 벽과 같은 존재이므로 이동불가
			return make_pair(moved, false);
		}
		else if (a[nx][ny] == '.') {
			swap(a[nx][ny], a[x][y]); //구슬 이동은 점과 구슬의 내용을 바꾸는 것과 같음.
			x = nx; y = ny; //구슬이 이동했으므로 위치도 변경
			moved = true;
		}
		else if (a[nx][ny] == 'O') {
			a[x][y] = '.'; //현재 칸을 빈칸으로 만들어주고
			moved = true; //움직였다고 하고
			return make_pair(moved, true); //빠졌으므로 두번째가 
		}
	}
	return make_pair(false, false);
}
int check(vector<string> a, vector<int> &dir) {
	int n = a.size();
	int m = a[0].size();
	int hx, hy, rx, ry,bx, by;
	//구멍과 빨강, 파랑구슬의 위치를 미리 구한다.
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (a[i][j] == 'O') {
				hx = i; hy = j;
			}
			else if (a[i][j] == 'R') {
				rx = i; ry = j;
			}
			else if (a[i][j] == 'B') {
				bx = i; by = j;
			}
		}			
	}
	int cnt = 0;//움직임
	//방향에 따라서 계속 이동을 한다.
	for (int k : dir) {
		cnt += 1;
		bool hole1 = false, hole2 = false; //빨간구슬 hole1, 파랑구슬 빠졌는지 hole2
		while (1) {
			auto p1 = simulate(a, k, rx, ry); //(구슬이 움직였는지, 구멍에 들어갔는지)
			auto p2 = simulate(a, k, bx, by);
			if (p1.first == false && p2.first == false) {
				break; //구슬이 둘다 안움직일 때까지 반복하는데 더이상 둘다 안 움직이면 break;
			}
			if (p1.second)hole1 = true;
			if (p2.second)hole2 = true;
		}
		if (hole2)return -1;
		if (hole1)return cnt;
	}
	return -1; //두개다 빠지지 않는 경우
}
bool valid(vector<int> &dir) {
	int l = dir.size();
	for (int i = 0; i + 1 < l; i++) {
		if (dir[i] == 0 && dir[i + 1] == 1) return false; 
		if (dir[i] == 1 && dir[i + 1] == 0)return false;
		if (dir[i] == 2 && dir[i + 1] == 3)return false;
		if (dir[i] == 3 && dir[i + 1] == 2)return false;
		if (dir[i] == dir[i + 1])return false;

	}
	return true;
}
int main() {
	int n, m;
	cin >> n >> m;
	vector<string> a(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	int ans = -1;
	//LIMIT*2인 이유는 4^10이므로 2^20을 이용
	for (int k = 0; k < (1 << (LIMIT * 2)); k++) { 
		//따라서 여기서 k는 10번의 이동의 경우수를 알려주는 변수이다.
		//이를 gen이라는 함수를 이용해서 모든 방향을 벡터 dir에 담을 것이다.
		vector<int> dir = gen(k);
		//valid는 같은 방향 두번하거나, 한방향하고 바로 반대방향으로 옮겼는지 체크
		if (!valid(dir)) continue;
		int cur = check(a, dir);
		if (cur == -1) continue;
		if (ans == -1 || ans > cur)ans = cur;
	}
	cout << ans << '\n';
	return 0;
}
