#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
using namespace std;

int n;
int tree[26][2];

//ȣ�� ������ �ָ�!
void preOrder(int node) { //��Ʈ -> ���ڽ� -> �����ڽ�
	if (node == (int)('.' - 'A')) //.�̸� �ٷ� Ż��
		return;
	printf("%c", (char)(node + 'A')); 
	//��Ʈ���� ��� �� �ڽ� ��� ���. char�� ��ȯ
	preOrder(tree[node][0]);
	preOrder(tree[node][1]);
}
void inOrder(int node) { //���ڽ� -> ��Ʈ -> �����ڽ�
	if (node == (int)('.' - 'A')) //.�̸� �ٷ� Ż��
		return;
	inOrder(tree[node][0]);
	printf("%c", (char)(node + 'A'));
	inOrder(tree[node][1]);
}
void postOrder(int node) {//���ڽ�->�����ڽ�->��Ʈ
	if (node == (int)('.' - 'A')) //.�̸� �ٷ� Ż��
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
		//���ڸ� �迭�� �ε����� �����ϴ°� �ٽɿ��.
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