#define _CRT_SECURE_NO_WARNINGS
#include <string>
#include <iostream>
#include <stack>
#include "node.h"
using namespace std;

node *node_arr[26];

int main() {

	int n;
	scanf("%d", &n);

	//n���� ��带 �����Ѵ�.
	for (int i = 0; i < n; i++) {
		node_arr[i] = new node((char)65+i);
		cout << node_arr[i]->data << endl;
	}

	//��� ���� �Է¿� ���� ������ �������־�� �Ѵ�.
	//ó������ ���� ������ �����־�� �Ѵ�.
	//A�� ���̿� ���� ������ �迭�� �ִ� �ָ� �̾��ָ� �ȴ�.
	for (int i = 0; i < n; i++) {
		char temp1, temp2,temp3;
		scanf("%c %c %c", &temp1, &temp2, &temp3);
		if (temp2 == '.') node_arr[i]->left = node_arr[temp2 - temp1+i];
		else if (temp3 == '.') node_arr[i]->right = node_arr[temp3 - temp1+i];
		else {
			node_arr[i]->left = node_arr[temp2 - temp1 + i];
			node_arr[i]->right = node_arr[temp3 - temp1 + i];
		}
		
		if (node_arr[i]->left != NULL) printf("%2c %2c\n", node_arr[i]->data, node_arr[i]->left->data);
		else if (node_arr[i]->right != NULL) printf("%2c %2c\n", node_arr[i]->data, node_arr[i]->right->data);
		else printf("%2c %2c %2c\n", node_arr[i]->data, node_arr[i]->left->data, node_arr[i]->right->data);
	}
	stack<char> preorder;
		

	return 0;
}