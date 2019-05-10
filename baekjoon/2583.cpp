#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>
using namespace std;

typedef struct {
	int y;
	int x;
}dir;
dir nextDir[4]= { {-1,0},{1,0},{0,1},{0,-1} }; //좌표이동

//0이 초기배열, -1이 m*n공간 표시, 1이 직사각형 영역
int m, n, k;
int arr[101][101];   //0이 기본
int visit[101][101]; //0이 방문x 1이 방문
int cnt;
vector<int> sizearr; //영역들의 사이즈를 담기 위한 벡터
int sizesave=0; //사이즈 갱신용

void dfs(int y, int x) {
	sizesave+=1; //사이즈 +1
	visit[y][x] = 1; //방문하면 방문했다 하기 
	/*for (int i = 0; i < 29; i++) {
		for (int j = 0; j < 29; j++) {
			printf("%2d ", visit[i][j]);
		}
		printf("\n");
	}*/
	for (int i = 0; i < 4; i++) {
		int nextX = x + nextDir[i].x;
		int nextY = y + nextDir[i].y;
		if (visit[nextY][nextX]==0&&arr[nextY][nextX] == -1 && 0 <= nextX && 0 <= nextY) {
			//방문하지 않았고 영역내라면
			dfs(nextY, nextX);
		}
	}
}

int main() {
	scanf("%d %d %d", &m, &n, &k);
	
	for (int i = 0; i < m; i++) {
		memset(arr[i], -1, sizeof(int)*n); //-1이 m*n공간
	}
	/*for (int i = 0; i < 29; i++) {
		for (int j = 0; j < 29; j++) {
			printf("%2d ", arr[i][j]);
		}
		printf("\n");
	}*/
	while (k--) {
		int x1, y1, x2, y2;
		scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
		for (int i = x1; i <= x2 - 1; i++) {
			for (int j = y1; j <= y2 - 1; j++) {
				arr[j][i] = 1;              //1이 직사각형 공간
				//배열에서 앞이 y좌표임
			}
		}
		/*for (int i = 0; i < 29; i++) {
			for (int j = 0; j < 29; j++) {
				printf("%2d ", arr[i][j]);
			}
			printf("\n");
		}*/		
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (arr[j][i] == -1&&visit[j][i]==0) { //m*n영역이고 방문하지 않았으면
				sizesave = 0; //size저장을 초기화하고
				cnt++; //영역개수를 추가해주고
				dfs(j, i); //dfs를 실행해준다.
				sizearr.push_back(sizesave); //해준 영역 넓이를 저장해준다.
			}
		}
	}
	printf("%d\n", cnt);
	int arrsize = sizearr.size();
	sort(sizearr.begin(), sizearr.end());
	for (int i = 0; i < sizearr.size(); i++) {
		printf("%d ", sizearr[i]);
	}
	//printf("\n");
	return 0;
}