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
int visit[201][201][201]; //�湮���θ� ����
//�� �ű�� ���: �׸��� ��ħ, �׸��� ��.
//��������: ó������ a=0 �̶� c�� ���� �����ؾ���

typedef struct {
	int arrw[3];
}water;

void bfs(int a, int b, int c ) {
	water init;
	init.arrw[0] = a; init.arrw[1] = b; init.arrw[2] = c;
	saveC.push_back(c); //�ʱⰪ �־��ְ�
	queue<water> q; //bfs�� ���� queue
	q.push(init);
	int first = 0;
	while (!q.empty()) {
		int arr[3]; 
		arr[0] = q.front().arrw[0]; arr[1] = q.front().arrw[1]; arr[2] = q.front().arrw[2];
		q.pop();

		if (visit[arr[0]][arr[1]][arr[2]] == 1) continue; //�湮������ �ѱ��.
		visit[arr[0]][arr[1]][arr[2]] = 1; //�湮ǥ��
	
		if (first != 0 && arr[0] == 0) { //ó���� �ƴϰ� ù ��Ⱑ ���� ����
			saveC.push_back(arr[2]);
		}

		first++;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int temp[3];
				temp[0] = arr[0]; temp[1] = arr[1]; temp[2] = arr[2]; //�ӽð� ����
				//i���� j�� �Ѱ��شٰ� �Ҷ�
				if (i == j) continue;
				if (temp[i] == 0) continue; //�׸��� ����
				if (temp[j] == maxarr[j]) continue; //�������� �ϴ� �׸��� ����������
				if (temp[i] + temp[j] >= maxarr[j]&& temp[i]-maxarr[j]>=0) {
					//�׸��� ��ġ�� ��� 
					//arr[j]�� max�� �ǰ� arr[i]�� �����ġ���� maxarr[j]���� ���簪�� arr[j]�� �� ���� �ξ��� ���̴�.
					temp[i] = temp[i]-(maxarr[j]- temp[j]);
					temp[j] = maxarr[j];
				}
				else if(temp[i]+ temp[j]<maxarr[j]){
					//��ġ�� �ʴ� ���
					//arr[i]�� �� ����ֹǷ� 0, arr[j]��  arr[i]��ŭ�� �޴´�.
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
	maxarr[0] = A; maxarr[1] = B; maxarr[2] = C; //max���� ����
	
	bfs(0, 0, C);

	sort(saveC.begin(), saveC.end());
	for (int i = 0; i < saveC.size(); i++) {
		printf("%d ", saveC[i]);
	}

	return 0;
}