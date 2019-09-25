#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
using namespace std;
int s[20][20];
int n;
int go(int index, vector<int> &first, vector<int> &second) {
	if (index == n) { //마지막일때
		if (first.size() != n / 2)return -1;
		if (second.size() != n / 2) return -1;
		//사이즈가 절반이 아니면 안되므로 -1
		int t1 = 0; int t2 = 0;
		//1, 2팀의 사람들의 능력치를 각각 t1,t2에 더해주어야 한다.
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < n / 2; j++) {
				if (i == j)continue;
				t1 += s[first[i]][first[j]];
				t2 += s[second[i]][second[j]];
			}
		}
		int diff = t1 - t2;
		if (diff < 0)diff = -diff;
		return diff;
	}
	//backtracking: 정답이 안나오는 경우에는 그냥 
	//끝내버려야 한다.
	//backtracking은 재귀함수에서 사용이 가능하다.
	if (first.size() > n / 2) return -1;
	if (second.size() > n / 2) return -1;
	int ans = -1;
	//index번째 선수를 1번에 넣을 경우
	//다음 선수를 1,2번에 넣었을 때 결과 값을 
	//가져온다. 
	first.push_back(index);
	int t1 = go(index + 1, first, second);
	//ans==-1은 아직 정답을 구하지 않은 것.
	if (ans == -1 || (t1 != -1 && ans > t1)) {
		//그 결과 값이 -1이 아니고 기존 ans보다 작으면 갱신
		ans = t1; 
	}
	first.pop_back(); 
	//2번에 넣는 경우를 체크해야하므로 
	//1번에 넣은 것을 다시 빼야한다.
	second.push_back(index);
	int t2 = go(index + 1, first, second);
	if (ans == -1 || (t2 != -1 && ans > t2)) {
		ans = t2;
	}
	second.pop_back();
	return ans;
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> s[i][j];
		}
	}
	vector<int> first, second;
	cout << go(0, first, second) << "\n";
	return 0;
}
