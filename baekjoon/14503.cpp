#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

vector<vector<int> > v(51, vector<int>(51, 0));

int dy[] = { -1,0,1,0 }; //북,동,남,서
int dx[] = { 0,1,0,-1 };
int answer;

int main() {
	int n, m; cin >> n >> m;
	int y, x, dir; cin >> y >> x >> dir; //시작포인트와 방향 지정
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int a; cin >> a;
			v[i][j] = a;
		}
	}
	int flag = 1; //청소모드
	while (1) {
		/////////////////////////시작///////////////////////////////////////////////////
		//cout << y << "," << x << endl;
		if (v[y][x] == 0&&flag==1) {//현재 위치가 청소가 안되어 있으면 청소하고 ++해준다.
			v[y][x] = -1;
			answer++;
		}
		/*if (answer == 19) {
			cout << 19 << endl;
		}*/
		int nx_dir = dir; //다음방향 변수
		for (int i = 0; i < 4; i++) {
			flag == 2;//왼쪽방향 탐색모드
			nx_dir = nx_dir - 1; //현재기준에서 왼쪽방향은 -1 해줘야함.
			if (nx_dir < 0) //-1이면 3으로 바꿔준다.
				nx_dir = 4 + nx_dir;
			int ny = y + dy[nx_dir]; 
			int nx = x + dx[nx_dir];
			// 범위 밖 인지 확인하기
			if (ny < 0 || ny >= n || nx < 0 || nx >= m) //범위체크
				continue;
			if (v[ny][nx] == 0) {
				//청소 안했으면 1번을 진행하기 위해 y,x,dir를 수정해주어 다음 반복문에서 청소시켜준다.
				y = ny; x = nx; dir = nx_dir;
				//청소 안 한 곳을 찾았으므로 회전 종료
				//2번 탐색모드를 종료하고 1번 청소모드로 돌아간다.
				flag == 1;
				break;
			}
			if (v[ny][nx] == -1 || v[ny][nx] == 1) {//이미 청소했거나 벽이면(청소할 공간이 없다면) 
				//c.d.
				if (i == 3) { //네 방향 모두 못가면 한칸 후진=> 방향은 유지하고,좌표만 후진
					//후진하고 2번으로 돌아간다.
					dir = nx_dir; //마지막에는 방향 바꾸면 안됌
					y = y + dy[(nx_dir + 2) % 4]; //방향의 반대 방향으로 후진
					x = x + dx[(nx_dir + 2) % 4]; //방향의 반대 방향으로 후진
					if (y < 0 || y >= n || x < 0 || x >= m) {//범위를 벗어나거나 
						cout << answer << "\n";
						return 0;
					}
					if (v[y][x] == 1) {//후진할 벽이면 정답출력 후 종료 
          /////////////////////////////후진할 지점이 청소를 했더라도 갈 수 있어!!!!!!!!!!!!!!!!!!!!!!!!!!
						cout << answer << "\n";
						return 0;
					}
					break;
				}
				//b.방향만 바꿔준다.
				dir = nx_dir; 
				continue;
			}
		}
		/*cout << "\n";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				//cout << v[i][j] << " ";
				printf("%2d ", v[i][j]);
			}
			cout << "\n";
		}*/
	}
	return 0;
}
