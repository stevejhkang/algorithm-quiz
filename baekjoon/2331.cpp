#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <map>
#include <vector>
#include <algorithm>
using namespace std;
map<int,int> m;
vector<int> v;

int a, p;
int cnt = 1;
int result;
int sum = 0;
int my_pow(int base, int exp) { int res = 1; while (exp) { if (exp & 1) res *= base; exp >>= 1; base *= base; } return res; }


int Plus(int a,int p) { //자리 수 더하기
	int sum=0;
	while (a != 0) {
		sum += my_pow(a%10,p);
		a = a / 10;
	}
	return sum;
}

int main() {
	scanf("%d %d", &a, &p);
	v.push_back(a); m.insert(make_pair(a, cnt++));
	while(1) {
		
		sum = Plus(a,p);
		if (m.find(sum) == m.end()) { // sum키 못찾으면 추가
			m.insert(make_pair(sum, cnt++));
			v.push_back(sum);
			a = sum;
		}
		else break; //찾으면 아웃
	} 
	//for (int i =0; i < v.size(); i++) {
	//	printf("%d ", v[i]);
	//}
	result = m.find(sum)->second;
	printf("%d\n", result-1 );
	return 0;
}
