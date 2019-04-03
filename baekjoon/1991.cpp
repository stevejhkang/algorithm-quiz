#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
using namespace std;

int n;
int tree[26][2];

//호출 순서에 주목!
void preOrder(int node) { //루트 -> 왼자식 -> 오른자식
	if (node == (int)('.' - 'A')) //.이면 바로 탈출
		return;
	printf("%c", (char)(node + 'A')); 
	//루트먼저 출력 후 자식 노드 출력. char로 변환
	preOrder(tree[node][0]);
	preOrder(tree[node][1]);
}
void inOrder(int node) { //왼자식 -> 루트 -> 오른자식
	if (node == (int)('.' - 'A')) //.이면 바로 탈출
		return;
	inOrder(tree[node][0]);
	printf("%c", (char)(node + 'A'));
	inOrder(tree[node][1]);
}
void postOrder(int node) {//왼자식->오른자식->루트
	if (node == (int)('.' - 'A')) //.이면 바로 탈출
		return;
	postOrder(tree[node][0]);
	postOrder(tree[node][1]);
	printf("%c", (char)(node + 'A'));
}

int main() {
	cin >> n;
	char a, b, c ;

	for (int i = 0; i < n; i++) {
		cin >> a >> b >> c;
		tree[a - 'A'][0] = b - 'A'; 
		//문자를 배열의 인덱스로 매핑하는게 핵심요소.
		tree[a - 'A'][1] = c - 'A';
	}

	preOrder(0);
	printf("\n");
	inOrder(0);
	printf("\n");
	postOrder(0);
	printf("\n");
	
	return 0;
}