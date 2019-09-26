#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

bool c[101][101];
int dy[] = { 0,-1,0,1 };
int dx[] = { 1,0,-1,0 };
vector<int> curve(int dir, int gen) {
	vector<int> ans = { dir };//0세대 방향을 넣어주고 처음 방향을 넣어주고
	for (int g = 1; g <= gen; g++) { //1세대부터 gen세대까지의 방향을 구해야될 듯
		vector<int> temp(ans); //임시배열 만들고 현재 이동 방향을 붙이고 뒤집어 준다.
		//왜냐하면 이전세대 끝나는 지점을 기준으로 회전이 시작되므로 첫 지점부터 방향을 90도 회전 시켜준것을
		//ans백터에 추가 시켜 줘야 한다.
		reverse(temp.begin(), temp.end()); 
		for (int &i : temp) {
			i = (i + 1) % 4;
		}
		ans.insert(ans.end(), temp.begin(), temp.end()); 
	}
	return ans;
}


int main() {
	int n; cin >> n;
	for (int i = 0; i < n; i++) {
		int x, y, dir, gen;
		cin >> x >> y >> dir >> gen;
		vector<int> dirs = curve(dir, gen); 
		c[y][x] = true; //시작 꼭지점 체크
		for (int j : dirs) { //방향에 따라서 점은 이동시켜 모든 꼭지점을 추가시켜준다.
			x += dx[j];
			y += dy[j];
			c[y][x] = true;
		}
	}
	int ans = 0;
	for(int i=0;i<=99;i++){
		for (int j = 0; j <= 99; j++) {
			if (c[i][j] && c[i][j + 1] && c[i + 1][j] && c[i + 1][j + 1]) { //4개 점이 모두 꼭지점이면 +1
				ans += 1;
			}
		}
	}
	cout << ans << '\n';
	return 0;
}
