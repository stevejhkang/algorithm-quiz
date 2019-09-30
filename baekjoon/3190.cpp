#include<iostream>
#include<vector>
#include<cstdlib>
#include<algorithm>
#include<deque>
using namespace std;

//조합과 BFS

int dy[] = { 0,-1,0,1 };
int dx[] = { 1,0,-1,0 };

int main() {
	int n, k, l; cin >> n >> k;
	vector<vector<int>> v(n+1, vector<int>(n+1 , 0)); //인덱스 0을 남겨두기 위해 n+1로
	while (k--) {
		int x, y; cin >> y >> x;
		v[y][x] = 2;//사과의 위치를 따로 저장한다.
	}
	cin >> l;
	vector<int> move(10002,0); //언제 회전할 지도 저장한다.
	//움직임 저장-> 0: 직진, 1: 왼쪽 2: 오른쪽
	while (l--) {
		int t; char m;
		cin >> t >> m;
		//t초가 끝난 후에 움직임이므로 t+1에서의 위치 변경이므로 t+1에 넣어준다.
		if (m == 'L') move[t+1] = 1; //왼쪽은 1
		else move[t+1] = 2; //오른쪽은 2
	}
	deque<pair<int, int>> dum; 
	dum.push_front(make_pair(1, 1));//첫 위치 넣어준다.
	int time = 0;
	int dir = 0;
	while (1) {
		time++;
		//기준을 딱 박아줘야 한다! 선입선출 즉 삽입은 back에서하고 꼬리 삭제는 front에서 한다.
		int y = dum.back().first; int x = dum.back().second;
		//나아갈 머리가 벽이나 몸에 충돌했는지 알기 위해 back의 것을 확인한다.

		int ny, nx; //몸길이 늘려서 간 위치
		if (move[time] == 1) { //왼쪽
			dir = (dir + 1) % 4;
		}
		else if (move[time] == 2) { //오른쪽
			dir = dir - 1;
			if (dir < 0) dir += 4;
		}
		ny = y + dy[dir]; nx = x + dx[dir];
		//cout << time << " " << ny << "," << nx << endl; //<", "<<dum.size()<<
		
		if (ny == 0 || nx == 0 || ny == n+1 || nx == n+1) { //늘린 부분이 벽일 경우
			cout << time << "\n"; //끝났으므로 시간 출력
			return 0;
		}
		
		//몸과는 부딛히는지 확인.
		deque<pair<int, int>> body(dum); //나아간 머리를 제외한 몸을 복사한다.
		while (!body.empty()) {
			//만약 머리가 몸에 부딪힐 경우 시간 출력하고 끝낸다.
			int cy = body.front().first; int cx = body.front().second;
			body.pop_front();
			//cout << cy << " " << cx << "||" << ny << " " << nx << endl;
			if (cy == ny && cx == nx) {
				cout << time << "\n";
				return 0;
			}
		}
		//"이동한 칸에 사과가 있다면" 이므로 이동한 후에 사과를 체크해주어야 한다.
		//현재가 사과가 아닐 경우 몸이 늘어난게 아니므로 꼬리를 없애준다.
		if (v[ny][nx] != 2) {
			dum.pop_front();
		}
		else if (v[ny][nx] == 2) { //사과를 먹었을때 사라지는 것도 처리해줬어야 한다.
			v[ny][nx] = 0; //사과를 먹었으므로 0으로 바꿔준다.
		}

		dum.push_back(make_pair(ny, nx)); //몸에 머리를 넣어준다.

		
		
	}
	return 0;
}
