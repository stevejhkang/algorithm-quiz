#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

int main() {
	int n; scanf("%d", &n);
	vector<long long> v;
	//입력받는 숫자가 -2^62 ~ 2^62까지 즉 8*8바이트인 long long으로 해줘야 된다.
	for (int i = 0; i < n; i++) {
		long long a;
		scanf("%lld", &a);
		//printf("%lld", a);
		v.push_back(a);
	}

	sort(v.begin(),v.end());
	
	if (v.size() == 1) {
		printf("%lld\n", v[0]);
		return 0;
	}
	int count = 1;
	map<long long, int> countArr; //map은 키-값의 데이터를 저장하는 중복없는 자료구조이다.
	countArr.insert(pair<long long, int>(v[0], count));
	for (int i = 1; i < n; i++) {
		if (v[i] == v[i - 1]) { //이전과 같으면 ++해준다.
			count++;
			if (i == n - 1) {
				countArr[v[i]] = count;
			}
		}
		if (v[i] != v[i - 1]) { //이전과 다르면 이전것을 최종카운트를 해준다.
			countArr[v[i-1]] = count;
			count = 1;
		}
	}
	long long max = 0; long long number=0;
	
	//auto max = max_element(countArr.begin(), countArr.end(),countArr.value_comp());
	for (auto it = countArr.begin(); it != countArr.end(); it++) {
		if (it->second > max) {
			max = it->second;
			number = it->first;
		}
		//printf("%lld\n", it->first);
	}
	printf("%lld\n", number);
	return 0;
}