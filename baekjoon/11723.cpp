#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstdio>
#include <string>
#include <set>
using namespace std;

char order[7];
int num;


/*
set<short> s;
int n;
	scanf("%d", &n);
	while (n--) {
		scanf("%s", order);
		if (order[0]=='a') {
			if (order[1] == 'd') {
				scanf("%d",&num);
				s.insert(num);
			}
			else if (order[1] == 'l') {
				for (short i = 1; i <= 20; i++) {
					s.insert(i);
				}
			}
		} 
		else if (order[0]=='r') {
			scanf("%d", &num);
			s.erase(num);
		}
		else if (order[0]=='c') {
			scanf("%d", &num);
			auto iter = s.find(num);
			if (iter != s.end()) cout << 1 << "\n";
			else cout << 0 << "\n";
		}
		else if (order[0]=='t') {
			scanf("%d", &num);
			auto iter = s.find(num);
			if (iter != s.end()) s.erase(num);
			else s.insert(num);
		}
		else if (order[0]=='e') {
			s.clear();
		}
	}
	return 0;
*/
int main() {
	int n;
	int s=0;
	scanf("%d", &n);
	while (n--) {
		scanf("%s", order);
		if (order[0]=='a') {
			if (order[1] == 'd') { //add
				scanf("%d",&num);
				s |= 1 << num;
			}
			else if (order[1] == 'l') { //all 1~20 ¸ðµÎ 1·Î
				s = (1 << 21) - 1;
			}
		} 
		else if (order[0]=='r') { //remove
			scanf("%d", &num);
			s &= ~(1 << num);
		}
		else if (order[0]=='c') { //check
			scanf("%d", &num);
			if (s & 1 << num) printf("1\n");
			else printf("0\n");
		}
		else if (order[0]=='t') { //toggle
			scanf("%d", &num);
			s ^= (1 << num);
		}
		else if (order[0]=='e') {
			s = 0;
		}
	}
	return 0;
}