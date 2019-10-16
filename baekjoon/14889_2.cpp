#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int s[20][20];
//비트마스크 풀이
int main() {
	int n; cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> s[i][j];
			//cout << "hi";
		}
	}
	int ans = -1;
	for (int i = 0; i < (1 << n); i++) {//n번 1에서 n번 left shift한 경우-1 까지를 조사한다. 즉 0111111111111111111임
		//즉 모든 경우를 체크하는 것이다. 우연히 1이 n/2개가 되면서 이제 그 점수들을 계산하게 만들어 주는 것이다.
		vector<int> first, second;
		for (int j = 0; j < n; j++) {
			if (i&(1 << j)) { //비트가 1이면 1번팀 0이면 2번팀에 넣어준다.
				first.push_back(j);
			}
			else {
				second.push_back(j);
			}
		}
		if (first.size() != n / 2) continue; //n/2를 넘거나 미달되면 backtracking

		int t1 = 0; int t2 = 0;
		for (int l1 = 0; l1 < n / 2; l1++) {
			for (int l2 = 0; l2 < n / 2; l2++) {
				if (l1 == l2)continue; //인덱스 같은 경우
				t1 += s[first[l1]][first[l2]];
				t2 += s[second[l1]][second[l2]];
			}
		}
		int diff = t1 - t2;
		if (diff < 0)diff = -diff;
		if (ans == -1 || ans > diff) ans = diff;
	}
	cout << ans << "\n";
	return 0;
}
