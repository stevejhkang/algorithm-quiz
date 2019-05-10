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
dir nextDir[4]= { {-1,0},{1,0},{0,1},{0,-1} }; //��ǥ�̵�

//0�� �ʱ�迭, -1�� m*n���� ǥ��, 1�� ���簢�� ����
int m, n, k;
int arr[101][101];   //0�� �⺻
int visit[101][101]; //0�� �湮x 1�� �湮
int cnt;
vector<int> sizearr; //�������� ����� ��� ���� ����
int sizesave=0; //������ ���ſ�

void dfs(int y, int x) {
	sizesave+=1; //������ +1
	visit[y][x] = 1; //�湮�ϸ� �湮�ߴ� �ϱ� 
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
			//�湮���� �ʾҰ� ���������
			dfs(nextY, nextX);
		}
	}
}

int main() {
	scanf("%d %d %d", &m, &n, &k);
	
	for (int i = 0; i < m; i++) {
		memset(arr[i], -1, sizeof(int)*n); //-1�� m*n����
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
				arr[j][i] = 1;              //1�� ���簢�� ����
				//�迭���� ���� y��ǥ��
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
			if (arr[j][i] == -1&&visit[j][i]==0) { //m*n�����̰� �湮���� �ʾ�����
				sizesave = 0; //size������ �ʱ�ȭ�ϰ�
				cnt++; //���������� �߰����ְ�
				dfs(j, i); //dfs�� �������ش�.
				sizearr.push_back(sizesave); //���� ���� ���̸� �������ش�.
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