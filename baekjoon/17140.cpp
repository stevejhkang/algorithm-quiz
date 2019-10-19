#include<iostream>
#include<vector>
#include<cstdlib>
#include<algorithm>
#include<deque>
#include<map>
#include<cstring>
using namespace std;

int a[101][101];

typedef struct {
	int what; int number;
	unsigned operator==(const int number) {
		return (what == number);
	}
}ope;

bool cmp(const ope &a, const ope &b) {
	if (a.number <= b.number) {
		if (a.number == b.number) {
			if (a.what < b.what) {
				return true;
			}
			return false;
		}
		return true;
	}
	return false;
}

int main() {
	int r, c, k; cin >> r >> c >> k;
	for (int i = 1; i <= 3; i++) {
		for (int j = 1; j <= 3; j++) {
			int num; cin >> num;
			a[i][j] = num;
		}
	}
	int max_r = 3;
	int max_c = 3;
	int time = 0;
	//반복문
	while (time <= 100) {
		/*for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				cout << a[i][j] << " ";
			}
			cout << endl;
		}*/
		if (a[r][c] == k) {
			cout << time << "\n";
			return 0;
		}
		if (max_r >= max_c) {
			//R연산: c의길이가 변화
			max_c = 0;//초기화
			for (int i = 1; i <= 100; i++) {
				vector<ope> temp; //숫자들을 담을 배열
				for (int j = 1; j <= 100; j++) {
					if (a[i][j] != 0) { //0이 아닐때 카운트해줘야함.
						vector<ope>::iterator it;
						it = find(temp.begin(), temp.end(), a[i][j]);
						if (it == temp.end()) {//못찾았으면 추가
							ope opeTemp; opeTemp.what = a[i][j];
							opeTemp.number = 1;
							temp.push_back(opeTemp);
						}
						else { //찾았으면 이미 있는 것이므로 갱신
							(*it).number += 1;
						}
					}
					if (j == 100) {//마지막까지 했으면 정렬해주고 재배치
						int tempSize = int(temp.size());
						//컬럼 최댓값 저장: 한번에 두개가 들어가니까 *2
						if (2 * tempSize > max_c) { 
							if (tempSize >= 50) {
								max_c = 100;
							}
							else {
								max_c = 2 * tempSize;
							}
						}
						//빈도수로 오름차순, 빈도수 같으면 숫자크기 오름차순
						sort(temp.begin(), temp.end(), cmp);
						//그것들을 배열에 갱신시켜준다.
						for (int k = 0; k < temp.size(); k++) {
							if (2 * (k+1) - 1 > 100 || 2 * (k+1) > 100) {
								break;
							}
							a[i][2 * (k+1) - 1] = temp[k].what;
							a[i][2 * (k+1)] = temp[k].number;
						}
						//나머지는 0으로 채워준다.
						if (2 * tempSize < 100) { 
							for (int k = 2 * tempSize+1; k <= 100; k++) {
								a[i][k] = 0;
							}
						}
					}
				}
			}
		}
		else if (max_r < max_c) {//C연산 r의 길이가 변화
			max_r = 0;
			for (int j = 1; j <= 100; j++) {
				vector<ope> temp;
				for (int i = 1; i <= 100; i++) {
					
					if (a[i][j] != 0) {
						vector<ope>::iterator it;
						it = find(temp.begin(), temp.end(), a[i][j]);
						if (it == temp.end()) {//못찾았으면 추가
							ope opeTemp; opeTemp.what = a[i][j];
							opeTemp.number = 1;
							temp.push_back(opeTemp);
						}
						else { //찾았으면 이미 있는 것이므로 갱신
							(*it).number += 1;
						}
					}
					
					if (i == 100) {//마지막까지 했으면 정렬해주고 재배치
						int tempSize = int(temp.size());
						if (2 * tempSize > max_r) {
							if (tempSize >= 50) {
								max_r = 100;
							}
							else {
								max_r = 2 * tempSize;
							}
						}
						sort(temp.begin(), temp.end(), cmp);
						for (int k = 0; k < temp.size(); k++) {
							if (2 * (k+1) - 1 > 100 || 2 * (k+1) > 100) {
								break;
							}
							a[2 * (k+1) - 1][j] = temp[k].what;
							a[2*(k+1)][j] = temp[k].number;
						}
						if (2 * tempSize < 100) {
							for (int k = 2 * tempSize + 1; k <= 100; k++) {
								a[k][j] = 0;
							}
						}
					}
				}
			}
		}
		time++;
	}
	cout << -1 << "\n";
	return 0;
}
