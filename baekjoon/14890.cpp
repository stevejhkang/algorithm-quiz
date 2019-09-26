#include<iostream>
#include<vector>
#include<algorithm>
#include<cstdlib>
using namespace std;

int main() {
	int n, l; cin >> n >> l;
	vector<vector<int>> v(n, vector<int>(n));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int a; cin >> a;
			v[i][j] = a;
		}
	}
	int ans = 0;
	//행 확인
	for (int i = 0; i < n; i++) {
		vector<bool> slide(n, false); //한 행에서 슬라이드를 놓은 곳을 체크한다.
		for (int j = 0; j < n; j++) {
			bool possible = true;
			if (j == 0) continue;
			else if (v[i][j-1] == v[i][j] - 1) { 
				//이전 것이 1만큼 작을 경우 l번만큼 돌아가서 슬라이드를 놓을 수 있는지 체크해주어야 함.
				int cnt = l; 
				int now = j - 1;
				while (cnt--) {
					if (now < 0) { //0보다 작으면 범위를 벗어나 못세움.
						possible = false; 
						break;
					}
					if (v[i][now] == v[i][j - 1] && !slide[now]) { //똑같으면 다시 l번만큼 돌아가야됨. 
						slide[now] = true;
						now--;
					} 
					else //나머지 경우는 그냥 다 false
						possible = false;
				}
				if (!possible) 
					j = n; //이미 안되면 현재 i행은 불가이므로 i행의 마지막으로 바꿔서 반복문 종료시킨다.
			}
			else if (v[i][j-1] == v[i][j]+1) {//이전 것이 1만큼 클 경우 l번만큼 앞으로 가서 체크해주어야 함.
				int cnt = l;
				int now = j;
				while (cnt--) {
					if (now > n - 1) { //n-1 보다 크면 범위 벗어남
						possible = false;
						break;
					}
					if (v[i][now] == v[i][j] && !slide[now]) {
						slide[now] = true;
						now++;
					}
					else possible = false;
				}
				if (!possible) 
					j = n; //이미 안되면 현재 i행은 불가이므로 i행의 마지막으로 바꿔서 반복문 종료시킨다.
			}
			else if (abs(v[i][j - 1] - v[i][j]) > 1) j = n; //만약 차이가 1이상일 경우에도 아예 불가
			if (j == n - 1) {
				ans++; //모든 것을 만족하고 n-1까지 오면 경우 수 +1
				//cout << "(i,j)=(" << i << "," << j << ")\n";
			}
		}
	}
	//열도 똑같이 확인
	for (int j = 0; j < n; j++) {
		vector<bool> slide(n, false);
		for (int i = 0; i < n; i++) {
			bool possible = true;
			if (i == 0) continue;
			else if (v[i-1][j] == v[i][j] - 1) { //이전 것이 1만큼 작을 경우 l번만큼 돌아가서 체크해주어야 함.
				int cnt = l;
				int now = i-1;
				while (cnt--) {
					if (now < 0) { //0보다 작으면 경사로 못세움.
						possible = false;
						break;
					}
					if (v[now][j] == v[i - 1][j] && !slide[now]) {
						//똑같으면 다시 l번만큼 돌아가야됨.
						slide[now] = true;
						now--;
					}
					else possible = false;
				}
				if (!possible) 
					i = n; //이미 안되면 현재 j열은 불가이므로 j열 마지막으로 바꿔서 반복문 종료시킨다.
			}
			else if (v[i-1][j] == v[i][j]+1) { //이전 것이 1만큼 클 경우 l번만큼 앞으로 가서 체크해주어야 함.
				int cnt = l;
				int now = i;
				while (cnt--) {
					if (now > n - 1) { //n-1 보다 크면 범위 벗어남
						possible = false;
						break;
					}
					if (v[now][j] == v[i][j] && !slide[now]) {
						slide[now] = true;
						now++;
					}
					else possible = false;
				}
				if (!possible) 
					i = n; //이미 안되면 현재 j열은 불가이므로 j열 마지막으로 바꿔서 반복문 종료시킨다.
			}
			else if (abs(v[i-1][j] - v[i][j]) > 1) i = n;
			//else i = n;
			if (i == n - 1) {
				//cout << "(i,j)=(" << i << "," << j << ")\n";
				ans++;
			}
		}
	}
	cout << ans << "\n";
	return 0;
}
