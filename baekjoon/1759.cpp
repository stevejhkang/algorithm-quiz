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
vector<char> passwd; //비밀번호 후보를 저장할 벡터
vector<int> order; //조합을 위한 보조 벡터

bool check(string s)
{
	int mo = 0;
	int ja = 0;

	for (int i = 0; i < s.length(); i++)
	{
		if (s.at(i) == 'a' || s.at(i) == 'e' || s.at(i) == 'i' || s.at(i) == 'o' || s.at(i) == 'u') //모음개수 체크
			mo++;
		else //자음개수 체크
			ja++;
	}
	return mo >= 1 && ja >= 2; //모음 1개이상 자음 2개이상 인지 체크
}

int main() {
	
	scanf("%d %d", &l, &c);
	fflush(stdin);
	//printf("%d\n", c);
	for (int i = 0; i < c; i++) {
		char a; //= getchar();//getchar는 띄어쓰기 없이 한번에 여러 문자 받을때
		cin >> a; //띄어쓰기 있어서 입력 받을 때는 그냥 cout쓴다.
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
		for (int i = 1; i < l; i++) { //오름차순 체크
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
		if (check(s)) //비밀번호 조건이 맞으면
			cout << s << "\n";		
	} while (next_permutation(order.begin(), order.end()));

	return 0;
}