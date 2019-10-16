#include <iostream>
using namespace std;
int a[20][20]; 
int dice[7];
//a[y][x]:
//동(0,1) 서(0,-1) 북(-1,0) 남(1,0)
int dx[] = { 0,0,-1,1 }; 
int dy[] = { 1,-1,0,0 };
int main() {
	int n, m, x, y, l; //세로,가로,첫엑스,첫와이,명령어 개수
	cin >> n >> m >> x >> y >> l;
	for(int i=0; i<n;i++){ //i가 세로 n
		for (int j = 0; j < m; j++) { //j가 가로 m
			cin >> a[i][j];
		}
	}
	while (l--) {
		int k; //명령어
		cin >> k;
		k -= 1; //인덱스를 위한 마이너스
		int nx, ny;
		nx = x + dx[k];
		ny = y + dy[k];
		if (nx < 0 || nx >= n || ny < 0 || ny >= m) { //범위 밖
			continue;
		}
		if (k == 0) { //오른쪽
			int temp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = temp;
		}
		else if (k == 1) {//왼쪽
			int temp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
		}
		else if (k == 2) {//북쪽
			int temp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = temp;
		}
		else{//남쪽
			int temp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = temp;
		}
		x = nx;
		y = ny;
		if (a[x][y] != 0) {
			dice[6] = a[x][y];
			a[x][y] = 0;
		}
		else {
			a[x][y] = dice[6];
		}
		cout << dice[1] << '\n';
	}
	return 0;
}
