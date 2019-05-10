#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>
using namespace std;

int k;

vector<int> lotto(6);
vector<int> vec;
void dfs(int next, int size) {
	if (size == 6) {
		for (int i = 0; i < 6; i++) {
			printf("%d ", lotto[i]);
		}
		printf("\n");
		return;
	}
	for (int i = next; i < k; i++) {
		lotto[size]=vec[i];
		dfs(i+1, size + 1);
	}
}

int main() {
	while (1) {
		scanf("%d", &k);
		if (k == 0) return 0; //0 �Է½� ����

		for (int i = 0; i < k; i++) {
			int num;
			scanf("%d", &num);
			vec.push_back(num);
			//printf("%d ", vec[i]);
		}
		
		dfs(0, 0);

		printf("\n"); //���̽����� ���� ����ش�.
		vec.clear(); //�迭�� �ʱ�ȭ ���ش�.
	}
	return 0;
}