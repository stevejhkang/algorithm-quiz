#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
using namespace std;

int a[10000];

int main() {
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &a[i]);
	}
	int i = n - 1;
	while (i > 0 && a[i - 1] > a[i]) { //���������� ������ �������� �̵��Ѵ�.
		i--; 
	}
	if (i == 0) { 
		printf("-1\n"); return 0; //������ ���� ���
	}
	int j = n - 1; //Ž���� ���� �ε���
	int idx = 0;
	int min = 10001;
	// a[i-1]���� ū ���� ���� ���� ���� ��ȯ�ؾ� �ϹǷ� Ž���ʱⰪ ����
	while (j > i - 1) {
		if (min > a[j]&&a[j]>a[i-1]) {
			min = a[j]; idx = j;
		}
		j--;
	}
	//a[i-1]�� �ٲ��ش�.
	int tmp = a[i - 1];
	a[i - 1] = min;
	a[idx] = tmp;
	//�׸��� ������������ ����
	sort(a + i, a + n);
	for (int k = 0; k < n; k++) {
		printf("%d ", a[k]);
	}
	return 0;
}