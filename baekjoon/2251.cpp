#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

vector<int> saveC;
int A, B, C;
int maxarr[3];
int visit[201][201][201]; //방문여부를 저장
//못 옮기는 경우: 그릇이 넘침, 그릇이 빔.
//종료조건: 처음빼고 a=0 이때 c의 값을 저장해야함

typedef struct {
	int arrw[3];
}water;

void bfs(int a, int b, int c ) {
	water init;
	init.arrw[0] = a; init.arrw[1] = b; init.arrw[2] = c;
	saveC.push_back(c); //초기값 넣어주고
	queue<water> q; //bfs를 위한 queue
	q.push(init);
	int first = 0;
	while (!q.empty()) {
		int arr[3]; 
		arr[0] = q.front().arrw[0]; arr[1] = q.front().arrw[1]; arr[2] = q.front().arrw[2];
		q.pop();

		if (visit[arr[0]][arr[1]][arr[2]] == 1) continue; //방문했으면 넘긴다.
		visit[arr[0]][arr[1]][arr[2]] = 1; //방문표시
	
		if (first != 0 && arr[0] == 0) { //처음이 아니고 첫 용기가 빌때 종료
			saveC.push_back(arr[2]);
		}

		first++;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int temp[3];
				temp[0] = arr[0]; temp[1] = arr[1]; temp[2] = arr[2]; //임시값 복사
				//i에서 j로 넘겨준다고 할때
				if (i == j) continue;
				if (temp[i] == 0) continue; //그릇이 빈경우
				if (temp[j] == maxarr[j]) continue; //부으려고 하는 그릇이 꽉차있을때
				if (temp[i] + temp[j] >= maxarr[j]&& temp[i]-maxarr[j]>=0) {
					//그릇이 넘치는 경우 
					//arr[j]는 max가 되고 arr[i]는 현재수치에서 maxarr[j]에서 현재값인 arr[j]을 뺀 값을 부어준 셈이다.
					temp[i] = temp[i]-(maxarr[j]- temp[j]);
					temp[j] = maxarr[j];
				}
				else if(temp[i]+ temp[j]<maxarr[j]){
					//넘치지 않는 경우
					//arr[i]는 다 비워주므로 0, arr[j]는  arr[i]만큼을 받는다.
					temp[j] += temp[i];
					temp[i] = 0;
				}
				water next; 
				next.arrw[i] = temp[i]; next.arrw[j] = temp[j];
				//0
				if (i == 1 && j == 2 || i == 2 && j == 1) next.arrw[0] = temp[0];
				//1
				if (i == 0 && j == 2 || i == 2 && j == 0) next.arrw[1] = temp[1];
				//2
				if (i == 0 && j == 1 || i == 1 && j == 0) next.arrw[2] = temp[2];
				q.push(next);
			}
		}
	}
}

int main() {
	scanf("%d %d %d", &A, &B, &C);
	maxarr[0] = A; maxarr[1] = B; maxarr[2] = C; //max값을 저장
	
	bfs(0, 0, C);

	sort(saveC.begin(), saveC.end());
	for (int i = 0; i < saveC.size(); i++) {
		printf("%d ", saveC[i]);
	}

	return 0;
}