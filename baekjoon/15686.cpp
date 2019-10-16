#include<iostream>
#include<queue>
#include<algorithm>
#include<vector>
#include<map>
#include<cstring>
using namespace std;

vector<vector<int>> v(51, vector<int>(51));
vector<pair<int, int>> chi;
int main() {
	int n, m; cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int a; cin >> a;
			v[i][j]=a;
			if (a == 2) {//치킨집 좌표 따로 저장
				chi.push_back(make_pair(i, j));
			}
		}
	}
	int chisize = int(chi.size());
	vector<int> com(chisize);//조합을 위한 벡터
	for (int i = chisize-1; i >= 0; i--) {
		if (i>chisize-1-m) {
			com[i] = 1; //m개의 1을 넣어준다.
		}
		else {
			com[i] = 0; //나머지 0을 넣어준다.
		}
	}
	int cityLength = 999999999;
	do {
		vector<vector<int>> Vcopy(51, vector<int>(51)); Vcopy.clear();
		Vcopy.assign(v.begin(), v.end());
		vector<pair<int, int>> q;
		for (int i = 0; i < chisize; i++) {
			if (com[i] == 1) {
				q.push_back(chi[i]); //탐색할 치킨위치가 담긴 벡터
			}
			else { //아닌건 0으로 만들어주기
				int y = chi[i].first; int x = chi[i].second;
				Vcopy[y][x] = 0;
			}
		}
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (Vcopy[i][j] == 1) {//집이면 치킨거리를 구해준다.
					int minLength = 999999;
					for (int k = 0; k < q.size(); k++) {
						int yLen = (q[k].first - i); int xLen = (q[k].second - j);
						if (yLen < 0) yLen = -yLen;
						if (xLen < 0) xLen = -xLen;
						int distance = xLen + yLen;
						if (distance < minLength) //해당 집에서 여러 치킨집과의 거리 중 최솟값을 찾는다.
							minLength = distance;
					}
					sum += minLength; //그리고 도시치킨거리 값에 더해준다.
				}
			}
		}
		//그렇게 하나의 케이스의 도시치킨거리 값을 이제까지 구한 케이스의 도시치킨거리값보다 작으면 갱신
		if (cityLength > sum) {
			cityLength = sum;
		}
	} while (next_permutation(com.begin(), com.end()));
	//최솟값 출력
	cout << cityLength << "\n";
	
	return 0;
}
