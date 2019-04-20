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
	while (i > 0 && a[i - 1] > a[i]) { //내림차순이 끝나는 지점까지 이동한다.
		i--; 
	}
	if (i == 0) { 
		printf("-1\n"); return 0; //내림차 순인 경우
	}
	int j = n - 1; //탐색을 위한 인덱스
	int idx = 0;
	int min = 10001;
	// a[i-1]보다 큰 값중 가장 작은 값과 교환해야 하므로 탐색초기값 설정
	while (j > i - 1) {
		if (min > a[j]&&a[j]>a[i-1]) {
			min = a[j]; idx = j;
		}
		j--;
	}
	//a[i-1]과 바꿔준다.
	int tmp = a[i - 1];
	a[i - 1] = min;
	a[idx] = tmp;
	//그리고 오름차순으로 정렬
	sort(a + i, a + n);
	for (int k = 0; k < n; k++) {
		printf("%d ", a[k]);
	}
	return 0;
}