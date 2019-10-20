#include<iostream>
#include<algorithm>
using namespace std;

int n, m, h;
int answer = 99999999;
bool flag = 0;
int laddercnt;
bool v[32][12]; //v[i][j] j높이에서 i에서 i+1로 가는 가로선

//재귀함수에서는 기저부분에서 리턴처리를 제대로 해줘야 한다!!!!!!!!!!!!!!!!!!!!!!!
//그리고 파라미터를 최소화시켜야 한다.

void dfs(int cnt) {
	if (cnt >= 4||flag==true) //4이상이거나 답을 이미 찾았으면 그냥 끝낸다.
		return;
	if (laddercnt == cnt) {//원하는 개수의 가로선만큼 추가하면 평가한다.
		/*
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < n; i++) {
				cout << v[j][i] << " ";
			}
			cout << "\n";
		}*/
		int check = 0;
		bool possible = true;
		for (int i = 1; i <= n; i++) {
			int a = i;
			for (int j = 0; j < h; j++) {
				if (v[j][a] == true) {
					a += 1;
					//cout << a << " " << i << endl;
				}
				else if (a > 1 && v[j][a - 1] == true) {
					a -= 1;
					//cout << a << " " << i << endl;
				}
			}
			//cout << a << " " << i << endl;
			//같은경우 check++;
			if (a != i) {
				//check++;
				possible = false;
				break;
			}
		}
		if (possible ) {
			flag = true;
		}
		return;
	}
	//dfs부분
	for (int j = 0; j < h ; j++) {
		for (int i = 1; i < n; i++) {
			if (v[j][i] != true && v[j][i-1] != true && v[j][i+1] != true) {
				//해당 가로선과 양쪽에 존재하지 않을때 추가한다.
				v[j][i] = true;
				dfs( cnt + 1);		
				v[j][i] = false;
			}
		}
	}
	return;
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m >> h;
	for (int i = 0; i < m; i++) { //가로선 정보를 받는다.
		int y, x; cin >> y >> x; //세로는 0부터 가로는 1부터 0번째 컬럼을 비워둔다.
		v[y - 1][x] = true;
	}
	for (int i = 0; i < 4; i++) { //1개를 추가했을때 가능한지부터 체크한다.
		laddercnt = i;
		///////////////////최대 설치가능 사다리 개수를 dfs의 파라미터로 넣어주지 않고 전역변수를 사용해서 체크함으로서
		//재귀함수 호출시 무분별한 메모리사용을 줄여준다.
		dfs(0); 
		if (flag) { 
			cout << laddercnt << "\n";
			return 0;
		}
	}
	cout << -1 << "\n";
	return 0;
}
