#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <string>
#include <cstring>
using namespace std;

int l, c;
vector<char> passwd; //��й�ȣ �ĺ��� ������ ����
vector<int> order; //������ ���� ���� ����

bool check(string s)
{
	int mo = 0;
	int ja = 0;

	for (int i = 0; i < s.length(); i++)
	{
		if (s.at(i) == 'a' || s.at(i) == 'e' || s.at(i) == 'i' || s.at(i) == 'o' || s.at(i) == 'u') //�������� üũ
			mo++;
		else //�������� üũ
			ja++;
	}
	return mo >= 1 && ja >= 2; //���� 1���̻� ���� 2���̻� ���� üũ
}

int main() {
	
	scanf("%d %d", &l, &c);
	fflush(stdin);
	//printf("%d\n", c);
	for (int i = 0; i < c; i++) {
		char a; //= getchar();//getchar�� ���� ���� �ѹ��� ���� ���� ������
		cin >> a; //���� �־ �Է� ���� ���� �׳� cout����.
		passwd.push_back(a);
	}
	
	sort(passwd.begin(), passwd.end());

	for (int i = 0; i < l; i++) {
		order.push_back(0);
	}
	for (int i = 0; i < c-l; i++) {
		order.push_back(1);
	}

	do {
		bool can = true;
		for (int i = 1; i < l; i++) { //�������� üũ
			if (passwd[i - 1] > passwd[i]) {
				can = false;
				break;
			}
		}
		if (!can) continue;
		string s;
		for (int i = 0; i < c; i++) {
			if (order[i] == 0)
				s += passwd[i];
				//printf("%c", passwd[i]);
		}
		if (check(s)) //��й�ȣ ������ ������
			cout << s << "\n";		
	} while (next_permutation(order.begin(), order.end()));

	return 0;
}